package xdroid.app;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;

import java.util.Map;

import xdroid.core.ActivityStarter;
import xdroid.core.ContextOwner;
import xdroid.customservice.CustomServiceResolver;

import static xdroid.collections.Prototypes.newHashMap;
import static xdroid.core.ObjectUtils.notNull;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class DialogFragmentExt extends DialogFragment implements ActivityStarter, ContextOwner, CustomServiceResolver {
    private Context mContext;
    private Map<String, Object> mCustomServices;

    public DialogFragmentExt() {
        // empty
    }

    public Context getContext() {
        return notNull(mContext);
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
            mCustomServices = newHashMap();
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
}
