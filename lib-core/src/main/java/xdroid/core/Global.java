package xdroid.core;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;

import java.util.HashMap;

import static xdroid.core.BuildConfig.SNAPSHOT;

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

    public static void init(Application app) {
        sContext = Objects.notNull(app);
        sUiHandler = new Handler(Looper.getMainLooper());
        sSingletons = new HashMap<Class<?>, Object>();
    }

    public static Context getContext() {
        return Objects.notNull(sContext);
    }

    public static Resources getResources() {
        return Objects.notNull(getContext().getResources());
    }

    public static Handler getUiHandler() {
        return Objects.notNull(sUiHandler);
    }

    public static <T> void putSingleton(Class<T> clazz, T instance) {
        if (SNAPSHOT) {
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
