package ma.inwi.innov.migration_app.utils;

import java.util.Objects;

/**
 * Utility class for conversion-related operations.
 * Contains methods to convert data from one type to another.
 */
public class ConversionUtils {

    private ConversionUtils() {

    }

    /**
     * Converts a {@link String} to a {@link Boolean}.
     * Returns {@code true} if the input string is "true", "True", or "1".
     * Otherwise, returns {@code false}.
     *
     * @param var the string to convert to a boolean
     * @return {@code true} if the input string represents a boolean true value, {@code false} otherwise
     */
    public static Boolean convertToBoolean(String var) {
        return Objects.equals(var, "true") || Objects.equals(var, "True") || Objects.equals(var, "1");
    }
}
