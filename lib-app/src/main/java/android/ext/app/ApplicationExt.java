package android.ext.app;

import android.app.Application;
import android.content.Context;
import android.ext.core.Global;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ApplicationExt extends Application implements CustomServiceResolver {
    private CustomServiceResolver mCustomServiceResolver;

    protected void setCustomServiceResolver(CustomServiceResolver customServiceResolver) {
        mCustomServiceResolver = customServiceResolver;
    }

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
        if (CustomServiceChecker.isCustom(name)) {
            return resolveCustomService(name);
        }

        return super.getSystemService(name);
    }

    @Override
    public Object resolveCustomService(String name) {
        Object result = null;

        CustomServiceResolver resolver = mCustomServiceResolver;
        if (resolver != null) {
            result = resolver.resolveCustomService(name);
        }

        return result;
    }
}
