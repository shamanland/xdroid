package android.ext.example.eventbus;

import android.ext.eventbus.EventBusFragment;
import android.ext.example.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SecondFragment extends EventBusFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        return inflater.inflate(R.layout.f_second, container, false);
    }
}
