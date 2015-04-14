package xdroid.core;

import android.content.SharedPreferences;

import java.util.Set;

public class SharedPreferencesUtils {
    public static int getInt(SharedPreferences prefs, String key, int defaultValue) {
        try {
            return prefs.getInt(key, defaultValue);
        } catch (Throwable ex) {
            return defaultValue;
        }
    }

    public static long getLong(SharedPreferences prefs, String key, long defaultValue) {
        try {
            return prefs.getLong(key, defaultValue);
        } catch (Throwable ex) {
            return defaultValue;
        }
    }

    public static float getFloat(SharedPreferences prefs, String key, float defaultValue) {
        try {
            return prefs.getFloat(key, defaultValue);
        } catch (Throwable ex) {
            return defaultValue;
        }
    }

    public static boolean getBoolean(SharedPreferences prefs, String key, boolean defaultValue) {
        try {
            return prefs.getBoolean(key, defaultValue);
        } catch (Throwable ex) {
            return defaultValue;
        }
    }

    public static String getString(SharedPreferences prefs, String key, String defaultValue) {
        try {
            return prefs.getString(key, defaultValue);
        } catch (Throwable ex) {
            return defaultValue;
        }
    }

    public static Set<String> getStringSet(SharedPreferences prefs, String key, Set<String> defaultValue) {
        try {
            return prefs.getStringSet(key, defaultValue);
        } catch (Throwable ex) {
            return defaultValue;
        }
    }
}
