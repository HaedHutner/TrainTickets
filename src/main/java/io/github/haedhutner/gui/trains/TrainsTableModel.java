package io.github.haedhutner.gui.trains;

import io.github.haedhutner.entity.Train;
import io.github.haedhutner.gui.EntityTableModel;
import io.github.haedhutner.managers.TrainManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class TrainsTableModel extends EntityTableModel<Train> {
    public TrainsTableModel(ResultSet rs) {
        super(Train.class, rs, Arrays.asList(
                "Id",
                "Departs",
                "Route"
        ));
    }

    @Override
    protected void setData(ResultSet rs) throws SQLException {
        while(rs.next()) TrainManager.getInstance().modelFromResultSet(rs).ifPresent(entities::add);
    }
}
