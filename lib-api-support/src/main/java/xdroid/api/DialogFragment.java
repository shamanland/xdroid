package xdroid.api;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public class DialogFragment extends android.support.v4.app.DialogFragment {
    public static <T extends DialogFragment> void showDialog(Context context, FragmentManager fragmentManager, Class<T> fragmentClass, String tag, Bundle args) {
        android.support.v4.app.Fragment fragment = Fragment.instantiate(context, fragmentClass.getName(), args);
        ((DialogFragment) fragment).show(fragmentManager, tag);
    }

    public FragmentManager getFm() {
        return getFragmentManager();
    }
}
