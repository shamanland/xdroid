package android.ext.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class CursorAdapterExt<V extends View> extends CursorAdapter {
    private ViewBinder<Cursor, V> mBinder;
    private int mLayoutId;

    private transient boolean mChangesLocked;

    public CursorAdapterExt(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Deprecated
    public CursorAdapterExt(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    public void setBinder(ViewBinder<Cursor, V> binder) {
        mBinder = binder;
        notifyDataSetChanged();
    }

    public void setLayoutId(int layoutId) {
        mLayoutId = layoutId;
        notifyDataSetChanged();
    }

    @SuppressWarnings("unused")
    public void lockChanges() {
        mChangesLocked = true;
    }

    @SuppressWarnings("unused")
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
    @SuppressWarnings("unchecked")
    public V newView(Context context, Cursor cursor, ViewGroup parent) {
        V result = (V) LayoutInflater.from(context).inflate(mLayoutId, parent, false);
        mBinder.onNewData(result, cursor);
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void bindView(View view, Context context, Cursor cursor) {
        mBinder.onNewData((V) view, cursor);
    }
}
