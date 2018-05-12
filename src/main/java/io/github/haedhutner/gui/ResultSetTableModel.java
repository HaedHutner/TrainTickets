package io.github.haedhutner.gui;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultSetTableModel extends AbstractTableModel {

    private List<String> header = new ArrayList<>();
    private List<Map<Integer,Object>> data = new ArrayList<>();

    public ResultSetTableModel(ResultSet rs) {
        try {
            setHeader(rs);
            setData(rs);
        } catch ( SQLException e ) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    private void setHeader(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();

        for ( int i = 1; i <= meta.getColumnCount(); i++ ) {
            header.add(i-1, meta.getColumnName(i));
        }
    }

    private void setData(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int columns = meta.getColumnCount();

        while (rs.next()) {
            Map<Integer, Object> row = new HashMap<>(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(i-1, rs.getObject(i));
            }
            data.add(row);
        }
    }

    public int getColumnCount() {
        return header.size();
    }

    public int getRowCount() {
        return data.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if ( data.size() < rowIndex ) return null;
        return data.get(rowIndex).get(columnIndex);
    }

    public String getColumnName(int columnIndex) {
        return header.get(columnIndex);
    }
}
