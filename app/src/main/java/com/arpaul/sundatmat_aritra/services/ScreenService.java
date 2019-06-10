package com.arpaul.sundatmat_aritra.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.display.DisplayManager;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.view.Display;

import com.arpaul.sundatmat_aritra.constants.Constant;
import com.arpaul.sundatmat_aritra.models.Sleep;
import com.arpaul.sundatmat_aritra.receivers.PowerReceiver;
import com.arpaul.sundatmat_aritra.receivers.RestarterBroadcastReceiver;
import com.arpaul.sundatmat_aritra.repo.SleepRepo;
import com.arpaul.utilitieslib.CalendarUtils;

import java.util.Timer;
import java.util.TimerTask;

import static com.arpaul.utilitieslib.CalendarUtils.DATE_FORMAT1;
import static com.arpaul.utilitieslib.CalendarUtils.DATE_TIME_FORMAT1;

public class ScreenService extends Service {
    private PowerReceiver mPowerKeyReceiver = new PowerReceiver();
    private Timer timer;
    private TimerTask timerTask;
    long oldTime=0;
    int counter = 0;

    public ScreenService() {
    }


    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, to wake up every 1 second
        timer.schedule(timerTask, 1000, 1 * 1000); //
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        registBroadcastReceiver();
        startTimer();
        return START_STICKY;
    }

    /**
     * it sets the timer to print the counter every x seconds
     */
    public void initializeTimerTask() {
        timerTask = new TimerTask() {
            public void run() {
                Log.i("in timer", "in timer ++++  "+ (counter++));

                Sleep sleep = new Sleep();
                sleep.setDay(CalendarUtils.getDateinPattern(DATE_FORMAT1));
                sleep.setTimeperiod(CalendarUtils.getDateinPattern(DATE_TIME_FORMAT1));
                if(checkScreen()) {
                    Log.i("in timer", "checkScreen true");

                    sleep.setAction(Constant.getSwitchOn());

                } else {
                    Log.i("in timer", "checkScreen false");
                    sleep.setAction(Constant.getSwitchOff());
                }

                new SleepRepo(getApplication()).insert(sleep);
            }
        };
    }

    private boolean checkScreen() {
        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= 21) {
            // If you use API20 or more:
            DisplayManager dm = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
            for (Display display : dm.getDisplays()) {
                if (display.getState() != Display.STATE_OFF) {
                    return true;
                }
            }
            return false;
        } else {
            // If you use less than API20:
            PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
            if (powerManager.isScreenOn()){
                return true;
            }
        }
        return false;
    }

    private void registBroadcastReceiver() {
        final IntentFilter theFilter = new IntentFilter();
        /** System Defined Broadcast */
        theFilter.addAction(Intent.ACTION_SCREEN_ON);
        theFilter.addAction(Intent.ACTION_SCREEN_OFF);

        getApplicationContext().registerReceiver(mPowerKeyReceiver, theFilter);
    }

    @Override
    public void onDestroy() {
        Log.i("EXIT", "ondestroy!");
        restartService();

        super.onDestroy();
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.i("EXIT", "onTaskRemoved!");
        restartService();

        super.onTaskRemoved(rootIntent);
    }

    private void restartService() {
        Intent broadcastIntent = new Intent(this, RestarterBroadcastReceiver.class);

        sendBroadcast(broadcastIntent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
