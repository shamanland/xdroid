package xdroid.core;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

public interface FragmentManagerHelper {
    Object fragmentInstantiate(Context context, String fragmentClassName, Bundle args);

    boolean fragmentIsVisible(Object fragment);

    Object findFragmentById(int id);

    Object findFragmentByTag(String tag);

    int getBackStackEntryCount();
    
    void popBackStack();

    Object beginTransaction();

    int TRANSIT_ENTER_MASK = 0x1000;
    int TRANSIT_EXIT_MASK = 0x2000;
    int TRANSIT_UNSET = -1;
    int TRANSIT_NONE = 0;
    int TRANSIT_FRAGMENT_OPEN = 1 | TRANSIT_ENTER_MASK;
    int TRANSIT_FRAGMENT_CLOSE = 2 | TRANSIT_EXIT_MASK;
    int TRANSIT_FRAGMENT_FADE = 3 | TRANSIT_ENTER_MASK;

    void transactionAdd(Object transaction, Object fragment, String tag);

    void transactionAdd(Object transaction, int containerViewId, Object fragment);

    void transactionAdd(Object transaction, int containerViewId, Object fragment, String tag);

    void transactionReplace(Object transaction, int containerViewId, Object fragment);

    void transactionReplace(Object transaction, int containerViewId, Object fragment, String tag);

    void transactionRemove(Object transaction, Object fragment);

    void transactionHide(Object transaction, Object fragment);

    void transactionShow(Object transaction, Object fragment);

    void transactionDetach(Object transaction, Object fragment);

    void transactionAttach(Object transaction, Object fragment);

    boolean transactionIsEmpty(Object transaction);

    void transactionSetCustomAnimations(Object transaction, int enter, int exit);

    void transactionSetCustomAnimations(Object transaction, int enter, int exit, int popEnter, int popExit);

    void transactionSetTransition(Object transaction, int transit);

    void transactionAddSharedElement(Object transaction, View sharedElement, String name);

    void transactionSetTransitionStyle(Object transaction, int styleRes);

    void transactionAddToBackStack(Object transaction, String name);

    boolean transactionIsAddToBackStackAllowed(Object transaction);

    void transactionDisallowAddToBackStack(Object transaction);

    void transactionSetBreadCrumbTitle(Object transaction, int res);

    void transactionSetBreadCrumbTitle(Object transaction, CharSequence text);

    void transactionSetBreadCrumbShortTitle(Object transaction, int res);

    void transactionSetBreadCrumbShortTitle(Object transaction, CharSequence text);

    int transactionCommit(Object transaction);

    int transactionCommitAllowingStateLoss(Object transaction);
}
