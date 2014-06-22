package android.ext.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.ext.app.FragmentExt;
import android.os.Bundle;

public class EventBusFragment extends FragmentExt implements EventDispatcherOwner {
    @Override
    public int getEventDispatcherXmlId() {
        return 0;
    }

    @Override
    public Bundle extractInitialEvent() {
        return EventBus.extract(getArguments());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        EventDispatcherHelper.init(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        EventBus.onActivityResult(getContext(), data);
    }
}
