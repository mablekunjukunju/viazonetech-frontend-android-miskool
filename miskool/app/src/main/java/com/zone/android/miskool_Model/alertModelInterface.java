package com.zone.android.miskool_Model;

import android.content.Context;

import com.zone.android.miskool_Presenter.alertPresInterface;
import com.zone.android.miskool_Presenter.mailPresInterface;

/**
 * Created by Inspiron on 20-03-2018.
 */

public interface alertModelInterface {

    void deleteTableMessage(String Student_Id, alertPresInterface alertPresInterface, Context context);

    void getAlertsSever(String Student_Id, alertPresInterface alertPresInterface, Context context);
    void getAlerts(String Student_Id, alertPresInterface alertPresInterface, Context context);

}
