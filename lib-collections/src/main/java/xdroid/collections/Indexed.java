package xdroid.collections;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface Indexed<E> {
    int size();

    E get(int location);

    E set(int location, E object);
}
