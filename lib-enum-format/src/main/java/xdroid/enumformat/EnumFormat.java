package xdroid.enumformat;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import static xdroid.core.Global.getContext;
import static xdroid.core.Global.getResources;

public class EnumFormat extends Format {
    private static final EnumFormat INSTANCE = new EnumFormat();

    private Map<String, Integer> mCache;

    public static EnumFormat getInstance() {
        return INSTANCE;
    }

    public EnumFormat() {
        mCache = new HashMap<>();
    }

    public synchronized void clearCache() {
        mCache.clear();
    }

    protected synchronized Integer getCache(String fullName) {
        return mCache.get(fullName);
    }

    protected synchronized void putCache(String fullName, Integer value) {
        mCache.put(fullName, value);
    }

    /**
     * Add annotations {@link EnumString} to each of your <code>enum</code> constants.
     * If this annotation is not present, make sure you added corresponding <code>string</code>
     * to <code>res</code> folder with the same name (lower case) as your <code>enum</code> constant.
     *
     * @param e   not null
     * @param <E> any enum type
     * @return localized string if it was correctly configured
     * @see EnumString
     */
    public <E extends Enum<E>> String format(E e) {
        return format((Object) e);
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public StringBuffer format(Object object, StringBuffer buffer, FieldPosition field) {
        if (object instanceof Enum) {
            Enum e = (Enum) object;

            int stringId = getStringId(e);
            if (stringId != 0) {
                buffer.append(getResources().getText(stringId));
            } else {
                buffer.append(e.name());
            }

            return buffer;
        }

        throw new IllegalArgumentException(String.valueOf(object));
    }

    protected int getStringId(Enum e) {
        String fullName = e.getClass().getName() + "." + e.name();

        Integer result = getCache(fullName);
        if (result != null) {
            return result;
        }

        EnumString a = getAnnotation(e);
        if (a != null) {
            result = a.value();
        } else {
            result = getResources().getIdentifier(e.name().toLowerCase(Locale.US), "string", getContext().getPackageName());
        }

        putCache(fullName, result);
        return result;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public Object parseObject(String string, ParsePosition position) {
        throw new UnsupportedOperationException();
    }

    protected static EnumString getAnnotation(Enum e) {
        try {
            java.lang.reflect.Field field = e.getClass().getField(e.name());
            return field.getAnnotation(EnumString.class);
        } catch (NoSuchFieldException ex) {
            throw new AssertionError(ex);
        }
    }
}
