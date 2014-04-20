package android.ext.core;

import static android.ext.core.BuildConfig.DEBUG;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class Objects {
    private Objects() {
        // disallow public access
    }

    public static <T> T notNull(T object) {
        if (DEBUG) {
            if (object == null) {
                throw new NullPointerException();
            }
        }

        return object;
    }
}
