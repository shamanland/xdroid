package android.ext.adapter;

import android.ext.collections.Indexed;
import android.ext.collections.IndexedIterator;
import android.ext.core.Objects;
import android.ext.core.ParcelUtils;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Iterator;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class AdapterExt<D, V extends View> extends BaseAdapter implements Iterable<D>, Parcelable {
    private Indexed<D> mData;
    private ViewBinder<D, V> mBinder;
    private int mLayoutId;

    private transient boolean mChangesLocked;

    public AdapterExt() {
        mBinder = NullBinder.getInstance();
        mLayoutId = android.R.layout.simple_list_item_1;
    }

    public Indexed<D> getData() {
        return mData;
    }

    public void setData(Indexed<D> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void setBinder(ViewBinder<D, V> binder) {
        mBinder = binder != null ? binder : NullBinder.<D, V>getInstance();
        notifyDataSetChanged();
    }

    public void setLayoutId(int layoutId) {
        mLayoutId = layoutId;
        notifyDataSetChanged();
    }

    public void lockChanges() {
        mChangesLocked = true;
    }

    public void unlockChanges() {
        mChangesLocked = false;
    }

    @Override
    public void notifyDataSetChanged() {
        if (!mChangesLocked) {
            super.notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mData != null ? mData.size() : 0;
    }

    @Override
    public D getItem(int position) {
        return mData != null ? mData.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public V getView(int position, View convertView, ViewGroup parent) {
        final V result;

        if (convertView != null) {
            result = (V) convertView;
        } else {
            result = (V) LayoutInflater.from(parent.getContext()).inflate(mLayoutId, parent, false);

            mBinder.onNewView(result);
        }

        mBinder.onNewData(result, getItem(position));

        return result;
    }

    public D getFirstItem() {
        return getCount() > 0 ? getItem(0) : null;
    }

    public D getLastItem() {
        int count = getCount();
        return count > 0 ? getItem(count - 1) : null;
    }

    @Override
    public Iterator<D> iterator() {
        return new IndexedIterator<D>(mData);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        ParcelUtils.writeParcelableOrSerializable(out, flags, mData);
        ParcelUtils.writeParcelableOrSerializable(out, flags, mBinder);
        out.writeInt(mLayoutId);
    }

    public static final Parcelable.Creator<AdapterExt> CREATOR = new Parcelable.Creator<AdapterExt>() {
        public AdapterExt createFromParcel(Parcel in) {
            return new AdapterExt(in);
        }

        public AdapterExt[] newArray(int size) {
            return new AdapterExt[size];
        }
    };

    protected AdapterExt(Parcel in) {
        ClassLoader cl = ((Object) this).getClass().getClassLoader();
        mData = ParcelUtils.readParcelableOrSerializable(in, cl);
        mBinder = Objects.notNull(ParcelUtils.<ViewBinder<D, V>>readParcelableOrSerializable(in, cl));
        mLayoutId = in.readInt();
    }
}
