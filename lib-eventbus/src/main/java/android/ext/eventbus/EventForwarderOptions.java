package android.ext.eventbus;

import android.content.Context;
import android.content.res.TypedArray;
import android.ext.core.Objects;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;

import static android.ext.eventbus.EventDispatcherInflater.readClass;

public class EventForwarderOptions implements Parcelable {
    public final String activity;
    public final boolean forResult;
    public final int requestCode;

    public EventForwarderOptions(Context context, AttributeSet attrs) {
        TypedArray a = Objects.notNull(context.obtainStyledAttributes(attrs, R.styleable.EventForwarder));

        try {
            activity = readClass(context, a, R.styleable.EventForwarder_activity);
            forResult = a.getBoolean(R.styleable.EventForwarder_for_result, false);
            requestCode = a.getInteger(R.styleable.EventForwarder_request_code, 0);
        } finally {
            a.recycle();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(activity);
        out.writeInt(forResult ? 1 : 0);
        out.writeInt(requestCode);
    }

    public static final Creator<EventForwarderOptions> CREATOR = new Creator<EventForwarderOptions>() {
        public EventForwarderOptions createFromParcel(Parcel in) {
            return new EventForwarderOptions(in);
        }

        public EventForwarderOptions[] newArray(int size) {
            return new EventForwarderOptions[size];
        }
    };

    private EventForwarderOptions(Parcel in) {
        activity = in.readString();
        forResult = in.readInt() == 1;
        requestCode = in.readInt();
    }
}
