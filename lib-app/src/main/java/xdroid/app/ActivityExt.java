package xdroid.app;

import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;

import java.util.Map;

import xdroid.api.Activity;
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
    private ActivityListeners mListeners;

    public ActivityExt() {
        // empty
    }

    public Context getContext() {
        return this;
    }

    private boolean hasListeners() {
        return mListeners != null;
    }

    private ActivityListeners getListeners() {
        if (mListeners == null) {
            mListeners = new ActivityListeners();
        }

        return mListeners;
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

    @Override
    public void onBackPressed() {
        boolean preventDefault = false;

        if (hasListeners()) {
            for (OnBackPressedListener listener : getListeners().getBackPressed()) {
                preventDefault |= listener.onBackPressed(this);
            }
        }

        if (!preventDefault) {
            super.onBackPressed();
        }
    }

    @Override
    public void finish() {
        if (hasListeners()) {
            for (OnFinishListener listener : getListeners().getFinish()) {
                listener.onFinish(this);
            }
        }

        super.finish();
    }

    public void addOnBackPressedListener(OnBackPressedListener listener) {
        getListeners().add(listener);
    }

    public void removeOnBackPressedListener(OnBackPressedListener listener) {
        getListeners().remove(listener);
    }

    public void addOnFinishListener(OnFinishListener listener) {
        getListeners().add(listener);
    }

    public void removeOnFinishListener(OnFinishListener listener) {
        getListeners().remove(listener);
    }
}
