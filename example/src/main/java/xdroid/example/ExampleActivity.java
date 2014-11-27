package xdroid.example;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import xdroid.app.ActivityExt;
import xdroid.eventbus.EventBus;
import xdroid.eventbus.EventDispatcher;
import xdroid.eventbus.EventDispatcherInflater;

public class ExampleActivity extends ActivityExt {
    @Override
    protected void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.a_example);

        if (state == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ExampleListFragment())
                    .commit();
        }

        putCustomService(EventDispatcher.class.getName(), EventDispatcherInflater.getInstance().inflate(getContext(), R.xml.ed_example));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, Menu.NONE, "EventBus");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                return EventBus.send(getContext(), R.id.ev_show_in_first);
        }

        return super.onOptionsItemSelected(item);
    }
}
