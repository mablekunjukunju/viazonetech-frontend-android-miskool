package com.zone.android.miskool_Presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Message_Out;
import com.zone.android.miskool_Model.loginModelClass;
import com.zone.android.miskool_Model.mailModelClass;
import com.zone.android.miskool_Model.mailModelInterface;
import com.zone.android.miskool_View.loginViewInterface;
import com.zone.android.miskool_View.mailViewInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Inspiron on 13-01-2018.
 */

public class maiPresClass extends AppCompatActivity implements  mailPresInterface {
     mailViewInterface mailViewInterface;
    mailModelInterface mailModelInterface;


    public maiPresClass(mailViewInterface mailViewInterface){

        this.mailViewInterface = mailViewInterface;

        this.mailModelInterface = new mailModelClass();

    }


    @Override
    public void getMessageServer(String Student_Id, Context context) {

        mailModelInterface.getMessagesServer(Student_Id,this,context );

    }

    @Override
    public void getMessages(String Stdudent_Id, Context context) {

        mailModelInterface.getMessages(Stdudent_Id,this,context );
    }

    @Override
    public void deleteTablesMessage(String studentID, Context context) {

        mailModelInterface.deleteTablesMessage(studentID,context,this);
    }

    @Override
    public void updateReadFlag(Message_In message_in, Context context,String studentId) {
       mailModelInterface.updateReadFlag(message_in,context,this,studentId);
    }

    @Override
    public void updateReadFlagResponse(Message_In message_in) {

        mailViewInterface.updateReadFlagResponse(message_in);

    }

    @Override
    public void deleteTablesMessageRes(int errorCode) {

        mailViewInterface.deleteTablesMessageRes(errorCode);
    }

    @Override
    public void setMessagesServer(int errorCode) {

        mailViewInterface.setMessagesServer(errorCode);
    }

    @Override
    public void setMessages(List<Message_In> messageList) {

        mailViewInterface.setMessages(messageList);
    }


    @Override
    public void sentReply(Message_In message_in, Context context) {
        mailModelInterface.sentReply(message_in,context,this);
    }

    @Override
    public void showMessage(int error) {

        mailViewInterface.showMessage(error);
    }

    @Override
    public void getMembersList(Context context) {
        mailModelInterface.getMembersList(context,this);

    }

    @Override
    public void getContactsDb(Context context) {

        mailModelInterface.getContactsDb(context,this);

    }

    @Override
    public void sentContacts(HashMap<String, String> contactList) {
        mailViewInterface.sentContacts(contactList);
    }

    @Override
    public void insertReply() {

    }
}
