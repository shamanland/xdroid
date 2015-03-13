package xdroid.example;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.TextView;

import xdroid.adapter.ViewBinder;
import xdroid.viewholder.ViewHolder;

public class ExampleBinder implements ViewBinder<ExampleData, View> {
    @Override
    public void onNewView(int position, View view) {
        // none
    }

    @Override
    public void onNewData(int position, View view, ExampleData data) {
        ViewHolder holder = ViewHolder.obtain(view);

        TextView text1 = holder.get(android.R.id.text1);
        if (text1 != null) {
            text1.setText(data.getTitle());
        }

        TextView text2 = holder.get(android.R.id.text2);
        if (text2 != null) {
            text2.setText(data.getSubTitle());
        }

        CompoundButton added = holder.get(R.id.added);
        if (added != null) {
            added.setChecked(data.isAdded());
        }

        RatingBar progress = holder.get(android.R.id.progress);
        if (progress != null) {
            progress.setRating((Math.abs(data.hashCode()) % 5) + 1);
        }
    }
}
