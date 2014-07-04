package xdroid.toaster;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import static xdroid.core.Global.getContext;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
@SuppressWarnings("unused")
public final class Toaster implements Handler.Callback {
    private static final Handler sHandler = new Handler(Looper.getMainLooper(), new Toaster());

    private Toaster() {
        // disallow public access
    }

    @Override
    public boolean handleMessage(Message msg) {
        Toast.makeText(getContext(), (CharSequence) msg.obj, msg.what).show();
        return true;
    }

    private static void sendMessage(CharSequence text, int length) {
        //noinspection ConstantConditions
        Message.obtain(sHandler, length, text).sendToTarget();
    }

    public static void toast(int stringId) {
        sendMessage(getContext().getText(stringId), Toast.LENGTH_SHORT);
    }

    public static void toastLong(int stringId) {
        sendMessage(getContext().getText(stringId), Toast.LENGTH_LONG);
    }

    public static void toast(int stringId, Object... args) {
        sendMessage(getContext().getString(stringId, args), Toast.LENGTH_SHORT);
    }

    public static void toastLong(int stringId, Object... args) {
        sendMessage(getContext().getString(stringId, args), Toast.LENGTH_LONG);
    }

    public static void toast(CharSequence text) {
        sendMessage(text, Toast.LENGTH_SHORT);
    }

    public static void toastLong(CharSequence text) {
        sendMessage(text, Toast.LENGTH_LONG);
    }

    public static void toast(String text, Object... args) {
        sendMessage(String.format(text, args), Toast.LENGTH_SHORT);
    }

    public static void toastLong(String text, Object... args) {
        sendMessage(String.format(text, args), Toast.LENGTH_LONG);
    }
}
