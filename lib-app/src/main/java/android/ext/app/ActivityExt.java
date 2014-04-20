package android.ext.app;

import android.app.Application;
import android.support.v7.app.ActionBarActivity;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ActivityExt extends ActionBarActivity implements CustomServiceResolver {
    private CustomServiceResolver mCustomServiceResolver;

    protected void setCustomServiceResolver(CustomServiceResolver customServiceResolver) {
        mCustomServiceResolver = customServiceResolver;
    }

    @Override
    public Object getSystemService(String name) {
        if (CustomServiceChecker.isCustom(name)) {
            return resolveCustomService(name);
        }

        return super.getSystemService(name);
    }

    @Override
    public Object resolveCustomService(String name) {
        Object result = null;

        CustomServiceResolver resolver = mCustomServiceResolver;
        if (resolver != null) {
            result = resolver.resolveCustomService(name);
        }

        if (result == null) {
            Application application = getApplication();
            if (application instanceof CustomServiceResolver) {
                result = ((CustomServiceResolver) application).resolveCustomService(name);
            }
        }

        return result;
    }
}
