package xdroid.app;

import android.app.Activity;
import android.content.ContextWrapper;
import android.view.LayoutInflater;

import xdroid.customservice.CustomService;
import xdroid.customservice.CustomServices;

import static xdroid.core.Objects.notNull;
import static xdroid.customservice.CustomService.asCustomServiceResolver;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class FragmentImpl extends ContextWrapper {
    private final Object mFragment;
    private final CustomServices mCustomServices;
    private LayoutInflater mInflater;

    public CustomServices getCustomServices() {
        return mCustomServices;
    }

    public FragmentImpl(Object fragment, Activity activity) {
        super(notNull(activity));
        mFragment = notNull(fragment);
        mCustomServices = new CustomServices(asCustomServiceResolver(activity));
    }

    @Override
    public String toString() {
        return FragmentImpl.class.getSimpleName() + '[' + mFragment.toString() + "]@" + System.identityHashCode(this);
    }

    @Override
    public Object getSystemService(@SuppressWarnings("NullableProblems") String name) {
        if (CustomService.isCustom(name)) {
            Object result = CustomService.resolve(mCustomServices, name);
            if (result != null) {
                return result;
            }
        }

        if (LAYOUT_INFLATER_SERVICE.equals(name)) {
            if (mInflater == null) {
                mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
            }

            return mInflater;
        }

        return super.getSystemService(name);
    }
}
