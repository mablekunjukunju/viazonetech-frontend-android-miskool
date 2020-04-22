package com.zone.android.miskool_Services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.zone.android.miskool_Entitiy.Token_det;
import com.zone.android.miskool_Model.BusDirectionModel;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.Methods;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Inspiron on 19-02-2018.
 */

public class BusTrackerService extends IntentService {
    SharedPreferences studentPreference ;

    public static int methodStatus;

    public BusTrackerService() {
        super("BusTrackerService");
    }

    public BusTrackerService(String name) {
        super(name);

    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

       Methods.getCurrentLocation(getApplicationContext());


        }




}
