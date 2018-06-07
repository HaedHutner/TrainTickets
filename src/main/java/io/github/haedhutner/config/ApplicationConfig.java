package io.github.haedhutner.config;

import io.github.haedhutner.db.DBConnection;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;

public final class ApplicationConfig extends Properties {

    private static final String DB_TYPE = "db-type";
    private static final String DB_HOST = "db-host";
    private static final String DB_USERNAME = "db-username";
    private static final String DB_PASSWORD = "db-password";

    private static ApplicationConfig instance = null;

    private Path configPath;

    private ApplicationConfig() {
        super.setProperty(DB_TYPE, "H2");
        super.setProperty(DB_HOST, "localhost/D:/database/trains");
        super.setProperty(DB_USERNAME, "sa");
        super.setProperty(DB_PASSWORD, " ");

        File configFile = new File(getPath().toString());

        if (!configFile.exists()) {
            if (!configFile.getParentFile().mkdirs()) System.out.println("Failed to create dirs.");
            try {
                if (!configFile.createNewFile()) System.out.println("Failed to create new file.");
                else save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else load();
    }

    public static ApplicationConfig getInstance() {
        if (instance == null) instance = new ApplicationConfig();
        return instance;
    }

    public String getDatabaseType() {
        return super.getProperty(DB_TYPE);
    }

    public String getDatabaseHost() {
        return super.getProperty(DB_HOST);
    }

    public String getDatabaseUsername() {
        return super.getProperty(DB_USERNAME);
    }

    public String getDatabasePassword() {
        return super.getProperty(DB_PASSWORD);
    }

    public String getConnectionString() {
        DBConnection.Type type = DBConnection.Type.valueOf(getDatabaseType());
        return String.format(type.getTemplate(), getDatabaseHost(), getDatabaseUsername(), getDatabasePassword());
    }

    private Path getPath() {
        if (configPath == null) configPath = FileSystems.getDefault().getPath("config", "config.conf");
        return configPath;
    }

    public boolean save() {
        try {
            super.store(new FileWriter(configPath.toFile(), false), "");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean load() {
        try {
            super.load(new FileReader(getPath().toFile()));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
