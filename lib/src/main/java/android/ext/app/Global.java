package android.ext.app;

import android.content.Context;
import android.ext.Objects;
import android.os.Handler;
import android.os.Looper;

import java.util.HashMap;

import static android.ext.BuildConfig.DEBUG;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class Global {
    private static Context sContext;
    private static Handler sUiHandler;
    private static HashMap<Class<?>, Object> sSingletons;

    private Global() {
        // disallow public access
    }

    static void init(ApplicationExt app) {
        sContext = Objects.notNull(app);
        sUiHandler = new Handler(Looper.getMainLooper());
        sSingletons = new HashMap<Class<?>, Object>();
    }

    public static Context getContext() {
        return Objects.notNull(sContext);
    }

    public static Handler getUiHandler() {
        return Objects.notNull(sUiHandler);
    }

    static <T> void putSingleton(Class<T> clazz, T instance) {
        if (DEBUG) {
            if (!clazz.isInstance(instance)) {
                throw new IllegalArgumentException();
            }

            Object old = sSingletons.get(clazz);
            if (old != null) {
                throw new IllegalStateException();
            }
        }

        sSingletons.put(clazz, Objects.notNull(instance));
    }

    public static boolean hasSingleton(Class<?> clazz) {
        return sSingletons.containsKey(clazz);
    }

    public static <T> T getSingleton(Class<T> clazz) {
        return Objects.notNull(clazz.cast(sSingletons.get(clazz)));
    }
}
