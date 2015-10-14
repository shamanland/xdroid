package xdroid.example;

import xdroid.app.ApplicationX;
import xdroid.core.Global;

public class ExampleApplication extends ApplicationX {
    @Override
    public void onCreate() {
        super.onCreate();

        Global.putSingleton(ExampleSingleton.class, new ExampleSingleton(this, R.string.app_name));
    }
}
