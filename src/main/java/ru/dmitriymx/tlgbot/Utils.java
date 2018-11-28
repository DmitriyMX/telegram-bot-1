package ru.dmitriymx.tlgbot;

public class Utils {
    public static boolean StringIsNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }

    public static String getVariable(String name) {
        return getVariable(name, null);
    }

    public static String getVariable(String name, String defaultValue) {
        String result = System.getProperty(name);

        if (StringIsNullOrEmpty(result)) {
            result = System.getenv(name);

            if (StringIsNullOrEmpty(result)) {
                return defaultValue;
            }
        }

        return result;
    }
}
