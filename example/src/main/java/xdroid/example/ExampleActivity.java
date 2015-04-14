package xdroid.example;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import xdroid.eventbus.EventBus;
import xdroid.eventbus.EventBusActivity;

public class ExampleActivity extends EventBusActivity {
    @Override
    public int getEventDispatcherXmlId() {
        return R.xml.ed_example;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.a_example);

        if (state == null) {
            getFm().beginTransaction()
                    .add(R.id.container, new ExampleListFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, Menu.NONE, "EventBus");
        menu.add(Menu.NONE, 1, Menu.NONE, "EnumFormat");
        menu.add(Menu.NONE, 99, Menu.NONE, "Settings");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                return EventBus.send(getContext(), R.id.ev_brief);
            case 1:
                showDialog(ExampleEnumFormatDialog.class, "enum.format", null);
                return true;
            case 99:
                return EventBus.send(getContext(), R.id.ev_settings);
        }

        return super.onOptionsItemSelected(item);
    }
}
