package io.github.haedhutner.db;

import io.github.haedhutner.entity.Entity;
import io.github.haedhutner.utils.FileUtils;
import io.github.haedhutner.utils.SwingUtils;

import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * An abstract implementation of DBManager where the SQL queries are to be stored under a specific directory within
 * the jar resources and retrieved during runtime based on their file names.
 */
public abstract class AbstractManager<T extends Entity<ID>, ID> implements DBManager<T,ID> {

    protected static final String CREATE_QUERY = "createTable";
    protected static final String INSERT_QUERY = "insert";
    protected static final String UPDATE_QUERY = "update";
    protected static final String SELECT_QUERY = "select";
    protected static final String DELETE_QUERY = "delete";
    protected static final String UPSERT_QUERY = "upsert";
    protected static final String SELECT_ALL_QUERY = "selectAll";

    protected Map<String, String> queries = new HashMap<>();

    protected AbstractManager(String sqlFolder) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(sqlFolder);

        if (url == null) {
            throw new RuntimeException("Could not find folder " + sqlFolder);
        }

        try {
            File folder = new File(url.toURI());
            if (folder.isDirectory()) {
                for (File file : Objects.requireNonNull(folder.listFiles())) {
                    queries.put(file.getName().replace(".sql", ""), FileUtils.fileToString(file));
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        DBConnection.exec(dbConnection -> dbConnection.noResultQuery(getRawQuery(CREATE_QUERY)));
    }

    public Map<String,String> getRawQueries() {
        return queries;
    }

    public String getRawQuery(String id) {
        return queries.get(id);
    }

    public void mapTo(JTable table) {
        Query.of(getRawQuery(SELECT_ALL_QUERY)).query(resultSet -> SwingUtils.mapResultSetToTable(resultSet, table));
    }

    public List<T> getAll() {
        List<T> list = new ArrayList<>();

        Query.of(getRawQuery(SELECT_ALL_QUERY)).query(resultSet -> {
            try {
                while (resultSet.next()) {
                    modelFromResultSet(resultSet).ifPresent(list::add);
                }
            } catch (SQLException e) {
                DBConnection.printError(e);
            }
        });

        return list;
    }

    protected abstract Optional<T> modelFromResultSet(ResultSet result) throws SQLException;

    protected void query(String queryName, Object... parameters) {
        Query.of(getRawQuery(queryName), parameters).exec();
    }

    protected Query buildQuery(String queryName, Object... parameters) {
        return Query.of(getRawQuery(queryName), parameters);
    }
}
