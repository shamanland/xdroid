package android.ext.core;

import android.content.res.Resources;
import android.util.AttributeSet;

import org.xmlpull.v1.XmlPullParser;

public interface Inflatable {
    void inflate(Resources r, XmlPullParser parser, AttributeSet attrs);
}
