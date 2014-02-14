package android.ext.app;

import android.app.Application;
import android.content.Context;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ApplicationExt extends Application implements SystemServiceResolver {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Global.init(this);
    }

    protected <T> void putSingleton(Class<T> clazz, T instance) {
        Global.putSingleton(clazz, instance);
    }

    @Override
    public Object getSystemService(String name) {
        Object result = resolveSystemService(name);
        if (result != null) {
            return result;
        }

        return super.getSystemService(name);
    }

    @Override
    public Object resolveSystemService(String name) {
        return null;
    }
}
