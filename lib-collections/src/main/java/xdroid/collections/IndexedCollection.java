package xdroid.collections;

import java.util.Collection;
import java.util.Iterator;

import static xdroid.core.ObjectUtils.notNull;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class IndexedCollection<E> implements Collection<E> {
    private final Indexed<E> mBase;

    public IndexedCollection(Indexed<E> base) {
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
        for (int i = 0, n = mBase.size(); i < n; ++i) {
            E e = mBase.get(i);

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
        return mBase.size() < 1;
    }

    @Override
    public Iterator<E> iterator() {
        return new IndexedIterator<E>(mBase);
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
        return mBase.size();
    }

    @Override
    public Object[] toArray() {
        int size = mBase.size();
        Object[] result = new Object[size];

        for (int i = 0; i < size; ++i) {
            result[i] = mBase.get(i);
        }

        return result;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        int size = mBase.size();

        for (int i = 0; i < size; ++i) {
            array[i] = (T) mBase.get(i);
        }

        return array;
    }
}
