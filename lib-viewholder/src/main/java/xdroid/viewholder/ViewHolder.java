package xdroid.viewholder;

import android.util.SparseArray;
import android.view.View;

import static xdroid.core.ObjectUtils.cast;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class ViewHolder {
    private static final Object NULL = new Object();

    private final View mView;
    private final SparseArray<Object> mViews;
    private int mInt;
    private Object mObject;

    /**
     * Obtain instance based on passed <code>view</code>.
     * Newly created instance of <code>ViewHolder</code> will be saved as a tag for passed <code>view</code>.
     * Make sure the method {@link View#setTag(Object)} won't be invoked from other scopes.
     * @param view not null
     * @return not null
     */
    public static ViewHolder obtain(View view) {
        if (view.getTag() instanceof ViewHolder) {
            return (ViewHolder) view.getTag();
        }

        return new ViewHolder(view);
    }

    private ViewHolder(View view) {
        view.setTag(this);
        mView = view;
        mViews = new SparseArray<>();
    }

    /**
     * Gets the saved <code>int</code>.
     *
     * @return previously saved value
     */
    public int getInt() {
        return mInt;
    }

    /**
     * Sets the <code>int</code> value associated with this instance (e.g. position from <code>Adapter</code>).
     *
     * @param intValue value
     */
    public void set(int intValue) {
        mInt = intValue;
    }

    /**
     * Gets the saved <code>Object</code>.
     * <b/>
     * NOTE: unchecked cast will be performed.
     * @param <T> any type return value to be casted to
     * @return previously saved instance
     */
    public <T> T get() {
        return cast(mObject);
    }

    /**
     * Sets the <code>Object</code> instance associated with this instance (e.g. data item from <code>Adapter</code>).
     *
     * @param object instance
     */
    public void set(Object object) {
        mObject = object;
    }

    /**
     * Gets the child view by id.
     *
     * @param id view id
     * @param <V> any type return value to be casted to
     * @return cached instance of <code>View</code> or <code>null</code> if not found
     */
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
