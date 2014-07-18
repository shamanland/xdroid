package xdroid.core;

import static xdroid.core.BuildConfig.SNAPSHOT;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class Strings {
    private Strings() {
        // disallow public access
    }

    public static boolean isEmpty(CharSequence text) {
        return text == null || text.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence text) {
        return !isEmpty(text);
    }

    public static CharSequence notEmpty(CharSequence text) {
        if (SNAPSHOT) {
            if (isEmpty(text)) {
                throw new IllegalArgumentException();
            }
        }

        return text;
    }

    public static boolean isEmpty(String text) {
        return text == null || text.trim().length() == 0;
    }

    public static boolean isNotEmpty(String text) {
        return !isEmpty(text);
    }

    public static String notEmpty(String text) {
        if (SNAPSHOT) {
            if (isEmpty(text)) {
                throw new IllegalArgumentException();
            }
        }

        return text.trim();
    }
}