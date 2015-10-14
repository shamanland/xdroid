package xdroid.example;

import java.io.Serializable;

public class ExampleData implements Serializable {
    private String mTitle;
    private String mSubTitle;
    private boolean mAdded;

    public ExampleData() {
        super();
    }

    public ExampleData(String title, String subTitle) {
        mTitle = title;
        mSubTitle = subTitle;
    }

    public ExampleData(String title, String subTitle, boolean added) {
        mTitle = title;
        mSubTitle = subTitle;
        mAdded = added;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public void setSubTitle(String subTitle) {
        mSubTitle = subTitle;
    }

    public boolean isAdded() {
        return mAdded;
    }

    public void setAdded(boolean added) {
        mAdded = added;
    }
}
