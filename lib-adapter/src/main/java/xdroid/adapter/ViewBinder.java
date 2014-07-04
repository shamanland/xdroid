package xdroid.adapter;

import android.view.View;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface ViewBinder<D, V extends View> {
    /**
     * Implementation should prepare passed <b>view</b> for future call {@link #onNewData}.
     *
     * @param position index of data item
     * @param view     newly created instance
     */
    void onNewView(int position, V view);

    void onNewData(int position, V view, D data);
}
