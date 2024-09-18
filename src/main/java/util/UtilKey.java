package util;

import org.openqa.selenium.Keys;

public class UtilKey {

    private static final String OS = System.getProperty("os.name").toLowerCase();

    public static Keys control() {
        return isMac() ? Keys.COMMAND : Keys.CONTROL;
    }

    private static boolean isMac() {
        return OS.contains("mac");
    }

    private UtilKey() {
        throw new IllegalStateException("Utility class");
    }
}