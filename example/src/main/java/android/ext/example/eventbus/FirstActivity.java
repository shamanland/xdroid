package android.ext.example.eventbus;

import android.ext.eventbus.EventBus;
import android.ext.eventbus.EventBusActivity;
import android.ext.eventbus.EventDispatcher;
import android.ext.example.R;
import android.os.Bundle;

public class FirstActivity extends EventBusActivity {
    private Bundle mLastEvent;

    @Override
    public int getEventDispatcherXmlId() {
        return R.xml.ed_first;
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.a_eventbus);

        final EventDispatcher base = (EventDispatcher) getCustomService(EventDispatcher.class.getName());
        putCustomService(EventDispatcher.class.getName(), new EventDispatcher() {
            @Override
            public boolean onNewEvent(int eventId, Bundle event) {
                mLastEvent = EventBus.prepare(eventId, event);
                return base.onNewEvent(eventId, event);
            }
        });

        if (state != null) {
            Bundle lastEvent = state.getBundle("last.event");
            if (lastEvent != null) {
                if (EventBus.getEventId(lastEvent) != EventBus.getEventId(mLastEvent)) {
                    EventBus.send(getContext(), lastEvent);
                }
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle state) {
        super.onSaveInstanceState(state);

        state.putBundle("last.event", mLastEvent);
    }
}
