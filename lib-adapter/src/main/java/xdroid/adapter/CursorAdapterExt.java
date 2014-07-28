package xdroid.adapter;

import android.content.Context;
import android.database.Cursor;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import xdroid.core.Objects;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class CursorAdapterExt<V extends View> extends CursorAdapter implements IAdapter<Cursor, V> {
    private ViewBinder<Cursor, V> mBinder;
    private ViewTypeResolver<Cursor> mViewTypeResolver;
    private SparseIntArray mLayoutId;

    private transient boolean mChangesLocked;

    public CursorAdapterExt(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        init();
    }

    @Deprecated
    public CursorAdapterExt(Context context, Cursor c, int flags) {
        super(context, c, flags);
        init();
    }

    private void init() {
        mLayoutId = new SparseIntArray();
        mLayoutId.put(0, android.R.layout.simple_list_item_1);
    }

    public void setBinder(ViewBinder<Cursor, V> binder) {
        mBinder = binder;
        notifyDataSetChanged();
    }

    public void setViewTypeResolver(ViewTypeResolver<Cursor> viewTypeResolver) {
        mViewTypeResolver = viewTypeResolver;
        notifyDataSetChanged();
    }

    public void setLayoutId(int layoutId) {
        putLayoutId(0, layoutId);
    }

    public void putLayoutId(int viewType, int layoutId) {
        mLayoutId.put(viewType, layoutId);
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
        V result = (V) LayoutInflater.from(Objects.notNull(parent.getContext()))
                .inflate(mLayoutId.get(getItemViewType(cursor.getPosition())), parent, false);
        mBinder.onNewView(cursor.getPosition(), result);
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void bindView(View view, Context context, Cursor cursor) {
        mBinder.onNewData(cursor.getPosition(), (V) view, cursor);
    }

    @Override
    public int getViewTypeCount() {
        return mLayoutId.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mViewTypeResolver != null) {
            return mViewTypeResolver.getViewType(position, (Cursor) getItem(position));
        }

        return super.getItemViewType(position);
    }
}
