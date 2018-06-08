package com.sample.doberan.jobintentsample.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.sample.doberan.jobintentsample.receiver.AlarmBroadcastReceiver;
import com.sample.doberan.jobintentsample.service.SampleJobIntentService;
import com.sample.doberan.jobintentsample.util.CommonUtil;

import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;

public class AlarmController{
        /**
         * 設定した時間にAirplaneModeChangeReceiverに通知を送るタイマーを設定
         *
         * @param context context
         */
        public void setAirplaneModeCheckTimer(Context context) {
            CommonUtil.log("setAirplaneModeCheckTimer start");
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            Calendar calendar = setAlarmInformation(30);
            CommonUtil.log("setAirplaneModeCheckTimer setTime = " + calendar.get(Calendar.MINUTE));
            // todo: すでにタイマーセットされてたらキャンセルする処理追加

            long upTime = calendar.getTime().getTime();
            try {
                if(alarmManager != null) {
                    Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
                    setAlarm(alarmManager, context, intent, upTime);
                }
            }catch (Exception e){
            }
        }

        /**
         * 設定した時間にAirplaneModeChangeReceiverに通知を送るタイマーを設定
         *
         * @param context context
         */
        public void setRestartCheckTimer(Context context) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            Calendar calendar = setAlarmInformation(2);
            // todo: すでにタイマーセットされてたらキャンセルする処理追加
            long upTime = calendar.getTime().getTime();
            try {
                if(alarmManager != null) {
                    Intent intent = new Intent(context, SampleJobIntentService.class);
                    setAlarm(alarmManager, context, intent, upTime);
                }
            }catch (Exception e){

            }
        }

    /**
     * 設定した時間にAirplaneModeChangeReceiverに通知を送るタイマーを設定
     *
     * @param context context
     */
    public void nowRestartCheckTimer(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        CommonUtil.log("nowRestartCheckTimer start");
        Calendar calendar = Calendar.getInstance();
        CommonUtil.log("nowRestartCheckTimer setTime = " + calendar.get(Calendar.SECOND));
        // todo: すでにタイマーセットされてたらキャンセルする処理追加
        long upTime = calendar.getTime().getTime();
        try {
            if(alarmManager != null) {
                Intent intent = new Intent(context, SampleJobIntentService.class);
                setAlarm(alarmManager, context, intent, upTime);
            }
        }catch (Exception e){

        }
    }
        /**
         * 設定した時間にAirplaneModeChangeReceiverに通知を送るタイマーを設定
         *
         * @param context context
         */
        public void cancelAirplaneModeObserveTimer(Context context) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            try {
                if(alarmManager != null) {
                    CommonUtil.log("alarmManager != null");
                    cancelAlarm(alarmManager, context);
                }
            }catch (Exception e){
            }
        }



        /**
         * タイマーを設定する時間用のオブジェクトを取得する
         *
         * @param setValue 設定分
         * @return Calendarオブジェクト
         */
        private Calendar setAlarmInformation(int setValue) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, setValue);
            return calendar;
        }

        /**
         * タイマーをセットする
         * @param alarmManager アラームマネージャ
         * @param context Contextオブジェクト
         * @param upTime タイマー更新時間
         */
        private void setAlarm(AlarmManager alarmManager, Context context, Intent intent, long upTime){
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, upTime, pendingIntent);
        }

        private void cancelAlarm(AlarmManager alarmManager, Context context){
            Intent intent = new Intent(context, AlarmBroadcastReceiver.class);
            PendingIntent p = PendingIntent.getBroadcast(context,1,intent,PendingIntent.FLAG_CANCEL_CURRENT);
            alarmManager.cancel(p);
            p.cancel();
            CommonUtil.log("cancel!");
        }
}
