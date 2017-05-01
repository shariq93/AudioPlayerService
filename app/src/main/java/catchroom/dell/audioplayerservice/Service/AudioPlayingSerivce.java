package catchroom.dell.audioplayerservice.Service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.IntDef;

import java.util.Timer;
import java.util.TimerTask;

import catchroom.dell.audioplayerservice.Utils.UserPrefs;

public class AudioPlayingSerivce extends Service {
    Timer timer;
    Handler handler;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        UserPrefs userPrefs = new UserPrefs(getApplicationContext());

        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (handler != null) {
            handler = null;
        }

        timer = new Timer();
        handler = new Handler();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

            }
        }, userPrefs.getInterval(), userPrefs.getInterval());

        return START_STICKY;
    }
}
