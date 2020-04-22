package com.zone.android.miskool_Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.zone.android.miskool.mapViewInterface;
import com.zone.android.miskool_Entitiy.RoutePoint;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.Methods;
import com.zone.android.miskool_db.AppDatabase;

import org.json.JSONObject;

import java.util.List;

public class mapModelClass implements mapModelInterface {


    @Override
    public void deleteTable(String studentName, Context context, mapViewInterface mapViewInterface) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                appdb.route_pointDao().DeleteRoute();

                mapViewInterface.setMessage(Constants.PASS_VALIDATION);

            }
        }).start();
    }

    @Override
    public void getRouteList(String studentName, Context context, mapViewInterface mapViewInterface) {

        SharedPreferences masterPreference = context.getSharedPreferences(Constants.MASTER_USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences lastTokenPreference = context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE, Context.MODE_PRIVATE);

        String mastername = masterPreference.getString("mastername", "");
        String token = lastTokenPreference.getString("tokenNo", "");

        String url = Constants.GET_MESSAGE_ALL;

        JSONObject jsonMessage = new JSONObject();


        if (studentName.equals(mastername)) {
            try {
                jsonMessage.put("token", token);
                jsonMessage.put("forusername", studentName);


            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            try {
                jsonMessage.put("token", token);
                jsonMessage.put("forusername", studentName);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        Methods methods = new Methods(context);
         methods.getRouteList(jsonMessage,context,mapViewInterface);


    }

    @Override
    public void getStops(String studentName, Context context, mapViewInterface mapViewInterface) {

        Methods.getRoutePoints(context,mapViewInterface);


    }

    @Override
    public void getStopsFromDB(String studentName, Context context, mapViewInterface mapViewInterface) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                List<RoutePoint> routeList;

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                routeList = appdb.route_pointDao().getAll();

                mapViewInterface.setRouteList(routeList);

            }
        }).start();
    }
}
