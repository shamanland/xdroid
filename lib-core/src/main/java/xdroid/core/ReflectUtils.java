package xdroid.core;

import android.content.Context;

import java.lang.ref.SoftReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class ReflectUtils {
    private static final Set<Class<?>> sWrapper;
    private static final Set<Class<?>> sImmutable;
    private static final Map<String, SoftReference<Class>> sCache;

    static {
        sWrapper = new HashSet<>();
        sWrapper.add(Boolean.class);
        sWrapper.add(Character.class);
        sWrapper.add(Byte.class);
        sWrapper.add(Short.class);
        sWrapper.add(Integer.class);
        sWrapper.add(Long.class);
        sWrapper.add(Float.class);
        sWrapper.add(Double.class);
        sWrapper.add(Void.class);

        sImmutable = new HashSet<>(sWrapper);
        sImmutable.add(String.class);
        sImmutable.add(BigInteger.class);
        sImmutable.add(BigDecimal.class);

        sCache = new HashMap<>();
    }

    private ReflectUtils() {
        // disallow public access
    }


    public static boolean isWrapper(Class<?> clazz) {
        return sWrapper.contains(clazz);
    }

    public static boolean isImmutable(Class<?> clazz) {
        return sImmutable.contains(clazz);
    }

    public static boolean setFieldValue(Object target, String fieldName, Object value) {
        if (target == null) return false;
        if (fieldName == null) return false;

        try {
            Class<?> clazz = target.getClass();
            Field field = getField(clazz, fieldName);
            if (field != null) {
                field.setAccessible(true);
                field.set(target, value);
                return true;
            }
        } catch (Throwable ex) {
            return false;
        }

        return false;
    }

    public static Object getFieldValue(Object target, String fieldName) {
        if (target == null) return null;
        if (fieldName == null) return null;

        try {
            Class<?> clazz = target.getClass();
            Field field = getField(clazz, fieldName);
            if (field != null) {
                field.setAccessible(true);
                return field.get(target);
            }
        } catch (Throwable ex) {
            return null;
        }

        return null;
    }

    public static Object getFieldValue(Object target, String... fieldNames) {
        if (fieldNames == null) return null;

        Object result = target;

        for (String fieldName : fieldNames) {
            result = getFieldValue(result, fieldName);
        }

        return result;
    }

    public static Object getFieldValue(Class<?> clazz, String fieldName) {
        try {
            Field f = getField(clazz, fieldName);
            f.setAccessible(true);
            return f.get(null);
        } catch (Throwable ex) {
            return null;
        }
    }

    public static Field getField(Class<?> clazz, String fieldName) {
        Class<?> cl = clazz;
        while (cl != null) {
            try {
                return cl.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ex) {
                cl = cl.getSuperclass();
            }
        }

        return null;
    }

    public static Method getMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        Class<?> cl = clazz;
        while (cl != null) {
            try {
                return cl.getDeclaredMethod(methodName, parameterTypes);
            } catch (NoSuchMethodException ex) {
                cl = cl.getSuperclass();
            }
        }

        return null;
    }

    public static Object invokeMethod(Object target, String methodName) {
        return invokeMethod(target, methodName, new Class<?>[]{}, new Object[]{});
    }

    public static Object invokeMethod(Object target, String methodName, Class<?>[] parameterTypes, Object[] values) {
        if (target == null) return null;
        if (methodName == null) return null;
        if (parameterTypes == null) return null;
        if (values == null) return null;

        try {
            Method method = getMethod(target.getClass(), methodName, parameterTypes);
            if (method != null) {
                return invokeMethod(method, target, values);
            }
        } catch (Throwable ex) {
            return null;
        }

        return null;
    }

    public static Object invokeMethod(Method method, Object target, Object... args) {
        try {
            method.setAccessible(true);
            return method.invoke(target, args);
        } catch (Throwable ex) {
            return null;
        }
    }

    public static Object invokeStaticMethod(Method method, Object... args) {
        return invokeMethod(method, null, args);
    }

    public static <T> T invokeConstructor(Class<T> clazz) {
        return invokeConstructor(clazz, new Class<?>[]{}, new Object[]{});
    }

    public static <T> T invokeConstructor(Class<T> clazz, Class<?>[] parameterTypes, Object[] values) {
        if (clazz == null) return null;
        if (parameterTypes == null) return null;
        if (values == null) return null;

        try {
            Constructor<T> ctor = clazz.getDeclaredConstructor(parameterTypes);
            if (ctor != null) {
                ctor.setAccessible(true);
                return ctor.newInstance(values);
            }
        } catch (Throwable ex) {
            return null;
        }

        return null;
    }

    public static String getEnclosingInstanceName(Object innerInstance) {
        int depth = -1;
        Class<?> enclosing = innerInstance.getClass().getEnclosingClass();
        while (enclosing != null) {
            ++depth;
            enclosing = enclosing.getEnclosingClass();
        }

        return (depth == -1) ? null : "this$" + depth;
    }

    public static Object getEnclosingInstance(Object innerInstance) {
        int depth = -1;
        Object result = innerInstance;

        try {
            Class<?> enclosing = innerInstance.getClass().getEnclosingClass();
            while (enclosing != null) {
                ++depth;
                Field field = getField(result.getClass(), "this$" + depth);
                field.setAccessible(true);
                result = field.get(result);
                enclosing = enclosing.getEnclosingClass();
            }
        } catch (Throwable ex) {
            return null;
        }

        return result;
    }

    @SuppressWarnings("UnnecessaryBoxing")
    public static Object newReturnInstance(Method method) throws InstantiationException, IllegalAccessException {
        Class<?> clazz = method.getReturnType();

        if (clazz == boolean.class) {
            return Boolean.valueOf(false);
        } else if (clazz == byte.class) {
            return Byte.valueOf((byte) 0);
        } else if (clazz == char.class) {
            return Character.valueOf((char) 0);
        } else if (clazz == short.class) {
            return Short.valueOf((short) 0);
        } else if (clazz == int.class) {
            return Integer.valueOf(0);
        } else if (clazz == long.class) {
            return Long.valueOf(0l);
        } else if (clazz == float.class) {
            return Float.valueOf(0f);
        } else if (clazz == double.class) {
            return Double.valueOf(0d);
        } else if (clazz == void.class || clazz == Void.class) {
            return null;
        }

        return clazz.newInstance();
    }

    public static <T> T newInstance(Class<T> clazz) {
        try {
            Constructor<T> ctor = clazz.getDeclaredConstructor();
            ctor.setAccessible(true);
            return ctor.newInstance();
        } catch (Throwable ex) {
            throw new IllegalStateException(ex);
        }
    }

    public static <T> T newInstance(Class<T> clazz, Class<?>[] parameterTypes, Object[] args) {
        try {
            Constructor<T> ctor = clazz.getDeclaredConstructor(parameterTypes);
            ctor.setAccessible(true);
            return ctor.newInstance(args);
        } catch (Throwable ex) {
            throw new IllegalStateException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T newInstanceByClassName(String className) {
        Class clazz = null;

        SoftReference<Class> ref = sCache.get(className);
        if (ref != null) {
            clazz = ref.get();
        }

        if (clazz == null) {
            try {
                clazz = Class.forName(className);
                sCache.put(className, new SoftReference<Class>(clazz));
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

    public static String fullClassName(Context context, String className) {
        if (className.charAt(0) == '.') {
            return context.getPackageName() + className;
        }

        return className;
    }
}
