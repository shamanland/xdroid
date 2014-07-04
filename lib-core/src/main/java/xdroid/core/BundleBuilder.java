package xdroid.core;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.ArrayList;

@TargetApi(Build.VERSION_CODES.FROYO)
public final class BundleBuilder {
    private final Bundle mBase;

    public Bundle get() {
        return mBase;
    }

    public BundleBuilder() {
        mBase = new Bundle();
    }

    public BundleBuilder(Bundle base, boolean copy) {
        mBase = copy ? new Bundle(Objects.notNull(base)) : Objects.notNull(base);
    }

    public BundleBuilder(Bundle base) {
        this(base, false);
    }

    public BundleBuilder(String key, boolean value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, byte value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, char value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, short value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, int value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, long value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, float value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, double value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, String value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, CharSequence value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, Parcelable value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, Parcelable[] value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, SparseArray<? extends Parcelable> value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, ArrayList<? extends Parcelable> value, Parcelable dummy) {
        this();
        put(key, value, dummy);
    }

    public BundleBuilder(String key, ArrayList<Integer> value, Integer dummy) {
        this();
        put(key, value, dummy);
    }

    public BundleBuilder(String key, ArrayList<String> value, String dummy) {
        this();
        put(key, value, dummy);
    }

    public BundleBuilder(String key, ArrayList<CharSequence> value, CharSequence dummy) {
        this();
        put(key, value, dummy);
    }

    public BundleBuilder(String key, Serializable value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, boolean[] value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, byte[] value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, short[] value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, char[] value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, int[] value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, long[] value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, float[] value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, double[] value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, String[] value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, CharSequence[] value) {
        this();
        put(key, value);
    }

    public BundleBuilder(String key, Bundle value) {
        this();
        put(key, value);
    }

    public BundleBuilder put(String key, boolean value) {
        mBase.putBoolean(key, value);
        return this;
    }

    public BundleBuilder put(String key, byte value) {
        mBase.putByte(key, value);
        return this;
    }

    public BundleBuilder put(String key, char value) {
        mBase.putChar(key, value);
        return this;
    }

    public BundleBuilder put(String key, short value) {
        mBase.putShort(key, value);
        return this;
    }

    public BundleBuilder put(String key, int value) {
        mBase.putInt(key, value);
        return this;
    }

    public BundleBuilder put(String key, long value) {
        mBase.putLong(key, value);
        return this;
    }

    public BundleBuilder put(String key, float value) {
        mBase.putFloat(key, value);
        return this;
    }

    public BundleBuilder put(String key, double value) {
        mBase.putDouble(key, value);
        return this;
    }

    public BundleBuilder put(String key, String value) {
        mBase.putString(key, value);
        return this;
    }

    public BundleBuilder put(String key, CharSequence value) {
        mBase.putCharSequence(key, value);
        return this;
    }

    public BundleBuilder put(String key, Parcelable value) {
        mBase.putParcelable(key, value);
        return this;
    }

    public BundleBuilder put(String key, Parcelable[] value) {
        mBase.putParcelableArray(key, value);
        return this;
    }

    public BundleBuilder put(String key, SparseArray<? extends Parcelable> value) {
        mBase.putSparseParcelableArray(key, value);
        return this;
    }

    public BundleBuilder put(String key, ArrayList<? extends Parcelable> value, Parcelable dummy) {
        mBase.putParcelableArrayList(key, value);
        return this;
    }

    public BundleBuilder put(String key, ArrayList<Integer> value, Integer dummy) {
        mBase.putIntegerArrayList(key, value);
        return this;
    }

    public BundleBuilder put(String key, ArrayList<String> value, String dummy) {
        mBase.putStringArrayList(key, value);
        return this;
    }

    public BundleBuilder put(String key, ArrayList<CharSequence> value, CharSequence dummy) {
        mBase.putCharSequenceArrayList(key, value);
        return this;
    }

    public BundleBuilder put(String key, Serializable value) {
        mBase.putSerializable(key, value);
        return this;
    }

    public BundleBuilder put(String key, boolean[] value) {
        mBase.putBooleanArray(key, value);
        return this;
    }

    public BundleBuilder put(String key, byte[] value) {
        mBase.putByteArray(key, value);
        return this;
    }

    public BundleBuilder put(String key, short[] value) {
        mBase.putShortArray(key, value);
        return this;
    }

    public BundleBuilder put(String key, char[] value) {
        mBase.putCharArray(key, value);
        return this;
    }

    public BundleBuilder put(String key, int[] value) {
        mBase.putIntArray(key, value);
        return this;
    }

    public BundleBuilder put(String key, long[] value) {
        mBase.putLongArray(key, value);
        return this;
    }

    public BundleBuilder put(String key, float[] value) {
        mBase.putFloatArray(key, value);
        return this;
    }

    public BundleBuilder put(String key, double[] value) {
        mBase.putDoubleArray(key, value);
        return this;
    }

    public BundleBuilder put(String key, String[] value) {
        mBase.putStringArray(key, value);
        return this;
    }

    public BundleBuilder put(String key, CharSequence[] value) {
        mBase.putCharSequenceArray(key, value);
        return this;
    }

    public BundleBuilder put(String key, Bundle value) {
        mBase.putBundle(key, value);
        return this;
    }
}
