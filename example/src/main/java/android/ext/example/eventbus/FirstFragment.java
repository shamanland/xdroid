package android.ext.example.eventbus;

import android.ext.app.FragmentExt;
import android.ext.eventbus.EventBus;
import android.ext.example.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FirstFragment extends FragmentExt {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        return inflater.inflate(R.layout.f_first, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle state) {
        super.onViewCreated(view, state);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.send(getContext(), R.id.ev_show_in_second);
            }
        });
    }
}
