package android.ext.eventbus;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.ext.core.ActivityStarter;
import android.ext.core.ContextActivityStarter;
import android.ext.core.Objects;
import android.ext.customservice.CustomService;
import android.ext.inflater.Inflatable;
import android.os.Bundle;
import android.util.AttributeSet;

import org.xmlpull.v1.XmlPullParser;

public class EventForwarder implements EventDispatcher, Inflatable {
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
        mContext = Objects.notNull(context);

        ActivityStarter starter = CustomService.get(mContext, ActivityStarter.class);
        mStarter = starter != null ? starter : new ContextActivityStarter(mContext);
    }

    @Override
    public boolean onNewEvent(int eventId, Bundle event) {
        Class<?> target;

        try {
            target = Class.forName(mOptions.activity);
        } catch (ClassNotFoundException ex) {
            throw new ActivityNotFoundException(mOptions.activity);
        }

        Intent intent = new Intent(mContext, target);
        intent.putExtra(EventBus.INTENT_EXTRA_EVENT, event);

        if (mOptions.forResult) {
            mStarter.startActivityForResult(intent, mOptions.requestCode);
        } else {
            mStarter.startActivity(intent);
        }

        return true;
    }

    @Override
    public void inflate(Context context, XmlPullParser parser, AttributeSet attrs) {
        setOptions(new EventForwarderOptions(context, attrs));
    }
}
