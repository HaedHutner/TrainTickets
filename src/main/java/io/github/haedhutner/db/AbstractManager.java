package io.github.haedhutner.db;

import io.github.haedhutner.utils.FileUtils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class AbstractManager<T> {

    protected static final String CREATE_QUERY = "createTable";
    protected static final String INSERT_QUERY = "insert";
    protected static final String UPDATE_QUERY = "update";
    protected static final String SELECT_QUERY = "select";
    protected static final String SELECT_ALL_QUERY = "selectAll";
    protected static final String DELETE_QUERY = "delete";

    protected Map<String, String> queries = new HashMap<>();

    protected AbstractManager(String sqlFolder) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(sqlFolder);

        if ( url == null ) {
            throw new RuntimeException("Could not find folder " + sqlFolder);
        }

        try {
            File folder = new File(url.toURI());
            if ( folder.isDirectory() ) {
                for ( File file : Objects.requireNonNull(folder.listFiles())) {
                    queries.put(file.getName().replace(".sql", ""), FileUtils.fileToString(file));
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        DBConnection.exec(dbConnection -> dbConnection.noResultQuery(getQuery(CREATE_QUERY)));
    }

    protected String getQuery(String id) {
        return queries.get(id);
    }

}
