package xdroid.eventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;

import xdroid.core.ActivityStarter;
import xdroid.core.ContextActivityStarter;
import xdroid.customservice.CustomService;
import xdroid.inflater.Inflatable;

import static xdroid.core.ObjectUtils.notNull;
import static xdroid.eventbus.BuildConfig.SNAPSHOT;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class EventForwarder extends DefaultEventDispatcher implements Inflatable {
    private static final String LOG_TAG = EventForwarder.class.getSimpleName();

    private final Context mContext;
    private final ActivityStarter mStarter;
    private EventForwarderOptions mOptions;

    public EventForwarderOptions getOptions() {
        return mOptions;
    }

    public void setOptions(EventForwarderOptions options) {
        mOptions = options;
    }

    public EventForwarder(Context context) {
        mContext = notNull(context);

        ActivityStarter starter = CustomService.get(mContext, ActivityStarter.class);
        mStarter = starter != null ? starter : new ContextActivityStarter(mContext);
    }

    @Override
    public boolean performOnNewEvent(int eventId, Bundle event) {
        Class<?> target;

        try {
            target = Class.forName(mOptions.activity);
        } catch (ClassNotFoundException ex) {
            if (SNAPSHOT) {
                Log.wtf(LOG_TAG, "performOnNewEvent: " + EventBus.getEventName(eventId) + debugThis(), ex);
            }

            return false;
        }

        Intent intent = new Intent(mContext, target);
        intent.putExtra(EventBus.INTENT_EXTRA_EVENT, EventBus.prepare(eventId, event));

        if (mOptions.forResult) {
            if (SNAPSHOT) {
                Log.v(LOG_TAG, "performOnNewEvent: " + EventBus.getEventName(eventId) + ", startActivityForResult: " + intent + ", " + mOptions.requestCode + debugThis());
            }

            mStarter.startActivityForResult(intent, mOptions.requestCode);
        } else {
            if (SNAPSHOT) {
                Log.v(LOG_TAG, "performOnNewEvent: " + EventBus.getEventName(eventId) + ", startActivity: " + intent + debugThis());
            }

            mStarter.startActivity(intent);
        }

        return true;
    }

    @Override
    public void inflate(Context context, XmlPullParser parser, AttributeSet attrs) {
        setOptions(new EventForwarderOptions(context, attrs));
    }
}
