package android.ext.eventbus;

import android.content.Context;
import android.content.res.TypedArray;

public class EventDispatcherInflater {
    public static String readClass(Context context, TypedArray a, int index) {
        String result = a.getString(index);
        if (result == null) {
            throw new IllegalArgumentException();
        }

        if (result.length() == 0) {
            throw new IllegalArgumentException();
        }

        if (result.charAt(0) == '.') {
            result = context.getPackageName().concat(result);
        }

        return result;
    }
}
