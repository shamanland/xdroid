package xdroid.eventbus;

import android.os.Bundle;
import android.util.Log;

import static xdroid.eventbus.BuildConfig.SNAPSHOT;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public abstract class DefaultEventDispatcher implements EventDispatcher, Internal {
    private static final String LOG_TAG = DefaultEventDispatcher.class.getSimpleName();

    private int mHandling;

    protected abstract boolean performOnNewEvent(int eventId, Bundle event);

    protected abstract boolean isForwarder(int eventId, Bundle event);

    @Override
    public final boolean onNewEvent(int eventId, Bundle event) {
        if (mHandling == eventId) {
            if (SNAPSHOT) {
                Log.e(LOG_TAG, "onNewEvent: " + EventBus.getEventName(eventId) + " is reflectively passed again" + debugThis());
            }

            return false;
        }

        try {
            mHandling = eventId;

            if (eventId == HELPER_EVENT_IS_FORWARDER) {
                return isForwarder(eventId, event);
            }

            return performOnNewEvent(eventId, event);
        } finally {
            mHandling = 0;
        }
    }

    protected String debugThis() {
        if (SNAPSHOT) {
            return ", this: @" + Integer.toHexString(System.identityHashCode(this));
        }

        return null;
    }

    protected String debugToStringTail() {
        if (SNAPSHOT) {
            return Integer.toHexString(System.identityHashCode(this));
        }

        return null;
    }

    @Override
    public String toString() {
        if (SNAPSHOT) {
            return getClass().getSimpleName() + '@' + debugToStringTail();
        }

        return super.toString();
    }
}
