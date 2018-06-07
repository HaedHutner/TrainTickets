package io.github.haedhutner.managers;

import io.github.haedhutner.db.AbstractManager;
import io.github.haedhutner.db.DBConnection;
import io.github.haedhutner.db.Query;
import io.github.haedhutner.entity.Line;
import io.github.haedhutner.gui.lines.TrainlinesTableModel;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class LineManager extends AbstractManager<Line, Integer> {

    private static final LineManager instance = new LineManager();

    protected LineManager() {
        super("sql/lineManager");
    }

    public static LineManager getInstance() {
        return instance;
    }

    @Override
    public void mapTo(JTable table) {
        table.setModel(new TrainlinesTableModel(getFilteredAll()));
    }

    @Override
    protected Query getFilterQuery(Line filter) {
        return Query.of(getRawQuery(FILTER_QUERY), filter.getStart(), filter.getDistance(), filter.getStop());
    }

    @Override
    public Optional<Line> modelFromResultSet(ResultSet result) throws SQLException {
        int id = result.getInt("line_id");
        String from = result.getString("line_start");
        double distance = result.getDouble("line_distance");
        String to = result.getString("line_stop");

        return Optional.of(new Line(id, from, distance, to));
    }

    @Override
    public Optional<Line> select(Integer id) {
        try (final DBConnection connection = new DBConnection()) {
            Optional<ResultSet> resultSet = connection.resultQuery(getRawQuery(SELECT_QUERY), id);

            if (resultSet.isPresent()) {
                ResultSet rs = resultSet.get();
                rs.next();
                return modelFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void insert(Line object) {
        query(INSERT_QUERY, object.getStart(), object.getDistance(), object.getStop());
    }

    @Override
    public void update(Line object) {
        query(UPDATE_QUERY, object.getStart(), object.getDistance(), object.getStop(), object.getId());
    }

    @Override
    public void delete(Line line) {
        query(DELETE_QUERY, line.getId());
    }

}
