package xdroid.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import xdroid.collections.Prototypes;
import xdroid.core.ActivityStarter;
import xdroid.core.ContextOwner;
import xdroid.core.Objects;
import xdroid.customservice.CustomServiceResolver;
import android.os.Bundle;
import android.view.LayoutInflater;

import java.util.Map;

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
}
