package xdroid.viewholder;

import android.util.SparseArray;
import android.view.View;

import static xdroid.core.ObjectUtils.cast;

public final class ViewHolder {
    private static final Object NULL = new Object();

    private final View mView;
    private final SparseArray<Object> mViews;
    private int mInt;
    private Object mObject;

    public static ViewHolder obtain(View view) {
        if (view.getTag() instanceof ViewHolder) {
            return (ViewHolder) view.getTag();
        }

        return new ViewHolder(view);
    }

    private ViewHolder(View view) {
        view.setTag(this);
        mView = view;
        mViews = new SparseArray<Object>();
    }

    public int getInt() {
        return mInt;
    }

    public void set(int intValue) {
        mInt = intValue;
    }

    public <T> T get() {
        return cast(mObject);
    }

    public void set(Object object) {
        mObject = object;
    }

    public <V extends View> V get(int id) {
        Object result = mViews.get(id);

        if (result == NULL) {
            return null;
        }

        if (result != null) {
            return cast(result);
        }

        result = mView.findViewById(id);

        if (result == null) {
            mViews.put(id, NULL);
        } else {
            mViews.put(id, result);
        }

        return cast(result);
    }
}
