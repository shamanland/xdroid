package xdroid.core;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static xdroid.core.BuildConfig.SNAPSHOT;
import static xdroid.core.ObjectUtils.cast;
import static xdroid.core.ObjectUtils.notNull;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class Global {
    private static Context sContext;

    private Global() {
        // disallow public access
    }

    public static void setContext(Context context) {
        sContext = context;
    }

    public static Context getContext() {
        if (sContext == null) {
            sContext = CurrentApplicationHolder.INSTANCE;
        }

        return notNull(sContext);
    }

    public static Resources getResources() {
        return notNull(getContext().getResources());
    }

    public static Handler getUiHandler() {
        return UiHandlerHolder.INSTANCE;
    }

    public static Handler getBackgroundHandler() {
        return BackgroundHandlerHolder.INSTANCE;
    }

    protected static Map<Class<?>, Object> getSingletons() {
        return SingletonsHolder.INSTANCE;
    }

    public static <T> void putSingleton(Class<T> clazz, T instance) {
        if (SNAPSHOT) {
            if (!clazz.isInstance(instance)) {
                throw new IllegalArgumentException();
            }

            Object old = getSingletons().get(clazz);
            if (old != null) {
                throw new IllegalStateException();
            }
        }

        getSingletons().put(clazz, notNull(instance));
    }

    public static boolean hasSingleton(Class<?> clazz) {
        return getSingletons().containsKey(clazz);
    }

    public static <T> T getSingleton(Class<T> clazz) {
        return notNull(clazz.cast(getSingletons().get(clazz)));
    }

    static class CurrentApplicationHolder {
        static final Application INSTANCE;

        static {
            try {
                Class<?> clazz = Class.forName("android.app.ActivityThread");
                Method method = ReflectUtils.getMethod(clazz, "currentApplication");
                INSTANCE = cast(ReflectUtils.invokeStaticMethod(method));
            } catch (Throwable ex) {
                throw new AssertionError(ex);
            }
        }
    }

    static class UiHandlerHolder {
        static final Handler INSTANCE = new Handler(Looper.getMainLooper());
    }

    static class BackgroundHandlerHolder {
        static final Handler INSTANCE = ThreadUtils.newThread(Global.class.getSimpleName(), null);
    }

    static class SingletonsHolder {
        static final HashMap<Class<?>, Object> INSTANCE = new HashMap<>();
    }
}
