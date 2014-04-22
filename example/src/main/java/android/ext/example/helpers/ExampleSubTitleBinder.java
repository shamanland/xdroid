package android.ext.example.helpers;

import android.ext.adapter.ViewBinder;
import android.ext.example.ExampleData;
import android.widget.TextView;

import java.io.Serializable;

public class ExampleSubTitleBinder implements ViewBinder<ExampleData, TextView>, Serializable {
    @Override
    public void onNewView(TextView view) {
        // empty
    }

    @Override
    public void onNewData(TextView view, ExampleData data) {
        if (data == null) {
            view.setText(null);
        } else {
            view.setText(">> " + data.getSubTitle());
        }
    }
}
