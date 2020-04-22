package com.zone.android.miskool_Presenter;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Message_Out;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Inspiron on 13-01-2018.
 */

public interface mailPresInterface {
    //directing calls from view to model

    void getMessageServer(String Student_Id, Context context);
    void getMessages(String Student_Id, Context context);


    void deleteTablesMessage(String studentID,Context context);
    void updateReadFlag(Message_In message_in,Context context,String studentId);

    void updateReadFlagResponse(Message_In message_in);

    void deleteTablesMessageRes(int errorCode);
    void setMessagesServer(int errorCode);
    void setMessages(List<Message_In> messageList);

    void sentReply(Message_In message_in,Context context);
    void showMessage(int error);

    void getMembersList(Context context);
    void getContactsDb(Context context);

    void sentContacts(HashMap<String, String> contactList);
    void insertReply();


}
