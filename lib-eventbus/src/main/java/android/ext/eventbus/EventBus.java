package android.ext.eventbus;

import android.content.Context;
import android.ext.customservice.CustomService;
import android.os.Bundle;
import android.util.Log;

import java.util.Locale;

import static android.ext.eventbus.BuildConfig.SNAPSHOT;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class EventBus {
    private static final String LOG_TAG = EventBus.class.getSimpleName();
    public static final String EVENT_ID = EventBus.class.getName().toLowerCase(Locale.UK) + ".id";

    private EventBus() {
        // disallow
    }

    public boolean send(Context context, Bundle event) {
        if (SNAPSHOT) {
            if (!event.containsKey(EVENT_ID)) {
                throw new IllegalArgumentException(String.valueOf(event));
            }

            if (event.getInt(EVENT_ID) == 0) {
                throw new IllegalArgumentException(String.valueOf(event.get(EVENT_ID)));
            }
        }

        EventDispatcher dispatcher = CustomService.get(context, EventDispatcher.class);
        if (dispatcher != null) {
            return dispatcher.onNewEvent(event);
        }

        if (SNAPSHOT) {
            Log.v(LOG_TAG, "send: dispatcher not found: " + context);
        }

        return false;
    }
}
