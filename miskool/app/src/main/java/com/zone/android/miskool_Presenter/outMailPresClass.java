package com.zone.android.miskool_Presenter;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Message_Out;
import com.zone.android.miskool_Model.outMailModelClass;
import com.zone.android.miskool_Model.outMailModelInterface;
import com.zone.android.miskool_View.outMailViewInterface;

import java.util.List;

/**
 * Created by Inspiron on 26-03-2018.
 */

public class outMailPresClass implements outMailPresInterface {

    outMailViewInterface outMailViewInterface;
    outMailModelInterface outMailModelInterface;


    public outMailPresClass(outMailViewInterface outMailViewInterface){

        this.outMailViewInterface=outMailViewInterface;
        this.outMailModelInterface=new outMailModelClass();
    }

    @Override
    public void getMessageServer(String Student_Id, Context context) {

        outMailModelInterface.getMessagesServer(Student_Id,this,context);
    }

    @Override
    public void getMessages(String Student_Id, Context context) {

        outMailModelInterface.getMessages(Student_Id,this,context);

    }

    @Override
    public void setMessages(List<Message_In> messageList) {

        outMailViewInterface.setMessages(messageList);
    }

    @Override
    public void deleteTablesMessage(String studentID, Context context) {
        outMailModelInterface.deleteTablesMessage(studentID,context,this);
    }

    @Override
    public void deleteTablesMessageRes(int errorCode) {
        outMailViewInterface.deleteTablesMessageRes(errorCode);
    }

    @Override
    public void setMessagesServer(int errorCode) {
       outMailViewInterface.setMessagesServer(errorCode);
    }

    @Override
    public void sentReply(Message_Out message_out, Context context) {

        outMailModelInterface.sentReply(message_out,context,this);
    }

    @Override
    public void showMessage(String Message) {

        outMailViewInterface.showMessage(Message);

    }
}
