package android.ext.example;

import android.ext.adapter.NullBinder;
import android.os.Parcel;
import android.widget.TextView;

public class ExampleTitleBinder extends NullBinder<ExampleData, TextView> {
    private static final ExampleTitleBinder sInstance;

    static {
        sInstance = new ExampleTitleBinder();
    }

    public static ExampleTitleBinder getInstance() {
        return sInstance;
    }

    private ExampleTitleBinder() {
        // disallow public access
    }

    @Override
    public void onNewData(TextView view, ExampleData data) {
        view.setText(data.getTitle());
    }

    public static final Creator<ExampleTitleBinder> CREATOR = new Creator<ExampleTitleBinder>() {
        public ExampleTitleBinder createFromParcel(Parcel in) {
            return ExampleTitleBinder.getInstance();
        }

        public ExampleTitleBinder[] newArray(int size) {
            return new ExampleTitleBinder[size];
        }
    };
}
