package com.zone.android.miskool_Presenter;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Schedule;
import com.zone.android.miskool_Model.mailModelClass;
import com.zone.android.miskool_Model.mailModelInterface;
import com.zone.android.miskool_Model.scheduleModelClass;
import com.zone.android.miskool_Model.scheduleModelInterface;
import com.zone.android.miskool_View.mailViewInterface;
import com.zone.android.miskool_View.scheduleViewInterface;

import java.util.List;

/**
 * Created by Inspiron on 20-10-2018.
 */

public class schedulePresClass implements schedulePresInterface {


    scheduleViewInterface scheduleViewInterface;
   scheduleModelInterface scheduleModelInterface;


    public  schedulePresClass(scheduleViewInterface scheduleViewInterface){

        this.scheduleViewInterface = scheduleViewInterface;

        this.scheduleModelInterface = new scheduleModelClass();

    }

    @Override
    public void deleteTablesMessage(String studentID, Context context) {

     scheduleModelInterface.deleteTablesMessage(studentID,context,schedulePresClass.this);
    }

    @Override
    public void getMessageServer(String Student_Id, Context context) {

     scheduleModelInterface.getMessagesServer(Student_Id,schedulePresClass.this,context);
    }

    @Override
    public void getMessages(String Student_Id, Context context) {
     scheduleModelInterface.getMessages(Student_Id,schedulePresClass.this,context);
    }

 @Override
 public void getSelEvents(String Student_Id, String Date, Context context) {

        scheduleModelInterface.getSelEvents(Student_Id,Date,schedulePresClass.this,context);
 }

 @Override
    public void deleteTablesMessageRes(int errorCode) {
     scheduleViewInterface.deleteTablesMessageRes(errorCode);
    }

    @Override
    public void setMessagesServer(int errorCode) {
     scheduleViewInterface.setMessagesServer(errorCode);
    }

    @Override
    public void setMessages(List<Schedule> scheduleList) {
       scheduleViewInterface.setMessages(scheduleList);
    }

 @Override
 public void setSelEvents(List<Schedule> scheduleList) {

     scheduleViewInterface.setSelEvents(scheduleList);
 }

    @Override
    public void sentNewEvent(String Student_Id, Schedule schedule, Context context) {
          scheduleModelInterface.sentNewEvent(Student_Id,schedule,context,schedulePresClass.this);
    }

    @Override
    public void sentUpEvent(String Student_Id, Schedule schedule, Context context) {

        scheduleModelInterface.sentUpEvent(Student_Id,schedule,context,schedulePresClass.this);
    }

    @Override
    public void insertUpSchedule(String StudentId, Schedule schedule, Context context) {
        scheduleModelInterface.insertUpSchedule(StudentId,schedule,context,this);
    }

    @Override
    public void sentUpresponse(String Student_id, Schedule schedule) {

        scheduleViewInterface.sentUpresponse(Student_id,schedule);

    }

    @Override
    public void sentDelEvent(String Student_Id, Schedule schedule, Context context) {

    }

    @Override
    public void sentResponse(int errorCode) {

    }
}
