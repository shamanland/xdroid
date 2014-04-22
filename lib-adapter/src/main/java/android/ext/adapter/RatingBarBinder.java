package android.ext.adapter;

import android.os.Parcel;
import android.widget.RatingBar;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class RatingBarBinder<D> extends ExtractorViewBinder<Float, D, RatingBar> {
    @Override
    public void onNewData(RatingBar view, D data) {
        Float value = getExtractor().extract(data);
        if (value != null) {
            view.setRating(value);
        } else {
            view.setRating(0f);
        }
    }

    public static final Creator<RatingBarBinder> CREATOR = new Creator<RatingBarBinder>() {
        public RatingBarBinder createFromParcel(Parcel in) {
            return new RatingBarBinder();
        }

        public RatingBarBinder[] newArray(int size) {
            return new RatingBarBinder[size];
        }
    };
}
