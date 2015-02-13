package xdroid.api;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

public abstract class Fragment extends android.app.Fragment {
    protected abstract Context getContext();

    public <T extends DialogFragment> void showDialog(Class<T> fragmentClass, String tag, Bundle args) {
        DialogFragment.showDialog(getContext(), getFragmentManager(), fragmentClass, tag, args);
    }

    public FragmentManager getFm() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return getChildFragmentManager();
        }

        return getFragmentManager();
    }
}
