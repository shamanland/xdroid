package android.ext.widget;

import android.ext.Strings;
import android.widget.Toast;

import static android.ext.app.Global.getContext;
import static android.ext.app.Global.getUiHandler;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class Toaster implements Runnable {
    private final CharSequence mText;
    private final int mDuration;

    private Toaster(CharSequence text) {
        mText = Strings.notEmpty(text);
        mDuration = Toast.LENGTH_SHORT;
    }

    private Toaster(CharSequence text, boolean ignored) {
        mText = Strings.notEmpty(text);
        mDuration = Toast.LENGTH_LONG;
    }

    @Override
    public void run() {
        Toast.makeText(getContext(), mText, mDuration).show();
    }

    public static void show(int stringId) {
        getUiHandler().post(new Toaster(getContext().getText(stringId)));
    }

    public static void showLong(int stringId) {
        getUiHandler().post(new Toaster(getContext().getText(stringId), true));
    }

    public static void show(int stringId, Object... args) {
        getUiHandler().post(new Toaster(getContext().getString(stringId, args)));
    }

    public static void showLong(int stringId, Object... args) {
        getUiHandler().post(new Toaster(getContext().getString(stringId, args), true));
    }

    public static void show(CharSequence text) {
        getUiHandler().post(new Toaster(text));
    }

    public static void showLong(CharSequence text) {
        getUiHandler().post(new Toaster(text, true));
    }

    public static void show(String text, Object... args) {
        getUiHandler().post(new Toaster(String.format(text, args)));
    }

    public static void showLong(String text, Object... args) {
        getUiHandler().post(new Toaster(String.format(text, args), true));
    }
}
