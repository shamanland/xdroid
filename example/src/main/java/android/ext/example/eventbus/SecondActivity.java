package android.ext.example.eventbus;

import android.ext.example.R;
import android.os.Bundle;

public class SecondActivity extends EventBusActivity {
    @Override
    protected int getEventDispatcherXmlId() {
        return R.xml.ed_second;
    }

    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.a_eventbus);
    }
}
