package android.ext.app;

import android.app.Activity;
import android.content.Context;
import android.ext.Objects;
import android.support.v4.app.Fragment;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class DialogFragmentExt extends Fragment implements CustomServiceResolver {
    private Context mContext;
    private CustomServiceResolver mCustomServiceResolver;

    protected void setCustomServiceResolver(CustomServiceResolver customServiceResolver) {
        mCustomServiceResolver = customServiceResolver;
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

    public Object resolveCustomService(String name) {
        Object result = null;

        CustomServiceResolver resolver = mCustomServiceResolver;
        if (resolver != null) {
            result = resolver.resolveCustomService(name);
        }

        if (result == null) {
            Activity activity = getActivity();
            if (activity instanceof CustomServiceResolver) {
                result = ((CustomServiceResolver) activity).resolveCustomService(name);
            }
        }

        return result;
    }
}
