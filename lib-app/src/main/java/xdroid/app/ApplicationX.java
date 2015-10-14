package xdroid.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import xdroid.core.ActivityStarter;
import xdroid.core.ContextOwner;
import xdroid.core.Global;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ApplicationX extends Application implements ActivityStarter, ContextOwner {
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
        Global.setContext(this);
    }
}
