package com.zone.android.miskool_Presenter;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Message_Out;

import java.util.List;

/**
 * Created by Inspiron on 26-03-2018.
 */

public interface outMailPresInterface {

    void getMessageServer(String Student_Id, Context context);
    void getMessages(String Student_Id, Context context);

    void setMessages(List<Message_In> messageList);
    void deleteTablesMessage(String studentID,Context context);
    void deleteTablesMessageRes(int errorCode);

    void setMessagesServer(int errorCode);
    void sentReply(Message_Out message_out, Context context);
    void showMessage(String Message);

}
