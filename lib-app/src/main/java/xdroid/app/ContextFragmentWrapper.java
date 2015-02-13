package xdroid.app;

import android.content.ContextWrapper;
import android.view.LayoutInflater;

import xdroid.core.Objects;
import xdroid.customservice.CustomService;
import xdroid.customservice.CustomServiceResolver;

import static xdroid.app.BuildConfig.SNAPSHOT;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ContextFragmentWrapper extends ContextWrapper {
    private final Object mFragment;
    private LayoutInflater mInflater;

    public ContextFragmentWrapper(FragmentExt fragment) {
        super(Objects.notNull(fragment.getActivity()));
        mFragment = fragment;
    }

    public ContextFragmentWrapper(DialogFragmentExt fragment) {
        super(Objects.notNull(fragment.getActivity()));
        mFragment = fragment;
    }

    @Override
    public String toString() {
        if (SNAPSHOT) {
            return ContextFragmentWrapper.class.getSimpleName() + '[' + mFragment.toString() + "]@" + System.identityHashCode(this);
        } else {
            return super.toString();
        }
    }

    @Override
    public Object getSystemService(String name) {
        if (CustomService.isCustom(name)) {
            Object result = CustomService.resolve((CustomServiceResolver) mFragment, name);
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
