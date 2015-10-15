package xdroid.core;

import java.util.HashMap;
import java.util.Map;

import static xdroid.core.ObjectUtils.notNull;

public final class Singletons {
    private static final Map<Class<?>, Object> MAP = new HashMap<>();

    public static <T> void putSingleton(Class<T> clazz, T instance) {
        if (!clazz.isInstance(instance)) {
            throw new IllegalArgumentException();
        }

        Object old = MAP.get(clazz);
        if (old != null) {
            throw new IllegalStateException();
        }

        MAP.put(clazz, notNull(instance));
    }

    public static boolean hasSingleton(Class<?> clazz) {
        return MAP.containsKey(clazz);
    }

    public static <T> T getSingleton(Class<T> clazz) {
        return notNull(clazz.cast(MAP.get(clazz)));
    }
}
