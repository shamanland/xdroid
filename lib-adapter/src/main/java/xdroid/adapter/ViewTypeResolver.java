package xdroid.adapter;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface ViewTypeResolver<D> {
    int getViewType(int position, D data);
}
