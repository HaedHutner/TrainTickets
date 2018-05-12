package io.github.haedhutner.db;

import io.github.haedhutner.utils.FileUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractManager<T> {

    protected static final String CREATE_QUERY = "createTable";
    protected static final String INSERT_QUERY = "insert";
    protected static final String UPDATE_QUERY = "update";
    protected static final String SELECT_QUERY = "select";
    protected static final String SELECT_ALL_QUERY = "selectAll";
    protected static final String DELETE_QUERY = "delete";

    protected Map<String, String> queries = new HashMap<>();

    protected AbstractManager(String sqlFolder) {
        queries.put(CREATE_QUERY, FileUtils.resourceToString(sqlFolder + "/createTable.sql"));
        queries.put(INSERT_QUERY, FileUtils.resourceToString(sqlFolder + "/insert.sql"));
        queries.put(DELETE_QUERY, FileUtils.resourceToString(sqlFolder + "/delete.sql"));
        queries.put(SELECT_QUERY, FileUtils.resourceToString(sqlFolder + "/select.sql"));
        queries.put(SELECT_ALL_QUERY, FileUtils.resourceToString(sqlFolder + "/selectAll.sql"));
        queries.put(UPDATE_QUERY, FileUtils.resourceToString(sqlFolder + "/update.sql"));

        System.out.println(queries.get(CREATE_QUERY));
        DBConnection.exec(dbConnection -> dbConnection.noResultQuery(getQuery(CREATE_QUERY)));
    }

    protected String getQuery(String id) {
        return queries.get(id);
    }

}
