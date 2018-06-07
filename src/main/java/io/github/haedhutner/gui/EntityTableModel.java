package io.github.haedhutner.gui;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class EntityTableModel<T> extends AbstractTableModel {

    protected List<T> entities = new ArrayList<>();

    private Class<T> clazz;

    public EntityTableModel(Class<T> clazz, List<T> entities) {
        this.clazz = clazz;
        this.entities = entities;
    }

    public EntityTableModel(Class<T> clazz, ResultSet rs) {
        this.clazz = clazz;
        try {
            setData(rs);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    protected abstract void setData(ResultSet rs) throws SQLException;

    @Override
    public int getColumnCount() {
        return clazz.getDeclaredFields().length;
    }

    @Override
    public int getRowCount() {
        return entities.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Field field = clazz.getDeclaredFields()[columnIndex];
            boolean accessible = field.isAccessible();

            field.setAccessible(true);

            Object result = field.get(entities.get(rowIndex));

            field.setAccessible(accessible);

            return result;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getColumnName(int columnIndex) {
        Field field = clazz.getDeclaredFields()[columnIndex];
        return field.getName();
    }

    public T getAt(int row) {
        return entities.get(row);
    }
}
