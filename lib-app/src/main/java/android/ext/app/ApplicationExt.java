package android.ext.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.ext.collections.Prototypes;
import android.ext.core.ActivityStarter;
import android.ext.core.ContextOwner;
import android.ext.core.Global;
import android.ext.customservice.CustomService;
import android.ext.customservice.CustomServiceResolver;
import android.util.Log;

import java.util.Map;

import static android.ext.app.BuildConfig.SNAPSHOT;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ApplicationExt extends Application implements ActivityStarter, ContextOwner, CustomServiceResolver {
    private static final String LOG_TAG = ApplicationExt.class.getSimpleName();

    private Map<String, Object> mCustomServices;

    public ApplicationExt() {
        // empty
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Global.init(this);
    }

    public void putCustomService(String name, Object instance) {
        if (mCustomServices == null) {
            mCustomServices = Prototypes.newHashMap();
        }

        mCustomServices.put(name, instance);
    }

    @Override
    public Object getCustomService(String name) {
        if (ActivityStarter.class.getName().equals(name)) {
            return this;
        }

        if (mCustomServices != null) {
            return mCustomServices.get(name);
        }

        return null;
    }

    @Override
    public CustomServiceResolver getParentResolver() {
        return null;
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

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        if (SNAPSHOT) {
            Log.w(LOG_TAG, "Cannot start Activity for result from Application: " + intent);
        }

        startActivity(intent);
    }
}
