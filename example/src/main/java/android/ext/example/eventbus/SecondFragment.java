package android.ext.example.eventbus;

import android.ext.app.FragmentExt;
import android.ext.example.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SecondFragment extends FragmentExt {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        return inflater.inflate(R.layout.f_second, container, false);
    }
}
