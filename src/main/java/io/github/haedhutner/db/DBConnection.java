package io.github.haedhutner.db;

import io.github.haedhutner.utils.FileUtils;

import java.sql.*;
import java.util.Optional;
import java.util.function.Consumer;

public class DBConnection implements AutoCloseable {

    public enum Type {
        MYSQL,
        H2
    }

    private static Type defaultType = Type.H2;

    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    public DBConnection() {
        try {
            String connectionString = "jdbc:h2:tcp:D:/databases/trains;USER=%s;PASSWORD=%s";
            if ( defaultType == Type.MYSQL ) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connectionString = String.format("jdbc:mysql://localhost/trainsDatabase?user=%s&password=%s&useSSL=false", "root", "");
            } else if ( defaultType == Type.H2 ) {
                Class.forName("org.h2.Driver");
                connectionString = String.format("jdbc:h2:tcp://localhost/D:/databases/trains;USER=%s;PASSWORD=%s", "sa", "");
            }
            connection = DriverManager.getConnection(connectionString);
        } catch (ClassNotFoundException e) {
            // TODO
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public static Type getDefaultType() {
        return defaultType;
    }

    public static void setDefaultType(Type defaultType) {
        DBConnection.defaultType = defaultType;
    }

    public static void exec(Consumer<DBConnection> consumer) {
        try (DBConnection connection = new DBConnection()) {
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
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
            return Optional.empty();
        }

        return Optional.of(statement);
    }

    public void noResultQuery(String sqlStatement, Object... parameters) {
        try {
            Optional<PreparedStatement> statement = constructStatement(sqlStatement, parameters);
            if (statement.isPresent()) statement.get().execute();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    public Optional<ResultSet> resultQuery(String sqlStatement, Object... parameters) {
        try {
            Optional<PreparedStatement> statement = constructStatement(sqlStatement, parameters);
            if (statement.isPresent()) result = statement.get().executeQuery();
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
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
            if (statement != null) statement.close();
            if (result != null) result.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
