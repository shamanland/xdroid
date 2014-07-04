package xdroid.example;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

import xdroid.adapter.AdapterExt;
import xdroid.app.FragmentExt;
import xdroid.widget.ListViewExt;

import static xdroid.toaster.Toaster.toast;

public class ExampleListFragment extends FragmentExt {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        View result = inflater.inflate(R.layout.f_main, container, false);

        ListViewExt list = (ListViewExt) result.findViewById(android.R.id.list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ExampleData item = (ExampleData) parent.getAdapter().getItem(position);
                toast(item.getTitle());
            }
        });

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
