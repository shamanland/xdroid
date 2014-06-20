package android.ext.eventbus;

import android.os.Bundle;
import android.util.Log;

import static android.ext.eventbus.BuildConfig.SNAPSHOT;
import static android.ext.eventbus.EventBus.getEventName;

public abstract class DefaultEventDispatcher implements EventDispatcher {
    private static final String LOG_TAG = DefaultEventDispatcher.class.getSimpleName();

    private int mHandling;

    protected abstract boolean performOnNewEvent(int eventId, Bundle event);

    @Override
    public final boolean onNewEvent(int eventId, Bundle event) {
        if (mHandling == eventId) {
            if (SNAPSHOT) {
                Log.e(LOG_TAG, "onNewEvent: " + getEventName(eventId) + " is reflectively passed again" + debugThis());
            }

            return false;
        }

        try {
            mHandling = eventId;
            return performOnNewEvent(eventId, event);
        } finally {
            mHandling = 0;
        }
    }

    protected String debugThis() {
        if (SNAPSHOT) {
            return ", this: " + this;
        } else {
            return null;
        }
    }
}
