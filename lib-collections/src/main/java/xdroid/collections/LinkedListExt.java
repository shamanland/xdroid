package xdroid.collections;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collection;
import java.util.LinkedList;

public class LinkedListExt<E> extends LinkedList<E> implements Indexed<E>, Parcelable {
    public LinkedListExt() {
        super();
    }

    public LinkedListExt(Collection<? extends E> collection) {
        super(collection);
    }

    @Override
    @SuppressWarnings("unchecked")
    public LinkedListExt<E> clone() {
        return (LinkedListExt) super.clone();
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

    public static final Parcelable.Creator<LinkedListExt> CREATOR = new Parcelable.Creator<LinkedListExt>() {
        public LinkedListExt createFromParcel(Parcel in) {
            return new LinkedListExt(in);
        }

        public LinkedListExt[] newArray(int size) {
            return new LinkedListExt[size];
        }
    };

    protected LinkedListExt(Parcel in) {
        ClassLoader cl = ((Object) this).getClass().getClassLoader();

        int count = in.readInt();
        while (count-- > 0) {
            add((E) in.readValue(cl));
        }
    }
}
