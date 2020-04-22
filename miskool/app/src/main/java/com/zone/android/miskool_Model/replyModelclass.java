package com.zone.android.miskool_Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Message_Out;
import com.zone.android.miskool_Presenter.replyPresInterface;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.Methods;
import com.zone.android.miskool_db.AppDatabase;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Inspiron on 04-04-2018.
 */

public class replyModelclass implements replyModelInterface {
    SharedPreferences lastTokenPreference;

    @Override
    public void getMessagesServer(String Student_Id, String thread_id, replyPresInterface replyPresInterface, Context context) {

        SharedPreferences masterPreference = context.getSharedPreferences(Constants.MASTER_USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences lastTokenPreference = context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE, Context.MODE_PRIVATE);

        String mastername = masterPreference.getString("mastername", "");
        String token = lastTokenPreference.getString("tokenNo", "");

        String url = Constants.GET_MESSAGE_RECEIVE;

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
        methods.getMessageReply(context, url, jsonMessage, replyPresInterface);

    }

    @Override
    public void getMessages(final String Student_Id, final String messagein_id, final replyPresInterface replyPresInterface, final Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {


                List<Message_In> messageList;
                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                messageList =appdb.messageIn_dao().getInboxMessagesStudentThread(messagein_id);
                appdb.messageIn_dao().updateFlag(Student_Id,messagein_id);

                replyPresInterface.setMessages(messageList);

            }
        }).start();
    }

    @Override
    public void sentReply(Message_In message_in, Context context, replyPresInterface replyPresInterface) {

        lastTokenPreference = context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE,Context.MODE_PRIVATE);
        final String token=lastTokenPreference.getString("tokenNo","");
        String url=Constants.CREATE_MESSAGE;

//{ token: _token, touser: _username2, subject: _subject, text: _text, thread_id: _message_id })

        JSONObject jsonReply = new JSONObject();

        try{
            jsonReply.put("token",token);
            jsonReply.put("touser",message_in.getMessageReceiver());
            jsonReply.put("subject",message_in.getMessageSub());
            jsonReply.put("text",message_in.getMessages());
            jsonReply.put("thread_id",message_in.getThreadId());

        }catch (Exception e){
            e.printStackTrace();
        }
        boolean checkNetwork= Methods.checknewtwork(context);


        Methods.sendReply(url,jsonReply,context,replyPresInterface);

           /* if(checkNetwork){


            }else{

                replyPresInterface.showMessage("Message not sent");
            }*/
    }
}
