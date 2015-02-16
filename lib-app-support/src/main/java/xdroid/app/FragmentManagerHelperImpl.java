package xdroid.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import xdroid.core.FragmentManagerHelper;

import static xdroid.core.Objects.notNull;

public class FragmentManagerHelperImpl implements FragmentManagerHelper {
    private final FragmentManager mBase;

    public FragmentManagerHelperImpl(FragmentManager base) {
        mBase = notNull(base);
    }

    @Override
    public Object fragmentInstantiate(Context context, String fragmentClassName, Bundle args) {
        return Fragment.instantiate(context, fragmentClassName, args);
    }

    @Override
    public boolean fragmentIsVisible(Object fragment) {
        return ((Fragment) fragment).isVisible();
    }

    @Override
    public Object findFragmentById(int id) {
        return mBase.findFragmentById(id);
    }

    @Override
    public Object findFragmentByTag(String tag) {
        return mBase.findFragmentByTag(tag);
    }

    @Override
    public int getBackStackEntryCount() {
        return mBase.getBackStackEntryCount();
    }

    @Override
    public void popBackStack() {
        mBase.popBackStack();
    }

    @Override
    @SuppressLint("CommitTransaction")
    public Object beginTransaction() {
        return mBase.beginTransaction();
    }

    @Override
    public void transactionAdd(Object transaction, Object fragment, String tag) {
        ((FragmentTransaction) transaction).add((Fragment) fragment, tag);
    }

    @Override
    public void transactionAdd(Object transaction, int containerViewId, Object fragment) {
        ((FragmentTransaction) transaction).add(containerViewId, (Fragment) fragment);
    }

    @Override
    public void transactionAdd(Object transaction, int containerViewId, Object fragment, String tag) {
        ((FragmentTransaction) transaction).add(containerViewId, (Fragment) fragment, tag);
    }

    @Override
    public void transactionReplace(Object transaction, int containerViewId, Object fragment) {
        ((FragmentTransaction) transaction).replace(containerViewId, (Fragment) fragment);
    }

    @Override
    public void transactionReplace(Object transaction, int containerViewId, Object fragment, String tag) {
        ((FragmentTransaction) transaction).replace(containerViewId, (Fragment) fragment, tag);
    }

    @Override
    public void transactionRemove(Object transaction, Object fragment) {
        ((FragmentTransaction) transaction).remove((Fragment) fragment);
    }

    @Override
    public void transactionHide(Object transaction, Object fragment) {
        ((FragmentTransaction) transaction).hide((Fragment) fragment);
    }

    @Override
    public void transactionShow(Object transaction, Object fragment) {
        ((FragmentTransaction) transaction).show((Fragment) fragment);
    }

    @Override
    public void transactionDetach(Object transaction, Object fragment) {
        ((FragmentTransaction) transaction).detach((Fragment) fragment);
    }

    @Override
    public void transactionAttach(Object transaction, Object fragment) {
        ((FragmentTransaction) transaction).attach((Fragment) fragment);
    }

    @Override
    public boolean transactionIsEmpty(Object transaction) {
        return ((FragmentTransaction) transaction).isEmpty();
    }

    @Override
    public void transactionSetCustomAnimations(Object transaction, int enter, int exit) {
        ((FragmentTransaction) transaction).setCustomAnimations(enter, exit);
    }

    @Override
    public void transactionSetCustomAnimations(Object transaction, int enter, int exit, int popEnter, int popExit) {
        ((FragmentTransaction) transaction).setCustomAnimations(enter, exit, popEnter, popExit);
    }

    @Override
    public void transactionSetTransition(Object transaction, int transit) {
        ((FragmentTransaction) transaction).setTransition(transit);
    }

    @Override
    public void transactionAddSharedElement(Object transaction, View sharedElement, String name) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ((FragmentTransaction) transaction).addSharedElement(sharedElement, name);
        }
    }

    @Override
    public void transactionSetTransitionStyle(Object transaction, int styleRes) {
        ((FragmentTransaction) transaction).setTransitionStyle(styleRes);
    }

    @Override
    public void transactionAddToBackStack(Object transaction, String name) {
        ((FragmentTransaction) transaction).addToBackStack(name);
    }

    @Override
    public boolean transactionIsAddToBackStackAllowed(Object transaction) {
        return ((FragmentTransaction) transaction).isAddToBackStackAllowed();
    }

    @Override
    public void transactionDisallowAddToBackStack(Object transaction) {
        ((FragmentTransaction) transaction).disallowAddToBackStack();
    }

    @Override
    public void transactionSetBreadCrumbTitle(Object transaction, int res) {
        ((FragmentTransaction) transaction).setBreadCrumbTitle(res);
    }

    @Override
    public void transactionSetBreadCrumbTitle(Object transaction, CharSequence text) {
        ((FragmentTransaction) transaction).setBreadCrumbTitle(text);
    }

    @Override
    public void transactionSetBreadCrumbShortTitle(Object transaction, int res) {
        ((FragmentTransaction) transaction).setBreadCrumbShortTitle(res);
    }

    @Override
    public void transactionSetBreadCrumbShortTitle(Object transaction, CharSequence text) {
        ((FragmentTransaction) transaction).setBreadCrumbShortTitle(text);
    }

    @Override
    public int transactionCommit(Object transaction) {
        return ((FragmentTransaction) transaction).commit();
    }

    @Override
    public int transactionCommitAllowingStateLoss(Object transaction) {
        return ((FragmentTransaction) transaction).commitAllowingStateLoss();
    }
}
