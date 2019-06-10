package com.arpaul.sundatmat_aritra.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.arpaul.sundatmat_aritra.models.Sleep;
import com.arpaul.sundatmat_aritra.viewmodel.SleepVM;
import com.arpaul.utilitieslib.CalendarUtils;

import static com.arpaul.utilitieslib.CalendarUtils.DATE_FORMAT1;
import static com.arpaul.utilitieslib.CalendarUtils.DATE_TIME_FORMAT1;

public class PowerReceiver extends BroadcastReceiver {

    private SleepVM sleepVM;

    @Override
    public void onReceive(Context context, Intent intent) {
        String strAction = intent.getAction();
        Log.d("mPowerKeyReceiver", "onReceive");

//        sleepVM = ViewModelProviders.of(context).get(SleepVM.class);

        if (strAction.equals(Intent.ACTION_SCREEN_OFF) || strAction.equals(Intent.ACTION_SCREEN_ON)) {
            Log.d("mPowerKeyReceiver", strAction);
            Sleep sleep = new Sleep();
            sleep.setDay(CalendarUtils.getDateinPattern(DATE_FORMAT1));
            sleep.setTimeperiod(CalendarUtils.getDateinPattern(DATE_TIME_FORMAT1));
            sleep.setAction(strAction);
//            sleepVM.insert(sleep);

//            new SleepRepo(context.get).insert(sleep);
        }
    }
}
