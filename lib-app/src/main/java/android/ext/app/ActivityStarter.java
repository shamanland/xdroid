package android.ext.app;

import android.content.Intent;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public interface ActivityStarter {
    void startActivity(Intent intent);

    void startActivityForResult(Intent intent, int requestCode);
}
