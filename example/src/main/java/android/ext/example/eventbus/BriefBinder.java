package android.ext.example.eventbus;

import android.ext.adapter.ViewBinder;
import android.view.View;
import android.widget.TextView;

public class BriefBinder implements ViewBinder<DataItem, View> {
    @Override
    public void onNewView(int position, View view) {
        // empty
    }

    @Override
    public void onNewData(int position, View view, DataItem data) {
        ViewHolder holder = ViewHolder.obtain(view);
        holder.text1.setText(data.getTitle());
        holder.text2.setText(data.getDescription());
    }

    static class ViewHolder {
        TextView text1;
        TextView text2;

        private ViewHolder(View view) {
            text1 = (TextView) view.findViewById(android.R.id.text1);
            text2 = (TextView) view.findViewById(android.R.id.text2);
        }

        static ViewHolder obtain(View view) {
            Object tag = view.getTag();
            if (tag instanceof ViewHolder) {
                return (ViewHolder) tag;
            }

            ViewHolder result = new ViewHolder(view);
            view.setTag(result);
            return result;
        }
    }
}
