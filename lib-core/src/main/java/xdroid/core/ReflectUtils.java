package xdroid.core;

import android.content.Context;

import java.lang.ref.SoftReference;
import java.util.HashMap;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class ReflectUtils {
    private static final HashMap<String, SoftReference<Class>> sClassesCache;

    static {
        sClassesCache = new HashMap<String, SoftReference<Class>>();
    }

    private ReflectUtils() {
        // disallow public access
    }

    public static String fullClassName(Context context, String className) {
        if (className.charAt(0) == '.') {
            return context.getPackageName() + className;
        }

        return className;
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstanceByClassName(String className) {
        Class clazz = null;

        SoftReference<Class> ref = sClassesCache.get(className);
        if (ref != null) {
            clazz = ref.get();
        }

        if (clazz == null) {
            try {
                clazz = Class.forName(className);
                sClassesCache.put(className, new SoftReference<Class>(clazz));
            } catch (Throwable ex) {
                throw new IllegalStateException(ex);
            }
        }

        try {
            return (T) clazz.newInstance();
        } catch (Throwable ex) {
            throw new IllegalStateException(ex);
        }
    }
}
