package xdroid.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import xdroid.core.ActivityStarter;
import xdroid.core.ContextOwner;
import xdroid.core.Global;
import xdroid.customservice.CustomService;
import xdroid.customservice.CustomServiceHolder;
import xdroid.customservice.CustomServiceResolver;
import xdroid.customservice.CustomServices;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ApplicationX extends Application implements ActivityStarter, ContextOwner, CustomServiceHolder {
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
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Global.init(this);
        mCustomServices = new CustomServices(null);
    }

    @Override
    public CustomServiceResolver getResolver() {
        return mCustomServices;
    }

    @Override
    public Object getSystemService(String name) {
        Object result = CustomService.resolve(this, name);
        if (result != null) {
            return result;
        }

        return super.getSystemService(name);
    }
}
