package com.zone.android.miskool_View;

import com.zone.android.miskool_Entitiy.Alerts;
import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Person_det;
import com.zone.android.miskool_Entitiy.Schedule;

import java.util.List;

/**
 * Created by Inspiron on 20-03-2018.
 */

public interface mainViewInterface {

    void setStudentList(List<Person_det> person_detList);
    void setAlerts(List<Schedule> alertList);
    void logOut();

}
