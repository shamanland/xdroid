package android.ext.adapter;

import android.view.View;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface ViewBinder<D, V extends View> {
    /**
     * Implementation should prepare passed <b>view</b> for future call {@link #onNewData}.
     *
     * @param view newly created instance
     */
    void onNewView(V view);

    void onNewData(V view, D data);
}
