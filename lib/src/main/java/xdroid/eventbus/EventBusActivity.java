package xdroid.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import xdroid.app.ActivityExt;

public class EventBusActivity extends ActivityExt implements EventDispatcherOwner {
    @Override
    public int getEventDispatcherXmlId() {
        return 0;
    }

    @Override
    public boolean allowKeepLastEvent() {
        return false;
    }

    @Override
    public boolean raiseInitialEventWhenReCreating() {
        return true;
    }

    @Override
    public Bundle extractInitialEvent() {
        return EventBus.extract(getIntent());
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        EventDispatcherHelper.onCreate(this, state);
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        EventDispatcherHelper.onSaveInstanceState(this, state);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (EventBus.onActivityResult(getContext(), data)) {
            return;
        }

        if (resultCode == Activity.RESULT_CANCELED) {
            EventDispatcherHelper.resetLastEvent(this);
        }
    }
}
