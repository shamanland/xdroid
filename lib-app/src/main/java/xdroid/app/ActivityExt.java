package xdroid.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;

import xdroid.core.ActivityStarter;
import xdroid.core.FragmentManagerHelper;
import xdroid.customservice.CustomService;
import xdroid.customservice.CustomServiceResolver;

public class ActivityExt extends Activity implements AppEntity {
    private ActivityImpl mImpl;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        mImpl = new ActivityImpl(this);
        mImpl.getCustomServices().putCustomService(Activity.class.getName(), this);
        mImpl.getCustomServices().putCustomService(ActivityStarter.class.getName(), this);
        mImpl.getCustomServices().putCustomService(FragmentManager.class.getName(), getFm());
        mImpl.getCustomServices().putCustomService(FragmentManagerHelper.class.getName(), new FragmentManagerHelperImpl(getFm()));
    }

    public <T extends DialogFragment> void showDialog(Class<T> fragmentClass, String tag, Bundle args) {
        DialogFragmentExt.showDialog(getContext(), getFm(), fragmentClass, tag, args);
    }

    @Override
    public FragmentManager getFm() {
        return getFragmentManager();
    }

    @Override
    public ActionBar getAb() {
        return getActionBar();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public Object getSystemService(@SuppressWarnings("NullableProblems") String name) {
        if (CustomService.isCustom(name) && mImpl != null) {
            Object result = CustomService.resolve(mImpl.getCustomServices(), name);
            if (result != null) {
                return result;
            }
        }

        return super.getSystemService(name);
    }

    @Override
    public void putCustomService(String name, Object instance) {
        mImpl.getCustomServices().putCustomService(name, instance);
    }

    @Override
    public Object getCustomService(String name) {
        return mImpl.getCustomServices().getCustomService(name);
    }

    @Override
    public CustomServiceResolver getParentResolver() {
        return mImpl.getCustomServices().getParentResolver();
    }

    public ActivityListeners getListeners() {
        return mImpl.getListeners();
    }

    @Override
    public void onBackPressed() {
        if (mImpl.onBackPressed()) {
            return;
        }

        super.onBackPressed();
    }

    @Override
    public void finish() {
        mImpl.finish();

        super.finish();
    }
}
