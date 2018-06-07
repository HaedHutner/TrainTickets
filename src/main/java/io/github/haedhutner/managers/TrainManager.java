package io.github.haedhutner.managers;

import io.github.haedhutner.db.AbstractManager;
import io.github.haedhutner.db.Query;
import io.github.haedhutner.entity.Train;
import io.github.haedhutner.gui.trains.TrainsTableModel;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class TrainManager extends AbstractManager<Train, Integer> {

    private static TrainManager instance = new TrainManager();

    protected TrainManager() {
        super("sql/trainManager");
    }

    public static TrainManager getInstance() {
        return instance;
    }

    @Override
    public void mapTo(JTable table) {
        table.setModel(new TrainsTableModel(getFilteredAll()));
    }

    @Override
    public Optional<Train> modelFromResultSet(ResultSet result) throws SQLException {
        Integer id = result.getInt("train_id");

        LocalDateTime train_departingAt = result.getTimestamp("train_departingAt").toLocalDateTime();

        Integer line_id = result.getInt("train_route");

        Train train = new Train(id, train_departingAt);
        LineManager.getInstance().select(line_id).ifPresent(train::setRoute);

        return Optional.of(train);
    }

    @Override
    public void insert(Train object) {
        query(INSERT_QUERY, object.getDepartureTime(), object.getRoute().getId());
    }

    @Override
    public void update(Train object) {
        query(UPDATE_QUERY, object.getDepartureTime(), object.getRoute().getId(), object.getId());
    }

    @Override
    public void delete(Train object) {
        query(DELETE_QUERY, object.getId());
    }

    @Override
    public Query getFilterQuery(Train entity) {
        return Query.of(getRawQuery(FILTER_QUERY), entity.getDepartureTime(), entity.getRoute().getId());
    }

    @Override
    public Optional<Train> select(Integer integer) {
        return Optional.empty();
    }
}
