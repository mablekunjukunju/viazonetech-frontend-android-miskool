package com.zone.android.miskool_Services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.zone.android.miskool_Entitiy.Alerts;
import com.zone.android.miskool_Entitiy.backGroundDB;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.Methods;
import com.zone.android.miskool_db.AppDatabase;

import java.util.List;

/**
 * Created by Inspiron on 30-06-2018.
 */

public class backGroundServices extends IntentService {

    public backGroundServices() {
        super("backGroundServices");
    }

    public backGroundServices(String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Methods.getBackgroundService(getApplicationContext());

    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return START_STICKY;
    }
}
