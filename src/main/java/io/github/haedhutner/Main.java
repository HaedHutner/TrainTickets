package io.github.haedhutner;

import io.github.haedhutner.config.ApplicationConfig;
import io.github.haedhutner.gui.ApplicationGUI;

public class Main {

    public static void main(String[] args) {
        ApplicationConfig.getInstance();
        ApplicationGUI.getInstance();
    }

}
