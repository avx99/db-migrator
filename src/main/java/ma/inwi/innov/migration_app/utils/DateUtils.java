package ma.inwi.innov.migration_app.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private DateUtils() {

    }

    public static LocalDateTime convertToLocalDateTime(Timestamp date, Boolean returnNowDate) {
        return date != null ? convertToLocalDateTime(date.toString(), returnNowDate) : null;
    }

    public static LocalDateTime convertToLocalDateTime(String date, Boolean returnNowDate) {
        try {
            var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            return LocalDateTime.parse(date, formatter);
        } catch (Exception e) {
            return Boolean.TRUE.equals(returnNowDate) ? LocalDateTime.now() : null;
        }
    }

    public static LocalDate convertToLocalDate(Timestamp date, Boolean returnNowDate) {
        return date != null ? convertToLocalDate(date.toString(), returnNowDate) : null;
    }

    public static LocalDate convertToLocalDate(Date date, Boolean returnNowDate) {
        return date != null ? convertToLocalDate(date.toString(), returnNowDate) : null;
    }


    public static LocalDate convertToLocalDate(String date, Boolean returnNowDate) {
        try {
            return LocalDate.parse(date);
        } catch (Exception e) {
            return Boolean.TRUE.equals(returnNowDate) ? LocalDate.now() : null;
        }
    }

    public static LocalTime convertToLocalTime(String time, String format, Boolean returnNowDate) {
        var formatter = DateTimeFormatter.ofPattern(format != null ? format : "HH:mm");
        try {
            return LocalTime.parse(time, formatter);
        } catch (Exception e) {
            return Boolean.TRUE.equals(returnNowDate) ? LocalTime.now() : null;
        }
    }
}
