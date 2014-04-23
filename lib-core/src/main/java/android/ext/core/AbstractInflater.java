package android.ext.core;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.InflateException;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;

import static android.ext.core.BuildConfig.DEBUG;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public abstract class AbstractInflater<T, C extends T> {
    private static final String LOG_TAG = AbstractInflater.class.getSimpleName();

    public static final char DOT = '.';

    private final Class<C> mCompositeClazz;
    private final HashMap<String, Class<?>> mClassesCache;

    protected static String fullClassName(Context context, String className) {
        if (className.charAt(0) == DOT) {
            return context.getPackageName() + className;
        }

        return className;
    }

    protected abstract T createFromTag(Context context, String tagName, C parent, AttributeSet attrs) throws XmlPullParserException;

    protected AbstractInflater(Class<C> compositeClazz) {
        mCompositeClazz = Objects.notNull(compositeClazz);
        mClassesCache = new HashMap<String, Class<?>>();
    }

    protected Object newInstanceByClassName(String className) {
        Class clazz = mClassesCache.get(className);
        if (clazz == null) {
            try {
                clazz = Class.forName(className);
                mClassesCache.put(className, clazz);
            } catch (Throwable ex) {
                throw new IllegalStateException(ex);
            }
        }

        try {
            return clazz.newInstance();
        } catch (Throwable ex) {
            throw new IllegalStateException(ex);
        }
    }

    public void clearCache() {
        mClassesCache.clear();
    }

    public T inflate(Context context, int xmlId) {
        XmlResourceParser parser = context.getResources().getXml(xmlId);
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

            T result = Objects.notNull(createFromTag(context, parser.getName(), null, parser));

            if (mCompositeClazz.isInstance(result)) {
                inflateRec(context, parser, mCompositeClazz.cast(result), parser);
            }

            return result;
        } catch (XmlPullParserException ex) {
            throw new InflateException(parser.getPositionDescription(), ex);
        } catch (IOException ex) {
            throw new InflateException(parser.getPositionDescription(), ex);
        }
    }

    private void inflateRec(Context context, XmlPullParser parser, C parent, AttributeSet attrs) throws XmlPullParserException, IOException {
        final int depth = parser.getDepth();
        int type;

        while (((type = parser.next()) != XmlPullParser.END_TAG || parser.getDepth() > depth) && type != XmlPullParser.END_DOCUMENT) {
            if (type != XmlPullParser.START_TAG) {
                continue;
            }

            T item = Objects.notNull(createFromTag(context, parser.getName(), parent, attrs));

            if (mCompositeClazz.isInstance(item)) {
                inflateRec(context, parser, mCompositeClazz.cast(item), attrs);
            }
        }
    }

    public static class MissedAttributeException extends XmlPullParserException {
        public MissedAttributeException(Context context, int[] styleableArray, int... attrIndices) {
            super(createMessage(context, styleableArray, attrIndices));
        }

        public static String createMessage(Context context, int[] styleableArray, int... attrIndices) {
            if (DEBUG) {
                if (ArrayUtils.isEmpty(attrIndices)) {
                    return null;
                }

                Resources resources = context.getResources();
                StringBuilder sb = new StringBuilder();

                for (int i : attrIndices) {
                    sb.append(resources.getResourceName(styleableArray[i]));
                    sb.append(", ");
                }

                sb.delete(sb.length() - 2, sb.length());
                return sb.toString();
            }

            return null;
        }
    }
}
