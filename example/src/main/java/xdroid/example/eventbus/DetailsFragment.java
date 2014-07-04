package xdroid.example.eventbus;

import xdroid.eventbus.EventBus;
import xdroid.eventbus.EventBusFragment;
import xdroid.eventbus.EventDispatcher;
import xdroid.example.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailsFragment extends EventBusFragment {
    private TextView mTitleView;
    private TextView mDescriptionView;
    private TextView mTextView;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        putCustomService(EventDispatcher.class.getName(), new EventDispatcher() {
            @Override
            public boolean onNewEvent(int eventId, Bundle event) {
                switch (eventId) {
                    case R.id.ev_show_in_second:
                        onNewData((DataItem) event.getParcelable("data"));
                        return true;
                }

                return false;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        return inflater.inflate(R.layout.f_second, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle state) {
        super.onViewCreated(view, state);

        mTitleView = (TextView) view.findViewById(android.R.id.text1);
        mDescriptionView = (TextView) view.findViewById(android.R.id.text2);
        mTextView = (TextView) view.findViewById(R.id.text);

        EventBus.send(getContext(), extractInitialEvent());
    }

    protected void onNewData(DataItem data) {
        mTitleView.setText(data.getTitle());
        mDescriptionView.setText(data.getDescription());
        mTextView.setText(data.getText());
    }
}
