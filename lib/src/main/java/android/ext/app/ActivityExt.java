package android.ext.app;

import android.support.v7.app.ActionBarActivity;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ActivityExt extends ActionBarActivity implements SystemServiceResolver {
    @Override
    public Object getSystemService(String name) {
        Object result = resolveSystemService(name);
        if (result != null) {
            return result;
        }

        return super.getSystemService(name);
    }

    @Override
    public Object resolveSystemService(String name) {
        return null;
    }
}
