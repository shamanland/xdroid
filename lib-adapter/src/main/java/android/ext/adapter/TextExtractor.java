package android.ext.adapter;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface TextExtractor<T> {
    CharSequence toText(T object);
}
