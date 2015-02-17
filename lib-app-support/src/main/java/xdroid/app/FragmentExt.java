package xdroid.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;

import xdroid.core.ActivityStarter;
import xdroid.core.FragmentManagerHelper;
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
        mImpl.getCustomServices().putCustomService(FragmentManagerHelper.class.getName(), new FragmentManagerHelperImpl(getFm()));
    }

    @Override
    public void onDetach() {
        super.onDetach();

        mImpl = null;
    }

    public <T extends DialogFragment> void showDialog(Class<T> fragmentClass, String tag, Bundle args) {
        DialogFragmentExt.showDialog(getContext(), getFm(), fragmentClass, tag, args);
    }

    /**
     * This method returns <code>getChildFragmentManager().
     */
    @Override
    public FragmentManager getFm() {
        return getChildFragmentManager();
    }

    @Override
    public ActionBar getAb() {
        FragmentActivity activity = getActivity();
        if (activity instanceof ActionBarActivity) {
            return ((ActionBarActivity) activity).getSupportActionBar();
        }

        return null;
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

    /**
     * This method overrides hidden method in order to propagate custom context deeper.
     * The same method is not required for DialogFragment.
     */
    @Override
    public LayoutInflater getLayoutInflater(Bundle state) {
        return LayoutInflater.from(getContext());
    }

    public String getWho() {
        return (String) ReflectUtils.getFieldValue(this, "mWho");
    }

    public void invalidateOptionsMenu() {
        getActivity().supportInvalidateOptionsMenu();
    }
}
