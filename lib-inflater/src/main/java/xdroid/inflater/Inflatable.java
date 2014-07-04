package xdroid.inflater;

import android.content.Context;
import android.util.AttributeSet;

import org.xmlpull.v1.XmlPullParser;

public interface Inflatable {
    void inflate(Context context, XmlPullParser parser, AttributeSet attrs);
}
