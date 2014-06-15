package android.ext.eventbus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.ext.core.Objects;
import android.os.Bundle;

public class EventDelivery implements EventDispatcher {
    private final Context mContext;
    private final FragmentManager mManager;
    private EventDeliveryOptions mOptions;

    public EventDeliveryOptions getOptions() {
        return mOptions;
    }

    public void setOptions(EventDeliveryOptions options) {
        mOptions = options;
    }

    public EventDelivery(Context context, FragmentManager manager) {
        mContext = Objects.notNull(context);
        mManager = Objects.notNull(manager);
    }

    @Override
    public boolean onNewEvent(int eventId, Bundle event) {
        Fragment fragment = mManager.findFragmentByTag(mOptions.tag);
        if (fragment != null) {
            if (!fragment.isVisible()) {
                mOptions.performTransaction(mManager, fragment);
            }

            if (fragment instanceof EventDispatcher) {
                return ((EventDispatcher) fragment).onNewEvent(eventId, event);
            } else {
                return false;
            }
        } else {
            mOptions.performTransaction(mManager, Fragment.instantiate(mContext, mOptions.fragment, event));
            return true;
        }
    }
}
