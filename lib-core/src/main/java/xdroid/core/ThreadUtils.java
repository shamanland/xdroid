package xdroid.core;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class ThreadUtils implements Runnable {
    private static AtomicInteger sCounter;
    private static ThreadUtils sStopper;

    private ThreadUtils() {
        // disallow public access
    }

    private static String newName() {
        AtomicInteger counter = sCounter;
        if (counter == null) {
            counter = new AtomicInteger();
            sCounter = counter;
        }

        return ThreadUtils.class.getSimpleName() + '-' + counter.incrementAndGet();
    }

    /**
     * @see #newThread(String, int, Handler.Callback)
     */
    public static Handler newThread(Handler.Callback callback) {
        return newThread(null, Process.THREAD_PRIORITY_LOWEST, callback);
    }

    /**
     * @see #newThread(String, int, Handler.Callback)
     */
    public static Handler newThread(String name, Handler.Callback callback) {
        return newThread(name, Process.THREAD_PRIORITY_LOWEST, callback);
    }

    /**
     * Creates new {@link HandlerThread} and returns new {@link Handler} associated with this thread.
     *
     * @param name     name of thread, in case of null - the default name will be generated
     * @param priority one of constants from {@link android.os.Process}
     * @param callback message handling callback, may be null
     * @return new instance
     */
    public static Handler newThread(String name, int priority, Handler.Callback callback) {
        HandlerThread thread = new HandlerThread(name != null ? name : newName(), priority);
        thread.start();
        return new Handler(thread.getLooper(), callback);
    }

    /**
     * Creates new {@link Handler} with the same {@link Looper} as the original handler.
     *
     * @param original original handler, can not be null
     * @param callback message handling callback, may be null
     * @return new instance
     */
    public static Handler newHandler(Handler original, Handler.Callback callback) {
        return new Handler(original.getLooper(), callback);
    }

    /**
     * @see #stopThread(Handler, boolean)
     */
    public static void stopThread(Handler handler) {
        stopThread(handler, true);
    }

    /**
     * Post the {@link Runnable} instance with the following code to the {@link Handler} provided:
     * <pre>
     * public void run() {
     *     Looper.myLooper().quit();
     * }
     * </pre>
     *
     * @param handler target handler, can not be null
     * @param asap    if true then method {@link Handler#postAtFrontOfQueue(Runnable)} will be used.
     */
    public static void stopThread(Handler handler, boolean asap) {
        ThreadUtils stopper = sStopper;
        if (stopper == null) {
            stopper = new ThreadUtils();
            sStopper = stopper;
        }

        if (asap) {
            handler.postAtFrontOfQueue(stopper);
        } else {
            handler.post(stopper);
        }
    }

    @Override
    public void run() {
        Looper.myLooper().quit();
    }
}
