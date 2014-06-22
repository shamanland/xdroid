package android.ext.eventbus;

import android.content.Intent;
import android.ext.app.ActivityExt;
import android.os.Bundle;

public class EventBusActivity extends ActivityExt {
    protected int getEventDispatcherXmlId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);

        if (getEventDispatcherXmlId() != 0) {
            EventDispatcher dispatcher = EventDispatcherInflater.getInstance().inflate(getContext(), getEventDispatcherXmlId());
            putCustomService(EventDispatcher.class.getName(), dispatcher);

            Bundle event = EventBus.extract(getIntent());
            if (event != null) {
                EventBus.send(getContext(), event);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        EventBus.onActivityResult(getContext(), data);
    }
}
