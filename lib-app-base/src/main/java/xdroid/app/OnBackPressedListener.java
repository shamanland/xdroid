package xdroid.app;

import android.app.Activity;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface OnBackPressedListener {
    /**
     * @param activity source
     * @return {@code true} in order to prevent default behavior
     */
    boolean onBackPressed(Activity activity);
}
