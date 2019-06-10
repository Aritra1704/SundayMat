package com.arpaul.sundatmat_aritra.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.arpaul.sundatmat_aritra.services.ScreenService;

public class RestarterBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(RestarterBroadcastReceiver.class.getSimpleName(), "Restart Service!!");
        context.startService(new Intent(context, ScreenService.class));;
    }
}
