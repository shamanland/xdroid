package android.ext.eventbus;

import android.ext.core.Objects;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;

import static android.ext.eventbus.BuildConfig.SNAPSHOT;
import static android.ext.eventbus.EventBus.getEventName;

public class EventTransmitter implements EventDispatcher {
    private static final String LOG_TAG = EventTransmitter.class.getSimpleName();

    private SparseArray<EventDispatcher> mTargets;

    public EventTransmitter() {
        mTargets = new SparseArray<EventDispatcher>();
    }

    public void put(int eventId, EventDispatcher target) {
        mTargets.put(eventId, Objects.notNull(target));
    }

    @Override
    public boolean onNewEvent(int eventId, Bundle event) {
        EventDispatcher target = mTargets.get(eventId);
        if (target != null) {
            if (SNAPSHOT) {
                Log.v(LOG_TAG, "onNewEvent: " + getEventName(eventId) + " is handling by " + target);
            }

            return target.onNewEvent(eventId, event);
        }

        if (SNAPSHOT) {
            Log.w(LOG_TAG, "onNewEvent: " + getEventName(eventId) + " is unhandled");
        }

        return false;
    }
}
