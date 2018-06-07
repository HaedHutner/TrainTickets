package io.github.haedhutner.db;

import java.sql.ResultSet;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Query {

    private String query;
    private Object[] params;

    private Query(String query, Object... params) {
        this.query = query;
        this.params = params;
    }

    public static Query of(String query, Object... params) {
        return new Query(query, params);
    }

    public void exec() {
        DBConnection.exec(dbConnection -> dbConnection.noResultQuery(query, params));
    }

    public void query(Consumer<ResultSet> action) {
        try (DBConnection connection = new DBConnection()) {
            connection.resultQuery(query, params).ifPresent(action);
        }
    }

    public <R> Optional<R> queryResult(Function<ResultSet, R> action) {
        try (DBConnection connection = new DBConnection()) {
            return connection.resultQuery(query, params).map(action);
        }
    }

}
