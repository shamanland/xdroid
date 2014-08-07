package xdroid.app;

import android.app.Activity;
import android.app.Application;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;

import java.util.Map;

import xdroid.collections.Prototypes;
import xdroid.core.ActivityStarter;
import xdroid.core.ContextOwner;
import xdroid.customservice.CustomService;
import xdroid.customservice.CustomServiceResolver;

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
            mCustomServices.put(Activity.class.getName(), this);
            mCustomServices.put(ActivityStarter.class.getName(), this);
            mCustomServices.put(FragmentManager.class.getName(), getFragmentManager());
        }

        mCustomServices.put(name, instance);
    }

    @Override
    public Object getCustomService(String name) {
        if (mCustomServices != null) {
            return mCustomServices.get(name);
        }

        if (Activity.class.getName().equals(name)) {
            return this;
        }

        if (ActivityStarter.class.getName().equals(name)) {
            return this;
        }

        if (FragmentManager.class.getName().equals(name)) {
            return getFragmentManager();
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

    public <T extends DialogFragment> void showDialog(Class<T> fragmentClass, String tag, Bundle args) {
        showDialog(getContext(), getFragmentManager(), fragmentClass, tag, args);
    }

    public static <T extends DialogFragment> void showDialog(Context context, FragmentManager fragmentManager, Class<T> fragmentClass, String tag, Bundle args) {
        Fragment fragment = Fragment.instantiate(context, fragmentClass.getName(), args);
        ((DialogFragment) fragment).show(fragmentManager, tag);
    }
}
