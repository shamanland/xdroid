package xdroid.example.eventbus;

import xdroid.core.BundleBuilder;
import xdroid.eventbus.EventBus;
import xdroid.eventbus.EventBusFragment;
import xdroid.example.R;
import xdroid.widget.ListViewExt;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.List;

public class BriefFragment extends EventBusFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        return inflater.inflate(R.layout.f_first, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle state) {
        super.onViewCreated(view, state);

        ListViewExt listView = (ListViewExt) view.findViewById(android.R.id.list);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataItem item = (DataItem) parent.getAdapter().getItem(position);
                EventBus.send(getContext(), R.id.ev_show_in_second, new BundleBuilder("data", item).get());
            }
        });

        if (state == null) {
            // NOTE this block generates stub data, you should require data from somewhere
            List<DataItem> list = listView.getRawAdapter().getDataList();
            for (int i = 1; i < 10; ++i) {
                list.add(new DataItem("Title " + i, "Description " + i, "Text " + i));
            }
        }
    }
}
