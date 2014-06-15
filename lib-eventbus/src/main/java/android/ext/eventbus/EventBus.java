package android.ext.eventbus;

import android.content.Context;
import android.content.Intent;
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

    public static Bundle prepare(int eventId) {
        return prepare(eventId, null);
    }

    public static Bundle prepare(int eventId, Bundle args) {
        if (args == null || args == Bundle.EMPTY) {
            args = new Bundle();
        }

        if (SNAPSHOT) {
            if (args.containsKey(EVENT_ID)) {
                Log.w(LOG_TAG, "prepare: args already has EVENT_ID: " + args.get(EVENT_ID));
            }
        }

        args.putInt(EVENT_ID, eventId);
        return args;
    }

    public static Bundle extract(Intent intent) {
        if (intent == null) {
            if (SNAPSHOT) {
                Log.w(LOG_TAG, "extract: intent is null");
            }

            return null;
        }

        return extract(intent.getExtras());
    }

    public static Bundle extract(Bundle bundle) {
        if (bundle == null) {
            if (SNAPSHOT) {
                Log.w(LOG_TAG, "extract: bundle is null");
            }

            return null;
        }

        Object eventId = bundle.get(EVENT_ID);
        if (!(eventId instanceof Integer)) {
            if (SNAPSHOT) {
                Log.w(LOG_TAG, "extract: wrong id type: " + bundle.get(EVENT_ID));
            }

            return null;
        }

        return bundle;
    }

    public static boolean send(Context context, Bundle event) {
        if (event == null) {
            if (SNAPSHOT) {
                Log.w(LOG_TAG, "send: event is null");
            }

            return false;
        }

        Object eventId = event.get(EVENT_ID);
        if (!(eventId instanceof Integer)) {
            if (SNAPSHOT) {
                Log.w(LOG_TAG, "send: wrong id type: " + event.get(EVENT_ID));
            }

            return false;
        }

        return send(context, (Integer) eventId, event);
    }

    public static boolean send(Context context, int eventId) {
        return send(context, eventId, null);
    }

    public static boolean send(Context context, int eventId, Bundle args) {
        EventDispatcher dispatcher = CustomService.get(context, EventDispatcher.class);
        if (dispatcher == null) {
            if (SNAPSHOT) {
                Log.v(LOG_TAG, "send: dispatcher not found in: " + context);
            }

            return false;
        }

        return dispatcher.onNewEvent(eventId, args != null ? args : Bundle.EMPTY);
    }
}
