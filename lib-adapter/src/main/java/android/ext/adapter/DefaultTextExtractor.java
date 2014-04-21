package android.ext.adapter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class DefaultTextExtractor<D> implements TextExtractor<D> {
    private static final DefaultTextExtractor sInstance;

    static {
        sInstance = new DefaultTextExtractor();
    }

    public static <D> DefaultTextExtractor<D> getInstance() {
        return sInstance;
    }

    private DefaultTextExtractor() {
        // disallow public access
    }

    @Override
    public CharSequence extract(D data) {
        return String.valueOf(data);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        // empty
    }

    public static final Parcelable.Creator<DefaultTextExtractor> CREATOR = new Parcelable.Creator<DefaultTextExtractor>() {
        public DefaultTextExtractor createFromParcel(Parcel in) {
            return getInstance();
        }

        public DefaultTextExtractor[] newArray(int size) {
            return new DefaultTextExtractor[size];
        }
    };
}
