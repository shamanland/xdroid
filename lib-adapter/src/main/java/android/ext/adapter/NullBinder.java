package android.ext.adapter;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class NullBinder<D, V extends View> implements ViewBinder<D, V>, Parcelable {
    private static final NullBinder sInstance;

    static {
        sInstance = new NullBinder();
    }

    public static <D, V extends View> NullBinder<D, V> getInstance() {
        return sInstance;
    }

    protected NullBinder() {
        // disallow public access
    }

    @Override
    public void onNewView(V view) {
        // empty
    }

    @Override
    public void onNewData(V view, D data) {
        // empty
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        // empty
    }

    public static final Creator<NullBinder> CREATOR = new Creator<NullBinder>() {
        public NullBinder createFromParcel(Parcel in) {
            return getInstance();
        }

        public NullBinder[] newArray(int size) {
            return new NullBinder[size];
        }
    };
}
