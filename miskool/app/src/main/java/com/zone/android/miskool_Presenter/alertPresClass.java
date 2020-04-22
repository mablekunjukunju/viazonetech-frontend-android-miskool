package com.zone.android.miskool_Presenter;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Alerts;
import com.zone.android.miskool_Model.alertModelClass;
import com.zone.android.miskool_Model.alertModelInterface;
import com.zone.android.miskool_View.alertViewInterface;

import java.util.List;

/**
 * Created by Inspiron on 20-03-2018.
 */

public class alertPresClass implements alertPresInterface {

    alertViewInterface alertViewInterface;
    alertModelInterface alertModelInterface;


    public alertPresClass(alertViewInterface alertViewInterface){

        this.alertViewInterface=alertViewInterface;
        this.alertModelInterface=new alertModelClass();

    }

    @Override
    public void deleteTablesMessage(String Student_Id, Context context) {

        alertModelInterface.deleteTableMessage(Student_Id,this,context);
    }

    @Override
    public void deleteTablesMessageRes(int errorCode) {

        alertViewInterface.deleteTableMessageResp(errorCode);

    }

    @Override
    public void getMessageServer(String Student_Id, Context context) {

        alertModelInterface.getAlertsSever(Student_Id,this,context);
    }

    @Override
    public void setMessagesServer(int errorCode) {

        alertViewInterface.setMessagesServer(errorCode);
    }

    @Override
    public void getAlerts(String Student_Id, Context context) {

        alertModelInterface.getAlerts(Student_Id,this,context);
    }

    @Override
    public void setMessages(List<Alerts> alertList) {
        alertViewInterface.setMessages(alertList);
    }
}
