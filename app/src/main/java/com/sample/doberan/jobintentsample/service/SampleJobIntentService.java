package com.sample.doberan.jobintentsample.service;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.JobIntentService;

import com.sample.doberan.jobintentsample.util.CommonUtil;

public class SampleJobIntentService extends JobIntentService {
    /**
     * Unique job ID for this service.
     */
    static final int JOB_ID = 1000;
    private static boolean isEnqueueWork = false;

    public SampleJobIntentService() {
        super();
        CommonUtil.log("constractor start --------------------------------------");
    }

    /**
     * Convenience method for enqueuing work in to this service.
     */
    public static void enqueueWork(Context context, Intent work) {
        CommonUtil.log("enqueueWork");
        if(!isEnqueueWork) {
            CommonUtil.log("enqueueWork isEnqueueWork is false.");
            CommonUtil.log("enqueueWork start");
            enqueueWork(context, SampleJobIntentService.class, JOB_ID, work);
            isEnqueueWork = true;
        }else{
            CommonUtil.log("enqueueWork isEnqueueWork is true.");
            CommonUtil.log("sorry, do not enqueueWork");
        }
    }

    @Override
    protected void onHandleWork(Intent intent) {
        CommonUtil.log("onHandleWork start -------------------------------------");


        CommonUtil.log("onHandleWork end -------------------------------------");
    }

    @Override
    public boolean onStopCurrentWork() {
        CommonUtil.log("onStopCurrentWork start --------------------------------------");
        isEnqueueWork = false;
        CommonUtil.log("onStopCurrentWork end --------------------------------------");
        return false;
    }

    @Override
    public void onCreate() {
        CommonUtil.log("onCreate start --------------------------------------");
        super.onCreate();
        CommonUtil.log("onCreate end --------------------------------------");
    }

    @Override
    public void onDestroy() {
        CommonUtil.log("onDestroy start --------------------------------------");
        isEnqueueWork = false;
        super.onDestroy();
        startService(new Intent(this, SampleJobIntentService.class));
        CommonUtil.log("onDestroy end --------------------------------------");
    }
}
