package android.ext.app;

import android.content.ContextWrapper;
import android.ext.Objects;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import static android.ext.BuildConfig.DEBUG;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ContextFragmentWrapper extends ContextWrapper {
    private final Fragment mFragment;
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
        if (DEBUG) {
            return ContextFragmentWrapper.class.getSimpleName() + '[' + mFragment.toString() + "]@" + System.identityHashCode(this);
        } else {
            return super.toString();
        }
    }

    @Override
    public Object getSystemService(String name) {
        if (LAYOUT_INFLATER_SERVICE.equals(name)) {
            if (mInflater == null) {
                mInflater = LayoutInflater.from(getBaseContext()).cloneInContext(this);
            }

            return mInflater;
        }

        SystemServiceResolver resolver = (SystemServiceResolver) mFragment;

        Object result = resolver.resolveSystemService(name);
        if (result != null) {
            return result;
        }

        return getBaseContext().getSystemService(name);
    }
}
