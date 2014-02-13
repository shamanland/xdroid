package android.ext.app;

import android.app.Application;
import android.content.Context;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ApplicationExt extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Global.init(this);
    }

    protected <T> void putSingleton(Class<T> clazz, T instance) {
        Global.putSingleton(clazz, instance);
    }
}
