package com.zone.android.miskool_View;

import com.zone.android.miskool_Entitiy.Schedule;

import java.util.List;

/**
 * Created by Inspiron on 20-10-2018.
 */

public interface scheduleViewInterface {


    void deleteTablesMessageRes(int errorCode);
    void updateReadFlagResponse(Schedule schedule);

    void setMessagesServer(int errorCode);
    void setMessages(List<Schedule> scheduleList);

    void setSelEvents(List<Schedule> scheduleList);
    void showMessage(int error);

    void sentResponse(int errorCode);

    void sentUpresponse(String Student_id,Schedule schedule);
}
