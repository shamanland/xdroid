package xdroid.example.eventbus;

import xdroid.eventbus.EventBusActivity;
import xdroid.example.R;
import android.os.Bundle;

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
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.a_eventbus);
    }
}
