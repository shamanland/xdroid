package android.ext.app;

import android.app.Activity;
import android.content.Context;
import android.ext.core.Objects;
import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class DialogFragmentExt extends Fragment implements CustomServiceResolver {
    private Context mContext;
    private HashMap<String, Object> mCustomServices;

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
            mCustomServices = new HashMap<String, Object>();
        }

        mCustomServices.put(name, instance);
    }

    public Object getCustomService(String name) {
        return mCustomServices != null ? mCustomServices.get(name) : null;
    }

    @Override
    public CustomServiceResolver getParentResolver() {
        Activity result = getActivity();

        if (result instanceof CustomServiceResolver) {
            return (CustomServiceResolver) result;
        }

        return null;
    }
}
