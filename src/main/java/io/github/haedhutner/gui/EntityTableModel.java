package io.github.haedhutner.gui;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class EntityTableModel<T> extends AbstractTableModel {
    private Class<T> clazz;

    private List<String> header = new ArrayList<>();
    protected List<T> entities = new ArrayList<>();

    public EntityTableModel(Class<T> clazz, ResultSet rs, List<String> header) {
        this.clazz = clazz;
        this.header = header;
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
        return header.size();
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
        return header.get(columnIndex);
    }

    public T getAt(int row) {
        return entities.get(row);
    }
}
