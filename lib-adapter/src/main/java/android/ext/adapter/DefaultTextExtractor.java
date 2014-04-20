package android.ext.adapter;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class DefaultTextExtractor<T> implements TextExtractor<T> {
    private static final DefaultTextExtractor sInstance = new DefaultTextExtractor();

    public static <T> DefaultTextExtractor<T> getInstance() {
        return sInstance;
    }

    private DefaultTextExtractor() {
        // disallow public access
    }

    @Override
    public CharSequence toText(Object object) {
        return String.valueOf(object);
    }
}
