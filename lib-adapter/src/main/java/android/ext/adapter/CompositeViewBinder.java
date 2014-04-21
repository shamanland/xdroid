package android.ext.adapter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import android.util.SparseArray;
import android.view.View;

import java.util.ArrayList;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class CompositeViewBinder<D, V extends View> implements ViewBinder<D, V>, Parcelable {
    private final SparseArray<ViewBinder<D, ? extends View>> mBinders;

    public CompositeViewBinder() {
        mBinders = new SparseArray<ViewBinder<D, ? extends View>>();
    }

    public void add(int id, ViewBinder<D, ? extends View> binder) {
        mBinders.put(id, binder);
    }

    @Override
    public void onNewView(V view) {
        Binders<D> binders = new Binders<D>();

        int count = mBinders.size();
        for (int i = 0; i < count; ++i) {
            View v = view.findViewById(mBinders.keyAt(i));
            if (v != null) {
                binders.add(new ViewAndBinder<D>(v, mBinders.valueAt(i)));
            }
        }

        view.setTag(binders);
    }

    @Override
    public void onNewData(V view, D data) {
        Object tag = view.getTag();
        if (tag instanceof Binders) {
            for (ViewAndBinder<D> vb : (Binders<D>) tag) {
                vb.onNewData(data);
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        int count = mBinders.size();
        out.writeInt(count);

        for (int i = 0; i < count; ++i) {
            out.writeInt(mBinders.keyAt(i));
            // NOTE binder have to be Parcelable or Serializable
            out.writeValue(mBinders.valueAt(i));
        }
    }

    public static final Creator<CompositeViewBinder> CREATOR = new Creator<CompositeViewBinder>() {
        public CompositeViewBinder createFromParcel(Parcel in) {
            return new CompositeViewBinder(in);
        }

        public CompositeViewBinder[] newArray(int size) {
            return new CompositeViewBinder[size];
        }
    };

    protected CompositeViewBinder(Parcel in) {
        ClassLoader cl = ((Object) this).getClass().getClassLoader();
        mBinders = new SparseArray<ViewBinder<D, ? extends View>>();

        int count = in.readInt();
        for (int i = 0; i < count; ++i) {
            int id = in.readInt();
            ViewBinder<D, ? extends View> binder = (ViewBinder) in.readValue(cl);

            mBinders.put(id, binder);
        }
    }

    protected static class ViewAndBinder<D> extends Pair<View, ViewBinder<D, View>> {
        public ViewAndBinder(View view, ViewBinder<D, ? extends View> binder) {
            super(view, (ViewBinder<D, View>) binder);
            second.onNewView(first);
        }

        public void onNewData(D data) {
            second.onNewData(first, data);
        }
    }

    protected class Binders<D> extends ArrayList<ViewAndBinder<D>> {
        protected Binders() {
            super(2);
        }
    }
}
