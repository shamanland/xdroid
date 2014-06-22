package android.ext.example.eventbus;

import android.ext.eventbus.EventBusActivity;
import android.ext.example.R;
import android.os.Bundle;

public class FirstActivity extends EventBusActivity {
    @Override
    public int getEventDispatcherXmlId() {
        return R.xml.ed_first;
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.a_eventbus);
    }
}
