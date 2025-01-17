package ma.inwi.innov.migration_app.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for date and time conversion operations.
 * Provides methods to convert various date and time representations
 * to {@link LocalDate}, {@link LocalDateTime}, and {@link LocalTime}.
 */
public class DateUtils {

    /**
     * Private constructor to prevent instantiation.
     */
    private DateUtils() {
        // Utility class, prevent instantiation
    }

    /**
     * Converts a {@link Timestamp} to a {@link LocalDateTime}.
     * If the {@code returnNowDate} is true and conversion fails, returns the current date and time.
     *
     * @param date         the timestamp to convert
     * @param returnNowDate flag to indicate if the current date and time should be returned on failure
     * @return the corresponding {@link LocalDateTime}, or {@code null} if input is {@code null} and {@code returnNowDate} is false
     */
    public static LocalDateTime convertToLocalDateTime(Timestamp date, Boolean returnNowDate) {
        return date != null ? convertToLocalDateTime(date.toString(), returnNowDate) : null;
    }

    /**
     * Converts a {@link String} to a {@link LocalDateTime}.
     * If the {@code returnNowDate} is true and conversion fails, returns the current date and time.
     *
     * @param date         the string representing the date and time
     * @param returnNowDate flag to indicate if the current date and time should be returned on failure
     * @return the corresponding {@link LocalDateTime}, or the current date and time on failure if {@code returnNowDate} is true
     */
    public static LocalDateTime convertToLocalDateTime(String date, Boolean returnNowDate) {
        try {
            var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            return LocalDateTime.parse(date, formatter);
        } catch (Exception e) {
            return Boolean.TRUE.equals(returnNowDate) ? LocalDateTime.now() : null;
        }
    }

    /**
     * Converts a {@link Timestamp} to a {@link LocalDate}.
     * If the {@code returnNowDate} is true and conversion fails, returns the current date.
     *
     * @param date         the timestamp to convert
     * @param returnNowDate flag to indicate if the current date should be returned on failure
     * @return the corresponding {@link LocalDate}, or {@code null} if input is {@code null} and {@code returnNowDate} is false
     */
    public static LocalDate convertToLocalDate(Timestamp date, Boolean returnNowDate) {
        return date != null ? convertToLocalDate(date.toString(), returnNowDate) : null;
    }

    /**
     * Converts a {@link Date} to a {@link LocalDate}.
     * If the {@code returnNowDate} is true and conversion fails, returns the current date.
     *
     * @param date         the date to convert
     * @param returnNowDate flag to indicate if the current date should be returned on failure
     * @return the corresponding {@link LocalDate}, or {@code null} if input is {@code null} and {@code returnNowDate} is false
     */
    public static LocalDate convertToLocalDate(Date date, Boolean returnNowDate) {
        return date != null ? convertToLocalDate(date.toString(), returnNowDate) : null;
    }

    /**
     * Converts a {@link String} to a {@link LocalDate}.
     * If the {@code returnNowDate} is true and conversion fails, returns the current date.
     *
     * @param date         the string representing the date
     * @param returnNowDate flag to indicate if the current date should be returned on failure
     * @return the corresponding {@link LocalDate}, or the current date on failure if {@code returnNowDate} is true
     */
    public static LocalDate convertToLocalDate(String date, Boolean returnNowDate) {
        try {
            var datePart = date.split(" ")[0];
            var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            return LocalDate.parse(datePart, formatter);
        } catch (Exception e) {
            return Boolean.TRUE.equals(returnNowDate) ? LocalDate.now() : null;
        }
    }

    /**
     * Converts a {@link String} to a {@link LocalTime} using a specified format.
     * If the {@code returnNowDate} is true and conversion fails, returns the current time.
     *
     * @param time         the string representing the time
     * @param format       the format of the time string, defaults to "HH:mm" if null
     * @param returnNowDate flag to indicate if the current time should be returned on failure
     * @return the corresponding {@link LocalTime}, or the current time on failure if {@code returnNowDate} is true
     */
    public static LocalTime convertToLocalTime(String time, String format, Boolean returnNowDate) {
        var formatter = DateTimeFormatter.ofPattern(format != null ? format : "HH:mm");
        try {
            return LocalTime.parse(time, formatter);
        } catch (Exception e) {
            return Boolean.TRUE.equals(returnNowDate) ? LocalTime.now() : null;
        }
    }
}

