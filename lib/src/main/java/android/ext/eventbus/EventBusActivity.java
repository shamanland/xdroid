package android.ext.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.ext.app.ActivityExt;
import android.os.Bundle;

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
    public Bundle extractInitialEvent() {
        return EventBus.extract(getIntent());
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        EventDispatcherHelper.onCreate(this, state, allowKeepLastEvent());
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
