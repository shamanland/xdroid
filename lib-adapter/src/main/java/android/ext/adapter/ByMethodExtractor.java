package android.ext.adapter;

import android.ext.core.Strings;
import android.os.Parcel;
import android.util.Log;

import java.lang.reflect.Method;

import static android.ext.adapter.BuildConfig.DEBUG;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ByMethodExtractor<D, E> implements ViewBinderExtractor<D, E> {
    private static final String LOG_TAG = ByMethodExtractor.class.getSimpleName();

    private final String mMethodName;
    private Method mMethod;

    public ByMethodExtractor(String methodName) {
        mMethodName = Strings.notEmpty(methodName);
    }

    @Override
    public E extract(D data) {
        if (data == null) {
            return null;
        }

        if (mMethod == null) {
            try {
                mMethod = data.getClass().getMethod(mMethodName);
            } catch (NoSuchMethodException ex) {
                throw new IllegalStateException(ex);
            }
        }

        try {
            return (E) mMethod.invoke(data);
        } catch (Throwable ex) {
            if (DEBUG) {
                if (mMethod.getDeclaringClass().isAssignableFrom(data.getClass())) {
                    Log.wtf(LOG_TAG, ex);
                } else {
                    Log.w(LOG_TAG, "Different data types used with single instance: previous " +
                            mMethod.getDeclaringClass().getName() + ", current " + data.getClass().getName());
                }
            }
        }

        return null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mMethodName);
    }

    public static final Creator<ByMethodExtractor> CREATOR = new Creator<ByMethodExtractor>() {
        public ByMethodExtractor createFromParcel(Parcel in) {
            return new ByMethodExtractor(in);
        }

        public ByMethodExtractor[] newArray(int size) {
            return new ByMethodExtractor[size];
        }
    };

    private ByMethodExtractor(Parcel in) {
        mMethodName = in.readString();
    }
}
