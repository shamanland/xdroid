package android.ext.example;

import android.ext.adapter.DefaultViewBinder;
import android.ext.adapter.TextExtractor;
import android.ext.adapter.TextViewBinder;
import android.ext.adapter.ViewBinder;
import android.ext.adapter.ViewBinderFactory;
import android.ext.core.ArrayUtils;
import android.view.View;
import android.widget.ProgressBar;

import java.io.Serializable;

public class ExampleBinderFactory implements ViewBinderFactory<ExampleData>, Serializable {
    @Override
    public ViewBinder<ExampleData, ? extends View> create(Integer id) {
        switch (id) {
            case android.R.id.text1:
                return new TextViewBinder<ExampleData>(id, new TextExtractor<ExampleData>() {
                    @Override
                    public CharSequence toText(ExampleData object) {
                        return object.getText1();
                    }
                });

            case android.R.id.progress:
                return new DefaultViewBinder<ExampleData, ProgressBar>() {
                    @Override
                    public void onNewData(ProgressBar view, ExampleData data) {
                        view.setProgress(data.getText2().length());
                    }
                };
        }

        throw new IllegalStateException();
    }

    @Override
    public int[] getIds() {
        return ArrayUtils.from(
                android.R.id.text1,
                android.R.id.progress
        );
    }
}
