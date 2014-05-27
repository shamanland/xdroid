package android.ext.app;

import android.app.Activity;
import android.content.Context;
import android.ext.collections.Prototypes;
import android.ext.core.Objects;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import java.util.HashMap;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class FragmentExt extends Fragment implements ActivityStarter, CustomServiceResolver {
    private Context mContext;
    private final HashMap<String, Object> mCustomServices;

    public FragmentExt() {
        mCustomServices = Prototypes.newHashMap();
        putCustomService(ActivityStarter.class.getName(), this);
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
        mCustomServices.put(name, instance);
    }

    public Object getCustomService(String name) {
        return mCustomServices.get(name);
    }

    @Override
    public CustomServiceResolver getParentResolver() {
        Activity result = getActivity();

        if (result instanceof CustomServiceResolver) {
            return (CustomServiceResolver) result;
        }

        return null;
    }

    @Override
    public LayoutInflater getLayoutInflater(Bundle state) {
        return LayoutInflater.from(getContext());
    }
}
