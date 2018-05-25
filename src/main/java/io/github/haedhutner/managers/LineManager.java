package io.github.haedhutner.managers;

import io.github.haedhutner.db.AbstractManager;
import io.github.haedhutner.db.DBConnection;
import io.github.haedhutner.entity.Line;
import io.github.haedhutner.entity.Train;
import io.github.haedhutner.gui.lines.TrainlinesTableModel;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LineManager extends AbstractManager<Line, Integer> {

    private static final LineManager instance = new LineManager();

    private static final String SELECT_ROUTE_QUERY = "selectRoute";
    private static final String INSERT_ROUTE_QUERY = "insertTrainRoute";
    private static final String DELETE_ROUTE_QUERY = "deleteTrainRoute";
    private static final String DELETE_LINE_FROM_TRAIN = "deleteLineFromRoute";

    protected LineManager() {
        super("sql/lineManager");
        query("createTrainsLinesJunction");
    }

    @Override
    public void mapTo(JTable table) {
        DBConnection.exec(connection -> connection.resultQuery(getRawQuery(SELECT_ALL_QUERY)).ifPresent(resultSet -> table.setModel(new TrainlinesTableModel(resultSet))));
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
        return buildQuery(SELECT_QUERY, id).queryFunction(resultSet -> {
            try {
                resultSet.next();
                Optional<Line> line = modelFromResultSet(resultSet);
                if ( line.isPresent() ) return line.get();
            } catch (SQLException e) {
                DBConnection.printError(e);
            }

            return null;
        });
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
        DBConnection.exec(connection -> connection.noResultQuery(getRawQuery(DELETE_QUERY), line.getId()));
    }

    public List<Line> getTrainRoute(Train train) {
        List<Line> route = new ArrayList<>();

        buildQuery(SELECT_ROUTE_QUERY, train.getId()).query(resultSet -> {
            try {
                while (resultSet.next()) modelFromResultSet(resultSet).ifPresent(route::add);
            } catch (SQLException e) {
                DBConnection.printError(e);
            }
        });

        return route;
    }

    public void insertTrainRoute(Train train) {
        train.getRoute().forEach(line -> query(INSERT_ROUTE_QUERY, train.getId(), line.getId()));
    }

    public void deleteTrainRoute(Train train) {
        query(DELETE_ROUTE_QUERY, train.getId());
    }

    public void deleteLineFromTrainRoute(Train train, Line line) {
        query(DELETE_LINE_FROM_TRAIN, train.getId(), line.getId());
    }

    public static LineManager getInstance() {
        return instance;
    }

}
