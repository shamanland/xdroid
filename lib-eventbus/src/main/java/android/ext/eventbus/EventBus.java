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
        if (event == null) {
            if (SNAPSHOT) {
                Log.w(LOG_TAG, "send: event is null");
            }

            return false;
        }

        if (!(event.get(EVENT_ID) instanceof Integer)) {
            if (SNAPSHOT) {
                Log.w(LOG_TAG, "send: wrong id type: " + event.get(EVENT_ID));
            }

            return false;
        }

        EventDispatcher dispatcher = CustomService.get(context, EventDispatcher.class);
        if (dispatcher == null) {
            if (SNAPSHOT) {
                Log.v(LOG_TAG, "send: dispatcher not found: " + context);
            }

            return false;
        }

        return dispatcher.onNewEvent(event);
    }
}
