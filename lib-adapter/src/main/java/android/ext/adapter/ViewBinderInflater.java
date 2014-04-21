package android.ext.adapter;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.ext.collections.Factory;
import android.ext.core.AbstractInflater;
import android.util.AttributeSet;

import org.xmlpull.v1.XmlPullParserException;

import java.util.HashMap;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class ViewBinderInflater extends AbstractInflater<ViewBinder, CompositeViewBinder> {
    private static final ViewBinderInflater sInstance;

    private static final String TAG_COMPOSITE = "CompositeViewBinder";
    private static final String TAG_TEXT_VIEW = "TextViewBinder";
    private static final String TAG_COMPOUND_BUTTON = "CompoundButtonBinder";

    private final HashMap<String, Factory<ViewBinder>> mFactories;

    static {
        sInstance = new ViewBinderInflater();
    }

    public static ViewBinderInflater getInstance() {
        return sInstance;
    }

    protected ViewBinderInflater() {
        super(CompositeViewBinder.class);

        mFactories = new HashMap<String, Factory<ViewBinder>>();

        mFactories.put(TAG_COMPOSITE, new Factory<ViewBinder>() {
            @Override
            public ViewBinder create() {
                return new CompositeViewBinder();
            }
        });

        mFactories.put(TAG_TEXT_VIEW, new Factory<ViewBinder>() {
            @Override
            public ViewBinder create() {
                return new TextViewBinder();
            }
        });

        mFactories.put(TAG_COMPOUND_BUTTON, new Factory<ViewBinder>() {
            @Override
            public ViewBinder create() {
                return new CompoundButtonBinder();
            }
        });
    }

    @Override
    protected ViewBinder createFromTag(Resources resources, String tagName, CompositeViewBinder parent, AttributeSet attrs) throws XmlPullParserException {
        final Factory<ViewBinder> factory = mFactories.get(tagName);
        if (factory == null) {
            throw new XmlPullParserException(tagName);
        }

        final ViewBinder result = factory.create();

        if (result instanceof ExtractorViewBinder) {
            ViewBinderExtractor extractor = createExtractor(resources, attrs);
            ((ExtractorViewBinder) result).setExtractor(extractor);
        }

        if (parent != null) {
            TypedArray a = resources.obtainAttributes(attrs, R.styleable.ViewBinder);
            if (a != null) {
                try {
                    int id = a.getResourceId(R.styleable.ViewBinder_id, 0);
                    if (id == 0) {
                        throw new XmlPullParserException(resources.getResourceName(R.styleable.ViewBinder_id));
                    }

                    parent.add(id, result);
                } finally {
                    a.recycle();
                }
            }
        }

        return result;
    }

    private ViewBinderExtractor createExtractor(Resources resources, AttributeSet attrs) {
        // TODO
        return null;
    }
}
