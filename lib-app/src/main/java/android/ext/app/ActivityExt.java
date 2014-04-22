package android.ext.app;

import android.app.Application;
import android.support.v7.app.ActionBarActivity;

import java.util.HashMap;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ActivityExt extends ActionBarActivity implements CustomServiceResolver {
    private HashMap<String, Object> mCustomServices;

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
        Application result = getApplication();

        if (result instanceof CustomServiceResolver) {
            return (CustomServiceResolver) result;
        }

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
