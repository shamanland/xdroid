package xdroid.app;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface OnBackPressedListener {
    /**
     * @param activity source
     * @return {@code true} in order to prevent default behavior
     */
    boolean onBackPressed(ActivityExt activity);
}
