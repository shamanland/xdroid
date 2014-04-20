package android.ext.adapter;

import android.ext.core.ViewUtils;
import android.view.View;
import android.widget.TextView;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class TextViewBinder<D> implements ViewBinder<D, View> {
    private final int mTextViewId;
    private final TextExtractor<D> mTextExtractor;

    public static <D, V extends View> ViewBinder<D, V> getDefaultInstance() {
        return InstanceHolder.INSTANCE;
    }

    protected TextViewBinder() {
        this(android.R.id.text1, DefaultTextExtractor.<D>getInstance());
    }

    public TextViewBinder(int textViewId) {
        this(textViewId, DefaultTextExtractor.<D>getInstance());
    }

    public TextViewBinder(int textViewId, TextExtractor<D> textExtractor) {
        mTextViewId = textViewId;
        mTextExtractor = textExtractor != null ? textExtractor : DefaultTextExtractor.<D>getInstance();
    }

    @Override
    public void onNewView(View view) {
        final TextView textView;

        if (view instanceof TextView) {
            textView = (TextView) view;
        } else {
            View v = view.findViewById(mTextViewId);
            if (v instanceof TextView) {
                textView = (TextView) v;
            } else {
                textView = ViewUtils.findViewByClass(view, TextView.class);
            }
        }

        view.setTag(textView);
    }

    @Override
    public void onNewData(View view, D data) {
        Object tag = view.getTag();
        if (tag instanceof TextView) {
            ((TextView) tag).setText(mTextExtractor.toText(data));
        }
    }

    static class InstanceHolder {
        static final TextViewBinder INSTANCE = new TextViewBinder();
    }
}
