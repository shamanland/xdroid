package android.ext.example.helpers;

import android.ext.adapter.ViewBinderExtractor;
import android.ext.example.ExampleData;
import android.os.Parcel;

public class ExampleRatingExtractor implements ViewBinderExtractor<ExampleData, Float> {
    @Override
    public Float extract(ExampleData data) {
        if (data == null) {
            return null;
        }

        String title = "" + data.getTitle();
        String subTitle = "" + data.getSubTitle();

        int min = Math.min(title.length(), subTitle.length());
        int max = Math.max(title.length(), subTitle.length());

        return 5 * min / (float) max;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        // empty
    }

    public static final Creator<ExampleRatingExtractor> CREATOR = new Creator<ExampleRatingExtractor>() {
        public ExampleRatingExtractor createFromParcel(Parcel in) {
            return new ExampleRatingExtractor();
        }

        public ExampleRatingExtractor[] newArray(int size) {
            return new ExampleRatingExtractor[size];
        }
    };
}
