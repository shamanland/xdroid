package android.ext.adapter;

import android.view.View;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class DefaultViewBinder<D, V extends View> implements ViewBinder<D, V> {
    @Override
    public void onNewView(V view) {
        // empty
    }

    @Override
    public void onNewData(V view, D data) {
        // empty
    }
}
