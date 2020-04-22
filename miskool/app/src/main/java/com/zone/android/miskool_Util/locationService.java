package com.zone.android.miskool_Util;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;

public class locationService extends JobIntentService {

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        Methods.getBackgroundService(getApplicationContext());
    }
}
