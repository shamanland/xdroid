package android.ext.core;

import android.content.Context;
import android.content.Intent;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ContextActivityStarter implements ActivityStarter {
    private final Context mContext;

    public ContextActivityStarter(Context context) {
        mContext = Objects.notNull(context);
    }

    @Override
    public void startActivity(Intent intent) {
        mContext.startActivity(intent);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        mContext.startActivity(intent);
    }
}
