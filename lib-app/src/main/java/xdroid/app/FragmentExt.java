package xdroid.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;

import xdroid.core.ActivityStarter;
import xdroid.core.ReflectUtils;
import xdroid.customservice.CustomServiceResolver;

public class FragmentExt extends Fragment implements AppEntity {
    private FragmentImpl mImpl;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mImpl = new FragmentImpl(this, activity);
        mImpl.getCustomServices().putCustomService(ActivityStarter.class.getName(), this);
        mImpl.getCustomServices().putCustomService(FragmentManager.class.getName(), getFm());
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mImpl = null;
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
        return getActivity().getActionBar();
    }

    @Override
    public Context getContext() {
        return mImpl;
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

    public String getWho() {
        return (String) ReflectUtils.getFieldValue(this, "mWho");
    }

    public void invalidateOptionsMenu() {
        getActivity().invalidateOptionsMenu();
    }
}
