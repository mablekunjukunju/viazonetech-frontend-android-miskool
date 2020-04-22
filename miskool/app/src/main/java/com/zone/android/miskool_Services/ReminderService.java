package com.zone.android.miskool_Services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;
import android.support.annotation.RequiresApi;


import com.zone.android.miskool_Util.Methods;


@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class ReminderService extends JobService {


    @Override
    public boolean onStartJob(JobParameters params) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                Methods.getBackgroundService(getApplicationContext());
            }
        }).start();


        jobFinished(params, true);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
