package android.ext.eventbus;

import android.ext.core.Objects;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import static android.ext.eventbus.BuildConfig.SNAPSHOT;
import static android.ext.eventbus.EventBus.getEventName;

public class EventTransmitter extends DefaultEventDispatcher {
    private static final String LOG_TAG = EventTransmitter.class.getSimpleName();

    private final SparseArray<EventDispatcher> mTargets;

    public EventTransmitter() {
        mTargets = new SparseArray<EventDispatcher>();
    }

    public void put(int eventId, EventDispatcher target) {
        mTargets.put(eventId, Objects.notNull(target));
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
