package io.github.haedhutner.managers;

import io.github.haedhutner.db.AbstractManager;
import io.github.haedhutner.db.Query;
import io.github.haedhutner.entity.Train;
import io.github.haedhutner.gui.trains.TrainsTableModel;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        Train train = new Train();

        train.setId(result.getInt("train_id"));
        train.setDepartureTime(result.getTimestamp("train_departingAt").toLocalDateTime());

        LineManager.getInstance().select(result.getInt("train_route")).ifPresent(train::setRoute);

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
