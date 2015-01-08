package xdroid.collections;

import java.util.Iterator;

import static xdroid.core.Objects.notNull;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ArrayIterator<E> implements Iterator<E> {
    private final E[] mBase;
    private int mCursor;

    public ArrayIterator(E[] base) {
        mBase = notNull(base);
        mCursor = -1;
    }

    @Override
    public boolean hasNext() {
        return mCursor + 1 < mBase.length;
    }

    @Override
    public E next() {
        return mBase[++mCursor];
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
