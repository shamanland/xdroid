package xdroid.core;

import android.util.Log;

import java.io.Closeable;

import static xdroid.core.BuildConfig.SNAPSHOT;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class IoUtils {
    private static final String LOG_TAG = IoUtils.class.getSimpleName();

    private IoUtils() {
        // disallow public access
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable ex) {
                if (SNAPSHOT) {
                    Log.wtf(LOG_TAG, ex);
                }
            }
        }
    }
}
