package android.ext.app;

import android.content.Context;
import android.os.Build;

import java.util.HashSet;

/**
 * @author Oleksii Kropachov (o.kropachov@shamanland.com)
 */
public final class CustomServiceChecker {
    private static final HashSet<String> SYSTEM;

    static {
        SYSTEM = new HashSet<String>();

        SYSTEM.add(Context.POWER_SERVICE);
        SYSTEM.add(Context.WINDOW_SERVICE);
        SYSTEM.add(Context.LAYOUT_INFLATER_SERVICE);
        SYSTEM.add(Context.ACCOUNT_SERVICE);
        SYSTEM.add(Context.ACTIVITY_SERVICE);
        SYSTEM.add(Context.ALARM_SERVICE);
        SYSTEM.add(Context.NOTIFICATION_SERVICE);
        SYSTEM.add(Context.ACCESSIBILITY_SERVICE);
        SYSTEM.add(Context.KEYGUARD_SERVICE);
        SYSTEM.add(Context.LOCATION_SERVICE);
        SYSTEM.add(Context.SEARCH_SERVICE);
        SYSTEM.add(Context.SENSOR_SERVICE);
        SYSTEM.add(Context.WALLPAPER_SERVICE);
        SYSTEM.add(Context.VIBRATOR_SERVICE);
        SYSTEM.add(Context.CONNECTIVITY_SERVICE);
        SYSTEM.add(Context.WIFI_SERVICE);
        SYSTEM.add(Context.AUDIO_SERVICE);
        SYSTEM.add(Context.TELEPHONY_SERVICE);
        SYSTEM.add(Context.CLIPBOARD_SERVICE);
        SYSTEM.add(Context.INPUT_METHOD_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            SYSTEM.add(Context.DROPBOX_SERVICE);
            SYSTEM.add(Context.DEVICE_POLICY_SERVICE);
            SYSTEM.add(Context.UI_MODE_SERVICE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            SYSTEM.add(Context.STORAGE_SERVICE);
            SYSTEM.add(Context.DOWNLOAD_SERVICE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD_MR1) {
            SYSTEM.add(Context.NFC_SERVICE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            SYSTEM.add(Context.USB_SERVICE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            SYSTEM.add(Context.WIFI_P2P_SERVICE);
            SYSTEM.add(Context.TEXT_SERVICES_MANAGER_SERVICE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            SYSTEM.add(Context.NSD_SERVICE);
            SYSTEM.add(Context.MEDIA_ROUTER_SERVICE);
            SYSTEM.add(Context.INPUT_SERVICE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            SYSTEM.add(Context.DISPLAY_SERVICE);
            SYSTEM.add(Context.USER_SERVICE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            SYSTEM.add(Context.BLUETOOTH_SERVICE);
        }
    }

    public static boolean isCustom(String name) {
        return !SYSTEM.contains(name);
    }

    private CustomServiceChecker() {
        // disallow public access
    }
}
