package android.ext.adapter;

import android.ext.collections.FactoryArg1;
import android.view.View;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface ViewBinderFactory<D> extends FactoryArg1<ViewBinder<D, ? extends View>, Integer> {
    int[] getIds();
}
