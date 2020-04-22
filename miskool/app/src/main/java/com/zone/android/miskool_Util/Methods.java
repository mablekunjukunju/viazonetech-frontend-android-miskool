package com.zone.android.miskool_Util;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;

import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;



import android.telecom.Call;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.zone.android.miskool.R;
import com.zone.android.miskool.mapViewInterface;
import com.zone.android.miskool_Entitiy.AlarmModel;
import com.zone.android.miskool_Entitiy.Alerts;
import com.zone.android.miskool_Entitiy.Attributes;
import com.zone.android.miskool_Entitiy.Bus_Route;
import com.zone.android.miskool_Entitiy.Config_det;
import com.zone.android.miskool_Entitiy.Contacts;
import com.zone.android.miskool_Entitiy.Message;
import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Message_Out;
import com.zone.android.miskool_Entitiy.Message_det;
import com.zone.android.miskool_Entitiy.Person_det;
import com.zone.android.miskool_Entitiy.RoutePoint;
import com.zone.android.miskool_Entitiy.Schedule;
import com.zone.android.miskool_Entitiy.Speech;
import com.zone.android.miskool_Entitiy.Token_det;
import com.zone.android.miskool_Entitiy.backGroundDB;
import com.zone.android.miskool_Model.BusDirectionModel;
import com.zone.android.miskool_Presenter.alertPresInterface;
import com.zone.android.miskool_Presenter.loginPresInterface;
import com.zone.android.miskool_Presenter.mailPresInterface;
import com.zone.android.miskool_Presenter.outMailPresInterface;
import com.zone.android.miskool_Presenter.replyPresInterface;
import com.zone.android.miskool_Presenter.schedulePresInterface;
import com.zone.android.miskool_Presenter.speechPresInterface;
import com.zone.android.miskool_View.loginViewInterface;
import com.zone.android.miskool_View.mainViewClass;
import com.zone.android.miskool_View.mainViewInterface;
import com.zone.android.miskool_View.registerInterface;
import com.zone.android.miskool_View.splashViewClass;
import com.zone.android.miskool_db.AppDatabase;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ocpsoft.prettytime.PrettyTime;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import static android.content.ContentValues.TAG;

/**
 * Created by Inspiron on 29-01-2018.
 */

public class Methods {

    Context context;
    static boolean  netwrk;
    String url;

    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";
    static CallWebservice Callweb =        new CallWebservice();

   static SharedPreferences lastTokenPreference,loginPreference;


    public Methods(Context context){
         this.context=context;
    }


    public synchronized static String id(Context context) {

            SharedPreferences sharedPrefs = context.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.commit();
            }

        return uniqueID;
    }



    public static void getConfigRequst(final Context context){

        String  REQUEST_TAG = "volleyStringRequest";
        String url="";
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TagStringrsp  ", response.toString());

                try {

                    JSONObject jsonObject1 = new JSONObject(response.toString());
                    JSONArray jsonArray = jsonObject1.getJSONArray("");
                    for(int i=0;i<jsonArray.length();i++){
                       JSONObject serviceObj=jsonArray.getJSONObject(i);

                        String serviceName=serviceObj.getString("ServiceName");
                        String serviceUrl=serviceObj.getString("ServiceUrl");


                        AppDatabase appdb = AppDatabase.getAppDatabase(context);

                        Config_det config_det = new Config_det();
                        config_det.setServiceId("xxxxx");
                        config_det.setServiceName(serviceName);
                        config_det.setServiceUrl(serviceUrl);

                        //        db.messageIn_dao().insertAll(msg);

                        appdb.config_dao().insertAll(config_det);


                    }

                    //need to call next service

                }catch (Exception e){
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TagStringrsp  ", "Error: " + error.getMessage());
            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(context).addToRequestQueue(strReq, REQUEST_TAG);
    }


public static  int getToken(String userName, String passWord, String uuid, final Context context){

    final boolean[] isSuccess = {false};

    final int[] statusCode = {0};

    String testurl="http://identity.01.via.zone:6001/login/loginservice/GenerateAccessToken/?username=FA001LA001&password=LA001FA001&macid=6";

    String finalUrl= Constants.GENERATE_AUTH_TOKEN+"username="+userName+"&password="+passWord+"&macid="+uuid;


    Callweb.CallGetWebServices(finalUrl, new ServiceCallback() {
        @Override
        public void SuccessCallbak(String resp) throws JSONException {


           try {
               JSONObject jsonresp = new JSONObject(resp.toString());

               //{"status": "success", "token": "0944667b-4017-4623-876a-d99c04ae36f9"}

           if(jsonresp!=null) {
               String status = jsonresp.getString("status");

               statusCode[0] = jsonresp.getInt("statuscode");

               if (status.equals("success")) {
                   String token = jsonresp.getString("token");

                 //  insertToken(token, context);
               }

           } else{
               statusCode[0]=0;

           }

               }catch(Exception e){
                   e.printStackTrace();
               statusCode[0]=0;

           }

        }

        @Override
        public void ErrorCallbak(String resp) {
            statusCode[0]=0;

        }
    });
    return   statusCode[0];

}


public static void getAccessToken(String userName, String passWord, String uuid, final loginPresInterface loginPresInterface, final Context context){

  //  String finalUrl= Constants.GET_ACCESS_TOKEN+"testuser001@via.zone/testuser001@via.zone/"+uuid+"/";

      String finalUrl= Constants.GET_LOGIN+userName+"/"+passWord+"/"+uuid+"/";

      Log.e("finalUrl ","finalUrl "+finalUrl);

    lastTokenPreference = context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE,Context.MODE_PRIVATE);

      Callweb.CallGetWebServices(finalUrl, new ServiceCallback() {
        @Override
        public void SuccessCallbak(String resp) throws JSONException {

            Log.e("miskool","miskoolAccessTokenresp "+resp.toString());


            try{

                JSONObject jsonObject = new JSONObject(resp.toString());

                String status=jsonObject.getString("status");

                if(status.contains("OK")){

                    String accesstoken=jsonObject.getString("accesstoken");

                    SharedPreferences.Editor editor = lastTokenPreference.edit();
                    editor.putString("tokenNo",accesstoken);
                    editor.putInt("tokeInc",0);
                    editor.commit();


                    insertToken(accesstoken,context,loginPresInterface);


                }else{

                    loginPresInterface.showError(Constants.ERROR_CREDENTIALS);

                }

            }catch (Exception e){
                e.printStackTrace();

                loginPresInterface.showError(Constants.ERROR_SERVICE);
            }

        }

        @Override
        public void ErrorCallbak(String resp) {
            loginPresInterface.showError(Constants.ERROR_SERVICE);

        }
    });

}



    public static void postAccessToken(String userName, String passWord, String uuid, final loginPresInterface loginPresInterface, final Context context){

        //  String finalUrl= Constants.GET_ACCESS_TOKEN+"testuser001@via.zone/testuser001@via.zone/"+uuid+"/";

        String finalUrl= Constants.GET_LOGIN+userName+"/"+passWord+"/"+uuid+"/";

        Log.e("finalUrl ","finalUrl "+finalUrl);

        lastTokenPreference = context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE,Context.MODE_PRIVATE);

        JSONObject jsonLogin= new JSONObject();

      try {
          jsonLogin.put("username", userName);
          jsonLogin.put("password",passWord);
      }catch (Exception e){
          e.printStackTrace();
      }

        Callweb.CallPostWebServices(Constants.GET_LOGIN, jsonLogin.toString(), new ServiceCallback() {
            @Override
            public void SuccessCallbak(String resp) throws JSONException {

                Log.e("miskool","miskoolAccessTokenresp "+resp.toString());


                try{

                    JSONObject jsonObject = new JSONObject(resp.toString());

                    String status=jsonObject.getString("status");

                    if(status.contains("OK")){

                        String accesstoken=jsonObject.getString("token");

                        SharedPreferences.Editor editor = lastTokenPreference.edit();
                        editor.putString("tokenNo",accesstoken);
                        editor.putInt("tokeInc",0);
                        editor.commit();


                        insertToken(accesstoken,context,loginPresInterface);


                    }else{

                        loginPresInterface.showError(Constants.ERROR_CREDENTIALS);

                    }

                }catch (Exception e){
                    e.printStackTrace();

                    loginPresInterface.showError(Constants.ERROR_SERVICE);
                }


            }

            @Override
            public void ErrorCallbak(String resp) {

            }
        });
    }


    public static void getUsers(final Context context, final loginPresInterface loginPresInterface){

        final SharedPreferences studentPreference= context.getSharedPreferences(Constants.STUDENT_SELECTION_PREFERENCE,Context.MODE_PRIVATE);
        final SharedPreferences masterPreference= context.getSharedPreferences(Constants.MASTER_USER_PREFERENCE,Context.MODE_PRIVATE);


        final String token=lastTokenPreference.getString("tokenNo","");
        deleteFromtables(context);

        String url =Constants.GET_USERS;

        JSONObject jsonUsers = new JSONObject();
        try {
            jsonUsers.put("token",token);

        } catch (JSONException e) {
            e.printStackTrace();
        }
       Callweb.CallSendPostServices(url, jsonUsers, new ServiceCallback() {
           @Override
           public void SuccessCallbak(String resp) throws JSONException {

               Log.e("getUsers ","getUsers "+resp.toString());
               resp.toString().replace("\\","");

               JSONObject jsonResp= new JSONObject(resp.toString());
               String status=jsonResp.getString("status");

               if(status.contains("true")){

                   String mastername=studentPreference.getString("studentname","test name");
                   String masterid=studentPreference.getString("studentid","test id");

                   SharedPreferences.Editor editormaster = masterPreference.edit();
                   editormaster.putString("masterid", masterid);
                   editormaster.putString("mastername",mastername);
                   editormaster.commit();


                   Person_det person_det2=new Person_det();
                   person_det2.setStudentId(masterid);
                   person_det2.setFirstName(mastername);
                   person_det2.setIdentitiy("M");

                   insertPersonalDetails(person_det2,context,loginPresInterface);

                   String array=jsonResp.getString("users");

                   JSONArray userArray = new JSONArray(array);
                   for(int i=0;i<userArray.length();i++){

                       JSONObject jsonUser=userArray.getJSONObject(i);
                       String userKey=jsonUser.getString("userpkey");
                       String username=jsonUser.getString("username");


                       Log.e("username ","username "+username);

                       Person_det person_det1=new Person_det();
                       person_det1.setStudentId(userKey);
                       person_det1.setFirstName(username);
                       person_det1.setIdentitiy("R");

                       insertPersonalDetails(person_det1,context,loginPresInterface);

                   }


               }

               loginPresInterface.setNavigation();
           }

           @Override
           public void ErrorCallbak(String resp) {

           }
       });


    }


    public static void updatereadFlagOnrepley(String message_in, final Context context, String studentId){

        SharedPreferences lastTokenPreference =context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE,Context.MODE_PRIVATE);

        final String token=lastTokenPreference.getString("tokenNo","");

        SharedPreferences masterPreference = context.getSharedPreferences(Constants.MASTER_USER_PREFERENCE, Context.MODE_PRIVATE);
        String mastername = masterPreference.getString("mastername", "");


        String updateURL=Constants.UPDATE_READ_FLAG;
        JSONObject jsonUpdate=new JSONObject();

        if (studentId.equals(mastername)) {

            try {

                jsonUpdate.put("token", token);
                jsonUpdate.put("message_id", message_in);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{

            try{

                jsonUpdate.put("token", token);
                jsonUpdate.put("message_id", message_in);
                jsonUpdate.put("forusername", studentId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

       Callweb.CallSendPostServices(updateURL, jsonUpdate, new ServiceCallback() {
           @Override
           public void SuccessCallbak(String resp) throws JSONException {

               Log.e("updateReadResp","updateReadResp" +resp.toString());
               Log.e("updateReadResp","updateReadResp" +resp.toString());

               JSONObject jsonResponse= new JSONObject(resp.toString());

               String status=jsonResponse.getString("status");

               if(status.contains("true")){

                   JSONObject jsonMessage=jsonResponse.getJSONObject("message");

                   String messageId=jsonMessage.getString("pkey");
                   String frompersonid=jsonMessage.getString("fromusername");

                   String topersonid=jsonMessage.getString("tousername");
                   String createdtimestamp=convertGMT(jsonMessage.getString("doc"),context);

                   String threadid=jsonMessage.getString("threadid");
                   String message= jsonMessage.getString("text");
                   String subject=jsonMessage.getString("subject");

                   String readflag=convertGMT(jsonMessage.getString("dor"),context);

                   Message_In message_in = new Message_In();

                   message_in.setStudentId(topersonid);
                   message_in.setMessageInId(messageId);
                   message_in.setThreadId(threadid);
                   message_in.setMessageSender(frompersonid);
                   message_in.setMessageReceiver(topersonid);
                   message_in.setMessageDateOfCreated(createdtimestamp);
                   message_in.setMessageSub(subject);
                   message_in.setMessages(message);
                   message_in.setReadFlag(readflag);

                   updateReadFlagDB(message_in,context);
               }

           }

           @Override
           public void ErrorCallbak(String resp) {

           }
       });

    }

    public static void updatereadFlag(Message_In message_in, final Context context, final mailPresInterface mailPresInterface, String studentId){
        SharedPreferences lastTokenPreference =context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE,Context.MODE_PRIVATE);

        final String token=lastTokenPreference.getString("tokenNo","");

        SharedPreferences masterPreference = context.getSharedPreferences(Constants.MASTER_USER_PREFERENCE, Context.MODE_PRIVATE);
        String mastername = masterPreference.getString("mastername", "");


        String updateURL=Constants.UPDATE_READ_FLAG;
        JSONObject jsonUpdate=new JSONObject();

        if (studentId.equals(mastername)) {

            try {

                jsonUpdate.put("token", token);
                jsonUpdate.put("message_id", message_in.getMessageInId());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{

            try{

                jsonUpdate.put("token", token);
                jsonUpdate.put("message_id", message_in.getMessageInId());
                jsonUpdate.put("forusername", studentId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }


        Callweb.CallSendPostServices(updateURL, jsonUpdate, new ServiceCallback() {
            @Override
            public void SuccessCallbak(String resp) throws JSONException {

                Log.e("updateReadResp","updateReadResp" +resp.toString());

                JSONObject jsonResponse= new JSONObject(resp.toString());

                String status=jsonResponse.getString("status");

                if(status.contains("true")){

                    JSONObject jsonMessage=jsonResponse.getJSONObject("message");

                    String messageId=jsonMessage.getString("pkey");
                    String frompersonid=jsonMessage.getString("fromusername");

                    String topersonid=jsonMessage.getString("tousername");
                    String createdtimestamp=convertGMT(jsonMessage.getString("doc"),context);

                    String threadid=jsonMessage.getString("threadid");
                   String message= jsonMessage.getString("text");
                    String subject=jsonMessage.getString("subject");

                    String readflag=convertGMT(jsonMessage.getString("dor"),context);

                    Message_In message_in = new Message_In();

                    message_in.setStudentId(topersonid);
                    message_in.setMessageInId(messageId);
                    message_in.setThreadId(threadid);
                    message_in.setMessageSender(frompersonid);
                    message_in.setMessageReceiver(topersonid);
                    message_in.setMessageDateOfCreated(createdtimestamp);
                    message_in.setMessageSub(subject);
                    message_in.setMessages(message);
                    message_in.setReadFlag(readflag);

                    updateReadFlag(message_in,context,mailPresInterface);
                }

            }

            @Override
            public void ErrorCallbak(String resp) {

            }
        });

    }

    public static void updateReadFlagDB(final Message_In message_in, final Context context){

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                appdb.messageIn_dao().updateReadFlag(message_in.getStudentId(),message_in.getMessageInId(),message_in.getReadFlag());


            }
        }).start();
    }


    public static void updateReadFlag(final Message_In message_in, final Context context, final mailPresInterface mailPresInterface){

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                appdb.messageIn_dao().updateReadFlag(message_in.getStudentId(),message_in.getMessageInId(),message_in.getReadFlag());

               mailPresInterface.updateReadFlagResponse(message_in);

            }
        }).start();
    }

    public static void getPersonWithToken(final Context context, final loginPresInterface loginPresInterface){
        Log.e("insidegetPerson","insidegetPerson");


      /*  Token_det token_det = new Token_det();
        token_det =getTokeStatus(context);

        String token=token_det.getToken();
        final int tokenInc=token_det.getTokenInc()+1;*/

      final String token=lastTokenPreference.getString("tokenNo","");
      final int tokenInc=lastTokenPreference.getInt("tokeInc",0)+1;


        Log.e("tokenDetailsSize ","tokenDetailstoken "+token);
        Log.e("tokenDetailsSizeInc ","tokenDetailstokenInc "+tokenInc);


        String id=id(context);
        String finalURL= Constants.GET_USER_TOKEN+token+"/"+id+"/"+tokenInc+"/";


        deleteFromtables(context);

       Callweb.CallGetWebServices(finalURL, new ServiceCallback() {
        @Override
        public void SuccessCallbak(String resp) throws JSONException {

            JSONObject jsonObject =new JSONObject(resp.toString());

            String status= jsonObject.getString("status");

            if(status.contains("OK")){


                //incrementing token in sharedpreference as well as in db
                SharedPreferences.Editor editor = lastTokenPreference.edit();
                editor.putString("tokenNo",token);
                editor.putInt("tokeInc",tokenInc);
                editor.commit();


                incrementToken(context,tokenInc);

                Log.e("getPersonWithToken ","getPersonWithTokenresp "+resp.toString());

             //adding users

                JSONObject objBaseUser= jsonObject.getJSONObject("baseuser");

                    //////////////////////////////////////////////////////////////

                    String username1=objBaseUser.getString("username");
                    String userid1=objBaseUser.getString("id");

                    JSONArray arrayAlerts1=objBaseUser.getJSONArray("alerts");

                    for(int x=0;x<arrayAlerts1.length();x++){

                        JSONObject jsonObject2= arrayAlerts1.getJSONObject(x);
                        String alert_id=jsonObject2.getString("pkey");
                        String alert_date=jsonObject2.getString("start_time");
                        String alert_enddate=jsonObject2.getString("end_time");
                        String alert_sub=jsonObject2.getString("subject");
                        String alert_message=jsonObject2.getString("text");

                        Alerts alerts=new Alerts();
                        alerts.setAlertId(alert_id);
                        alerts.setStudentId(userid1);
                        alerts.setAlertDate(converISO8601stamp(alert_date));
                        String alertdate=converISO8601stamp(alert_date);

                        Log.e("alertdate","alertdate "+alertdate);
                        alerts.setAlertEndDate(converISO8601stamp(alert_enddate));
                        alerts.setAlertSub(alert_sub);
                        alerts.setAlertMsg(alert_message);

                     //   insertAlerts(alerts,context);

                    }


                    JSONArray arrayMessages1=objBaseUser.getJSONArray("messages");
                    for(int j=0;j<arrayMessages1.length();j++){

                        JSONObject jsonObject2 = arrayMessages1.getJSONObject(j);

                        String messageId=jsonObject2.getString("pkey");
                        String frompersonid__username=jsonObject2.getString("frompersonid__username");

                        String frompersonid=jsonObject2.getString("frompersonid");

                        String topersonid__username=jsonObject2.getString("topersonid__username");

                        String topersonid=jsonObject2.getString("topersonid");
                        String createdtimestamp=converISO8601stamp(jsonObject2.getString("createdtimestamp"));

                        String threadid=jsonObject2.getString("threadid");
                        String message= jsonObject2.getString("text");
                        String subject=jsonObject2.getString("subject");

                        String priority=jsonObject2.getString("priority");


                        Message_In message_in = new Message_In();

                        message_in.setStudentId(userid1);
                        message_in.setMessageInId(messageId);
                        message_in.setThreadId(threadid);
                        message_in.setMessageSender(frompersonid);
                        message_in.setMessageReceiver(topersonid);
                        message_in.setMessageDateOfCreated(createdtimestamp);
                        message_in.setMessageSub(subject);
                        message_in.setMessages(message);
                     //  message_in.setReadFlag(0);

                        //insertMessages(message_in,context);


                    }

                    Person_det person_det1=new Person_det();
                    person_det1.setStudentId(userid1);
                    person_det1.setFirstName(username1);
                    person_det1.setIdentitiy("P");


                  // insertPersonalDetails(person_det1,context);

                    /////////////////////////////////////////



                //adding subusers


                JSONArray arraySubuser= jsonObject.getJSONArray("subusers");

                for(int i =0;i<arraySubuser.length();i++){

                    JSONObject jsonObject1=arraySubuser.getJSONObject(i);

                    String username=jsonObject1.getString("username");
                    String userid=jsonObject1.getString("id");

                    JSONArray arrayAlerts=jsonObject1.getJSONArray("alerts");

                    for(int x=0;x<arrayAlerts.length();x++){

                        JSONObject jsonObject2= arrayAlerts.getJSONObject(x);
                        String alert_id=jsonObject2.getString("pkey");
                        String alert_date=jsonObject2.getString("start_time");
                        String alert_enddate=jsonObject2.getString("end_time");
                        String alert_sub=jsonObject2.getString("subject");
                        String alert_message=jsonObject2.getString("text");

                        Alerts alerts=new Alerts();
                        alerts.setAlertId(alert_id);
                        alerts.setStudentId(userid);
                        alerts.setAlertDate(converISO8601stamp(alert_date));
                        String alertdate=converISO8601stamp(alert_date);
                      //  alerts.setAlertAdd(0);
                        Log.e("alertdate","alertdate "+alertdate);
                        alerts.setAlertEndDate(converISO8601stamp(alert_enddate));
                        alerts.setAlertSub(alert_sub);
                        alerts.setAlertMsg(alert_message);

                        //insertAlerts(alerts,context);

                    }


                    JSONArray arrayMessages=jsonObject1.getJSONArray("messages");
                     for(int j=0;j<arrayMessages.length();j++){

                         JSONObject jsonObject2 = arrayMessages.getJSONObject(j);

                         String messageId=jsonObject2.getString("pkey");
                         String frompersonid__username=jsonObject2.getString("frompersonid__username");

                         String frompersonid=jsonObject2.getString("frompersonid");

                         String topersonid__username=jsonObject2.getString("topersonid__username");

                         String topersonid=jsonObject2.getString("topersonid");
                         String createdtimestamp=converISO8601stamp(jsonObject2.getString("createdtimestamp"));


                         String threadid=jsonObject2.getString("threadid");
                         String message= jsonObject2.getString("text");
                         String subject=jsonObject2.getString("subject");

                         String priority=jsonObject2.getString("priority");


                         Message_In message_in = new Message_In();

                         message_in.setStudentId(userid);
                         message_in.setMessageInId(messageId);
                         message_in.setThreadId(threadid);
                         message_in.setMessageSender(frompersonid);
                         message_in.setMessageReceiver(topersonid);
                         message_in.setMessageDateOfCreated(createdtimestamp);
                         message_in.setMessageSub(subject);
                         message_in.setMessages(message);

                      //   insertMessages(message_in,context);


                     }

                    Person_det person_det=new Person_det();
                    person_det.setStudentId(userid);
                    person_det.setFirstName(username);
                    person_det.setIdentitiy("S");
                   // insertPersonalDetails(person_det,context);

                }


                //ending iff.......

                loginPresInterface.setNavigation();


            }else{

            }

        }

        @Override
        public void ErrorCallbak(String resp) {

            loginPresInterface.showError(Constants.ERROR_SERVICE);

        }
    });

     }



     public static int GetUserWithToken(Context context){

    final int[] statusCode = {0};
    statusCode[0] = 0;

    Token_det token_det = new Token_det();
    token_det =getTokeStatus(context);

    String token=token_det.getToken().toString();
    int tokenInc=token_det.getTokenInc();
    String id=id(context);

    String finalUrl=Constants.GET_USER_TOKEN+"accesstoken="+token+"&instanceid="+id+"&incrementid="+tokenInc;


    Callweb.CallGetWebServices("", new ServiceCallback() {
        @Override
        public void SuccessCallbak(String resp) throws JSONException {

            Log.e("miSkool","miSkoolUserWithToken "+resp.toString());

            JSONObject jsonObject = new JSONObject(resp.toString());

        }

        @Override
        public void ErrorCallbak(String resp) {

        }
    });

    return statusCode[0];

}

    public static  int putReply(Message_Out message_out, final Context context, final mailPresInterface mailPresInterface){

        //changee this according to service

        final boolean[] isSuccess = {false};

        final int[] statusCode = {0};

        String testurl=Constants.PUT_MSG_REPLY_DETAILS;

        Token_det token_det = new Token_det();
        token_det =getTokeStatus(context);

        String token="xxxxx";
        int tokenInc=2;

        final String finalUrl= testurl+"authid"+"inc"+"message_outdetails";


        try {
            Callweb.CallGetWebServices(finalUrl, new ServiceCallback() {
                @Override
                public void SuccessCallbak(String resp) throws JSONException {

                    isSuccess[0] = true;

                    try {
                        JSONObject jsonresp = new JSONObject(resp.toString());

                        //{"status": "success", "token": "0944667b-4017-4623-876a-d99c04ae36f9"}

                        String status = jsonresp.getString("status");
                        if (status.equals("success")) {

                           // mailPresInterface.showMessage("Message sent successfully");



                            // insertToken(token,context,loginPresInterface);

                        } else {

                           // mailPresInterface.showMessage("Message not sent");
                            Log.e("msx1","msx1");
                            backGroundDB backGroundDB = new backGroundDB();
                            backGroundDB.setJsonName("outMessage");
                            backGroundDB.setJsonValue(finalUrl);
                            backGroundDB.setUpdateStatus("0");
                            insertBackUP(backGroundDB,context);



                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                       // mailPresInterface.showMessage("Message not sent");
                        Log.e("msx1","msx1");

                        backGroundDB backGroundDB = new backGroundDB();
                        backGroundDB.setJsonName("outMessage");
                        backGroundDB.setJsonValue(finalUrl);
                        backGroundDB.setUpdateStatus("0");
                        insertBackUP(backGroundDB,context);

                    }

                }

                @Override
                public void ErrorCallbak(String resp) {

                  //  mailPresInterface.showMessage("Message not sent");
                    Log.e("msx1","msx1");

                    backGroundDB backGroundDB = new backGroundDB();
                    backGroundDB.setJsonName("outMessage");
                    backGroundDB.setJsonValue(finalUrl);
                    backGroundDB.setUpdateStatus("0");
                    insertBackUP(backGroundDB,context);

                    isSuccess[0] = false;

                    statusCode[0] = 0;

                }
            });

        }catch (Exception e){
            e.printStackTrace();
            Log.e("msx1","msx1 "+e);
           // mailPresInterface.showMessage("Message not sent");
            backGroundDB backGroundDB = new backGroundDB();
            backGroundDB.setJsonName("outMessage");
            backGroundDB.setJsonValue(finalUrl);
            backGroundDB.setUpdateStatus("0");
            insertBackUP(backGroundDB,context);
        }

        return   statusCode[0];

    }


    public static void sendNew(String url, JSONObject jsonObject, Context context, final mailPresInterface mailPresInterface){


        Callweb.CallSendPostServices(url, jsonObject, new ServiceCallback() {
            @Override
            public void SuccessCallbak(String resp) throws JSONException {

                JSONObject jsonresp=new JSONObject(resp.toString());

                Log.e("messagenew","messagenew "+resp.toString());
                String status=jsonresp.getString("status");
                if(status.contains("true")){

                    mailPresInterface.showMessage(Constants.PASS_SERVICE);
                }else{

                    mailPresInterface.showMessage(Constants.ERROR_SERVICE);
                }

            }

            @Override
            public void ErrorCallbak(String resp) {

            }
        });
    }



    public static void sendSpeech(Speech speech, Context context, speechPresInterface speechPresInterface){

        String url=Constants.GET_AI;
        String message=speech.getMessages();

        SharedPreferences lastTokenPreference =context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE,Context.MODE_PRIVATE);

        final String token=lastTokenPreference.getString("tokenNo","");

        JSONObject jsonObject = new JSONObject();

        try{

            jsonObject.put("token",token);
            jsonObject.put("question",message);


        }catch (Exception e){
            e.printStackTrace();
        }


   Callweb.CallSendPostServices(url, jsonObject, new ServiceCallback() {
       @Override
       public void SuccessCallbak(String resp) throws JSONException {

        String response=resp.toString();

           JSONObject jsonResponse= new JSONObject(resp);

           String status=jsonResponse.getString("status");

           if(status.contains("true")){

              String askresponse=jsonResponse.getString("response");

              JSONArray jsonArray=new JSONArray(askresponse);

              if(jsonArray.length()>0) {

                  String aiResponse = jsonArray.get(1).toString();

                  Calendar c = Calendar.getInstance();
                  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                  String strDate = sdf.format(c.getTime());



                  Speech speech1 = new Speech();
                  speech1.setMessageSender("ai");
                  speech1.setMessageDate(strDate);
                  speech1.setMessageReceiver(speech.getMessageSender());
                  speech1.setMessages(aiResponse);
                  speech1.setStudentId(speech.getStudentId());


                  insertSpeechResponse(speech1,context,speechPresInterface);
              }



           }
       }

       @Override
       public void ErrorCallbak(String resp) {

       }
   });

    }

public static void insertSpeechResponse(Speech speech,Context context,speechPresInterface speechPresInterface){

new Thread(new Runnable() {
    @Override
    public void run() {

        AppDatabase appdb = AppDatabase.getAppDatabase(context);
        appdb.speech_dao().insertAll(speech);

        speechPresInterface.sentMessage(Constants.PASS_SERVICE);

    }
}).start();
}



    public static void sendReply(String url, JSONObject jsonObject, Context context, final replyPresInterface replyPresInterface){

        Callweb.CallSendPostServices(url, jsonObject, new ServiceCallback() {
            @Override
            public void SuccessCallbak(String resp) throws JSONException {


                // String response=resp.toString().replace("\\","");
                String response=resp;
                response.replace(" ","");
                Log.e("getMessageUser","getMessageUser "+response);

                JSONObject jsonResponse = new JSONObject(response);

                String status=jsonResponse.getString("status");

                if(status.contains("true")){


                    JSONObject jsonObject2=jsonResponse.getJSONObject("message");
                //    JSONArray messageArray = new JSONArray(array);

                    if(jsonObject2!=null) {

                            String messageId = jsonObject2.getString("pkey");
                            String frompersonid = jsonObject2.getString("fromusername");

                            String topersonid = jsonObject2.getString("tousername");
                            String createdtimestamp = convertGMTCreated(jsonObject2.getString("doc"),context);

                            String threadid = jsonObject2.getString("threadid");
                            String message = jsonObject2.getString("text");
                            String subject = jsonObject2.getString("subject");

                           // String readflag = convertGMT(jsonObject2.getString("dor"),context);
                        String readflag=createdtimestamp;

                            Message_In message_in = new Message_In();

                            message_in.setStudentId(topersonid);
                            message_in.setMessageInId(messageId);
                            message_in.setThreadId(threadid);
                            message_in.setMessageSender(frompersonid);
                            message_in.setMessageReceiver(topersonid);
                            message_in.setMessageDateOfCreated(createdtimestamp);
                            message_in.setMessageSub(subject);
                            message_in.setMessages(message);
                            message_in.setReadFlag(readflag);

                            updateReplyMessages(message_in, context, replyPresInterface, topersonid);


                    }else{

                        replyPresInterface.showMessage(Constants.ERROR_SERVICE);
                    }

                }




            }

            @Override
            public void ErrorCallbak(String resp) {

            }
        });
        }



    public static  int putReplyNew(Message_In message_in, final Context context, final replyPresInterface replyPresInterface){

        //changee this according to service

        final boolean[] isSuccess = {false};

        final int[] statusCode = {0};

        String testurl=Constants.PUT_MSG_REPLY_DETAILS;

        Token_det token_det = new Token_det();
        token_det =getTokeStatus(context);

        String token="xxxxx";
        int tokenInc=2;

        final String finalUrl= testurl+"authid"+"inc"+"message_outdetails";


        try {
            Callweb.CallGetWebServices(finalUrl, new ServiceCallback() {
                @Override
                public void SuccessCallbak(String resp) throws JSONException {

                    isSuccess[0] = true;

                    try {
                        JSONObject jsonresp = new JSONObject(resp.toString());

                        //{"status": "success", "token": "0944667b-4017-4623-876a-d99c04ae36f9"}

                        String status = jsonresp.getString("status");
                        if (status.equals("success")) {

                           // replyPresInterface.showMessage("Message sent successfully");



                            // insertToken(token,context,loginPresInterface);

                        } else {

                         //   replyPresInterface.showMessage("Message not sent");
                            Log.e("msx1","msx1");
                            backGroundDB backGroundDB = new backGroundDB();
                            backGroundDB.setJsonName("outMessage");
                            backGroundDB.setJsonValue(finalUrl);
                            backGroundDB.setUpdateStatus("0");
                            insertBackUP(backGroundDB,context);



                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                       // replyPresInterface.showMessage("Message not sent");
                        Log.e("msx1","msx1");

                        backGroundDB backGroundDB = new backGroundDB();
                        backGroundDB.setJsonName("outMessage");
                        backGroundDB.setJsonValue(finalUrl);
                        backGroundDB.setUpdateStatus("0");
                        insertBackUP(backGroundDB,context);

                    }

                }

                @Override
                public void ErrorCallbak(String resp) {

                   // replyPresInterface.showMessage("Message not sent");
                    Log.e("msx1","msx1");

                    backGroundDB backGroundDB = new backGroundDB();
                    backGroundDB.setJsonName("outMessage");
                    backGroundDB.setJsonValue(finalUrl);
                    backGroundDB.setUpdateStatus("0");
                    insertBackUP(backGroundDB,context);

                    isSuccess[0] = false;

                    statusCode[0] = 0;

                }
            });

        }catch (Exception e){
            e.printStackTrace();
            Log.e("msx1","msx1 "+e);
           // replyPresInterface.showMessage("Message not sent");
            backGroundDB backGroundDB = new backGroundDB();
            backGroundDB.setJsonName("outMessage");
            backGroundDB.setJsonValue(finalUrl);
            backGroundDB.setUpdateStatus("0");
            insertBackUP(backGroundDB,context);
        }

        return   statusCode[0];

    }

    public static List<Person_det> getDefaultStudent(final Context context){
        final List<Person_det> stlist=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {

                Log.e("insidegetDfltStudent ","insidegetDfltStudent ");
                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                List<Person_det> stlist1 =appdb.person_dao().getDefaultStudent();
                Person_det person_det=new Person_det();
                person_det=stlist1.get(0);

                stlist.add(person_det);

            }
        }).start();

        return  stlist;
    }

    public static List<Person_det> getStudentList(final Context context){
        final List<Person_det> stlist=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                List<Person_det> stlist1 =appdb.person_dao().getPersonDetails();

                for(Person_det person_det:stlist1){
                   stlist.add(person_det);
                }

            }
        }).start();

        return  stlist;
    }



    public static boolean sendOutmessages(final Message_Out message_out, final Context context){
        final boolean[] isSuccess = {false};

        try {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    String finalUrl = Constants.PUT_MSG_SEND_DETAILS;

                    Callweb.CallGetWebServices(finalUrl, new ServiceCallback() {
                        @Override
                        public void SuccessCallbak(String resp) throws JSONException {

                            try {

                                JSONObject jsonObject2 = new JSONObject(resp.toString());

                                String status = jsonObject2.getString("status");

                                if (status.equals("OK")) {
                                    isSuccess[0] = true;

                                    String messageId = jsonObject2.getString("pkey");
                                    String frompersonid__username = jsonObject2.getString("frompersonid__username");

                                    String frompersonid = jsonObject2.getString("frompersonid");

                                    String topersonid__username = jsonObject2.getString("topersonid__username");

                                    String topersonid = jsonObject2.getString("topersonid");
                                    String createdtimestamp = jsonObject2.getString("createdtimestamp");

                                    String threadid = jsonObject2.getString("threadid");
                                    String message = jsonObject2.getString("text");
                                    String subject = jsonObject2.getString("subject");

                                    String priority = jsonObject2.getString("priority");

                                    Message_In message_in = new Message_In();

                                    message_in.setStudentId(message_out.getStudentId());
                                    message_in.setMessageInId(messageId);
                                    message_in.setThreadId(threadid);
                                    message_in.setMessageSender(frompersonid);
                                    message_in.setMessageReceiver(topersonid);
                                    message_in.setMessageDateOfCreated(createdtimestamp);
                                    message_in.setMessageSub(subject);
                                    message_in.setMessages(message);

                                  //  insertMessages(message_in, context);
                                } else {
                                    isSuccess[0] = false;

                                    Intent localIntent1 =
                                            new Intent(Constants.BROADCAST_ACTION_MSG)
                                                    // Puts the status into the Intent
                                                    .putExtra(Constants.EXTENDED_DATA_STATUS_MSG, Constants.ERROR_SERVICE);
                                    // Broadcasts the Intent to receivers in this app.
                                    LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent1);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                isSuccess[0] = false;

                                Intent localIntent1 =
                                        new Intent(Constants.BROADCAST_ACTION_MSG)
                                                // Puts the status into the Intent
                                                .putExtra(Constants.EXTENDED_DATA_STATUS_MSG, Constants.ERROR_SERVICE);
                                // Broadcasts the Intent to receivers in this app.
                                LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent1);
                            }
                        }

                        @Override
                        public void ErrorCallbak(String resp) {
                            isSuccess[0] = false;

                            Intent localIntent1 =
                                    new Intent(Constants.BROADCAST_ACTION_MSG)
                                            // Puts the status into the Intent
                                            .putExtra(Constants.EXTENDED_DATA_STATUS_MSG, Constants.ERROR_SERVICE);
                            // Broadcasts the Intent to receivers in this app.
                            LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent1);

                        }
                    });

                }
            }).start();


        }catch (Exception e){

            e.printStackTrace();
        }
        return   isSuccess[0];
    }

    public static void insertBackUP(final backGroundDB backGroundDB, final Context context){

        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                appdb.background_dao().insertAll(backGroundDB);



            }
        }).start();


    }


    public static boolean GetPersonWithToken(final Context context){

        final boolean[] isSuccess = {false};

        String  REQUEST_TAG = "personalDetailsRequest";
        Token_det token_det = new Token_det();
        token_det =getTokeStatus(context);

        String token=token_det.getToken().toString();
        int tokenInc=token_det.getTokenInc();
//token=490ac2df-a54d-44ef-8047-3b0931764a6b&inc=3

        //output: {"status": "success", "lastname": "LA001", "firstname": "FA001"}

        //need to sent person id and last inserted student id so gat we dot have to insert it all over again

        //////////////////////////////
       /* HashMap<String,String> hashMap = new HashMap<String,String>();
        hashMap=getDbIds(context,Constants.DB_PERSON);

        String accoundId=hashMap.get("accounId");
        String studentId=hashMap.get("studentId");*/
        ///////////////////////////////////

        String url=Constants.GetPersonWithToken+"token="+token+"&inc="+tokenInc;

        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TagStringrsp  ", response.toString());


                try {

                    JSONObject jsonObject1 = new JSONObject(response.toString());

                    String status=jsonObject1.getString("status");

                    if(status.equals("success")) {

                        isSuccess[0]=true;
                        JSONArray jsonArray = jsonObject1.getJSONArray("personaldetails");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String studentId=jsonObject.getString("studentid");
                            String lastname=jsonObject.getString("lastname");
                            String firstname=jsonObject.getString("firstname");

                            Person_det person_det = new Person_det();
                            person_det.setStudentId(studentId);
                            person_det.setFirstName(firstname);
                            person_det.setLastName(lastname);

                           // insertPersonalDetails(person_det,context);


                        }
                    }else{

                        isSuccess[0]=false;

                    }

                    //need to call next service

                }catch (Exception e){
                    e.printStackTrace();
                    isSuccess[0]=false;
                }



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TagStringrsp  ", "Error: " + error.getMessage());
                isSuccess[0]=false;
            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(context).addToRequestQueue(strReq, REQUEST_TAG);
        return  isSuccess[0];
    }
//getmessages services
    public static boolean GetMessages(final Context context){

        final boolean[] isSuccess = {false};

        String  REQUEST_TAG = "messagesRequest";

        Token_det token_det = new Token_det();
        token_det =getTokeStatus(context);

        String token=token_det.getToken().toString();
        int tokenInc=token_det.getTokenInc();
//token=490ac2df-a54d-44ef-8047-3b0931764a6b&inc=3

        //output: {"status": "success", "lastname": "LA001", "firstname": "FA001"}

       /* int arr[]={12,13,14,44};

        for(int i:arr){
            System.out.println(i);
        }*/
////////////////////////////////////////////////////////////////////

          /* List<Message_In> message_list=getMessageIds(context);
           JSONArray jsonArray = new JSONArray();

           try {


               for (Message_In message_in : message_list) {
                   JSONObject jsonObject = new JSONObject();
                   jsonObject.put("message_id", message_in.getMessageInId());
                   jsonObject.put("student_id",message_in.getStudentId());
                   jsonArray.put(jsonObject);

               }
           }catch (Exception e){
               e.printStackTrace();
           }*/

     ////////////////////////////////////////////////////
        String url=Constants.GET_MESSAGES+"token="+token+"&inc="+tokenInc;
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TagStringrsp  ", response.toString());

                try {

                    JSONObject jsonObject1 = new JSONObject(response.toString());

                    String status=jsonObject1.getString("status");


                    if(status.equals("success")) {
                        isSuccess[0]=true;


                        JSONArray jsonArray = jsonObject1.getJSONArray("messages");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String studentId=jsonObject.getString("studenid");
                            JSONArray arraymessageall=jsonObject.getJSONArray("messageall");

                            for(int j=0;j<arraymessageall.length();j++){

                                JSONObject jsonObject2 = arraymessageall.getJSONObject(j);
                                String thread_id=jsonObject2.getString("thread_id");
                                String message_created_date=jsonObject2.getString("message_created_date");
                                String messagesub=jsonObject2.getString("messagesub");

                                JSONArray arraymessagedetails = jsonObject2.getJSONArray("messagedetails");

                                for(int x=0;x<arraymessagedetails.length();x++){

                                    JSONObject jsonObject3 = arraymessagedetails.getJSONObject(x);

                                    String message_id=jsonObject3.getString("message_id");
                                    String message=jsonObject3.getString("message");
                                    String messagetype=jsonObject3.getString("messagetype");

                                    String messagetimeresecent=jsonObject3.getString("messagetimeresecent");
                                    String messagesender=jsonObject3.getString("messagesender");
                                    String messagereceiver=jsonObject3.getString("messagereceiver");

                                    Message_In message_in = new Message_In();

                                    message_in.setMessageInId(message_id);
                                    message_in.setStudentId(studentId);
                                    message_in.setMessageDateOfCreated(message_created_date);
                                    message_in.setMessageSub(messagesub);
                                    message_in.setThreadId(thread_id);
                                    message_in.setMessages(message);
                                    message_in.setMessageTyp(messagetype);
                                   // message_in.setMessageTimeRecent(messagetimeresecent);
                                    message_in.setMessageSender(messagesender);
                                    message_in.setMessageReceiver(messagereceiver);

                                    //insertMessages(message_in,context);
                                }


                            }

                        }


                    }else{

                        isSuccess[0]=false;
                    }

                    //need to call next service

                }catch (Exception e){
                    e.printStackTrace();
                    isSuccess[0]=false;
                }



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TagStringrsp  ", "Error: " + error.getMessage());
                isSuccess[0]=false;
            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(context).addToRequestQueue(strReq, REQUEST_TAG);
        return  isSuccess[0];
    }


//getBusDetails

    public static boolean GetBusDetails(final Context context){

        final boolean[] isSuccess = {false};

        String  REQUEST_TAG = "busDetailsRequest";
        Token_det token_det = new Token_det();
        token_det =getTokeStatus(context);

        String token=token_det.getToken().toString();
        int tokenInc=token_det.getTokenInc();
//token=490ac2df-a54d-44ef-8047-3b0931764a6b&inc=3

        //output: {"status": "success", "lastname": "LA001", "firstname": "FA001"}

        String url=Constants.GET_BUS_DETAILS+"token="+token+"&inc="+tokenInc;
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TagStringrsp  ", response.toString());

                try {

                    JSONObject jsonObject1 = new JSONObject(response.toString());

                    String status=jsonObject1.getString("status");

                    if(status.equals("success")) {

                        isSuccess[0]=true;

                        JSONArray jsonArray = jsonObject1.getJSONArray("busdetails");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String studentId=jsonObject.getString("studentid");

                                String routeid=jsonObject.getString("routeid");
                                String routename=jsonObject.getString("routename");
                                String busid=jsonObject.getString("busid");
                                String drivername=jsonObject.getString("drivername");
                                String driverphone=jsonObject.getString("driverphone");
                                String helpername=jsonObject.getString("helpername");
                                String helperphone=jsonObject.getString("helperphone");

                                JSONArray arraystopdetails= jsonObject.getJSONArray("stopdetails");
                                for(int x=0;x<arraystopdetails.length();x++){

                                    JSONObject jsonObject3 = arraystopdetails.getJSONObject(x);

                                    int stopno=jsonObject.getInt("stopno");
                                    String stopname=jsonObject.getString("stopname");
                                    String stoplat=jsonObject.getString("stoplat");
                                    String stoplong=jsonObject.getString("stoplong");

                                    Bus_Route bus_route= new Bus_Route();

                                    bus_route.setRouteId(routeid);
                                    bus_route.setRouteName(routename);
                                    bus_route.setStudentId(studentId);
                                    bus_route.setBusId(busid);
                                    bus_route.setDriverName(drivername);
                                    bus_route.setDriverPhone(driverphone);
                                    bus_route.setHelperName(helpername);
                                    bus_route.setHelperPhone(helperphone);
                                    bus_route.setStopNo(stopno);
                                    bus_route.setStopName(stopname);
                                    bus_route.setStopLat(stoplat);
                                    bus_route.setStopLon(stoplong);

                              insertBusDetails(bus_route,context);
                                }

                        }
                    } else{

                        isSuccess[0]=false;
                    }

                    //need to call next service

                }catch (Exception e){
                    e.printStackTrace();
                    isSuccess[0]=false;
                }



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TagStringrsp  ", "Error: " + error.getMessage());
                isSuccess[0]=false;
            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(context).addToRequestQueue(strReq, REQUEST_TAG);
        return  isSuccess[0];
    }

    public static boolean GetAlerts(final Context context){

        final boolean[] isSuccess = {false};

        String  REQUEST_TAG = "alertsRequest";
        Token_det token_det = new Token_det();
        token_det =getTokeStatus(context);

        String token=token_det.getToken().toString();
        int tokenInc=token_det.getTokenInc();
//token=490ac2df-a54d-44ef-8047-3b0931764a6b&inc=3

        //output: {"status": "success", "lastname": "LA001", "firstname": "FA001"}


        //////////////////////////////////////////////
      /*  List<Alerts> alert_list=getAlertIds(context);
           JSONArray jsonArray = new JSONArray();

           try {


               for (Alerts alerts : alert_list) {
                   JSONObject jsonObject = new JSONObject();
                   jsonObject.put("alert_id", alerts.getAlertId());
                   jsonObject.put("student_id",alerts.getStudentId());
                   jsonArray.put(jsonObject);

               }
           }catch (Exception e){
               e.printStackTrace();
           }
*/
      ///////////////////////////////////////////////////
        String url=Constants.GET_ALERTS+"token="+token+"&inc="+tokenInc;
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TagStringrsp  ", response.toString());


                try {

                    JSONObject jsonObject1 = new JSONObject(response.toString());

                    String status=jsonObject1.getString("status");

                    if(status.equals("success")) {
                        isSuccess[0]=true;
                        JSONArray jsonArray = jsonObject1.getJSONArray("personaldetails");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String studentId=jsonObject.getString("studenid");
                            JSONArray arrayalertsAll=jsonObject.getJSONArray("alertsAll");

                            for(int j=0;j<arrayalertsAll.length();j++){
                                JSONObject jsonObject2 = arrayalertsAll.getJSONObject(j);

                                String alertid=jsonObject.getString("alertid");
                                String alertdatetime=jsonObject.getString("alertdatetime");
                                String alertsub=jsonObject.getString("alertsub");
                                String alertmessage=jsonObject.getString("alertmessage");

                                Alerts alerts = new Alerts();
                                alerts.setAlertId(alertid);
                                alerts.setStudentId(studentId);
                                alerts.setAlertDate(alertdatetime);
                                alerts.setAlertMsg(alertmessage);
                                alerts.setAlertSub(alertsub);

                                //alertmessage
                            //    insertAlerts(alerts,context,studentId);

                            }

                        }


                    }else {
                        isSuccess[0]=false;
                    }

                    //need to call next service

                }catch (Exception e){
                    e.printStackTrace();
                    isSuccess[0]=false;
                }



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TagStringrsp  ", "Error: " + error.getMessage());
                isSuccess[0]=false;
            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(context).addToRequestQueue(strReq, REQUEST_TAG);
        return  isSuccess[0];
    }

    public static boolean GetBusLocation(final Context context){

        final boolean[] isSuccess = {false};

        String  REQUEST_TAG = "busLocationRequest";
        Token_det token_det = new Token_det();
        token_det =getTokeStatus(context);

        String token=token_det.getToken().toString();
        int tokenInc=token_det.getTokenInc();
//token=490ac2df-a54d-44ef-8047-3b0931764a6b&inc=3

        //output: {"status": "success", "lastname": "LA001", "firstname": "FA001"}

        String url=Constants.GET_BUS_LOCATION+"token="+token+"&inc="+tokenInc;
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TagStringrsp  ", response.toString());
                isSuccess[0]=true;

                try {

                    JSONObject jsonObject1 = new JSONObject(response.toString());

                    String status=jsonObject1.getString("status");

                    if(status.equals("success")) {
                        JSONArray jsonArray = jsonObject1.getJSONArray("personaldetails");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String studentId=jsonObject.getString("studentid");
                            String lastname=jsonObject.getString("lastname");
                            String firstname=jsonObject.getString("firstname");

                            Person_det person_det = new Person_det();
                            person_det.setStudentId(studentId);
                            person_det.setFirstName(firstname);
                            person_det.setLastName(lastname);

                          //  insertPersonalDetails(person_det,context);

                        }
                    }

                    //need to call next service

                }catch (Exception e){
                    e.printStackTrace();
                    isSuccess[0]=false;
                }



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TagStringrsp  ", "Error: " + error.getMessage());
                isSuccess[0]=false;
            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(context).addToRequestQueue(strReq, REQUEST_TAG);
        return  isSuccess[0];
    }

    public static Token_det getTokeStatus(final Context context){
        final Token_det token_det = new Token_det();

        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                List<Token_det> tokenDetails=appdb.token_dao().getTokenDetails();

                Log.e("tokenDetailsSize ","tokenDetailsSize "+tokenDetails.size());

                String token=tokenDetails.get(0).getToken();
                int tokenIncrement=tokenDetails.get(0).getTokenInc();
                token_det.setToken(token);
                token_det.setTokenInc(tokenIncrement);

            }
        }).start();

        return token_det;
    }

    public static void incrementToken(final Context context, final int currentTokenInc){

        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                appdb.token_dao().incrementToken(currentTokenInc);

            }
        }).start();

    }


    public static HashMap<String, String> getDbIds(final Context context, final int dbId){
        final HashMap<String, String> hashMap = new HashMap<String, String>();

        new Thread(new Runnable() {
            @Override
            public void run() {
              try {

                  AppDatabase appdb = AppDatabase.getAppDatabase(context);
                  switch (dbId) {
                      case Constants.DB_PERSON:
                          int studentCount=appdb.person_dao().getStudentCount();
                          int accountCount=appdb.account_dao().getAccountCount();

                          String accountId;
                          String studentId;

                          if(accountCount!=0){
                              accountId = appdb.account_dao().getAccountId();
                              studentId=appdb.person_dao().getlastStudentId();
                          }else{

                              accountId = "";
                              studentId = "";

                          }
                          hashMap.put("accountId",accountId);
                          hashMap.put("studentId",studentId);


                       case Constants.DB_MESSAGE:

                  }

              }catch (Exception e){
                  e.printStackTrace();
              }
            }
        }).start();


        return hashMap;
    }

public static HashMap<String, String> getPersonDetaisl(final Context context){

    final HashMap<String, String> hashMap = new HashMap<String, String>();

    new Thread(new Runnable() {
        @Override
        public void run() {

            try {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);

            int studentCount=appdb.person_dao().getStudentCount();
            int accountCount=appdb.account_dao().getAccountCount();

            String accountId;
            String studentId;

            if(accountCount!=0){
                accountId = appdb.account_dao().getAccountId();
                studentId=appdb.person_dao().getlastStudentId();
            }else{

                accountId = "";
                studentId = "";

            }
                hashMap.put("accountId",accountId);
                hashMap.put("studentId",studentId);

            }catch (Exception e){
                e.printStackTrace();
            }


        }
    }).start();

   return hashMap;
}


/*
public static List<Message_In> getMessageIds(final Context context){
      final List<Message_In> idex = new ArrayList<>();

     new Thread(new Runnable() {
        @Override
        public void run() {

            AppDatabase appdb = AppDatabase.getAppDatabase(context);
            List<Message_In> ids =appdb.messageIn_dao().getlastStudentIdMessageIds();

            for(int i=0;i<ids.size();i++) {
                Message_In messageIn = new Message_In();
                 messageIn=ids.get(i);
                idex.add(i,messageIn);
            }

        }
    }).start();

return idex;
}
*/



    public static List<Alerts> getAlertIds(final Context context){
        final List<Alerts> idex = new ArrayList<>();

        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                List<Alerts> ids =appdb.alerts_dao().getlastStudentIdAlertIds();

                for(int i=0;i<ids.size();i++) {
                    Alerts alerts = new Alerts();
                    alerts=ids.get(i);
                    idex.add(i,alerts);
                }

            }
        }).start();

        return idex;
    }

   public static void insertPersonalDetails(final Person_det person_det, final Context context, final loginPresInterface loginPresInterface){
       new Thread(new Runnable() {
           @Override
           public void run() {
               SharedPreferences studentPreference= context.getSharedPreferences(Constants.STUDENT_SELECTION_PREFERENCE,Context.MODE_PRIVATE);
               Log.e("insertTables1 ","insertTables1 ");

               AppDatabase appdb = AppDatabase.getAppDatabase(context);
               // appdb.person_dao().DeleteToken();
                appdb.person_dao().insertAll(person_det);


           }
       }).start();

      }



      public static void deleteFromtables(final Context context){

       new Thread(new Runnable() {
           @Override
           public void run() {

               Log.e("delteTables1 ","delteTables1 ");
               AppDatabase appdb = AppDatabase.getAppDatabase(context);
               appdb.person_dao().DeleteToken();
               appdb.messageIn_dao().DeleteToken();
               appdb.alerts_dao().DeleteToken();


           }
       }).start();
      }



    public static  void updateReplyMessages(final Message_In message_in, final Context context, final replyPresInterface mailPresInterface, final String username){

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                // appdb.messageIn_dao().DeleteUserReceive(username);

                appdb.messageIn_dao().insert(message_in);

                mailPresInterface.showMessage(Constants.PASS_SERVICE);



            }
        }).start();

    }






    public void insertMessages(final Message_In message_in, final Context context, final mailPresInterface mailPresInterface, final String username,int intex,int arraysize){

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase appdb = AppDatabase.getAppDatabase(context);
               // appdb.messageIn_dao().DeleteUserReceive(username);

                appdb.messageIn_dao().insert(message_in);

                if(intex==arraysize-1) {

                    mailPresInterface.setMessagesServer(Constants.PASS_SERVICE);
                }

            }
        }).start();

      }



      public static void deleteMessage(final String messageId, final Context context){

          new Thread(new Runnable() {
              @Override
              public void run() {
                  AppDatabase appdb = AppDatabase.getAppDatabase(context);
                  appdb.messageIn_dao().DeleteMessageId(messageId);


              }
          }).start();
      }

      public static void insertAlerts(final Alerts alerts, final Context context,alertPresInterface alertPresInterface,int intext,int arraysize){

          new Thread(new Runnable() {
              @Override
              public void run() {
                  AppDatabase appdb = AppDatabase.getAppDatabase(context);

                    appdb.alerts_dao().insertAll(alerts);

                    if(intext==arraysize-1) {
                        alertPresInterface.setMessagesServer(Constants.PASS_SERVICE);
                    }

              }
          }).start();

      }

    public static void insertSchedules(final Schedule schedule, final Context context, schedulePresInterface schedulePresInterface,int intex,int arraysize){

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase appdb = AppDatabase.getAppDatabase(context);

                appdb.schedule_dao().insertAll(schedule);

                if(intex==arraysize-1) {
                    schedulePresInterface.setMessagesServer(Constants.PASS_SERVICE);
                }

            }
        }).start();

    }

    public static void insertSchedulesNew(final Schedule schedule, final Context context, schedulePresInterface schedulePresInterface){

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase appdb = AppDatabase.getAppDatabase(context);

                appdb.schedule_dao().insertAll(schedule);

                    schedulePresInterface.setMessagesServer(Constants.PASS_NEW);


            }
        }).start();

    }
    public static void insertSchedulesUp(final Schedule schedule, final Context context, schedulePresInterface schedulePresInterface){

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase appdb = AppDatabase.getAppDatabase(context);

                appdb.schedule_dao().insertOnConflict(schedule);

                schedulePresInterface.setMessagesServer(Constants.PASS_UP);


            }
        }).start();

    }


    public static void insertBusDetails(final Bus_Route bus_route, final Context context){
          new Thread(new Runnable() {
              @Override
              public void run() {
                  AppDatabase appdb = AppDatabase.getAppDatabase(context);
                  appdb.busRoute_dao().insertAll(bus_route);


              }
          }).start();


    }

    public  static void insertToken(final String token, final Context context, final loginPresInterface loginPresInterface){
      Thread thread = new Thread(new Runnable() {
          @Override
          public void run() {

              Log.e("miskoolinsertToken ","miskoolinsertToken ");

              AppDatabase appdb = AppDatabase.getAppDatabase(context);
              appdb.token_dao().DeleteToken();

              Token_det token_det = new Token_det();
              token_det.setTokenId(1);
              token_det.setToken(token);
              token_det.setTokenInc(0);
              appdb.token_dao().insertAll(token_det);
              appdb.attribute_dao().DeleteSequence();

              populateWithAtts(appdb);



              //cpmmenting for nowwwww/////////////////////
             Methods.getUsers(context,loginPresInterface);


          }
      });thread.start();


     }


     public static List<Attributes> getAttributes(final Context context, final String StudentId){
          final List<Attributes>attList1= new ArrayList<>();

         new Thread(new Runnable() {
          @Override
          public void run() {
              AppDatabase appdb = AppDatabase.getAppDatabase(context);

              Log.e("called","getAttributes");


              List<Attributes>attList= appdb.attribute_dao().getAttsStudent(StudentId);

              for(int i=0;i<attList.size();i++){

                  Attributes atts= new Attributes();
                  atts=attList.get(i);
                  attList1.add(atts);

              }

          }
      }).start();

         return attList1;
     }

     public static void getloginTest(){

         JSONObject json = new JSONObject();

         try {
             json.put("username", "eldhosemani3");
             json.put("password","loother1");
         }catch (Exception e){
             e.printStackTrace();
         }

         CloseableHttpClient httpClient = HttpClientBuilder.create().build();

         try {
             HttpPost request = new HttpPost(Constants.GET_LOGIN);
             StringEntity params = new StringEntity(json.toString());
             request.addHeader("content-type", "application/json");
             request.setEntity(params);
          //   HttpEntity entity = httpClient.execute(request);


// handle response here...
         } catch (Exception ex) {
             // handle exception here
         } finally {
             try {
                 httpClient.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }


     }

    public static void getLoginDetailsJson(final Context context, String url){


        JSONObject jsonLogin= new JSONObject();

        try {
            jsonLogin.put("username", "eldhosemani3");
            jsonLogin.put("password","loother1");
        }catch (Exception e){
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Constants.GET_LOGIN, jsonLogin,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());

                        Log.e("MableTest2 ","MableTest2 "+response.toString());

                        Toast.makeText(context,"got response",Toast.LENGTH_LONG).show();

                        Log.e("json_obj_req","json_obj_req "+response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
            }
        });

// Adding request to request queue
        AppSingleton.getInstance(context).addToRequestQueue(jsonObjReq, "tag_json_obj");

    }
    public static void getLoginDetails(final Context context, String url){

        String  REQUEST_TAG = "volleyStringRequest";
        StringRequest strReq = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TagStringrsp  ", response.toString());

                try {

                    JSONObject jsonresp = new JSONObject(response.toString());

                    //{"status": "success", "token": "0944667b-4017-4623-876a-d99c04ae36f9"}

                    AppDatabase appdb = AppDatabase.getAppDatabase(context);

                    Log.e("jsonresp ","jsonresp "+jsonresp);
                    String status=jsonresp.getString("status");
                    String token =jsonresp.getString("token");
                     if(status.equals("success")){
                         Token_det token_det = new Token_det();
                         token_det.setToken(token);
                         token_det.setTokenInc(0);
                      appdb.token_dao().insertAll(token_det);
                     }



                    //need to call next service

                }catch (Exception e){
                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TagStringrsp  ", "Error: " + error.getMessage());
            }
        });
        // Adding String request to request queue
        AppSingleton.getInstance(context).addToRequestQueue(strReq, REQUEST_TAG);
    }

    public static void getBusTrackdetails( String Student_id, String auth_token, Context context){

        //username=FA001LA001&password=LA001FA001
            String url=Constants.GET_BUSTRACKER_DETAILS;

            String EncodeUrl= url+"Student_id="+Student_id+"Auth_token="+auth_token;
            String  REQUEST_TAG = "BusTrackerRequest";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                EncodeUrl, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
            }
        });

// Adding request to request queue
        AppSingleton.getInstance(context).addToRequestQueue(jsonObjReq, REQUEST_TAG);


           }


    public static void uploadBackGround(String url, final Context context){


        try{

          Callweb.CallGetWebServices(url, new ServiceCallback() {
              @Override
              public void SuccessCallbak(String resp) throws JSONException {


                  Intent localIntent1 =
                          new Intent(Constants.BROADCAST_ACTION_BUS)
                                  // Puts the status into the Intent
                                  .putExtra(Constants.EXTENDED_DATA_STATUS_MSG, Constants.SERVICE_UPDATED);
                  // Broadcasts the Intent to receivers in this app.
                  LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent1);
              }

              @Override
              public void ErrorCallbak(String resp) {

              }
          });





        }catch(Exception e){
            e.printStackTrace();
        }


    }

    public static void getBusLocationDetails(final Context context){

        //username=FA001LA001&password=LA001FA001
      /*  lastTokenPreference = context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE,Context.MODE_PRIVATE);

        final String token=lastTokenPreference.getString("tokenNo","");
        final int tokenInc=lastTokenPreference.getInt("tokeInc",0)+1;

*/

        Log.e("BusLocationRequestCall ", "BusLocationRequestCall ");

        final SharedPreferences locationPreferences =context.getSharedPreferences(Constants.BUS_LOCATION_PREFERENCE,0);


        final int[] statusCode = new int[1];

        //https://identity.01.via.zone/Services/SchoolApp/Json/GetTrasportWithToken/ACCESSTOKEN/TIMESTAMP/

        String url=Constants.GET_BUS_LOCATION;
       List<BusDirectionModel> dlist;

        String EncodeUrl= url+"0001"+"/"+"0001"+"/";
        String  REQUEST_TAG = "BusLocationRequest";

        Log.e("EncodeUrl ", "EncodeUrl "+EncodeUrl);



        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                EncodeUrl, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("BusLocationRequest ", "BusLocationRequest "+response.toString());

                      try{

                          SharedPreferences.Editor editorstudent = locationPreferences.edit();
                          JSONObject jsonObject = new JSONObject(response.toString());

                          String status=jsonObject.getString("status");

                          Calendar c = Calendar.getInstance();
                          SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                          String curDateTime = df.format(c.getTime());

                          Date Curdate=df.parse(curDateTime);



                          //  int statusCode=jsonObject.getInt("")

                       if(status.equals("OK")){
                              statusCode[0] = 2;

                           Log.e("statusinvolley", "statusinvolley "+ statusCode[0]);


                           JSONObject jsonObj=response.getJSONObject("data");

                               JSONObject jsonOrgin=jsonObj.getJSONObject("origin");
                               String orginLat=jsonOrgin.getString("lat");
                               String originLon=jsonOrgin.getString("lng");

                               JSONObject jsonDes=jsonObj.getJSONObject("destination");
                               String desLat=jsonDes.getString("lat");
                               String desLon=jsonDes.getString("lng");


                               String curLat=jsonObj.getString("lat");
                               String curLon=jsonObj.getString("lng");

                               String dir=jsonObj.getString("timestamp");

                               //getting preference values..........

                               String prefDir=locationPreferences.getString("dir","NoDir");

                               String prefOrginLat=locationPreferences.getString("orginLat","null");
                               String prefOrginLon=locationPreferences.getString("originLon","null");

                               String prefDesLat=locationPreferences.getString("desLat","null");
                               String prefDesLon=locationPreferences.getString("desLon","null");


                               String prefCurLat=locationPreferences.getString("curLat","null");
                               String prefCurLon=locationPreferences.getString("curLon","null");

                               String prefrouteredrawTime=locationPreferences.getString("routeredrawTime",curDateTime);

                               SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                               Date prefdDate=df1.parse(prefrouteredrawTime);


                           if(prefDir.equals(dir)){
                                   //no route change
                                   //checking for destination change
                                   if(prefDesLat.equals(desLat)){
                                       //no destination change at all//everything stays the sameee

                                       editorstudent.putString("orginLat", orginLat);
                                       editorstudent.putString("originLon",originLon);
                                       editorstudent.putString("desLat",desLat);
                                       editorstudent.putString("desLon", desLon);
                                       editorstudent.putString("curLat",curLat);
                                       editorstudent.putString("curLon",curLon);
                                       editorstudent.putString("prevLat",prefCurLat);
                                       editorstudent.putString("prevLon",prefCurLon);
                                       editorstudent.putString("dir",dir);


                                       long diff = prefdDate.getTime() - Curdate.getTime();
                                       long seconds = diff / 1000;
                                       long minutes = seconds / 60;
                                       long hours = minutes / 60;
                                       long days = hours / 24;

                                       if(minutes>30){
                                           //need to redraw the route
                                           editorstudent.putBoolean("routeDrawStatus",true);
                                           editorstudent.putString("routeredrawTime",curDateTime);
                                       }else{

                                           editorstudent.putBoolean("routeDrawStatus",false);
                                       }

                                       editorstudent.commit();



                                   }else {
                                       //if pref destination changes as the current destin

                                       editorstudent.putString("orginLat", orginLat);
                                       editorstudent.putString("originLon",originLon);
                                       editorstudent.putString("desLat",desLat);
                                       editorstudent.putString("desLon", desLon);
                                       editorstudent.putString("curLat",curLat);
                                       editorstudent.putString("curLon",curLon);
                                       editorstudent.putString("prevLat",orginLat);
                                       editorstudent.putString("prevLon",originLon);
                                       editorstudent.putString("dir",dir);

                                       editorstudent.putBoolean("routeDrawStatus",true);
                                       editorstudent.putString("routeredrawTime",curDateTime);
                                       editorstudent.commit();

                                   }


                               } else{

                               //the route as such changes


                               editorstudent.putString("orginLat", orginLat);
                               editorstudent.putString("originLon",originLon);
                               editorstudent.putString("desLat",desLat);
                               editorstudent.putString("desLon", desLon);
                               editorstudent.putString("curLat",curLat);
                               editorstudent.putString("curLon",curLon);
                               editorstudent.putString("prevLat",orginLat);
                               editorstudent.putString("prevLon",originLon);
                               editorstudent.putString("dir",dir);

                               editorstudent.putBoolean("routeDrawStatus",true);
                               editorstudent.putString("routeredrawTime",curDateTime);
                               editorstudent.commit();
                           }

                           Intent localIntent =
                                   new Intent(Constants.BROADCAST_ACTION_BUS)
                                           // Puts the status into the Intent
                                           .putExtra(Constants.EXTENDED_DATA_STATUS, Constants.PASS_SERVICE);
                           // Broadcasts the Intent to receivers in this app.
                           LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent);

                           Log.e("volleyserviceIntent", "volleyserviceIntent");



                       }else{

                           statusCode[0] = 1;

                           Intent localIntent1 =
                                   new Intent(Constants.BROADCAST_ACTION_BUS)
                                           // Puts the status into the Intent
                                           .putExtra(Constants.EXTENDED_DATA_STATUS, Constants.ERROR_SERVICE);
                           // Broadcasts the Intent to receivers in this app.
                           LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent1);

                          }


                      }catch (Exception e){
                          e.printStackTrace();
                      }




                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                statusCode[0] = 0;
            }
        });

// Adding request to request queue
        AppSingleton.getInstance(context).addToRequestQueue(jsonObjReq, REQUEST_TAG);

        int status=statusCode[0];
        Log.e("statusinMethod", "statusinMethod "+status);

   ///  return null;

    }


    public static boolean checknewtwork( Context context)
    {
        // TODO Auto-generated method stub
        ConnectivityManager cm = ( ConnectivityManager ) context.getSystemService( Context.CONNECTIVITY_SERVICE );
        // NetworkInfo ni = cm.getActiveNetworkInfo();
        NetworkInfo ni_wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo ni_mob = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);


        if ( ni_wifi != null && ni_wifi.isAvailable() && ni_wifi.isConnected()){
            netwrk = true;
        }
        else{
            if(ni_mob != null && ni_mob.isAvailable() && ni_mob.isConnected() )
            {
                netwrk = true;
            }
            else{
                netwrk = false;
            }


        }

        return netwrk;
    }


    public static boolean hasActiveInternetConnection( Context context )
    {
        Log.e( "", "hasActiveInternetConnection............." );
        if (checknewtwork(context) )
        {

            try
            {
                // HttpURLConnection urlc = (HttpURLConnection) (new
                // URL("http://www.google.com").openConnection());
                // String url_Update_preparedstock = "http://" + Ip_Add + Variables.str_GetBank_Details;
                //Log.e( "", "Checking Active network " + url_Update_preparedstock );
                HttpURLConnection urlc = ( HttpURLConnection ) ( new URL( "https://identity.01.via.zone/admin/"
                ).openConnection() );
                urlc.setRequestProperty( "User-Agent", "Test" );
                urlc.setRequestProperty( "Connection", "close" );
                urlc.setConnectTimeout( 1500 );
                urlc.connect();
                Log.e( "", "HttpURLConnection..setRequestProperty..........." );
                return ( urlc.getResponseCode() == 200 );
            }
            catch ( IOException e )
            {
                Log.e( "", "Error checking internet connection", e );
            }
        }
        else
        {
            Log.d( "", "No network available!" );
        }
        return false;
    }

public static int serviceStausCheck(Context context){

    SharedPreferences servicepreference = context.getSharedPreferences(Constants.SERVICE_STATUS_PREFERENCE,Context.MODE_PRIVATE);
    int lastCalledservice= servicepreference.getInt("service",0);

    return lastCalledservice;

}

public static void serviceLoop(Context context){
    SharedPreferences studentPreference = context.getSharedPreferences(Constants.SERVICE_STATUS_PREFERENCE,Context.MODE_PRIVATE);
    int lastCalledservice= studentPreference.getInt("service",0);
     switch(lastCalledservice){

         case 0:


     }

}

   /* public static int getLastReplyId(Context context){
        final int[] msgout_id = {0};
        final AppDatabase appdb = AppDatabase.getAppDatabase(context);

        new Thread(new Runnable() {
            @Override
            public void run() {

               List<Message_Out> mglist = appdb.messageOut_dao().getAll();
                if(mglist==null){
                    msgout_id[0]=0;
                }else{
                    msgout_id[0] = appdb.messageOut_dao().getMaxMessageOutID();
                }


            }
        }).start();

        return msgout_id[0];
    }
*/
public static String getSharedStudId(Context context){
   SharedPreferences studentPreference=context.getSharedPreferences(Constants.STUDENT_SELECTION_PREFERENCE, Context.MODE_PRIVATE);

   String prefstudentId=studentPreference.getString("studentid","novaluee");

return prefstudentId;
}

public static void getAlertsforFuture(final Context context){

      List<Alerts> alertList=new ArrayList<>();

    new Thread(new Runnable() {
        @Override
        public void run() {
            Alerts alerts = new Alerts();
            AppDatabase appdb = AppDatabase.getAppDatabase(context);
            List<Alerts> alertList1=appdb.alerts_dao().getAlerts();

            for(int i=0;i<alertList1.size();i++){
                String alertId=alertList1.get(i).getAlertId();
                String studentId=alertList1.get(i).getStudentId();

                String alertSub=alertList1.get(i).getAlertSub();
                String message=alertList1.get(i).getAlertMsg();
                String dateofalarm=alertList1.get(i).getAlertDate();

                AlarmModel alarmobj=convertTimeStamp(converISO8601stamp(dateofalarm));

                scheduleNotification(getNotification(message,alertSub,context),context, alarmobj);

            }

        //    scheduleNotification(getNotification("IMP Message"), 5000);


        }
    }).start();


}

    public static void scheduleNotification(Notification notification, Context context,AlarmModel alarmModel) {
    Log.e("scheduleNotification","scheduleNotification");

        Intent notificationIntent = new Intent(context, MyReceiver.class);
        notificationIntent.putExtra(MyReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyReceiver.NOTIFICATION, notification);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 1, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();

      /*  calendar.set(Calendar.YEAR,2018);
        calendar.set(Calendar.MONTH,03);
        calendar.set(Calendar.DAY_OF_MONTH,15);

        calendar.set(Calendar.HOUR_OF_DAY, 17);
        calendar.set(Calendar.MINUTE, 49);
        calendar.set(Calendar.SECOND, 0);*/

        calendar.set(Calendar.YEAR,Integer.parseInt(alarmModel.getYear()));
        calendar.set(Calendar.MONTH,Integer.parseInt(alarmModel.getMonth())-1);
        calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(alarmModel.getDayofmonth()));

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(alarmModel.getHour()));
        calendar.set(Calendar.MINUTE, Integer.parseInt(alarmModel.getMinute()));
        calendar.set(Calendar.SECOND, Integer.parseInt(alarmModel.getScond()));

        Log.e("Calendar","Calendar "+alarmModel.getHour() +" "+alarmModel.getMinute()+" "+alarmModel.getScond());


        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis()-10, pendingIntent);

    }



    public static void scheduleNotificationNew(Notification notification, Context context){
        Intent notificationIntent = new Intent(context, MyReceiver.class);
        notificationIntent.putExtra(MyReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyReceiver.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + 10000;

        Log.e("CalendarNew","CalendarNew " + futureInMillis);

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);


    }

    public static  Notification getNotificationNew(String content, Context context) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.alertb);
        return builder.build();
    }


    public static Notification getNotification(String content,String title, Context context) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.alert);
        return builder.build();
    }

   public static AlarmModel convertTimeStamp(String Date){

      //  String isodate="2018-03-11T16:57:04Z";
        AlarmModel alarObj=new AlarmModel();

        try {

            StringTokenizer tk = new StringTokenizer(Date);

            String date1 = tk.nextToken();  // <---  yyyy-mm-dd
            String time = tk.nextToken();  // <---  hh:mm:ss

            Log.e("date1","date1 "+date1);
            Log.e("time1","time1 "+time);

            Log.e("date1","date1 "+date1);
            Log.e("time1","time1 "+time);
            String []datearray=date1.split("-");
            String []timearray=time.split(":");

            String year="",month = "",dayofmonth="",hour="",minute="",scond="";

            if(datearray.length>2){
                year=datearray[0];
                month=datearray[1];
                dayofmonth=datearray[2];
            }

            if(timearray.length>2){
                hour=timearray[0];
                minute=timearray[1];
                scond=timearray[2];
            }

            Log.e("splitDate ","splitDate m "+ month+" s "+minute);


           alarObj.setYear(year);
           alarObj.setMonth(month);
           alarObj.setDayofmonth(dayofmonth);
           alarObj.setHour(hour);
           alarObj.setMinute(minute);
           alarObj.setScond(scond);



        }catch (Exception e){
            e.printStackTrace();
        }

      return  alarObj;
    }

    static String converISO8601stamp(String isoDate){

      //  String isodate="2018-03-11T16:57:04Z";
        String finalDate="";

        String isodate=isoDate;

        long timestamp = 0;

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateformat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {

            Date date = dateformat.parse(isodate);
            timestamp = date.getTime();

            // DateFormat sdf = new SimpleDateFormat("hh:mm:ss yyyy-MM-dd");

            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date netDat = new Date(timestamp);

             finalDate = dateFormatter.format(netDat);

            Log.e("netDate ", "netDate " + finalDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        return finalDate;
    }

    public static   void copyFile(Context context)
    {
        try
        {
            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();

            /////////////////////////////////////////////////////////////////////////
            File outDir = new File( sd.getAbsolutePath() + File.separator + "MiSkool" );
            if ( !outDir.isDirectory() )
            {
                outDir.mkdir();
            }


            OutputStreamWriter outStreamWriter = null;
            FileOutputStream outStream = null;

            File out;


            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy" );
            SimpleDateFormat TimeFormat = new SimpleDateFormat( "HH:mm" );
            Calendar cal = Calendar.getInstance();
            String Currentdate = sdf.format( cal.getTime() );
            String CurrentTime = TimeFormat.format( cal.getTime() );

            // out = new File( new File( outDir.getAbsolutePath() ), "miskool_db" + "_" + Currentdate );

            out =  new File( outDir.getAbsolutePath());

            if ( out.exists() == false )
            {
                out.createNewFile();
            }


            if (sd.canWrite())
            {
                String currentDBPath = "//data//"+context.getPackageName()+"//databases//"+"miskool-database";

                String backupDBPath = "miskool-database";
                File currentDB = new File(data, currentDBPath);
                File backupDB = new File(out, backupDBPath+"_"+Currentdate );

                if (currentDB.exists()) {
                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                }




               /* if(bool == true)
                {
                    Toast.makeText(Settings.this, "Backup Complete", Toast.LENGTH_SHORT).show();
                    bool = false;
                }*/
            }
        }
        catch (Exception e) {
            Log.w("Settings Backup", e);

            e.printStackTrace();
        }
    }



    private static void populateWithAtts(AppDatabase appdb) {

        Log.e("insidepopulateWithAtts","insidepopulateWithAtts");
        Attributes atts = new Attributes();


        //adding parent mable

        ArrayList<String> AttsName=new ArrayList<>();
        AttsName.add("Name");
        AttsName.add("Identitiy");
        AttsName.add("NoOfChild");
        AttsName.add("Place");
        AttsName.add("Occupation");
        AttsName.add("Phone No");


        ArrayList<String> AttsVal=new ArrayList<>();
        AttsVal.add("mable1");
        AttsVal.add("Parent");
        AttsVal.add("2");
        AttsVal.add("Perumbavoor");
        AttsVal.add("Teacher");
        AttsVal.add("0484-2594330");


        for(int i=0;i<AttsName.size();i++){
            atts.setStudentId("94f3137c-a613-47c5-8e14-5528000f38d2");
            atts.setAttName(AttsName.get(i));
            atts.setSttValue(AttsVal.get(i));
            addAtts(appdb, atts);

        }



        //adding child one amble1
        ArrayList<String> AttsName1=new ArrayList<>();
        AttsName1.add("Name");
        AttsName1.add("Identitiy");
        AttsName1.add("Place");
        AttsName1.add("Dept");
        AttsName1.add("DIV");
        AttsName1.add("ClassTeacher");
        AttsName1.add("TeacherPh");


        ArrayList<String> AttsVal1=new ArrayList<>();
        AttsVal1.add("amble1");
        AttsVal1.add("Child");
        AttsVal1.add("Perumbavoor");
        AttsVal1.add("LKG");
        AttsVal1.add("Orchid");
        AttsVal1.add("Renni");
        AttsVal1.add("9745696626");

        for(int i=0;i<AttsName1.size();i++){
            atts.setStudentId("2456a8bd-7573-4122-931e-60c629c4ca7a");
            atts.setAttName(AttsName1.get(i));
            atts.setSttValue(AttsVal1.get(i));
            addAtts(appdb, atts);
        }



        //adding child 2 anju1

        ArrayList<String> AttsName2=new ArrayList<>();
        AttsName2.add("Name");
        AttsName2.add("Identitiy");
        AttsName2.add("Place");
        AttsName2.add("Dept");
        AttsName2.add("DIV");
        AttsName2.add("ClassTeacher");
        AttsName2.add("TeacherPh");

        ArrayList<String> AttsVal2=new ArrayList<>();
        AttsVal2.add("anju1");
        AttsVal2.add("Child");
        AttsVal2.add("Perumbavoor");
        AttsVal2.add("UKG");
        AttsVal2.add("Saphire");
        AttsVal2.add("Pathmini");
        AttsVal2.add("7034696975");



        for(int i=0;i<AttsName2.size();i++){
            atts.setStudentId("08b5e4ff-41b8-45fa-8696-f1bebfa1f80c");
            atts.setAttName(AttsName2.get(i));
            atts.setSttValue(AttsVal2.get(i));
            addAtts(appdb, atts);
        }



    }

    private static Attributes addAtts(final AppDatabase db, Attributes atts) {
        db.attribute_dao().insertAll(atts);
        return atts;
    }
    public static String convertPrettyTime(String date){

        // String dateString="2018-04-23 15:00:47";

        String datetime="";

        String dateString=date;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        java.util.Date convertedDate = new java.util.Date();


        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Log.e("formatDate","formatDate "+convertedDate);

        PrettyTime p  = new PrettyTime();

        datetime= p.format(convertedDate);


        //dd MMMM yyyy, hh:mm:ss.SSS a

        // String messagedatetime=(String)android.text.format.DateFormat.format("dd MM yyyy hh:mm:ss a", convertedDate);



        String messagedatetime=(String)android.text.format.DateFormat.format("dd MM yyyy", convertedDate);

        String currentmessagedatetime=(String)android.text.format.DateFormat.format("dd MM yyyy", new Date());

        if(messagedatetime.equals(currentmessagedatetime)){
            datetime= (String)android.text.format.DateFormat.format("hh:mm a", convertedDate);
        }else{

            datetime= (String)android.text.format.DateFormat.format("MMM dd", convertedDate);

        }

        String testdate=(String)android.text.format.DateFormat.format("MMM dd", convertedDate);

        Log.e("loggedDateMonth","loggedDateMonth "+testdate);

        Log.e("loggedDateDay","loggedDateday "+currentmessagedatetime);

        return datetime;
    }

    public void getSendMsgUser(final Context context, String url, JSONObject jsonobj, final outMailPresInterface outMailPresInterface){

        Callweb.CallSendPostServices(url, jsonobj, new ServiceCallback() {
            @Override
            public void SuccessCallbak(String resp) throws JSONException {


                // String response=resp.toString().replace("\\","");
                String response=resp;
                response.replace(" ","");
                Log.e("getSendUser","getSendUser "+response);

                JSONObject jsonResponse = new JSONObject(response);

                String status=jsonResponse.getString("status");

                if(status.contains("true")){


                    String array=jsonResponse.getString("messages");
                    JSONArray messageArray = new JSONArray(array);

                    int arraysize=messageArray.length();

                    if(messageArray.length()>0 && !messageArray.equals(null)) {

                        for (int i = 0; i < messageArray.length(); i++) {

                            JSONObject jsonObject2 = messageArray.getJSONObject(i);

                            String messageId = jsonObject2.getString("pkey");
                            String frompersonid = jsonObject2.getString("fromusername");

                            String topersonid = jsonObject2.getString("tousername");
                            String createdtimestamp = convertGMT(jsonObject2.getString("doc"),context);

                            String threadid = jsonObject2.getString("threadid");
                            String message = jsonObject2.getString("text");
                            String subject = jsonObject2.getString("subject");

                            String readflag = convertGMT(jsonObject2.getString("dor"),context);


                            Message_In message_in = new Message_In();

                            message_in.setStudentId(topersonid);
                            message_in.setMessageInId(messageId);
                            message_in.setThreadId(threadid);
                            message_in.setMessageSender(frompersonid);
                            message_in.setMessageReceiver(topersonid);
                            message_in.setMessageDateOfCreated(createdtimestamp);
                            message_in.setMessageSub(subject);
                            message_in.setMessages(message);
                            message_in.setReadFlag(readflag);

                            insertSendMessages(message_in, context, outMailPresInterface,i,arraysize);

                        }
                    }else{
                        outMailPresInterface.setMessagesServer(Constants.NO_MESSAGE);
                    }



                }



            }

            @Override
            public void ErrorCallbak(String resp) {

            }
        });

    }



    public void insertSendMessages(final Message_In message_in, final Context context, final outMailPresInterface outMailPresInterface,int intex, int arrasize){

        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                // appdb.messageIn_dao().DeleteUserReceive(username);

                appdb.messageIn_dao().insert(message_in);

if(intex==arrasize-1) {
    outMailPresInterface.setMessagesServer(Constants.PASS_SERVICE);

}
              /*  Intent localIntent1 =
                        new Intent(Constants.BROADCAST_ACTION_MSG)
                                // Puts the status into the Intent
                                .putExtra(Constants.EXTENDED_DATA_STATUS_MSG, Constants.PASS_SERVICE);
                // Broadcasts the Intent to receivers in this app.
                LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent1);
*/

            }
        }).start();

    }

    public  void getMessageReply(final Context context, String url, JSONObject jsonobj, final replyPresInterface replyPresInterface){

        Callweb.CallSendPostServices(url, jsonobj, new ServiceCallback() {
            @Override
            public void SuccessCallbak(String resp) throws JSONException {


                // String response=resp.toString().replace("\\","");
                String response=resp;
                response.replace(" ","");
                Log.e("getMessageUser","getMessageUser "+response);

                JSONObject jsonResponse = new JSONObject(response);

                String status=jsonResponse.getString("status");

                if(status.contains("true")){


                    String array=jsonResponse.getString("messages");
                    JSONArray messageArray = new JSONArray(array);

                    if(messageArray.length()>0) {
                        for (int i = 0; i < messageArray.length(); i++) {

                            JSONObject jsonObject2 = messageArray.getJSONObject(i);

                            String messageId = jsonObject2.getString("pkey");
                            String frompersonid = jsonObject2.getString("fromusername");

                            String topersonid = jsonObject2.getString("tousername");
                            String createdtimestamp = convertGMT(jsonObject2.getString("doc"),context);

                            String threadid = jsonObject2.getString("threadid");
                            String message = jsonObject2.getString("text");
                            String subject = jsonObject2.getString("subject");

                            String readflag = convertGMT(jsonObject2.getString("dor"),context);


                            Message_In message_in = new Message_In();

                            message_in.setStudentId(topersonid);
                            message_in.setMessageInId(messageId);
                            message_in.setThreadId(threadid);
                            message_in.setMessageSender(frompersonid);
                            message_in.setMessageReceiver(topersonid);
                            message_in.setMessageDateOfCreated(createdtimestamp);
                            message_in.setMessageSub(subject);
                            message_in.setMessages(message);
                            message_in.setReadFlag(readflag);

                            updateReplyMessages(message_in, context, replyPresInterface, topersonid);

                        }
                    }else{

                        replyPresInterface.showMessage(Constants.ERROR_SERVICE);
                    }

                }



            }

            @Override
            public void ErrorCallbak(String resp) {

            }
        });

    }


    public  void getAlertsUser(final Context context, String url, JSONObject jsonobj, final alertPresInterface alertPresInterface, String studentId){

        Callweb.CallSendPostServices(url, jsonobj, new ServiceCallback() {
            @Override
            public void SuccessCallbak(String resp) throws JSONException {

                // String response=resp.toString().replace("\\","");
                String response=resp;
                response.replace(" ","");
                Log.e("getMessageUser","getMessageUser "+response);

                JSONObject jsonResponse = new JSONObject(response);


                    String array=jsonResponse.getString("alerts");
                    JSONArray messageArray = new JSONArray(array);

                    int arraySize=messageArray.length();

                    if(messageArray.length()>0 && !messageArray.equals(null)) {
                        for (int i = 0; i < messageArray.length(); i++) {

                            JSONObject jsonObject2 = messageArray.getJSONObject(i);

                            String alertId = jsonObject2.getString("pkey");
                            String priority = jsonObject2.getString("priority");

                            String endtime = convertGMTMILLI(jsonObject2.getString("endtime"));
                            String starttime = convertGMTMILLI(jsonObject2.getString("starttime"));

                            String subject = jsonObject2.getString("subject");

                            String active = jsonObject2.getString("active");


                            Alerts alerts = new Alerts();

                          alerts.setAlertId(alertId);
                          alerts.setAlertPriority(priority);
                          alerts.setAlertDate(starttime);
                          alerts.setAlertEndDate(endtime);
                          alerts.setAlertSub(subject);
                          alerts.setActive(active);
                          alerts.setStudentId(studentId);


                            insertAlerts(alerts, context, alertPresInterface,i,arraySize);

                        }
                    }else{

                        alertPresInterface.setMessagesServer(Constants.NO_MESSAGE);
                    }





            }

            @Override
            public void ErrorCallbak(String resp) {

            }
        });

    }


    public  void getSchedulesUser(final Context context, String url, JSONObject jsonobj, final schedulePresInterface schedulePresInterface, String studentId){

        Callweb.CallSendPostServices(url, jsonobj, new ServiceCallback() {
            @Override
            public void SuccessCallbak(String resp) throws JSONException {

                // String response=resp.toString().replace("\\","");

                //if (obj2.getString("patronCheckoutInfo") != null)


                String response=resp;
                response.replace(" ","");
                Log.e("getSchedules","getSchedulesAll "+response);

                JSONObject jsonResponse = new JSONObject(response);

                String status =jsonResponse.getString("status");

              if(status.contains("true")&& !jsonResponse.isNull("schedules")) {


                  String array=jsonResponse.getString("schedules");
                  JSONArray schedulesarray = new JSONArray(array);
                  int arraySize=schedulesarray.length();

                  if (schedulesarray.length() > 0 && !schedulesarray.equals(null)) {
                      for (int i = 0; i < schedulesarray.length(); i++) {

                          JSONObject jsonObject2 = schedulesarray.getJSONObject(i);

                         //checking if condition

                              String pkey = jsonObject2.getString("pkey");
                              String starttime = convertGMTSchedule(jsonObject2.getString("starttime"),context);

                              String starttimeNull = formatDate(starttime);


                              String alertid = jsonObject2.getString("alertid");
                              String text = jsonObject2.getString("text");

                              String subject = jsonObject2.getString("subject");

                              String active = jsonObject2.getString("active");

                              //endtime

                              String endtime = convertGMTSchedule(jsonObject2.getString("endtime"),context);
                              String endtimeNull = formatDate(endtime);

                              Schedule schedule = new Schedule();

                              schedule.setStudentId(studentId);
                              schedule.setPkey(pkey);
                              schedule.setScheduleId(alertid);
                              schedule.setStarttime(starttime);
                              schedule.setEndtime(endtime);
                              schedule.setSubject(subject);
                              schedule.setText(text);
                              schedule.setActive(active);
                              schedule.setStarttimeNull(starttimeNull);
                              schedule.setEndtimeNull(endtimeNull);


                              insertSchedules(schedule, context, schedulePresInterface, i, arraySize);



                      }
                  } else {

                      schedulePresInterface.setMessagesServer(Constants.NO_MESSAGE);
                  }


              }else{

                  schedulePresInterface.setMessagesServer(Constants.ERROR_SERVICE);
              }

////end of if}



            }

            @Override
            public void ErrorCallbak(String resp) {

            }
        });

    }

    public void deleteteScheduleid(String pkey,Schedule schedule,schedulePresInterface schedulePresInterface,Context context,String studentId){

    new Thread(new Runnable() {
        @Override
        public void run() {

            Log.e("pkey ","pkey23  "+pkey);

            AppDatabase appdb = AppDatabase.getAppDatabase(context);
            appdb.schedule_dao().DeleteScheduleAllUser(studentId);

            schedulePresInterface.deleteTablesMessageRes(Constants.PASS_VALIDATION);

        }
    }).start();
    }

    public  void updatewSchedulesUser(final Context context, String url, JSONObject jsonobj, final schedulePresInterface schedulePresInterface, String studentId, String pkey){

        Log.e("updatewSchedulesUser","updatewSchedulesUser "+jsonobj.toString());



        Callweb.CallSendPostServices(url, jsonobj, new ServiceCallback() {
            @Override
            public void SuccessCallbak(String resp) throws JSONException {



                // String response=resp.toString().replace("\\","");
                String response=resp;
                response.replace(" ","");
                Log.e("updatewSchedulesUser","updatewSchedulesUser "+response);

                JSONObject jsonResponse = new JSONObject(response);

                String status =jsonResponse.getString("status");

                if(status.contains("true")) {


                    String schedules =jsonResponse.getString("schedule");

                            JSONObject jsonObject2 = new JSONObject(schedules);

                            String pkey = jsonObject2.getString("pkey");
                            String starttime = convertGMT(jsonObject2.getString("starttime"),context);

                            String starttimeNull=formatDate(starttime);


                            String alertid = jsonObject2.getString("alertid");
                            String text = jsonObject2.getString("text");

                            String subject = jsonObject2.getString("subject");

                            String active = jsonObject2.getString("active");

                            String activeFlag="";
                            if(active.contains("1")){
                                activeFlag="true";
                            }else{
                                activeFlag="false";
                            }

                            //endtime

                            String endtime = convertGMT(jsonObject2.getString("endtime"),context);
                            String endtimeNull=formatDate(endtime);

                            Schedule schedule = new Schedule();

                            schedule.setStudentId(studentId);
                            schedule.setPkey(pkey);
                            schedule.setScheduleId(alertid);
                            schedule.setStarttime(starttime);
                            schedule.setEndtime(endtime);
                            schedule.setSubject(subject);
                            schedule.setText(text);
                            schedule.setActive(activeFlag);
                            schedule.setStarttimeNull(starttimeNull);
                            schedule.setEndtimeNull(endtimeNull);


                   // insertSchedulesUp(schedule, context, schedulePresInterface,pkey);

                   deleteteScheduleid(pkey.toString(),schedule,schedulePresInterface,context,studentId);

                }else{

                    schedulePresInterface.setMessagesServer(Constants.ERROR_SERVICE);
                }

////end of if}



            }

            @Override
            public void ErrorCallbak(String resp) {

            }
        });

    }


    public  static void getBackgroundService(Context context){

    String url=Constants.GET_BACKGROUND_SERVICE;

     SharedPreferences lastTokenPreference =context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE,Context.MODE_PRIVATE);

        final String token=lastTokenPreference.getString("tokenNo","");

        JSONObject jsonObject = new JSONObject();

        try{
            jsonObject.put("token",token);

        }catch (Exception e){
            e.printStackTrace();
        }

        Log.e("getBackgroundService ","getBackgroundService "+jsonObject.toString());

    Callweb.CallSendPostServices(url, jsonObject, new ServiceCallback() {
        @Override
        public void SuccessCallbak(String resp) throws JSONException {

           Log.e("getBackgroundService ","getBackgroundService "+resp.toString());

           JSONObject jsonObject1 = new JSONObject(resp.toString());
           String status=jsonObject1.getString("status");

           if(status.contains("true")){

               JSONObject jsonStatus=jsonObject1.getJSONObject("stats");

               JSONObject jsonMessage=jsonStatus.getJSONObject("message");
               int messagecount= Integer.parseInt(jsonMessage.getString("count_receive"));



               JSONObject jsonAlert=jsonStatus.getJSONObject("alert");
               int alertcount= Integer.parseInt(jsonAlert.getString("count_current"));

               JSONObject jsonSchedule=jsonStatus.getJSONObject("schedule");
               int schedulecount= Integer.parseInt(jsonSchedule.getString("count_current"));

               compareDbCounts(messagecount,alertcount,schedulecount,context);



           }

        }

        @Override
        public void ErrorCallbak(String resp) {

        }
    });
    }

    public static void compareDbCounts(int msgcount,int alcount,int schecount, Context context){

    new Thread(new Runnable() {
        @Override
        public void run() {

            SharedPreferences masterPreference = context.getSharedPreferences(Constants.MASTER_USER_PREFERENCE, Context.MODE_PRIVATE);

            SharedPreferences prevPreference = context.getSharedPreferences("Previous_preference", Context.MODE_PRIVATE);

            String mastername = masterPreference.getString("mastername", "");

            AppDatabase appdb = AppDatabase.getAppDatabase(context);


            int messagecountdb=appdb.messageIn_dao().getMessageCunt(mastername);
            int alertcountdb=appdb.alerts_dao().getAlertCount(mastername);
            int schedulecountdb=appdb.schedule_dao().getScheduleCount(mastername);




            int prevMsgCount=prevPreference.getInt("prevMsgCount",0);
            int prevAlertCount=prevPreference.getInt("prevAlertCount",0);
            int prevScheduleCount=prevPreference.getInt("prevScheduleCount",0);


            Log.e("messagecount ","messagecountprev "+prevMsgCount +" msgcount "+msgcount);

            //building message


            if(prevMsgCount==0 || prevMsgCount<msgcount){

                prevMsgCount=msgcount;

                SharedPreferences.Editor editor = prevPreference.edit();
                editor.putInt("prevMsgCount",prevMsgCount);
                editor.commit();

                int msgDifference = msgcount-messagecountdb;

                if(msgDifference>0) {

                    for (int i = 0; i < msgDifference; i++) {
                        showNotification(context, "new message", "you have a new message");
                    }

                }


            }else if(prevMsgCount==msgcount){

                //no notif needed
            }

            //////alertttt

            Log.e("messagecount ","alertcountprev "+prevMsgCount +" msgcount "+msgcount);


            if(prevAlertCount==0 || prevAlertCount<alcount){

                prevAlertCount=alcount;

                SharedPreferences.Editor editor = prevPreference.edit();
                editor.putInt("prevAlertCount",prevAlertCount);
                editor.commit();

                int alerdifference=alcount-alertcountdb;

                if(alerdifference>0) {

                    for (int i = 0; i < alerdifference; i++) {
                        showNotification(context, "new alert", "you have a new alert");
                    }

                }


            }else if(prevAlertCount==alcount){

                //no notif needed
            }

///schedules

            Log.e("prevScheduleCount ","prevScheduleCount "+prevScheduleCount +" schecount "+schecount);

            if(prevScheduleCount==0 || prevScheduleCount<schecount){

                prevScheduleCount=schecount;

                SharedPreferences.Editor editor = prevPreference.edit();
                editor.putInt("prevScheduleCount",prevScheduleCount);
                editor.commit();

                int scheduledifference=schecount-schedulecountdb;

                if(scheduledifference>0) {

                    for (int i = 0; i < scheduledifference; i++) {
                        showNotification(context, "new schedule", "you have a new schedule");
                    }

                }


            }else if(prevAlertCount==alcount){

                //no notif needed
            }


            Alarm alarm = new Alarm();
            alarm.setAlarm(context);


        }
    }).start();
    }





    public static void showNotification(Context context, String title, String body) {

        Intent intent = new Intent(context, splashViewClass.class);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
        int notificationId = m;
        String channelId = "channel-01";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.alert)
                .setContentTitle(title)
                .setSound(alarmSound)
               .setOnlyAlertOnce(true)
                //.setDefaults(Notification.FLAG_ONLY_ALERT_ONCE)
                .setAutoCancel(true)
                .setContentText(body);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(intent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }



    public  void sentNewSchedulesUser(final Context context, String url, JSONObject jsonobj, final schedulePresInterface schedulePresInterface, String studentId){

        Log.e("sentNewSchedulesUser ","sentNewSchedulesUser "+jsonobj.toString());
        Callweb.CallSendPostServices(url, jsonobj, new ServiceCallback() {
            @Override
            public void SuccessCallbak(String resp) throws JSONException {

                // String response=resp.toString().replace("\\","");
                String response=resp;
                response.replace(" ","");

                Log.e("getSchedules","getSchedulesNew "+response);

                JSONObject jsonResponse = new JSONObject(response);

                String status =jsonResponse.getString("status");

                if(status.contains("true")) {


                    String schedules =jsonResponse.getString("schedule");

                    JSONObject jsonObject2 = new JSONObject(schedules);

                    String pkey = jsonObject2.getString("pkey");
                   // String starttime = convertGMT(jsonObject2.getString("starttime"));
                    String starttime = jsonObject2.getString("starttime");


                    String starttimeNull=formatDate(starttime);


                    String alertid = jsonObject2.getString("alertid");
                    String text = jsonObject2.getString("text");

                    String subject = jsonObject2.getString("subject");

                    String active = jsonObject2.getString("active");

                    //endtime

                    //String endtime = convertGMT(jsonObject2.getString("endtime"));

                    String endtime = jsonObject2.getString("endtime");

                    String endtimeNull=formatDate(endtime);

                    Schedule schedule = new Schedule();

                    schedule.setStudentId(studentId);
                    schedule.setPkey(pkey);
                    schedule.setScheduleId(alertid);
                    schedule.setStarttime(starttime);
                    schedule.setEndtime(endtime);
                    schedule.setSubject(subject);
                    schedule.setText(text);
                    schedule.setActive(active);
                    schedule.setStarttimeNull(starttimeNull);
                    schedule.setEndtimeNull(endtimeNull);


                    insertSchedulesNew(schedule, context, schedulePresInterface);

                }else{

                    schedulePresInterface.setMessagesServer(Constants.ERROR_SERVICE);
                }

////end of if}



            }

            @Override
            public void ErrorCallbak(String resp) {

            }
        });

    }


    public  void getMessageUser(final Context context, String url, JSONObject jsonobj, final mailPresInterface mailPresInterface){

        Callweb.CallSendPostServices(url, jsonobj, new ServiceCallback() {
            @Override
            public void SuccessCallbak(String resp) throws JSONException {


               // String response=resp.toString().replace("\\","");
                String response=resp;
                response.replace(" ","");
                Log.e("getMessageUser","getMessageUser "+response);

                JSONObject jsonResponse = new JSONObject(response);

                String status=jsonResponse.getString("status");

                if(status.contains("true")){


                    String array=jsonResponse.getString("messages");
                    JSONArray messageArray = new JSONArray(array);

                    int arraysize=messageArray.length();

                    if(messageArray.length()>0 && !messageArray.equals(null)) {
                        for (int i = 0; i < messageArray.length(); i++) {

                            JSONObject jsonObject2 = messageArray.getJSONObject(i);

                            String messageId = jsonObject2.getString("pkey");
                            String frompersonid = jsonObject2.getString("fromusername");

                            String topersonid = jsonObject2.getString("tousername");
                            String createdtimestamp = convertGMT(jsonObject2.getString("doc"),context);

                            String threadid = jsonObject2.getString("threadid");
                            String message = jsonObject2.getString("text");
                            String subject = jsonObject2.getString("subject");

                            String readflag = convertGMT(jsonObject2.getString("dor"),context);


                            Message_In message_in = new Message_In();

                            message_in.setStudentId(topersonid);
                            message_in.setMessageInId(messageId);
                            message_in.setThreadId(threadid);
                            message_in.setMessageSender(frompersonid);
                            message_in.setMessageReceiver(topersonid);
                            message_in.setMessageDateOfCreated(createdtimestamp);
                            message_in.setMessageSub(subject);
                            message_in.setMessages(message);
                            message_in.setReadFlag(readflag);

                            insertMessages(message_in, context, mailPresInterface, topersonid,i,arraysize);

                        }
                    }else{

                        mailPresInterface.setMessagesServer(Constants.NO_MESSAGE);
                    }

                }



            }

            @Override
            public void ErrorCallbak(String resp) {

            }
        });

    }

   public static void testLogOut(Context context, final mainViewInterface mainViewInterface){

       loginPreference = context.getSharedPreferences(Constants.LOGIN_PREFERENCE, Context.MODE_PRIVATE);
       lastTokenPreference = context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE,Context.MODE_PRIVATE);


       final String token=lastTokenPreference.getString("tokenNo","");
       final int tokenInc=lastTokenPreference.getInt("tokeInc",0)+1;

       String url=Constants.GET_LOGOUT;

       JSONObject jsonLogout = new JSONObject();

       try {
           jsonLogout.put("token",token);
       } catch (JSONException e) {
           e.printStackTrace();
       }

       Callweb.CallSendPostServices(url, jsonLogout, new ServiceCallback() {
           @Override
           public void SuccessCallbak(String resp) throws JSONException {

               Log.e("logoutresp ","logoutresp "+resp.toString());

               String response=resp.toString();
               mainViewInterface.logOut();

           }

           @Override
           public void ErrorCallbak(String resp) {


           }
       });

   }




    public static void testLogin(final String userName, final String passWord, final loginPresInterface loginPresInterface, final Context context){

       // new SendPostRequest().execute();

        final SharedPreferences studentPreference= context.getSharedPreferences(Constants.STUDENT_SELECTION_PREFERENCE,Context.MODE_PRIVATE);

        lastTokenPreference = context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE,Context.MODE_PRIVATE);

        final AppDatabase appdb = AppDatabase.getAppDatabase(context);
        String url=Constants.GET_LOGIN;
        JSONObject postDataParams = new JSONObject();
        try {
            postDataParams.put("username", userName);
            postDataParams.put("password", passWord);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("params",postDataParams.toString());

        Callweb.CallSendPostServices(url, postDataParams, new ServiceCallback() {
            @Override
            public void SuccessCallbak(String resp) throws JSONException {
                Log.e("miskool","miskoolAccessTokenresp "+resp.toString());


                try{

                    JSONObject jsonObject = new JSONObject(resp.toString());

                    String status=jsonObject.getString("status");

                    if(status.contains("true")){

                        String accesstoken=jsonObject.getString("token");

                        SharedPreferences.Editor editor = lastTokenPreference.edit();
                        editor.putString("tokenNo",accesstoken);
                        editor.putInt("tokeInc",0);
                        editor.commit();

                        //adding master user details on sharedpreference

                        SharedPreferences.Editor editorstudent = studentPreference.edit();
                        editorstudent.putString("studentid", passWord);
                        editorstudent.putString("studentname",userName);
                        editorstudent.commit();


                       // insertToken(accesstoken,context,loginPresInterface);



                        Methods.getUsers(context,loginPresInterface);



                    }else{

                        loginPresInterface.showError(Constants.ERROR_CREDENTIALS);

                    }

                }catch (Exception e){
                    e.printStackTrace();

                    loginPresInterface.showError(Constants.ERROR_SERVICE);
                }
            }

            @Override
            public void ErrorCallbak(String resp) {

                loginPresInterface.showError(Constants.ERROR_SERVICE);

            }
        });

    }


    public static  class SendPostRequest extends AsyncTask<String, Void, String> {

        protected void onPreExecute(){}

        protected String doInBackground(String... arg0) {

            try {

                URL url = new URL(Constants.GET_LOGIN); // here is your URL path

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("username", "eldhosemani3");
                postDataParams.put("password", "loother1");
                Log.e("params",postDataParams.toString());


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000 /* milliseconds */);
                conn.setConnectTimeout(15000 /* milliseconds */);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode=conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in=new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line="";

                    while((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                }
                else {
                    return new String("false : "+responseCode);
                }
            }
            catch(Exception e){
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result) {

            Log.e("resutlllt ","cccccc "+result);

        }
    }

    public static String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }


    String formatDate(String dateString){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String finlDate="";

        try {
            Date date1 = format1.parse(dateString);

             finlDate=format2.format(date1);


        } catch (ParseException e) {
            e.printStackTrace();
        }

       return finlDate;
    }


    public static String convertGMTMILLI(String isoDate){

        String finalDate="";

        String isodate=isoDate;

        long timestamp = 0;
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateformat.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {

            Date date = dateformat.parse(isodate);
            timestamp = date.getTime();

            // DateFormat sdf = new SimpleDateFormat("hh:mm:ss yyyy-MM-dd");

            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormatter.setTimeZone(TimeZone.getTimeZone("IST"));

            Date netDat = new Date(timestamp);

            finalDate = dateFormatter.format(netDat);

            Log.e("netDate ", "netDate " + finalDate);
        }catch (Exception e){
            e.printStackTrace();
        }

        return finalDate;
    }



    public static String insertString(
            String originalString,
            String stringToBeInserted,
            int index)
    {

        // Create a new string
        String newString = new String();

        for (int i = 0; i < originalString.length(); i++) {

            // Insert the original string character
            // into the new string
            newString += originalString.charAt(i);

            if (i == index) {

                // Insert the string to be inserted
                // into the new string
                newString += stringToBeInserted;
            }
        }

        // return the modified String
        return newString;
    }

    public static String convertGMTSchedule(String isoDate,Context context) {


        long timestamp = 0;

        String finalDate = "";
        try {

            String isodate = isoDate;

            Log.e("isodate ", "isodate " + isodate);


            String myName = isodate;

            if (!isodate.equals(null) && !isodate.equals("null") && !isodate.equals("none")) {
                char[] myNameChars = myName.toCharArray();
                myNameChars[10] = 'T';
                myName = String.valueOf(myNameChars);
            }

            //2019-03-22 18:28:13.647221+00:00    String string2 = "2001-07-04T12:08:56.235-07:00";   DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");

            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");

            df2.setTimeZone(TimeZone.getTimeZone("UTC"));
            String string2 = myName;


            Date result2 = df2.parse(string2);
            DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = targetFormat.format(result2);

            Log.e("formattedDate ", " formattedDate" + formattedDate);

            finalDate = formattedDate;

        } catch (Exception e) {
            e.printStackTrace();
        }
return  finalDate;
    }







        public static String convertGMT(String isoDate,Context context){


        long timestamp = 0;

        String finalDate="";
        try{

        String isodate=isoDate;

        Log.e("isodate ","isodate "+isodate);



        String myName = isodate;

        if(!isodate.equals(null)&& !isodate.equals("null")&& !isodate.equals("none")) {
            char[] myNameChars = myName.toCharArray();
            myNameChars[10] = 'T';
            myName = String.valueOf(myNameChars);
        }

        //2019-03-22 18:28:13.647221+00:00    String string2 = "2001-07-04T12:08:56.235-07:00";   DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");

        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");

        df2.setTimeZone(TimeZone.getTimeZone("UTC"));
        String string2 = myName;



            Date result2 = df2.parse(string2);
            DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = targetFormat.format(result2);

            Log.e("formattedDate "," formattedDate"+ formattedDate);

            finalDate=formattedDate;

        } catch (Exception e) {
            e.printStackTrace();
        }



/*

        java.util.Date date = new Date(); // A Date object coming from other code.

// Pass the java.util.Date object to constructor of Joda-Time DateTime object.
        DateTimeZone kolkataTimeZone = DateTimeZone.forID( "Asia/Kolkata" );
        DateTime dateTimeInKolkata = new DateTime( date, kolkataTimeZone );

        DateTimeFormatter formatter = DateTimeFormat.forPattern( "yyyy-MM-dd");


        System.out.println( "dateTimeInKolkata formatted for date: " + formatter.print( dateTimeInKolkata ) );
        System.out.println( "dateTimeInKolkata formatted for ISO 8601: " + dateTimeInKolkata );
*/




       /* // set the formatter to UTC
        org.joda.time.format.DateTimeFormatter inputFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSXXX")
                .withZone(DateTimeZone.UTC);

// DateTime will be in UTC
        DateTime parsed = inputFormatter.parseDateTime(isoDate);

        org.joda.time.format.DateTimeFormatter outputFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(DateTimeZone.forID("Asia/Kolkata"));

        System.out.println(outputFormatter.print(parsed));

        Log.e("foxxx "," foxxx "+outputFormatter.print(parsed));
*/


       /* String date2 = "2018-01-09T11:11:02.0+03:00";
        DateTimeFormatter formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        DateTimeFormatter formatterOut = DateTimeFormatter.ofPattern("dd.MM.yyyy hh:mm");
        LocalDate parsedDate = LocalDate.parse(isodate, formatter);

        String formattedDate = formatterOut.format(parsedDate);
        Log.d("Date format", "output date :" + formattedDate);*/



      /*  DateTimeFormatter parser2 = ISODateTimeFormat.dateTimeNoMillis();
        String jtdate = "2010-01-01T12:00:00+01:00";
        System.out.println(parser2.parseDateTime(jtdate));
*/



        String dateString = "2019-03-22T18:28:13.647221+00:00";
        /*DateTimeFormatter inputFormatter =
                DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSSSSSZ").withZone(DateTimeZone.UTC);
        DateTime dt = inputFormatter.parseDateTime(dateString);
        System.out.println(dt); // 1990-05-03T20:00:00.000Z*/

        /*DateTime jodaTime = DateTime.parse(dateString);

        SimpleDateFormat dateformat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS'Z'" );

        String forxdate=dateformat1.format(jodaTime);


        Log.e("foxxx "," foxxx "+forxdate);

       *//* LocalDate date = LocalDate.of(forxdate); // get from somewhere
        String formattedDate = date.toString();
        System.out.println(formattedDate);*//*


        long timestamp = 0;
       // SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" );

        dateformat.setTimeZone(TimeZone.getTimeZone("GMT"));




        try {

            Date date2 = dateformat.parse(isodate.trim());
            timestamp = date.getTime();

            // DateFormat sdf = new SimpleDateFormat("hh:mm:ss yyyy-MM-dd");

            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormatter.setTimeZone(TimeZone.getTimeZone("IST"));

            Date netDat = new Date(timestamp);

            finalDate = dateFormatter.format(netDat);

            Log.e("netDate11 ", "netDate11 " + finalDate);
        }catch (Exception e){
            e.printStackTrace();
        }*/

        return finalDate;
    }




    public static String convertGMTCreated(String isoDate,Context context) {


        long timestamp = 0;

        String finalDate = "";
        try {

            String isodate = isoDate;

            Log.e("isodate ", "isodate " + isodate);


            String myName = isodate;

            if (!isodate.equals(null) && !isodate.equals("null") && !isodate.equals("none")) {
                char[] myNameChars = myName.toCharArray();
                myNameChars[10] = 'T';
                myName = String.valueOf(myNameChars);
            }

            //2019-03-22 18:28:13.647221+00:00    String string2 = "2001-07-04T12:08:56.235-07:00";   DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSXXX");

            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");

            df2.setTimeZone(TimeZone.getTimeZone("UTC"));
            String string2 = myName;


            Date result2 = df2.parse(string2);
            DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate = targetFormat.format(result2);

            Log.e("formattedDate ", " formattedDate" + formattedDate);

            finalDate = formattedDate;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalDate;
    }


        private static ZoneId geZoneId() {

            return ZoneId.systemDefault();

    }

    public static ZonedDateTime getDate(@NonNull String isoDateString) {
        return ZonedDateTime.parse(isoDateString).withZoneSameInstant(geZoneId());

    }





    public static List<Date> getDates(String dateString1, String dateString2)
    {


        ArrayList<Date> dates = new ArrayList<Date>();
        try {
            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

            Date date1 = null;
            Date date2 = null;

            try {
                date1 = df1.parse(dateString1);
                date2 = df1.parse(dateString2);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date1);


            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(date2);

            while (!cal1.after(cal2)) {
                dates.add(cal1.getTime());
                cal1.add(Calendar.DATE, 1);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return dates;
    }



    public static void getRouteList(JSONObject jsonObject,Context context,mapViewInterface mapViewInterface){

    String url=Constants.GET_ROUTE_DETAILS;

        final SharedPreferences routeIdPreference= context.getSharedPreferences(Constants.ROUTE_ID_PREFERENCE,Context.MODE_PRIVATE);


        Log.e("getRouteList", "getRouteListInp "+jsonObject.toString());

        Callweb.CallSendPostServices(url, jsonObject, new ServiceCallback() {
        @Override
        public void SuccessCallbak(String resp) throws JSONException {


            Log.e("getRouteList", "getRouteList "+resp.toString());


           JSONObject jsonObject1 = new JSONObject(resp.toString());

            String array = jsonObject1.getString("route_ids");
            JSONArray jsonArray = new JSONArray(array);

           //JSONArray jsonArray = jsonObject1.getJSONArray("route_ids");

           for(int i=0;i<jsonArray.length();i++){

               JSONObject jsonObject2= jsonArray.getJSONObject(0);
               String routeId=jsonObject2.getString("routeid");
               String orgid=jsonObject2.getString("orgid");

               SharedPreferences.Editor editor = routeIdPreference.edit();
               editor.putString("routeId",routeId);
               editor.putString("orgid",orgid);
               editor.commit();

               mapViewInterface.setRouteId(routeId);

           }
        }

        @Override
        public void ErrorCallbak(String resp) {

        }
    });


    }

    public static void getRoutePoints(Context context,mapViewInterface mapViewInterface){
        final SharedPreferences routeIdPreference= context.getSharedPreferences(Constants.ROUTE_ID_PREFERENCE,Context.MODE_PRIVATE);

        final SharedPreferences currLocationPreference = context.getSharedPreferences(Constants.CURR_BUS_LOCATION,Context.MODE_PRIVATE);

        SharedPreferences lastTokenPreference =context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE,Context.MODE_PRIVATE);
        final String token=lastTokenPreference.getString("tokenNo","");
        String routeId=routeIdPreference.getString("routeId","");

        String orgid=routeIdPreference.getString("orgid","");

        JSONObject jsonObject= new JSONObject();
        try{
            jsonObject.put("routeid",routeId);
            jsonObject.put("token",token);
            jsonObject.put("orgid",orgid);

        }catch (Exception e){
            e.printStackTrace();
        }

        String url=Constants.GET_CURR_LOC;



        Callweb.CallSendPostServices(url, jsonObject, new ServiceCallback() {
            @Override
            public void SuccessCallbak(String resp) throws JSONException {

                JSONObject jsonObject1 = new JSONObject(resp.toString());

                JSONObject jsonObject2=jsonObject1.getJSONObject("route");

                String status=jsonObject1.getString("status");

                if(status.contains("true")) {

                    String current_lat = jsonObject2.getString("current_lat");
                    String current_long = jsonObject2.getString("current_long");
                    String routeId= jsonObject2.getString("routeid");
                    String routeName= jsonObject2.getString("name");
                    String last_index = jsonObject2.getString("last_index");
                    String last_update_time=jsonObject2.getString("last_update_time");


                    SharedPreferences.Editor editor = currLocationPreference.edit();
                    editor.putString("current_lat", current_lat);
                    editor.putString("current_long",current_long);
                    editor.putInt("last_index",Integer.parseInt(last_index));

                    editor.commit();


                    String array = jsonObject2.getString("routepoints");
                    JSONArray routeArray = new JSONArray(array);

                    int arraysize = routeArray.length();

                    if(routeArray.length()>0 && !routeArray.equals(null)){

                        for (int i=0;i<routeArray.length();i++) {
                            JSONObject jsonObject3 = routeArray.getJSONObject(i);


                            String lng = jsonObject3.getString("lng");
                            int index = Integer.parseInt(jsonObject3.getString("index"));
                            String pointid = jsonObject3.getString("pointid");
                            String name = jsonObject3.getString("name");
                            String pkey = jsonObject3.getString("pkey");
                            String lat = jsonObject3.getString("lat");

                            RoutePoint routePoint = new RoutePoint();
                            routePoint.setRouteLong(lng);
                            routePoint.setRouteIndex(index);
                            routePoint.setPointId(pointid);
                            routePoint.setRouteName(name);
                            routePoint.setRouteKey(pkey);
                            routePoint.setRouteLat(lat);

                            insertRoutePoints(context, routePoint, mapViewInterface, i, arraysize);


                        }



                    }else{
                        mapViewInterface.setMessage(Constants.ERROR_SERVICE);
                    }






                }else{


                    Intent localIntent1 =
                            new Intent(Constants.BROADCAST_ACTION_BUS)
                                    // Puts the status into the Intent
                                    .putExtra(Constants.EXTENDED_DATA_STATUS, Constants.ERROR_SERVICE);
                    // Broadcasts the Intent to receivers in this app.
                    LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent1);

                }

            }

            @Override
            public void ErrorCallbak(String resp) {

            }
        });


    }

    public static void deleteRoutePoints(final Context context, final mapViewInterface mapViewInterface){

        new Thread(new Runnable() {
            @Override
            public void run() {

                Log.e("delteTables1 ","delteTables1 ");
                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                appdb.route_pointDao().DeleteRoute();

                mapViewInterface.setMessage(Constants.PASS_VALIDATION);


            }
        }).start();
    }




    public static void insertRoutePoints(final Context context, final RoutePoint routePoint, final mapViewInterface mapViewInterface, final int intext, final int arraySize){

        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                appdb.route_pointDao().insertAll(routePoint);

                if(intext==arraySize-1){
                    mapViewInterface.setMessage(Constants.PASS_SERVICE);
                }


            }
        }).start();

    }

    public static void getCurrentLocation(Context context){
        final SharedPreferences routeIdPreference= context.getSharedPreferences(Constants.ROUTE_ID_PREFERENCE,Context.MODE_PRIVATE);

        final SharedPreferences currLocationPreference = context.getSharedPreferences(Constants.CURR_BUS_LOCATION,Context.MODE_PRIVATE);

        SharedPreferences lastTokenPreference =context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE,Context.MODE_PRIVATE);
        final String token=lastTokenPreference.getString("tokenNo","");
        String routeId=routeIdPreference.getString("routeId","");

        String orgid=routeIdPreference.getString("orgid","");

        JSONObject jsonObject= new JSONObject();
        try{
            jsonObject.put("orgid",orgid);
            jsonObject.put("routeid",routeId);
            jsonObject.put("token",token);


        }catch (Exception e){
            e.printStackTrace();
        }

        String url=Constants.GET_CURR_LOC;



    Callweb.CallSendPostServices(url, jsonObject, new ServiceCallback() {
        @Override
        public void SuccessCallbak(String resp) throws JSONException {

          JSONObject jsonObject1 = new JSONObject(resp.toString());

          JSONObject jsonObject2=jsonObject1.getJSONObject("route");

          String status=jsonObject1.getString("status");

          if(status.contains("true")) {

              String current_lat = jsonObject2.getString("current_lat");
              String current_long = jsonObject2.getString("current_long");
              String routeId= jsonObject2.getString("routeid");
              String routeName= jsonObject2.getString("name");
              String last_index = jsonObject2.getString("last_index");
              String last_update_time=jsonObject2.getString("last_update_time");


              int lastPrevIntex=currLocationPreference.getInt("lastPrevIntex",-1);

              SharedPreferences.Editor editor = currLocationPreference.edit();
              editor.putString("current_lat", current_lat);
              editor.putString("current_long",current_long);
              editor.putInt("last_index",Integer.parseInt(last_index));

              editor.commit();


              Log.e("BROADCAST_ACTION_BUS", "BROADCAST_ACTION_BUS ");


              Intent localIntent1 =
                      new Intent(Constants.BROADCAST_ACTION_BUS)
                              // Puts the status into the Intent
                              .putExtra(Constants.EXTENDED_DATA_STATUS, Constants.PASS_SERVICE);
              // Broadcasts the Intent to receivers in this app.
             context.sendBroadcast(localIntent1);



          }else{


              Intent localIntent1 =
                      new Intent(Constants.BROADCAST_ACTION_BUS)
                              // Puts the status into the Intent
                              .putExtra(Constants.EXTENDED_DATA_STATUS, Constants.ERROR_SERVICE);
              // Broadcasts the Intent to receivers in this app.
              LocalBroadcastManager.getInstance(context).sendBroadcast(localIntent1);

          }

        }

        @Override
        public void ErrorCallbak(String resp) {

        }
    });
    }


    public static void registerNewUser(JSONObject jsonObject, registerInterface loginViewInterface){

    String url=Constants.CREATE_USER;

    Log.e("JSONObject","JSONObject "+jsonObject.toString());

    Callweb.CallSendPostServices(url, jsonObject, new ServiceCallback() {
        @Override
        public void SuccessCallbak(String resp) throws JSONException {

            Log.e("registerNewUser ","registerNewUser "+resp.toString());

            JSONObject jsonObject1 = new JSONObject(resp.toString());

            String status=jsonObject1.getString("status");

            if(status.contains("true")) {

                loginViewInterface.showMessage(Constants.NEW_USER);
            }

        }

        @Override
        public void ErrorCallbak(String resp) {

        }
    });


    }

    public static void getContacts(Context context,mailPresInterface mailPresInterface){

    String url=Constants.GET_CONTACTS;

        SharedPreferences lastTokenPreference =context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE,Context.MODE_PRIVATE);
        final String token=lastTokenPreference.getString("tokenNo","");

        JSONObject jsonObject= new JSONObject();
        try{
            jsonObject.put("token",token);

        }catch (Exception e){
            e.printStackTrace();

        }


        Callweb.CallSendPostServices(url, jsonObject, new ServiceCallback() {
            @Override
            public void SuccessCallbak(String resp) throws JSONException {


                Log.e("registerNewUser ","registerNewUser "+resp.toString());

                JSONObject jsonObject1 = new JSONObject(resp.toString());

                String status=jsonObject1.getString("status");

                if(status.contains("true")) {

                    String array = jsonObject1.getString("contacts");
                    JSONArray routeArray = new JSONArray(array);

                    int arraysize = routeArray.length();

                    if(routeArray.length()>0 && !routeArray.equals(null)){

                        for(int i=0;i<routeArray.length();i++){

                            JSONObject jsonObject3 = routeArray.getJSONObject(i);

                            String username=jsonObject3.getString("username");
                            String pkey=jsonObject3.getString("pkey");
                            String firstname=jsonObject3.getString("firstname");
                            String othernames=jsonObject3.getString("othernames");
                            String lastname=jsonObject3.getString("lastname");
                            String combName= username+" "+firstname+" "+lastname+" "+othernames;



                            Contacts contacts = new Contacts();
                            contacts.setPkey(pkey);
                            contacts.setFirstname(firstname);
                            contacts.setOthernames(othernames);
                            contacts.setLastname(lastname);
                            contacts.setUsername(username);
                            contacts.setCombName(combName);

                            insertContacts(contacts,context,arraysize,i,mailPresInterface);

                        }
                    }

                }

                }

            @Override
            public void ErrorCallbak(String resp) {

            }
        });

    }

    public static void insertContacts(Contacts contacts,Context context,int arraylen,int index,mailPresInterface mailPresInterface){

  new Thread(new Runnable() {
    @Override
    public void run() {



        AppDatabase appdb = AppDatabase.getAppDatabase(context);
        appdb.contacts_dao().insertAll(contacts);


        if(index==arraylen-1) {
            mailPresInterface.setMessagesServer(Constants.CONTACTS_UP);

        }
    }
}).start();

    }

}


