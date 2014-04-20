package android.ext.collections;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ArrayListExt<E> extends ArrayList<E> implements Indexed<E>, Parcelable {
    public ArrayListExt(int capacity) {
        super(capacity);
    }

    public ArrayListExt() {
        super();
    }

    public ArrayListExt(Collection<? extends E> collection) {
        super(collection);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(size());

        for (E item : this) {
            out.writeValue(item);
        }
    }

    public static final Creator<ArrayListExt> CREATOR = new Creator<ArrayListExt>() {
        public ArrayListExt createFromParcel(Parcel in) {
            return new ArrayListExt(in);
        }

        public ArrayListExt[] newArray(int size) {
            return new ArrayListExt[size];
        }
    };

    protected ArrayListExt(Parcel in) {
        ClassLoader cl = ((Object) this).getClass().getClassLoader();

        int count = in.readInt();
        ensureCapacity(count);

        while (count-- > 0) {
            add((E) in.readValue(cl));
        }
    }
}
