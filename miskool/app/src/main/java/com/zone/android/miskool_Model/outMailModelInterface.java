package com.zone.android.miskool_Model;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Message_Out;
import com.zone.android.miskool_Presenter.mailPresInterface;
import com.zone.android.miskool_Presenter.outMailPresInterface;

/**
 * Created by Inspiron on 26-03-2018.
 */

public interface outMailModelInterface {
    void getMessagesServer(String Student_Id,outMailPresInterface outMailPresInterface, Context context);
    void getMessages(String Student_Id, outMailPresInterface outMailPresInterface, Context context);
    void sentReply(Message_Out message_out, Context context, outMailPresInterface outMailPresInterface);

    void deleteTablesMessage(String studentID,Context context,outMailPresInterface outMailPresInterface);

}
