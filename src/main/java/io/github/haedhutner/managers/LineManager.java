package io.github.haedhutner.managers;

import io.github.haedhutner.db.AbstractManager;
import io.github.haedhutner.db.DBConnection;
import io.github.haedhutner.models.Line;
import io.github.haedhutner.models.Train;
import io.github.haedhutner.utils.SwingUtils;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LineManager extends AbstractManager<Line> {

    private static final LineManager instance = new LineManager();

    protected LineManager() {
        super("sql/lineManager");
    }

    public void insert(Line line) {
        DBConnection.exec(connection -> connection.noResultQuery(getQuery(INSERT_QUERY), line.getStart(), line.getDistance(), line.getStop()));
    }

    private Optional<Line> lineFromResultSet(ResultSet result) throws SQLException {
        String from = result.getString("line_from");
        double distance = result.getDouble("line_distance");
        String to = result.getString("line_to");

        return Optional.of(new Line(from, distance, to));
    }

    public Optional<Line> select(int id) {
        try (DBConnection connection = new DBConnection()) {
            Optional<ResultSet> resultSet = connection.resultQuery(getQuery(SELECT_QUERY), id);
            if (resultSet.isPresent()) {
                ResultSet result = resultSet.get();
                result.next();
                return lineFromResultSet(result);
            }
        } catch (SQLException e) {
            DBConnection.printError(e);
        }

        return Optional.empty();
    }

    public List<Line> getTrainRoute(Train train) {
        List<Line> route = new ArrayList<>();

        try ( DBConnection connection = new DBConnection() ) {
            Optional<ResultSet> resultSet = connection.resultQuery(getQuery("selectRoute"), train.getId());
            if (resultSet.isPresent()) {
                ResultSet result = resultSet.get();
                while ( result.next() ) lineFromResultSet(result).ifPresent(route::add);
            }
        } catch (SQLException e) {
            DBConnection.printError(e);
        }

        return route;
    }

    public void update(Line line) {
        DBConnection.exec(connection -> connection.noResultQuery(getQuery(UPDATE_QUERY), line.getStart(), line.getDistance(), line.getStop(), line.getId()));
    }

    public void delete(Line line) {
        DBConnection.exec(connection -> connection.noResultQuery(getQuery(DELETE_QUERY), line.getId()));
    }

    public void mapTo ( JTable table ) {
        DBConnection.exec(connection -> connection.resultQuery(getQuery(SELECT_ALL_QUERY)).ifPresent(resultSet -> SwingUtils.mapResultSetToTable(resultSet, table)));
    }

    public static LineManager getInstance() {
        return instance;
    }

}
