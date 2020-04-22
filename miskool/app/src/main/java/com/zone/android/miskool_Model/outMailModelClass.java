package com.zone.android.miskool_Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Message_Out;
import com.zone.android.miskool_Presenter.mailPresInterface;
import com.zone.android.miskool_Presenter.outMailPresInterface;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.Methods;
import com.zone.android.miskool_db.AppDatabase;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Inspiron on 26-03-2018.
 */

public class outMailModelClass implements outMailModelInterface {


    @Override
    public void getMessagesServer(String Student_Id, outMailPresInterface outMailPresInterface, Context context) {

        SharedPreferences masterPreference=context.getSharedPreferences(Constants.MASTER_USER_PREFERENCE,Context.MODE_PRIVATE);
        SharedPreferences lastTokenPreference = context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE,Context.MODE_PRIVATE);

        String mastername= masterPreference.getString("mastername","");
        String token=lastTokenPreference.getString("tokenNo","");

        String url=Constants.GET_MESSAGE_SEND;

        JSONObject jsonMessage = new JSONObject();


        if(Student_Id.equals(mastername)){
            try {
                jsonMessage.put("token", token);

            }catch (Exception e){
                e.printStackTrace();
            }

        }else{

            try {
                jsonMessage.put("token", token);
                jsonMessage.put("forusername", Student_Id);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        Methods methods=new Methods(context);
        methods.getSendMsgUser(context,url,jsonMessage,outMailPresInterface);
    }

    @Override
    public void getMessages(final String Student_Id, final outMailPresInterface outMailPresInterface, final Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<Message_In> messageList;
                AppDatabase appdb = AppDatabase.getAppDatabase(context);

                messageList =appdb.messageIn_dao().getOutMessagesStudent(Student_Id);
                outMailPresInterface.setMessages(messageList);

            }
        }).start();
    }

    @Override
    public void sentReply(Message_Out message_out, Context context, outMailPresInterface outMailPresInterface) {



    }

    @Override
    public void deleteTablesMessage(final String studentID, final Context context, final outMailPresInterface outMailPresInterface) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                appdb.messageIn_dao().DeleteUserSender(studentID);

                outMailPresInterface.deleteTablesMessageRes(Constants.PASS_VALIDATION);

            }
        }).start();
    }
}
