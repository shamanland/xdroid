package android.ext.adapter;

import android.view.View;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface ViewBinder<D, V extends View> {
    void onNewView(V view);

    void onNewData(V view, D data);
}
