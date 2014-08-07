package xdroid.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;

import java.util.Map;

import xdroid.collections.Prototypes;
import xdroid.core.ActivityStarter;
import xdroid.core.ContextOwner;
import xdroid.core.Objects;
import xdroid.customservice.CustomServiceResolver;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class FragmentExt extends Fragment implements ActivityStarter, ContextOwner, CustomServiceResolver {
    private Context mContext;
    private Map<String, Object> mCustomServices;

    public FragmentExt() {
        // empty
    }

    public Context getContext() {
        return Objects.notNull(mContext);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = new ContextFragmentWrapper(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }

    public void putCustomService(String name, Object instance) {
        if (mCustomServices == null) {
            mCustomServices = Prototypes.newHashMap();
        }

        mCustomServices.put(name, instance);
    }

    public Object getCustomService(String name) {
        if (ActivityStarter.class.getName().equals(name)) {
            return this;
        }

        if (mCustomServices != null) {
            return mCustomServices.get(name);
        }

        return null;
    }

    @Override
    public CustomServiceResolver getParentResolver() {
        Activity result = getActivity();

        if (result instanceof CustomServiceResolver) {
            return (CustomServiceResolver) result;
        }

        return null;
    }

    // Override
    @SuppressWarnings("UnusedDeclaration")
    public LayoutInflater getLayoutInflater(Bundle state) {
        return LayoutInflater.from(getContext());
    }

    public ActionBar getActionBar() {
        Activity activity = getActivity();
        if (activity != null) {
            return activity.getActionBar();
        }

        return null;
    }

    @Override
    public void onStart() {
        super.onStart();

        ActionBar ab = getActionBar();
        if (ab != null) {
            onInitActionBar(ab);
        }
    }

    @Override
    public void onStop() {
        ActionBar ab = getActionBar();
        if (ab != null) {
            onRestoreActionBar(ab);
        }

        super.onStop();
    }

    protected void invalidateActionBar() {
        ActionBar ab = getActionBar();
        if (ab != null) {
            onRestoreActionBar(ab);
            onInitActionBar(ab);
        }
    }

    protected void onInitActionBar(ActionBar ab) {
        // for inheritors
    }

    protected void onRestoreActionBar(ActionBar ab) {
        // for inheritors
    }

    protected void invalidateOptionsMenu() {
        Activity activity = getActivity();
        if (activity != null) {
            activity.invalidateOptionsMenu();
        }
    }
}
