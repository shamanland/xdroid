package xdroid.api;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public abstract class Fragment extends android.support.v4.app.Fragment {
    protected abstract Context getContext();

    public <T extends DialogFragment> void showDialog(Class<T> fragmentClass, String tag, Bundle args) {
        DialogFragment.showDialog(getContext(), getFragmentManager(), fragmentClass, tag, args);
    }

    public FragmentManager getFm() {
        return getChildFragmentManager();
    }
}
