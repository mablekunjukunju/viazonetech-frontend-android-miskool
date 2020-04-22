package com.zone.android.miskool_Model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.zone.android.miskool_Entitiy.Contacts;
import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Message_Out;
import com.zone.android.miskool_Presenter.mailPresInterface;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.Methods;
import com.zone.android.miskool_View.mailViewClass;
import com.zone.android.miskool_db.AppDatabase;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Inspiron on 13-01-2018.
 */

public class mailModelClass extends Activity implements mailModelInterface {
    SharedPreferences lastTokenPreference;

    @Override
    public void getMessages(final String Student_Id, final mailPresInterface mailPresInterface, final Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<Message_In> messageList;
                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                messageList = appdb.messageIn_dao().getInboxMessagesStudent(Student_Id);

                mailPresInterface.setMessages(messageList);
            }
        }).start();

    }

    @Override
    public void getMessagesServer(String Student_Id, mailPresInterface mailPresInterface, Context context) {

        SharedPreferences masterPreference = context.getSharedPreferences(Constants.MASTER_USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences lastTokenPreference = context.getSharedPreferences(Constants.LAST_TOKEN_PREFERENCE, Context.MODE_PRIVATE);

        String mastername = masterPreference.getString("mastername", "");
        String token = lastTokenPreference.getString("tokenNo", "");

        String url = Constants.GET_MESSAGE_ALL;

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
        methods.getMessageUser(context, url, jsonMessage, mailPresInterface);


    }

    @Override
    public void deleteTablesMessage(final String studentID, final Context context, final mailPresInterface mailPresInterface) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                appdb.messageIn_dao().DeleteUserReceive(studentID);
                appdb.contacts_dao().DeleteToken();

                mailPresInterface.deleteTablesMessageRes(Constants.PASS_VALIDATION);

            }
        }).start();
    }

    @Override
    public void updateReadFlag(Message_In message_in, Context context, mailPresInterface mailPresInterface, String studentId) {

        //need to call a service
        Methods.updatereadFlag(message_in, context, mailPresInterface, studentId);


    }

    @Override
    public void sentReply(final Message_In message_in, final Context context, final mailPresInterface mailPresInterface) {
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
            if(!message_in.getMessageInId().equals("")){
                jsonReply.put("thread_id",message_in.getMessageInId());
            }


        }catch (Exception e){
            e.printStackTrace();
        }



        Methods.sendNew(url,jsonReply,context,mailPresInterface);

    }

    @Override
    public void getMembersList(Context context, mailPresInterface mailPresInterface) {

        Methods.getContacts(context,mailPresInterface);
    }

    @Override
    public void getContactsDb(Context context, mailPresInterface mailPresInterface) {

new Thread(new Runnable() {
    @Override
    public void run() {

        HashMap<String, String> hashMap_Contacts = new HashMap<String, String>();


        List<Contacts> contactsList;
        AppDatabase appdb = AppDatabase.getAppDatabase(context);

        contactsList=appdb.contacts_dao().getAlerts();

        for(int i=0;i<contactsList.size();i++){


            String combname = contactsList.get(i).getCombName();
            String userName = contactsList.get(i).getUsername();


            hashMap_Contacts.put(combname,userName);
        }

        mailPresInterface.sentContacts(hashMap_Contacts);


    }
}).start();

    }
}
