package android.ext.example;

import android.ext.adapter.AdapterExt;
import android.ext.app.ActivityExt;
import android.ext.app.FragmentExt;
import android.ext.widget.ListViewExt;
import android.ext.widget.Toaster;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import static android.ext.core.Global.getSingleton;

public class MainActivity extends ActivityExt {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Toaster.show(getSingleton(ExampleSingleton.class).getText());
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends FragmentExt {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
            View result = inflater.inflate(R.layout.f_main, container, false);

            ListViewExt list = (ListViewExt) result.findViewById(android.R.id.list);

            if (state == null) {
                AdapterExt<ExampleData, View> adapter = list.getRawAdapter();
                List<ExampleData> data = adapter.getDataList();

                // FIXME remove this example
                try {
                    adapter.lockChanges();
                    data.add(new ExampleData("Hello", "The Best World", true));
                    data.add(new ExampleData("How are you?", "I'm fine", false));
                    data.add(new ExampleData("What about you?", "Not bad at all", true));
                    data.add(new ExampleData("Hello 2", "The Best World 2", true));
                    data.add(new ExampleData("How are you 2?", "I'm fine 2", false));
                    data.add(new ExampleData("What about you 2?", "Not bad at all 2", true));
                    data.add(new ExampleData("Hello 3", "The Best World 3", true));
                    data.add(new ExampleData("How are you 3?", "I'm fine 3", false));
                    data.add(new ExampleData("What about you 3?", "Not bad at all 3", true));
                } finally {
                    adapter.unlockChanges();
                    adapter.notifyDataSetChanged();
                }
            }

            return result;
        }
    }
}
