package android.ext.core;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.InflateException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public abstract class AbstractInflater<T, C extends T> {
    private final Class<C> mCompositeClazz;

    protected abstract T createFromTag(Resources resources, String tagName, C parent, AttributeSet attrs) throws XmlPullParserException;

    protected AbstractInflater(Class<C> compositeClazz) {
        mCompositeClazz = Objects.notNull(compositeClazz);
    }

    public T inflate(Resources resources, int xmlId) {
        XmlResourceParser parser = resources.getXml(xmlId);
        if (parser == null) {
            throw new InflateException(String.valueOf(xmlId));
        }

        try {
            int type;
            while ((type = parser.next()) != XmlPullParser.START_TAG && type != XmlPullParser.END_DOCUMENT) {
                // NOTE looking for the root node
            }

            if (type != XmlPullParser.START_TAG) {
                throw new InflateException(parser.getPositionDescription());
            }

            T result = Objects.notNull(createFromTag(resources, parser.getName(), null, parser));

            if (mCompositeClazz.isInstance(result)) {
                inflateRec(resources, parser, mCompositeClazz.cast(result), parser);
            }

            return result;
        } catch (XmlPullParserException ex) {
            throw new InflateException(parser.getPositionDescription(), ex);
        } catch (IOException ex) {
            throw new InflateException(parser.getPositionDescription(), ex);
        }
    }

    private void inflateRec(Resources resources, XmlPullParser parser, C parent, AttributeSet attrs) throws XmlPullParserException, IOException {
        final int depth = parser.getDepth();
        int type;

        while (((type = parser.next()) != XmlPullParser.END_TAG || parser.getDepth() > depth) && type != XmlPullParser.END_DOCUMENT) {
            if (type != XmlPullParser.START_TAG) {
                continue;
            }

            T item = Objects.notNull(createFromTag(resources, parser.getName(), parent, attrs));

            if (mCompositeClazz.isInstance(item)) {
                inflateRec(resources, parser, mCompositeClazz.cast(item), attrs);
            }
        }
    }
}
