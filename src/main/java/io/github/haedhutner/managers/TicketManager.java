package io.github.haedhutner.managers;

import io.github.haedhutner.db.AbstractManager;
import io.github.haedhutner.db.Query;
import io.github.haedhutner.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TicketManager extends AbstractManager<Ticket,Integer> {

    private static final TicketManager instance = new TicketManager();

    protected TicketManager() {
        super("sql/ticketManager");
    }

    public static TicketManager getInstance() {
        return instance;
    }

    @Override
    protected Query getFilterQuery(Ticket filter) {
        return null;
    }

    @Override
    protected Optional<Ticket> modelFromResultSet(ResultSet result) throws SQLException {
        Ticket ticket = new Ticket();

        ticket.setPrice(result.getDouble("ticket_price"));

        TrainManager.getInstance().select(result.getInt("ticket_train")).ifPresent(ticket::setTrain);

        return Optional.of(ticket);
    }

    @Override
    public Optional<Ticket> select(Integer integer) {
        return Optional.empty();
    }

    @Override
    public void insert(Ticket object) {
        query(INSERT_QUERY, object.getPrice(), object.getTrain().getId() );
    }

    @Override
    public void update(Ticket object) {
        query(UPDATE_QUERY, object.getPrice(), object.getTrain().getId(), object.getId());
    }

    @Override
    public void delete(Ticket object) {
        query(DELETE_QUERY, object.getId());
    }
}
