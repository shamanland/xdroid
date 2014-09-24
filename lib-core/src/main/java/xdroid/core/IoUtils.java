package xdroid.core;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class IoUtils {
    private IoUtils() {
        // disallow public access
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ex) {
                // ignore
            }
        }
    }
}
