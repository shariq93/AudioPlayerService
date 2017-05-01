package catchroom.dell.audioplayerservice.Utils;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by DeLL on 01-May-17.
 */

public class CustomMethods {
    private boolean isMyServiceRunning(Context context,Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
}
