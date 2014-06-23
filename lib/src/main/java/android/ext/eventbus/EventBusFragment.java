package android.ext.eventbus;

import android.content.Intent;
import android.ext.app.FragmentExt;
import android.os.Bundle;

public class EventBusFragment extends FragmentExt implements EventDispatcherOwner {
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
        return EventBus.extract(getArguments());
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        EventDispatcherHelper.onCreate(this, state, allowKeepLastEvent());
    }

    @Override
    public void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        EventDispatcherHelper.onSaveInstanceState(this, state);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        EventBus.onActivityResult(getContext(), data);
    }
}
