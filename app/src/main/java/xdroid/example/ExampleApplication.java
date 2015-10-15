package xdroid.example;

import xdroid.app.ApplicationX;
import xdroid.core.Global;

import static xdroid.core.Singletons.putSingleton;
import static xdroid.toaster.Toaster.toast;

public class ExampleApplication extends ApplicationX {
    @Override
    public void onCreate() {
        super.onCreate();

        putSingleton(ExampleSingleton.class, new ExampleSingleton(this, R.string.app_name));
        toast(Global.getContext().toString());
    }
}
