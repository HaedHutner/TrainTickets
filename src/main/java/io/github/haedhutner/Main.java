package io.github.haedhutner;

import io.github.haedhutner.db.DBConnection;
import io.github.haedhutner.gui.ApplicationGUI;

public class Main {

    public static void main(String[] args) {
        DBConnection.setDefaultType(DBConnection.Type.H2);
        ApplicationGUI.getInstance();
    }

}
