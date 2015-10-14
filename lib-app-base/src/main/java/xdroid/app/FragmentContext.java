package xdroid.app;

import android.app.Activity;
import android.content.ContextWrapper;
import android.view.LayoutInflater;

import xdroid.customservice.CustomService;
import xdroid.customservice.CustomServiceHolder;
import xdroid.customservice.CustomServiceResolver;
import xdroid.customservice.CustomServices;

import static xdroid.core.ObjectUtils.notNull;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class FragmentContext<F> extends ContextWrapper implements CustomServiceHolder {
    private final F mFragment;
    private final CustomServices mCustomServices;
    private LayoutInflater mInflater;

    public F getFragment() {
        return mFragment;
    }

    public FragmentContext(F fragment, Activity activity, CustomServiceResolver parentResolver) {
        super(notNull(activity));
        mFragment = notNull(fragment);
        mCustomServices = new CustomServices(parentResolver);
    }

    @Override
    public String toString() {
        return FragmentContext.class.getSimpleName() + '[' + mFragment.toString() + "]@" + System.identityHashCode(this);
    }

    @Override
    public Object getSystemService(@SuppressWarnings("NullableProblems") String name) {
        Object result = CustomService.resolve(this, name);
        if (result != null) {
            return result;
        }

        if (LAYOUT_INFLATER_SERVICE.equals(name)) {
            if (mInflater == null) {
                mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
            }

            return mInflater;
        }

        return super.getSystemService(name);
    }

    @Override
    public CustomServiceResolver getResolver() {
        return mCustomServices;
    }
}
