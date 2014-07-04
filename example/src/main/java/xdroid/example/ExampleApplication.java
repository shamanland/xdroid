package xdroid.example;

import xdroid.app.ApplicationExt;
import xdroid.core.Global;

public class ExampleApplication extends ApplicationExt {
    @Override
    public void onCreate() {
        super.onCreate();

        Global.putSingleton(ExampleSingleton.class, new ExampleSingleton(this, R.string.app_name));
    }
}
