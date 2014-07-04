package xdroid.core;

import android.view.View;
import android.view.ViewGroup;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class ViewUtils {
    private ViewUtils() {
        // disallow public access
    }

    public static View traverse(View view, ViewVisitor visitor) {
        if (visitor.onVisitView(view)) {
            return view;
        }

        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) view;
            int count = group.getChildCount();

            for (int i = 0; i < count; ++i) {
                View result = traverse(group.getChildAt(i), visitor);
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    public static <V extends View> V findViewByClass(View view, final Class<V> clazz) {
        return clazz.cast(traverse(view, new ViewVisitor() {
            @Override
            public boolean onVisitView(View view) {
                return clazz.isInstance(view);
            }
        }));
    }
}
