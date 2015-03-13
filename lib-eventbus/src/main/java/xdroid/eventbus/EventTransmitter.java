package xdroid.eventbus;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import static xdroid.core.ObjectUtils.notNull;
import static xdroid.eventbus.BuildConfig.SNAPSHOT;
import static xdroid.eventbus.EventBus.getEventName;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class EventTransmitter extends DefaultEventDispatcher {
    private static final String LOG_TAG = EventTransmitter.class.getSimpleName();

    private final SparseArray<EventDispatcher> mTargets;

    public EventTransmitter() {
        mTargets = new SparseArray<>();
    }

    public void put(int eventId, EventDispatcher target) {
        mTargets.put(eventId, notNull(target));
    }

    @Override
    protected boolean performOnNewEvent(int eventId, Bundle event) {
        EventDispatcher target = mTargets.get(eventId);
        if (target != null) {
            if (SNAPSHOT) {
                Log.v(LOG_TAG, "performOnNewEvent: " + getEventName(eventId) + " is handling by " + target + debugThis());
            }

            return target.onNewEvent(eventId, event);
        }

        if (SNAPSHOT) {
            Log.w(LOG_TAG, "performOnNewEvent: " + getEventName(eventId) + " is unhandled" + debugThis());
        }

        return false;
    }
}
