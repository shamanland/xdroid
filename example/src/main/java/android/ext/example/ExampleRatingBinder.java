package android.ext.example;

import android.ext.adapter.NullBinder;
import android.os.Parcel;
import android.widget.ProgressBar;

public class ExampleRatingBinder extends NullBinder<ExampleData, ProgressBar> {
    private static final ExampleRatingBinder sInstance;

    static {
        sInstance = new ExampleRatingBinder();
    }

    public static ExampleRatingBinder getInstance() {
        return sInstance;
    }

    private ExampleRatingBinder() {
        // disallow public access
    }

    @Override
    public void onNewData(ProgressBar view, ExampleData data) {
        view.setProgress(data.getSubTitle().length());
    }

    public static final Creator<ExampleRatingBinder> CREATOR = new Creator<ExampleRatingBinder>() {
        public ExampleRatingBinder createFromParcel(Parcel in) {
            return ExampleRatingBinder.getInstance();
        }

        public ExampleRatingBinder[] newArray(int size) {
            return new ExampleRatingBinder[size];
        }
    };
}
