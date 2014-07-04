package xdroid.core;

import static xdroid.core.BuildConfig.SNAPSHOT;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class Objects {
    private Objects() {
        // disallow public access
    }

    public static <T> T notNull(T object) {
        if (SNAPSHOT) {
            if (object == null) {
                throw new NullPointerException();
            }
        }

        return object;
    }
}
