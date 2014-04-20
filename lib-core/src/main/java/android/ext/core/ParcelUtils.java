package android.ext.core;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class ParcelUtils {
    public static final int VAL_PARCELABLE = 0x01;
    public static final int VAL_SERIALIZABLE = 0x02;
    public static final int VAL_NULL = 0x03;

    private ParcelUtils() {
        // disallow public access
    }

    public static void writeParcelableOrSerializable(Parcel out, int flags, Object value) {
        if (value instanceof Parcelable) {
            out.writeInt(VAL_PARCELABLE);
            out.writeParcelable((Parcelable) value, flags);
        } else if (value instanceof Serializable) {
            out.writeInt(VAL_SERIALIZABLE);
            out.writeSerializable((Serializable) value);
        } else {
            out.writeValue(VAL_NULL);
        }
    }

    public static <T> T readParcelableOrSerializable(Parcel in, ClassLoader classLoader) {
        switch (in.readInt()) {
            case VAL_PARCELABLE:
                return (T) in.readParcelable(classLoader);

            case VAL_SERIALIZABLE:
                return (T) in.readSerializable();

            case VAL_NULL:
                // fall down

            default:
                return null;
        }
    }
}
