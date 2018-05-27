package io.github.haedhutner.db;

import io.github.haedhutner.utils.FileUtils;

import java.sql.*;
import java.util.Optional;
import java.util.function.Consumer;

public final class DBConnection implements AutoCloseable {

    public enum Type {
        MYSQL,
        H2
    }

    private static Type defaultType = Type.H2;

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    public DBConnection() {
        this(getDefaultType());
    }

    public DBConnection(Type type) {
        try {
            String connectionString = String.format("jdbc:h2:tcp://localhost/D:/databases/trains;USER=%s;PASSWORD=%s", "sa", "");
            if (type == Type.MYSQL) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connectionString = String.format("jdbc:mysql://localhost/trainsDatabase?user=%s&password=%s&useSSL=false", "root", "");
            } else if (type == Type.H2) {
                Class.forName("org.h2.Driver");
                connectionString = String.format("jdbc:h2:tcp://localhost/D:/databases/trains;USER=%s;PASSWORD=%s", "sa", "");
            }
            connection = DriverManager.getConnection(connectionString);
        } catch (ClassNotFoundException e) {
            // TODO
            e.printStackTrace();
        } catch (SQLException e) {
            printError(e);
        }
    }

    public static Type getDefaultType() {
        return defaultType;
    }

    public static void setDefaultType(Type defaultType) {
        DBConnection.defaultType = defaultType;
    }

    public static void exec(Consumer<DBConnection> consumer) {
        try (DBConnection connection = new DBConnection(defaultType)) {
            consumer.accept(connection);
        }
    }

    public Optional<PreparedStatement> constructStatement(String sqlStatement, Object... parameters) {
        try {
            System.out.println(sqlStatement);
            statement = connection.prepareStatement(sqlStatement);
            for (int i = 0; i < parameters.length; i++) {
                statement.setObject(i + 1, parameters[i]);
            }
        } catch (SQLException e) {
            printError(e);
            return Optional.empty();
        }

        return Optional.of(statement);
    }

    public void noResultQuery(String sqlStatement, Object... parameters) {
        try {
            Optional<PreparedStatement> statement = constructStatement(sqlStatement, parameters);
            if (statement.isPresent()) statement.get().execute();
        } catch (SQLException e) {
            printError(e);
        }
    }

    public Optional<ResultSet> resultQuery(String sqlStatement, Object... parameters) {
        try {
            Optional<PreparedStatement> statement = constructStatement(sqlStatement, parameters);
            if (statement.isPresent()) result = statement.get().executeQuery();
        } catch (SQLException e) {
            printError(e);
        }

        return Optional.ofNullable(result);
    }

    public void noResultQueryFromResource(String resource, Object... parameters) {
        String sqlQuery = FileUtils.resourceToString(resource);
        noResultQuery(sqlQuery, parameters);
    }

    @Override
    public void close() {
        try {
            if (result != null) result.close();
            if (statement != null) statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void printError(SQLException e) {
        System.out.println("SQLException: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("VendorError: " + e.getErrorCode());
        e.printStackTrace();
    }
}
