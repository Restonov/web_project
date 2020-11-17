package by.restonov.tyrent.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataTimeFormatter {

    /**
     * Convert LocalDateTime to
     * more clear format
     *
     * @return formatted Date and time
     */
    public static String showFormattedDateTime(LocalDateTime creationDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return creationDateTime.format(formatter);
    }
}
