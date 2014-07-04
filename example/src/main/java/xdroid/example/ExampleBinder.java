package xdroid.example;

import xdroid.adapter.ViewBinder;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RatingBar;
import android.widget.TextView;

public class ExampleBinder implements ViewBinder<ExampleData, View> {
    static class ViewHolder {
        TextView text1;
        TextView text2;
        CompoundButton added;
        RatingBar progress;
    }

    @Override
    public void onNewView(int position, View view) {
        ViewHolder holder = new ViewHolder();
        holder.text1 = (TextView) view.findViewById(android.R.id.text1);
        holder.text2 = (TextView) view.findViewById(android.R.id.text2);
        holder.added = (CompoundButton) view.findViewById(R.id.added);
        holder.progress = (RatingBar) view.findViewById(android.R.id.progress);
        view.setTag(holder);
    }

    @Override
    public void onNewData(int position, View view, ExampleData data) {
        ViewHolder holder = (ViewHolder) view.getTag();

        if (holder.text1 != null) {
            holder.text1.setText(data.getTitle());
        }

        if (holder.text2 != null) {
            holder.text2.setText(data.getSubTitle());
        }

        if (holder.added != null) {
            holder.added.setChecked(data.isAdded());
        }

        if (holder.progress != null) {
            holder.progress.setRating(3.5f);
        }
    }
}
