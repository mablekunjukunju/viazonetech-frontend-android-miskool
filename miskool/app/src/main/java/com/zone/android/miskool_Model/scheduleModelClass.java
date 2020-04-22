package com.zone.android.miskool_Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Schedule;
import com.zone.android.miskool_Presenter.schedulePresInterface;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.Methods;
import com.zone.android.miskool_db.AppDatabase;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Inspiron on 20-10-2018.
 */

public class scheduleModelClass implements scheduleModelInterface {
    @Override
    public void deleteTablesMessage(String studentID, Context context, schedulePresInterface schedulePresInterface) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                appdb.schedule_dao().DeleteScheduleAllUser(studentID);

                schedulePresInterface.deleteTablesMessageRes(Constants.PASS_VALIDATION);

            }
        }).start();
    }

    @Override
    public void getMessages(String Student_Id, schedulePresInterface schedulePresInterface, Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<Schedule> scheduleList;
                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                scheduleList= appdb.schedule_dao().getActiveSchedules("true","True",Student_Id);

                schedulePresInterface.setMessages(scheduleList);

            }
        }).start();

    }

    @Override
    public void getMessagesServer(String Student_Id, schedulePresInterface schedulePresInterface, Context context) {


        SharedPreferences masterPreference = context.getSharedPreferences(Constants.MASTER_USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences lastTokenPreference = context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE, Context.MODE_PRIVATE);

        String mastername = masterPreference.getString("mastername", "");
        String token = lastTokenPreference.getString("tokenNo", "");

        String url = Constants.GET_SCHEDULES_ALL;

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
        methods.getSchedulesUser(context, url, jsonMessage, schedulePresInterface,Student_Id);


    }

    @Override
    public void getSelEvents(String Student_Id, String date, schedulePresInterface schedulePresInterface, Context context) {


        new Thread(new Runnable() {
            @Override
            public void run() {


                List<Schedule> scheduleList;
                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                scheduleList= appdb.schedule_dao().getSelEvents("true","True",Student_Id,date);

                schedulePresInterface.setSelEvents(scheduleList);



            }
        }).start();
    }

    @Override
    public void sentNewEvent(String Student_Id, Schedule schedule, Context context, schedulePresInterface schedulePresInterface) {

        SharedPreferences masterPreference = context.getSharedPreferences(Constants.MASTER_USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences lastTokenPreference = context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE, Context.MODE_PRIVATE);


        String mastername = masterPreference.getString("mastername", "");
        String token = lastTokenPreference.getString("tokenNo", "");

        String url = Constants.GET_SCHEDULES_CREATE;

        JSONObject jsonMessage = new JSONObject();


        if (Student_Id.equals(mastername)) {
            try {
                jsonMessage.put("token", token);
                jsonMessage.put("starttime", schedule.getStarttime());
                jsonMessage.put("endtime", schedule.getEndtime());
                jsonMessage.put("subject", schedule.getSubject());
                jsonMessage.put("text", schedule.getText());


            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            try {
                jsonMessage.put("starttime", schedule.getStarttime());
                jsonMessage.put("endtime", schedule.getEndtime());
                jsonMessage.put("subject", schedule.getSubject());
                jsonMessage.put("text", schedule.getText());
                jsonMessage.put("token", token);
               // jsonMessage.put("forusername", Student_Id);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        Methods methods = new Methods(context);
        methods.sentNewSchedulesUser(context, url, jsonMessage, schedulePresInterface,Student_Id);


    }

    @Override
    public void sentUpEvent(String Student_Id, Schedule schedule, Context context, schedulePresInterface schedulePresInterface) {

        SharedPreferences masterPreference = context.getSharedPreferences(Constants.MASTER_USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences lastTokenPreference = context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE, Context.MODE_PRIVATE);



        String mastername = masterPreference.getString("mastername", "");
        String token = lastTokenPreference.getString("tokenNo", "");

        String url = Constants.GET_SCHEDULES_UPDATE;

        JSONObject jsonMessage = new JSONObject();


        if (Student_Id.equals(mastername)) {
            try {


                jsonMessage.put("token", token);
                jsonMessage.put("schedule_id", schedule.getPkey());
                jsonMessage.put("starttime", schedule.getStarttime());
                jsonMessage.put("endtime", schedule.getEndtime());
                jsonMessage.put("subject", schedule.getSubject());
                jsonMessage.put("text", schedule.getText());
                jsonMessage.put("active", "True");


            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            try {
                jsonMessage.put("token", token);
                jsonMessage.put("schedule_id", schedule.getPkey());
                jsonMessage.put("starttime", schedule.getStarttime());
                jsonMessage.put("endtime", schedule.getEndtime());
                jsonMessage.put("subject", schedule.getSubject());
                jsonMessage.put("text", schedule.getText());
                jsonMessage.put("active", "True");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        Methods methods = new Methods(context);
        methods.updatewSchedulesUser(context, url, jsonMessage, schedulePresInterface,Student_Id,schedule.getPkey());




    }

    @Override
    public void sentDelEvent(String Student_Id, Schedule schedule, Context context, schedulePresInterface schedulePresInterface) {

    }

    @Override
    public void insertUpSchedule(String StudentId, Schedule schedule, Context context,schedulePresInterface schedulePresInterface) {

        Methods.insertSchedulesUp(schedule,context,schedulePresInterface);

    }
}
