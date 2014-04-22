package android.ext.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.ext.collections.Factory;
import android.ext.core.AbstractInflater;
import android.ext.core.Objects;
import android.ext.core.Strings;
import android.util.AttributeSet;

import org.xmlpull.v1.XmlPullParserException;

import java.util.HashMap;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class ViewBinderInflater extends AbstractInflater<ViewBinder, CompositeViewBinder> {
    private static final ViewBinderInflater sInstance;

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

        mFactories.put(CompositeViewBinder.class.getSimpleName(), new Factory<ViewBinder>() {
            @Override
            public ViewBinder create() {
                return new CompositeViewBinder();
            }
        });

        mFactories.put(TextViewBinder.class.getSimpleName(), new Factory<ViewBinder>() {
            @Override
            public ViewBinder create() {
                return new TextViewBinder();
            }
        });

        mFactories.put(CompoundButtonBinder.class.getSimpleName(), new Factory<ViewBinder>() {
            @Override
            public ViewBinder create() {
                return new CompoundButtonBinder();
            }
        });

        mFactories.put(RatingBarBinder.class.getSimpleName(), new Factory<ViewBinder>() {
            @Override
            public ViewBinder create() {
                return new RatingBarBinder();
            }
        });
    }

    @Override
    protected ViewBinder createFromTag(Context context, String tagName, CompositeViewBinder parent, AttributeSet attrs) throws XmlPullParserException {
        ViewBinder result;

        Factory<ViewBinder> factory = mFactories.get(tagName);
        if (factory != null) {
            result = Objects.notNull(factory.create());
        } else {
            if (ViewBinder.class.getSimpleName().equals(tagName)) {
                result = createCustomBinder(context, attrs);
            } else {
                throw new XmlPullParserException(tagName);
            }
        }

        if (result instanceof ExtractorViewBinder) {
            ViewBinderExtractor extractor = createExtractor(context, attrs);
            ((ExtractorViewBinder) result).setExtractor(extractor);
        }

        if (parent != null) {
            TypedArray a = context.getResources().obtainAttributes(attrs, R.styleable.ViewBinder);
            if (a != null) {
                try {
                    int id = a.getResourceId(R.styleable.ViewBinder_viewId, 0);
                    if (id == 0) {
                        throw new MissedAttributeException(context, R.styleable.ViewBinder, R.styleable.ViewBinder_viewId);
                    }

                    parent.add(id, result);
                } finally {
                    a.recycle();
                }
            }
        }

        return result;
    }

    private ViewBinder createCustomBinder(Context context, AttributeSet attrs) throws XmlPullParserException {
        TypedArray a = context.getResources().obtainAttributes(attrs, R.styleable.ViewBinder);
        if (a != null) {
            try {
                String binder = a.getString(R.styleable.ViewBinder_binderClass);
                if (!Strings.isEmpty(binder)) {
                    return (ViewBinder) newInstanceByClassName(fullClassName(context, binder));
                }
            } finally {
                a.recycle();
            }
        }

        throw new MissedAttributeException(context, R.styleable.ViewBinder, R.styleable.ViewBinder_binderClass);
    }

    private ViewBinderExtractor createExtractor(Context context, AttributeSet attrs) throws XmlPullParserException {
        TypedArray a = context.getResources().obtainAttributes(attrs, R.styleable.ViewBinder);
        if (a != null) {
            try {
                String extractor = a.getString(R.styleable.ViewBinder_extractorClass);
                if (!Strings.isEmpty(extractor)) {
                    return (ViewBinderExtractor) newInstanceByClassName(fullClassName(context, extractor));
                }

                String method = a.getString(R.styleable.ViewBinder_extractorMethod);
                if (!Strings.isEmpty(method)) {
                    return new ByMethodExtractor(method);
                }
            } finally {
                a.recycle();
            }
        }

        throw new MissedAttributeException(context, R.styleable.ViewBinder, R.styleable.ViewBinder_extractorClass, R.styleable.ViewBinder_extractorMethod);
    }
}
