package xdroid.app;

import android.app.Activity;

import java.util.Collection;

import static xdroid.collections.Prototypes.newArrayList;
import static xdroid.core.ObjectUtils.notNull;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ActivityListeners {
    private Collection<OnBackPressedListener> mBackPressed;
    private Collection<OnFinishListener> mFinish;

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

    public boolean onBackPressed(Activity activity) {
        boolean preventDefault = false;

        if (mBackPressed != null) {
            for (OnBackPressedListener listener : mBackPressed) {
                preventDefault |= listener.onBackPressed(activity);
            }
        }

        return preventDefault;
    }

    public void onFinish(Activity activity) {
        if (mFinish != null) {
            for (OnFinishListener listener : mFinish) {
                listener.onFinish(activity);
            }
        }
    }
}
