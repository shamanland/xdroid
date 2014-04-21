package android.ext.adapter;

import android.os.Parcelable;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface ViewBinderExtractor<D, E> extends Parcelable {
    E extract(D data);
}
