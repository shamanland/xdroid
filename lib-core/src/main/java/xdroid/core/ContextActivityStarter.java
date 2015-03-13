package xdroid.core;

import android.content.Context;
import android.content.Intent;

import static xdroid.core.ObjectUtils.notNull;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public class ContextActivityStarter implements ActivityStarter {
    private final Context mContext;

    public ContextActivityStarter(Context context) {
        mContext = notNull(context);
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
