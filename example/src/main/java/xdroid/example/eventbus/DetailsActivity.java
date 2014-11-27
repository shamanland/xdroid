package xdroid.example.eventbus;

import android.os.Bundle;

import xdroid.eventbus.EventBusActivity;
import xdroid.example.R;

public class DetailsActivity extends EventBusActivity {
    @Override
    public int getEventDispatcherXmlId() {
        return R.xml.ed_details;
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.a_eventbus);
    }
}
