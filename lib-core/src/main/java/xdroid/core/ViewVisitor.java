package xdroid.core;

import android.view.View;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface ViewVisitor {
    /**
     * @param view current node
     * @return true if need to stop traverse
     */
    boolean onVisitView(View view);
}
