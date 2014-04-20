package android.ext.core;

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
        final View[] result = ArrayUtils.fromObjects((View) null);

        traverse(view, new ViewVisitor() {
            @Override
            public boolean onVisitView(View view) {
                if (clazz.isInstance(view)) {
                    result[0] = view;
                    return true;
                }

                return false;
            }
        });

        return clazz.cast(result[0]);
    }
}
