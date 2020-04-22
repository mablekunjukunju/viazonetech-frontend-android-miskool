package com.zone.android.miskool_Model;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Message_Out;
import com.zone.android.miskool_Presenter.loginPresInterface;
import com.zone.android.miskool_Presenter.mailPresInterface;

import java.util.ArrayList;

/**
 * Created by Inspiron on 13-01-2018.
 */

public interface mailModelInterface {
    void getMessages(String Student_Id,mailPresInterface mailPresInterface, Context context);
    void getMessagesServer(String Student_Id,mailPresInterface mailPresInterface, Context context);
    void deleteTablesMessage(String studentID,Context context,mailPresInterface mailPresInterface);

    void updateReadFlag(Message_In message_in,Context context,mailPresInterface mailPresInterface,String studentId);


    void sentReply(Message_In message_in,Context context,mailPresInterface mailPresInterface);

    void getMembersList(Context context,mailPresInterface mailPresInterface);
    void getContactsDb(Context context,mailPresInterface mailPresInterface);


}
