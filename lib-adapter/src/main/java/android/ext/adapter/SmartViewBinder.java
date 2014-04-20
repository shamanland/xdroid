package android.ext.adapter;

import android.ext.core.Objects;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;
import android.view.View;

import java.util.ArrayList;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class SmartViewBinder<D, V extends View> implements ViewBinder<D, V>, Parcelable {
    private final ViewBinderFactory<D> mFactory;
    private final int[] mIds;

    public SmartViewBinder(ViewBinderFactory<D> factory) {
        mFactory = Objects.notNull(factory);
        mIds = Objects.notNull(factory.getIds());
    }

    @Override
    public void onNewView(V view) {
        Binders<D> binders = new Binders<D>();

        for (int id : mIds) {
            View v = view.findViewById(id);
            if (v != null) {
                ViewBinder<D, ? extends View> b = mFactory.create(id);
                if (b != null) {
                    binders.add(new ViewAndBinder<D>(v, b));
                }
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
        out.writeValue(mFactory);
    }

    public static final Parcelable.Creator<SmartViewBinder> CREATOR = new Parcelable.Creator<SmartViewBinder>() {
        public SmartViewBinder createFromParcel(Parcel in) {
            return new SmartViewBinder(in);
        }

        public SmartViewBinder[] newArray(int size) {
            return new SmartViewBinder[size];
        }
    };

    private SmartViewBinder(Parcel in) {
        ClassLoader cl = ((Object) this).getClass().getClassLoader();
        mFactory = (ViewBinderFactory) Objects.notNull(in.readValue(cl));
        mIds = Objects.notNull(mFactory.getIds());
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
