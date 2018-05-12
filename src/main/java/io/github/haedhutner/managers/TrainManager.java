package io.github.haedhutner.managers;

import io.github.haedhutner.db.AbstractManager;
import io.github.haedhutner.models.Train;

public class TrainManager extends AbstractManager<Train> {

    private static TrainManager instance = new TrainManager();

    protected TrainManager() {
        super("sql/trainManager");
    }

    public static TrainManager getInstance() {
        return instance;
    }
}
