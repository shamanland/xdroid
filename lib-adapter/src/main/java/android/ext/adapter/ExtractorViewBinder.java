package android.ext.adapter;

import android.ext.core.Objects;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ExtractorViewBinder<E, D, V extends View> implements ViewBinder<D, V>, Parcelable {
    private ViewBinderExtractor<D, E> mExtractor;

    public ExtractorViewBinder() {
        mExtractor = Objects.notNull(createDefaultExtractor());
    }

    protected ViewBinderExtractor<D, E> createDefaultExtractor() {
        return NullExtractor.getInstance();
    }

    public final ViewBinderExtractor<D, E> getExtractor() {
        return mExtractor;
    }

    public final void setExtractor(ViewBinderExtractor<D, E> extractor) {
        mExtractor = extractor != null ? extractor : Objects.notNull(createDefaultExtractor());
    }

    @Override
    public void onNewView(V view) {
        // empty
    }

    @Override
    public void onNewData(V view, D data) {
        // empty
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeParcelable(mExtractor, flags);
    }

    public static final Creator<ExtractorViewBinder> CREATOR = new Creator<ExtractorViewBinder>() {
        public ExtractorViewBinder createFromParcel(Parcel in) {
            return new ExtractorViewBinder(in);
        }

        public ExtractorViewBinder[] newArray(int size) {
            return new ExtractorViewBinder[size];
        }
    };

    protected ExtractorViewBinder(Parcel in) {
        ClassLoader cl = ((Object) this).getClass().getClassLoader();
        mExtractor = Objects.notNull(in.<ViewBinderExtractor<D, E>>readParcelable(cl));
    }
}
