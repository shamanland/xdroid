package android.ext.app;

import android.app.Activity;
import android.content.Context;
import android.ext.Objects;
import android.support.v4.app.Fragment;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class DialogFragmentExt extends Fragment implements SystemServiceResolver {
    private Context mContext;

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

    @Override
    public Object resolveSystemService(String name) {
        return null;
    }
}
