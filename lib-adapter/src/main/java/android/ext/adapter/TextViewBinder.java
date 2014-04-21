package android.ext.adapter;

import android.os.Parcel;
import android.widget.TextView;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class TextViewBinder<D> extends ExtractorViewBinder<CharSequence, D, TextView> {
    @Override
    protected ViewBinderExtractor<D, CharSequence> createDefaultExtractor() {
        return DefaultTextExtractor.getInstance();
    }

    @Override
    public void onNewData(TextView view, D data) {
        view.setText(getExtractor().extract(data));
    }

    public static final Creator<TextViewBinder> CREATOR = new Creator<TextViewBinder>() {
        public TextViewBinder createFromParcel(Parcel in) {
            return new TextViewBinder();
        }

        public TextViewBinder[] newArray(int size) {
            return new TextViewBinder[size];
        }
    };
}
