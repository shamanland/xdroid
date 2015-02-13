package xdroid.api;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;

public abstract class Activity extends android.app.Activity {
    protected abstract Context getContext();

    public <T extends DialogFragment> void showDialog(Class<T> fragmentClass, String tag, Bundle args) {
        DialogFragment.showDialog(getContext(), getFragmentManager(), fragmentClass, tag, args);
    }

    public FragmentManager getFm() {
        return getFragmentManager();
    }
}
