package xdroid.example;

import xdroid.app.ApplicationX;
import xdroid.core.Global;

import static xdroid.toaster.Toaster.toast;

public class ExampleApplication extends ApplicationX {
    @Override
    public void onCreate() {
        super.onCreate();

        Global.putSingleton(ExampleSingleton.class, new ExampleSingleton(this, R.string.app_name));
        toast(Global.getContext().toString());
    }
}
