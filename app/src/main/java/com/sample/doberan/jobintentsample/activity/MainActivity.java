package com.sample.doberan.jobintentsample.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sample.doberan.jobintentsample.R;
import com.sample.doberan.jobintentsample.controller.AlarmController;
import com.sample.doberan.jobintentsample.util.CommonUtil;

public class MainActivity extends AppCompatActivity {
    private static final int JOB_ID_UPDATE = 0x1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        CommonUtil.log("onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final AlarmController alarmController = new AlarmController();
        Button startButton = findViewById(R.id.job_start_button);
        Button cancelButton = findViewById(R.id.job_cancel_button);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.log("start onClick");
                alarmController.setAirplaneModeCheckTimer(getApplicationContext());
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonUtil.log("cancel onClick");
                alarmController.cancelAirplaneModeObserveTimer(getApplicationContext());
            }
        });
    }
}
