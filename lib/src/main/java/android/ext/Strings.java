package android.ext;

import static android.ext.BuildConfig.DEBUG;

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

    public static CharSequence notEmpty(CharSequence text) {
        if (DEBUG) {
            if (isEmpty(text)) {
                throw new IllegalArgumentException();
            }
        }

        return text;
    }

    public static boolean isEmpty(String text) {
        return text == null || text.trim().length() == 0;
    }

    public static String notEmpty(String text) {
        if (DEBUG) {
            if (isEmpty(text)) {
                throw new IllegalArgumentException();
            }
        }

        return text.trim();
    }
}
