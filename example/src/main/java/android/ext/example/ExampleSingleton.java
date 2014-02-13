package android.ext.example;

import android.content.Context;

public class ExampleSingleton {
    private String mText;

    public String getText() {
        return mText;
    }

    public ExampleSingleton(Context context, int stringId) {
        mText = context.getString(stringId);
    }
}
