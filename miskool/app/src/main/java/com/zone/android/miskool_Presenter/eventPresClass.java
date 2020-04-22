package com.zone.android.miskool_Presenter;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Schedule;
import com.zone.android.miskool_Model.eventModelClass;
import com.zone.android.miskool_Model.eventModelInterface;
import com.zone.android.miskool_Model.mailModelClass;
import com.zone.android.miskool_Model.mailModelInterface;
import com.zone.android.miskool_View.eventViewInterface;
import com.zone.android.miskool_View.mailViewInterface;

/**
 * Created by Inspiron on 23-10-2018.
 */

public class eventPresClass implements eventPresInterface {

    eventViewInterface eventViewInterface;
   eventModelInterface eventModelInterface;


    public eventPresClass(eventViewInterface eventViewInterface){

        this.eventViewInterface = eventViewInterface;

        this.eventModelInterface = new eventModelClass();

    }
    @Override
    public void sentNewEvent(String Student_Id, Schedule schedule, Context context) {

    }

    @Override
    public void sentUpEvent(String Student_Id, Schedule schedule, Context context) {

    }

    @Override
    public void sentDelEvent(String Student_Id, Schedule schedule, Context context) {

    }

    @Override
    public void sentResponse(int errorCode) {

    }
}
