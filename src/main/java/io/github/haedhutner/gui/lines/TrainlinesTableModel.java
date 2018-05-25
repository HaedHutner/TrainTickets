package io.github.haedhutner.gui.lines;

import io.github.haedhutner.managers.LineManager;
import io.github.haedhutner.entity.Line;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrainlinesTableModel extends AbstractTableModel {

    private List<String> header = new ArrayList<>();
    private List<Line> lines = new ArrayList<>();

    public TrainlinesTableModel(ResultSet rs) {
        try {
            setHeader();
            setData(rs);
        } catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            System.out.println("SQLState: " + e.getSQLState());
            System.out.println("VendorError: " + e.getErrorCode());
        }
    }

    private void setHeader() {
        header.add(0, "Id");
        header.add(1, "From");
        header.add(2, "To");
        header.add(3, "Distance");
    }

    private void setData(ResultSet rs) throws SQLException {
        while (rs.next()) {
            LineManager.getInstance().modelFromResultSet(rs).ifPresent(lines::add);
        }
    }

    public int getColumnCount() {
        return header.size();
    }

    public int getRowCount() {
        return lines.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0: return lines.get(rowIndex).getId();
            case 1: return lines.get(rowIndex).getStart();
            case 2: return lines.get(rowIndex).getStop();
            case 3: return lines.get(rowIndex).getDistance();
            default: return "NaN";
        }
    }

    public String getColumnName(int columnIndex) {
        return header.get(columnIndex);
    }

    public Line getAt(int row) {
        return lines.get(row);
    }
}
