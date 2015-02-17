package xdroid.core;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.Method;
import java.util.HashMap;

import static xdroid.core.BuildConfig.SNAPSHOT;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class Global {
    private static Context sContext;
    private static Handler sUiHandler;
    private static Handler sBackgroundHandler;
    private static HashMap<Class<?>, Object> sSingletons;

    private Global() {
        // disallow public access
    }

    static {
        sUiHandler = new Handler(Looper.getMainLooper());
        sBackgroundHandler = ThreadUtils.newThread(Global.class.getSimpleName(), null);
        sSingletons = new HashMap<Class<?>, Object>();
    }

    public static void init(Application app) {
        sContext = ObjectUtils.notNull(app);
    }

    public static Context getContext() {
        if (sContext == null) {
            synchronized (Global.class) {
                if (sContext == null) {
                    Throwable exception = null;

                    try {
                        Class<?> clazz = Class.forName("android.app.ActivityThread");
                        Method method = ReflectUtils.getMethod(clazz, "currentApplication");
                        sContext = (Context) ReflectUtils.invokeStaticMethod(method);
                    } catch (Throwable ex) {
                        exception = ex;
                    }

                    if (sContext == null) {
                        throw new IllegalStateException(exception);
                    }
                }
            }
        }

        return ObjectUtils.notNull(sContext);
    }

    public static Resources getResources() {
        return ObjectUtils.notNull(getContext().getResources());
    }

    public static Handler getUiHandler() {
        return sUiHandler;
    }

    public static Handler getBackgroundHandler() {
        return sBackgroundHandler;
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

        sSingletons.put(clazz, ObjectUtils.notNull(instance));
    }

    public static boolean hasSingleton(Class<?> clazz) {
        return sSingletons.containsKey(clazz);
    }

    public static <T> T getSingleton(Class<T> clazz) {
        return ObjectUtils.notNull(clazz.cast(sSingletons.get(clazz)));
    }
}
