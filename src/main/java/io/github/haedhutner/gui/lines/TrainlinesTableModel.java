package io.github.haedhutner.gui.lines;

import io.github.haedhutner.entity.Line;
import io.github.haedhutner.gui.EntityTableModel;
import io.github.haedhutner.managers.LineManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TrainlinesTableModel extends EntityTableModel<Line> {

    public TrainlinesTableModel(ResultSet rs) {
        super(Line.class, rs);
    }

    public TrainlinesTableModel(List<Line> lines) {
        super(Line.class, lines);
    }

    @Override
    protected void setData(ResultSet rs) throws SQLException {
        while (rs.next()) LineManager.getInstance().modelFromResultSet(rs).ifPresent(entities::add);
    }
}
