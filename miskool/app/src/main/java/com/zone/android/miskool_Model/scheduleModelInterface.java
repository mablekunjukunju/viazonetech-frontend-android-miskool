package com.zone.android.miskool_Model;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Schedule;
import com.zone.android.miskool_Presenter.schedulePresInterface;

/**
 * Created by Inspiron on 20-10-2018.
 */

public interface scheduleModelInterface {

    void deleteTablesMessage(String studentID,Context context,schedulePresInterface schedulePresInterface);
    void getMessages(String Student_Id,schedulePresInterface schedulePresInterface, Context context);
    void getMessagesServer(String Student_Id,schedulePresInterface schedulePresInterface, Context context);

    void getSelEvents(String Student_Id,String date,schedulePresInterface schedulePresInterface, Context context);

    void sentNewEvent(String Student_Id, Schedule schedule, Context context, schedulePresInterface schedulePresInterface);
    void sentUpEvent(String Student_Id,Schedule schedule, Context context,schedulePresInterface schedulePresInterface);
    void sentDelEvent(String Student_Id,Schedule schedule, Context context,schedulePresInterface schedulePresInterface);

    void insertUpSchedule(String StudentId, Schedule schedule,Context context,schedulePresInterface schedulePresInterface);

}
