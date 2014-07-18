package xdroid.adapter;

import android.view.View;

public interface IAdapter<D, V extends View> {
    void setBinder(ViewBinder<D, V> binder);

    void setViewTypeResolver(ViewTypeResolver<D> viewTypeResolver);

    void setLayoutId(int layoutId);

    void putLayoutId(int viewType, int layoutId);
}
