package xdroid.eventbus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import xdroid.customservice.CustomService;

import static xdroid.eventbus.BuildConfig.SNAPSHOT;

public class EventFinish implements EventDispatcher {
    private static final String LOG_TAG = EventFinish.class.getSimpleName();

    private final Activity mActivity;

    public EventFinish(Context context) {
        mActivity = CustomService.get(context, Activity.class);
    }

    @Override
    public boolean onNewEvent(int eventId, Bundle event) {
        if (mActivity == null) {
            if (SNAPSHOT) {
                Log.v(LOG_TAG, "onNewEvent: " + EventBus.getEventName(eventId) + " no Activity");
            }

            return false;
        }

        if (SNAPSHOT) {
            Log.v(LOG_TAG, "onNewEvent: " + EventBus.getEventName(eventId) + " finish Activity: " + mActivity);
        }

        mActivity.setResult(Activity.RESULT_CANCELED, EventBus.prepare(new Intent(EventFinish.class.getName()), eventId, event));
        mActivity.finish();
        return true;
    }
}
