package xdroid.app;

import android.app.Activity;

import xdroid.customservice.CustomServices;

import static xdroid.core.Objects.notNull;
import static xdroid.customservice.CustomService.asCustomServiceResolver;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ActivityImpl {
    private final Activity mActivity;
    private final CustomServices mCustomServices;
    private ActivityListeners mListeners;

    public CustomServices getCustomServices() {
        return mCustomServices;
    }

    private boolean hasListeners() {
        return mListeners != null;
    }

    public ActivityListeners getListeners() {
        if (mListeners == null) {
            mListeners = new ActivityListeners();
        }

        return mListeners;
    }

    public ActivityImpl(Activity activity) {
        mActivity = notNull(activity);
        mCustomServices = new CustomServices(asCustomServiceResolver(activity.getApplication()));
    }

    public boolean onBackPressed() {
        boolean preventDefault = false;

        if (hasListeners()) {
            for (OnBackPressedListener listener : getListeners().getBackPressed()) {
                preventDefault |= listener.onBackPressed(mActivity);
            }
        }

        return preventDefault;
    }

    public void finish() {
        if (hasListeners()) {
            for (OnFinishListener listener : getListeners().getFinish()) {
                listener.onFinish(mActivity);
            }
        }
    }
}
