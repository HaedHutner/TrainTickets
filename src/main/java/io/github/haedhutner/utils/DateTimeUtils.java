package io.github.haedhutner.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DateTimeUtils {

    public static LocalDateTime parseOrDefault(String parse, LocalDateTime def) {
        LocalDateTime parsed;
        try {
            parsed = LocalDateTime.parse(parse);
        } catch ( DateTimeParseException e ) {
            parsed = def;
        }
        return parsed;
    }

}
