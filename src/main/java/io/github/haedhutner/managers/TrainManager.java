package io.github.haedhutner.managers;

import io.github.haedhutner.db.AbstractManager;
import io.github.haedhutner.entity.Train;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;

public class TrainManager extends AbstractManager<Train,Integer> {

    private static TrainManager instance = new TrainManager();

    protected TrainManager() {
        super("sql/trainManager");
    }

    public static TrainManager getInstance() {
        return instance;
    }

    @Override
    protected Optional<Train> modelFromResultSet(ResultSet result) throws SQLException {
        Integer id = result.getInt("train_id");
        LocalDateTime departure = LocalDateTime.from(result.getDate("train_departingAt").toLocalDate());

        Train train = new Train(id, departure);
        train.setRoute( LineManager.getInstance().getTrainRoute(train) );

        return Optional.of(train);
    }

    @Override
    public void insert(Train object) {
        query(INSERT_QUERY, object.getDepartureTime());
    }

    @Override
    public void update(Train object) {
        query(UPDATE_QUERY, object.getId());
    }

    @Override
    public void delete(Train object) {
        query(DELETE_QUERY, object.getId());
    }

    @Override
    public Optional<Train> select(Integer integer) {
        return Optional.empty();
    }
}
