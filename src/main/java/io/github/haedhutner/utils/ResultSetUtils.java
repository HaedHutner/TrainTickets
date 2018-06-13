package io.github.haedhutner.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class ResultSetUtils {

    public static <T> T get(ResultSet set, String column, Class<T> asClazz) {
        try {
            return asClazz.cast(set.getObject(column));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
