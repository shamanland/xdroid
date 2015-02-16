package xdroid.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import xdroid.core.Global;
import xdroid.customservice.CustomService;
import xdroid.customservice.CustomServiceResolver;
import xdroid.customservice.CustomServices;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ApplicationExt extends Application implements AppEntity {
    private CustomServices mCustomServices;

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        startActivity(intent);
    }

    @Override
    public Object getFm() {
        return null;
    }

    @Override
    public Object getAb() {
        return null;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Global.init(this);
        mCustomServices = new CustomServices(null);
    }

    @Override
    public void putCustomService(String name, Object instance) {
        mCustomServices.putCustomService(name, instance);
    }

    @Override
    public Object getCustomService(String name) {
        return mCustomServices.getCustomService(name);
    }

    @Override
    public CustomServiceResolver getParentResolver() {
        return mCustomServices.getParentResolver();
    }

    @Override
    public Object getSystemService(String name) {
        if (CustomService.isCustom(name)) {
            Object result = CustomService.resolve(this, name);
            if (result != null) {
                return result;
            }
        }

        return super.getSystemService(name);
    }
}
