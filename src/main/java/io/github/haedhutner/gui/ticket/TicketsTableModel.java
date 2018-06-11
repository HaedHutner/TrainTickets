package io.github.haedhutner.gui.ticket;

import io.github.haedhutner.entity.Ticket;
import io.github.haedhutner.gui.EntityTableModel;
import io.github.haedhutner.managers.TicketManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TicketsTableModel extends EntityTableModel<Ticket> {

    public TicketsTableModel(Class clazz, List entities) {
        super(clazz, entities);
    }

    @Override
    protected void setData(ResultSet rs) throws SQLException {
        while (rs.next()) TicketManager.getInstance().modelFromResultSet(rs).ifPresent(entities::add);
    }
}
