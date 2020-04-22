package com.zone.android.miskool_Model;

import android.content.Context;

import com.zone.android.miskool_Presenter.mainPresInterface;

/**
 * Created by Inspiron on 20-03-2018.
 */

public interface mainModelInterface {
    void getStudentList(mainPresInterface mainPresInterface, Context context);

    void getAlertList(mainPresInterface mainPresInterface, Context context, String stdId);
    void logout();
}
