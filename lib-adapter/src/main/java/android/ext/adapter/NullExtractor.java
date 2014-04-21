package android.ext.adapter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class NullExtractor<D, E> implements ViewBinderExtractor<D, E> {
    private static final NullExtractor sInstance;

    static {
        sInstance = new NullExtractor();
    }

    public static <D, E> NullExtractor<D, E> getInstance() {
        return sInstance;
    }

    private NullExtractor() {
        // disallow public access
    }

    @Override
    public E extract(D data) {
        return null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        // empty
    }

    public static final Parcelable.Creator<NullExtractor> CREATOR = new Parcelable.Creator<NullExtractor>() {
        public NullExtractor createFromParcel(Parcel in) {
            return getInstance();
        }

        public NullExtractor[] newArray(int size) {
            return new NullExtractor[size];
        }
    };
}
