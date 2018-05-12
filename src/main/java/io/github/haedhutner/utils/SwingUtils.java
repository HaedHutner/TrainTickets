package io.github.haedhutner.utils;

import io.github.haedhutner.gui.ResultSetTableModel;

import javax.swing.*;
import java.sql.ResultSet;

public final class SwingUtils {

    public static void mapResultSetToTable(ResultSet set, JTable table) {
        table.setModel(new ResultSetTableModel(set));
    }

}
