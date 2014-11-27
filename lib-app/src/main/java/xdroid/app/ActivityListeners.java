package xdroid.app;

import java.util.Collection;

import static java.util.Collections.emptyList;
import static xdroid.collections.Prototypes.newArrayList;
import static xdroid.core.ObjectUtils.notNull;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
class ActivityListeners {
    private Collection<OnBackPressedListener> mBackPressed;
    private Collection<OnFinishListener> mFinish;

    public Iterable<OnBackPressedListener> getBackPressed() {
        if (mBackPressed != null) {
            return mBackPressed;
        }

        return emptyList();
    }

    public Iterable<OnFinishListener> getFinish() {
        if (mFinish != null) {
            return mFinish;
        }

        return emptyList();
    }

    public void add(OnBackPressedListener listener) {
        if (mBackPressed == null) {
            mBackPressed = newArrayList();
        }

        mBackPressed.add(notNull(listener));
    }

    public void add(OnFinishListener listener) {
        if (mFinish == null) {
            mFinish = newArrayList();
        }

        mFinish.add(notNull(listener));
    }

    public void remove(OnBackPressedListener listener) {
        if (mBackPressed != null) {
            mBackPressed.remove(listener);
        }
    }

    public void remove(OnFinishListener listener) {
        if (mFinish != null) {
            mFinish.remove(listener);
        }
    }
}
