package xdroid.collections;

import java.util.Collection;
import java.util.Iterator;

import static xdroid.core.ObjectUtils.notNull;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ArrayCollection<E> implements Collection<E> {
    private final E[] mBase;

    public ArrayCollection(E[] base) {
        mBase = notNull(base);
    }

    @Override
    public boolean add(E object) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        return false;
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean contains(Object object) {
        for (E e : mBase) {
            if (e != null && e.equals(object)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object object : collection) {
            if (!contains(object)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isEmpty() {
        return mBase.length < 1;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator<E>(mBase);
    }

    @Override
    public boolean remove(Object object) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public int size() {
        return mBase.length;
    }

    @Override
    public Object[] toArray() {
        return mBase.clone();
    }

    @Override
    public <T> T[] toArray(T[] array) {
        System.arraycopy(mBase, 0, array, 0, mBase.length);
        return array;
    }
}
