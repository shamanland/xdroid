package android.ext.eventbus;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.ext.core.ContextOwner;
import android.ext.core.Objects;
import android.ext.customservice.CustomService;
import android.ext.inflater.Inflatable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;

import static android.ext.eventbus.BuildConfig.SNAPSHOT;
import static android.ext.eventbus.EventBus.getEventName;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class EventDelivery extends DefaultEventDispatcher implements Inflatable {
    private static final String LOG_TAG = EventDelivery.class.getSimpleName();

    private final Context mContext;
    private final FragmentManager mManager;
    private EventDeliveryOptions mOptions;

    public EventDeliveryOptions getOptions() {
        return mOptions;
    }

    public void setOptions(EventDeliveryOptions options) {
        mOptions = options;
    }

    public EventDelivery(Context context) {
        mContext = Objects.notNull(context);
        mManager = Objects.notNull(CustomService.get(context, FragmentManager.class));
    }

    @Override
    protected boolean performOnNewEvent(int eventId, Bundle event) {
        Fragment fragment = mManager.findFragmentByTag(mOptions.tag);
        if (fragment != null) {
            if (SNAPSHOT) {
                Log.v(LOG_TAG, "performOnNewEvent: " + getEventName(eventId) + " delivering to " + fragment + debugThis());
            }

            if (!fragment.isVisible()) {
                mOptions.performTransaction(mManager, fragment);
            }

            if (fragment instanceof ContextOwner) {
                return EventBus.send(((ContextOwner) fragment).getContext(), eventId, event);
            } else {
                if (SNAPSHOT) {
                    Log.v(LOG_TAG, "performOnNewEvent: " + EventBus.getEventName(eventId) + ", "
                            + fragment + " should extends android.ext.app.FragmentExt" + debugThis());
                }
            }

            return false;
        } else {
            if (SNAPSHOT) {
                Log.v(LOG_TAG, "performOnNewEvent: " + getEventName(eventId) + " instantiating of " + mOptions.fragment + debugThis());
            }

            mOptions.performTransaction(mManager, Fragment.instantiate(mContext, mOptions.fragment, EventBus.prepare(eventId, event)));
            return true;
        }
    }

    @Override
    public void inflate(Context context, XmlPullParser parser, AttributeSet attrs) {
        setOptions(new EventDeliveryOptions(context, attrs));
    }
}
