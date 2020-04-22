package com.zone.android.miskool_Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.zone.android.miskool_Entitiy.Alerts;
import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Presenter.alertPresInterface;
import com.zone.android.miskool_Presenter.mailPresInterface;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.Methods;
import com.zone.android.miskool_db.AppDatabase;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Inspiron on 20-03-2018.
 */

public class alertModelClass implements alertModelInterface {


    @Override
    public void deleteTableMessage(String Student_Id, alertPresInterface alertPresInterface, Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                appdb.alerts_dao().DeleteUserAlerts(Student_Id);

                alertPresInterface.deleteTablesMessageRes(Constants.PASS_VALIDATION);

            }
        }).start();
    }

    @Override
    public void getAlertsSever(String Student_Id, alertPresInterface alertPresInterface, Context context) {

        SharedPreferences masterPreference = context.getSharedPreferences(Constants.MASTER_USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences lastTokenPreference = context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE, Context.MODE_PRIVATE);


        String mastername = masterPreference.getString("mastername", "");
        String token = lastTokenPreference.getString("tokenNo", "");

        String url = Constants.GET_ALERTS_ACTIVE;

        JSONObject jsonMessage = new JSONObject();


        if (Student_Id.equals(mastername)) {
            try {
                jsonMessage.put("token", token);

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            try {
                jsonMessage.put("token", token);
                jsonMessage.put("forusername", Student_Id);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        Methods methods = new Methods(context);
        methods.getAlertsUser(context, url, jsonMessage, alertPresInterface,Student_Id);

    }

    @Override
    public void getAlerts(final String Student_Id, final alertPresInterface alertPresInterface, final Context context) {


        new Thread(new Runnable() {
            @Override
            public void run() {


                List<Alerts> alertList;

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                alertList=appdb.alerts_dao().getAlertsStudent(Student_Id);

                alertPresInterface.setMessages(alertList);

            }
        }).start();
    }
}
