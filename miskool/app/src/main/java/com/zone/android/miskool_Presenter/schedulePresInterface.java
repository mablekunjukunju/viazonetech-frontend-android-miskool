package com.zone.android.miskool_Presenter;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Schedule;

import java.util.List;

/**
 * Created by Inspiron on 20-10-2018.
 */

public interface schedulePresInterface {
    void deleteTablesMessage(String studentID,Context context);
    void getMessageServer(String Student_Id, Context context);
    void getMessages(String Student_Id, Context context);

    void getSelEvents(String Student_Id,String Date, Context context);

    void deleteTablesMessageRes(int errorCode);
    void setMessagesServer(int errorCode);
    void setMessages(List<Schedule> scheduleList);

    void setSelEvents(List<Schedule> scheduleList);

    void sentNewEvent(String Student_Id, Schedule schedule, Context context);
    void sentUpEvent(String Student_Id,Schedule schedule, Context context);
   void insertUpSchedule(String StudentId, Schedule schedule,Context context);

    void sentUpresponse(String Student_id,Schedule schedule);
    void sentDelEvent(String Student_Id,Schedule schedule, Context context);

    void sentResponse(int errorCode);



}
