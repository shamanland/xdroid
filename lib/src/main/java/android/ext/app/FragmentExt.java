package android.ext.app;

import android.app.Activity;
import android.content.Context;
import android.ext.Objects;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class FragmentExt extends Fragment implements SystemServiceResolver {
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
    public LayoutInflater getLayoutInflater(Bundle savedInstanceState) {
        return LayoutInflater.from(getContext());
    }

    public Object resolveSystemService(String name) {
        return null;
    }
}
