package com.sample.doberan.jobintentsample.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.sample.doberan.jobintentsample.service.SampleJobIntentService;
import com.sample.doberan.jobintentsample.util.CommonUtil;

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        CommonUtil.log("onReceive");
        Intent service = new Intent();
        SampleJobIntentService.enqueueWork(context, service);
    }
}
