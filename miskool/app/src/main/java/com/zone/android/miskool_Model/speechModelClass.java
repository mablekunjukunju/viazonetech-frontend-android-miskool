package com.zone.android.miskool_Model;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Speech;
import com.zone.android.miskool_Presenter.speechPresInterface;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.Methods;
import com.zone.android.miskool_db.AppDatabase;

import java.util.List;

public class speechModelClass implements speechModelInterface {
    @Override
    public void deleteTable(Context context, speechPresInterface speechPresInterface) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                appdb.speech_dao().DeleteToken();
                speechPresInterface.sentMessage(Constants.PASS_VALIDATION);
            }
        }).start();
    }

    @Override
    public void getMessages(String Student_Id, speechPresInterface speechPresInterface, Context context) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<Speech> messageList;
                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                messageList= appdb.speech_dao().getInboxMessagesStudentThread(Student_Id);
                speechPresInterface.setMessages(messageList);


            }
        }).start();

    }

    @Override
    public void sentReply(Speech speech, Context context, speechPresInterface speechPresInterface) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                appdb.speech_dao().insertAll(speech);

                Methods.sendSpeech(speech,context,speechPresInterface);
            }
        }).start();

    }
}
