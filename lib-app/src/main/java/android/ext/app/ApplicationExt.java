package android.ext.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.ext.collections.Prototypes;
import android.ext.core.Global;
import android.util.Log;

import java.util.HashMap;

import static android.ext.app.BuildConfig.SNAPSHOT;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ApplicationExt extends Application implements ActivityStarter, CustomServiceResolver {
    private static final String LOG_TAG = ApplicationExt.class.getSimpleName();

    private final HashMap<String, Object> mCustomServices;

    public ApplicationExt() {
        mCustomServices = Prototypes.newHashMap();
        putCustomService(ActivityStarter.class.getName(), this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        Global.init(this);
    }

    public void putCustomService(String name, Object instance) {
        mCustomServices.put(name, instance);
    }

    @Override
    public Object getCustomService(String name) {
        return mCustomServices.get(name);
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
