package xdroid.example.eventbus;

import android.os.Parcel;
import android.os.Parcelable;

public class DataItem implements Parcelable {
    private String mTitle;
    private String mDescription;
    private String mText;

    public DataItem() {
        // empty
    }

    public DataItem(String title, String description, String text) {
        mTitle = title;
        mDescription = description;
        mText = text;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mTitle);
        out.writeString(mDescription);
        out.writeString(mText);
    }

    public static final Creator<DataItem> CREATOR = new Creator<DataItem>() {
        public DataItem createFromParcel(Parcel in) {
            return new DataItem(in);
        }

        public DataItem[] newArray(int size) {
            return new DataItem[size];
        }
    };

    private DataItem(Parcel in) {
        mTitle = in.readString();
        mDescription = in.readString();
        mText = in.readString();
    }
}
