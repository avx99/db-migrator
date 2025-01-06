package ma.inwi.innov.migration_app.utils;

import java.util.Objects;

public class ConversionUtils {

    private ConversionUtils() {

    }

    public static Boolean convertToBoolean(String var) {
        return Objects.equals(var, "true") || Objects.equals(var, "True") || Objects.equals(var, "1");
    }
}
