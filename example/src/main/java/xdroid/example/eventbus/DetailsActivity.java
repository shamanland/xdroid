package xdroid.example.eventbus;

import xdroid.eventbus.EventBusActivity;
import xdroid.example.R;
import android.os.Bundle;

public class DetailsActivity extends EventBusActivity {
    @Override
    public int getEventDispatcherXmlId() {
        return R.xml.ed_details;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.a_eventbus);
    }
}
