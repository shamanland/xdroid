package xdroid.example.eventbus;

import android.os.Bundle;

import xdroid.eventbus.EventBusActivity;
import xdroid.example.R;

public class BriefActivity extends EventBusActivity {
    @Override
    public int getEventDispatcherXmlId() {
        return R.xml.ed_brief;
    }

    @Override
    public boolean allowKeepLastEvent() {
        return true;
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.a_eventbus);
    }
}
