package android.ext.app;

import android.app.Application;
import android.content.Context;
import android.ext.core.Global;

import java.util.HashMap;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ApplicationExt extends Application implements CustomServiceResolver {
    private HashMap<String, Object> mCustomServices;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Global.init(this);
    }

    public void putCustomService(String name, Object instance) {
        if (mCustomServices == null) {
            mCustomServices = new HashMap<String, Object>();
        }

        mCustomServices.put(name, instance);
    }

    @Override
    public Object getCustomService(String name) {
        return mCustomServices != null ? mCustomServices.get(name) : null;
    }

    @Override
    public CustomServiceResolver getParentResolver() {
        return null;
    }

    @Override
    public Object getSystemService(String name) {
        if (CustomService.isCustom(name)) {
            Object result = CustomService.resolve(this, name);
            if (result != null) {
                return result;
            }
        }

        return super.getSystemService(name);
    }
}
