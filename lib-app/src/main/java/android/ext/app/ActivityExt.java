package android.ext.app;

import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.ext.collections.Prototypes;
import android.ext.core.ActivityStarter;
import android.ext.core.ContextOwner;
import android.ext.customservice.CustomService;
import android.ext.customservice.CustomServiceResolver;

import java.util.Map;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ActivityExt extends Activity implements ActivityStarter, ContextOwner, CustomServiceResolver {
    private Map<String, Object> mCustomServices;

    public ActivityExt() {
        // empty
    }

    public Context getContext() {
        return this;
    }

    public void putCustomService(String name, Object instance) {
        if (mCustomServices == null) {
            mCustomServices = Prototypes.newHashMap();
        }

        mCustomServices.put(name, instance);
    }

    @Override
    public Object getCustomService(String name) {
        if (ActivityStarter.class.getName().equals(name)) {
            return this;
        }

        if (FragmentManager.class.getName().equals(name)) {
            return getFragmentManager();
        }

        if (Activity.class.getName().equals(name)) {
            return this;
        }

        if (mCustomServices != null) {
            return mCustomServices.get(name);
        }

        return null;
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
    public Object getSystemService(@SuppressWarnings("NullableProblems") String name) {
        if (CustomService.isCustom(name)) {
            Object result = CustomService.resolve(this, name);
            if (result != null) {
                return result;
            }
        }

        return super.getSystemService(name);
    }
}
