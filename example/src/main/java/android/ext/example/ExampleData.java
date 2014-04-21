package android.ext.example;

import java.io.Serializable;

public class ExampleData implements Serializable {
    private String mTitle;
    private String mSubTitle;

    public ExampleData() {
        super();
    }

    public ExampleData(String title, String subTitle) {
        mTitle = title;
        mSubTitle = subTitle;
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
}
