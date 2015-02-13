package xdroid.api;

import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;

public class DialogFragment extends android.app.DialogFragment {
    public static <T extends DialogFragment> void showDialog(Context context, FragmentManager fragmentManager, Class<T> fragmentClass, String tag, Bundle args) {
        android.app.Fragment fragment = Fragment.instantiate(context, fragmentClass.getName(), args);
        ((DialogFragment) fragment).show(fragmentManager, tag);
    }

    public FragmentManager getFm() {
        return getFragmentManager();
    }
}
