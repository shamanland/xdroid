package android.ext.adapter;

import android.os.Parcel;
import android.widget.CompoundButton;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class CompoundButtonBinder<D> extends ExtractorViewBinder<Boolean, D, CompoundButton> {
    @Override
    public void onNewData(CompoundButton view, D data) {
        Boolean value = getExtractor().extract(data);
        if (value != null) {
            view.setChecked(value.booleanValue());
        } else {
            view.setChecked(false);
        }
    }

    public static final Creator<CompoundButtonBinder> CREATOR = new Creator<CompoundButtonBinder>() {
        public CompoundButtonBinder createFromParcel(Parcel in) {
            return new CompoundButtonBinder();
        }

        public CompoundButtonBinder[] newArray(int size) {
            return new CompoundButtonBinder[size];
        }
    };
}
