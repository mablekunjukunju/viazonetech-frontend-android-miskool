package com.zone.android.miskool_Presenter;

import android.content.Context;

import com.zone.android.miskool_Model.courseModelClass;
import com.zone.android.miskool_Model.cpurseModelInterface;
import com.zone.android.miskool_View.courseViewInterface;

/**
 * Created by Inspiron on 20-09-2018.
 */

public class coursePresClass  implements coursePresInterface {
    courseViewInterface courseViewInterface;
    cpurseModelInterface cpurseModelInterface;

    public coursePresClass(courseViewInterface courseViewInterface){
        this.courseViewInterface=courseViewInterface;
        this.cpurseModelInterface=new courseModelClass();

    }
    @Override
    public void getTimeTable(Context context, String stdId) {
        cpurseModelInterface.getTimeTable(this,context,"");
    }

    @Override
    public void getAttendance(Context context, String stdId) {

    }

    @Override
    public void getHomeWork(Context context, String stdId) {

    }

    @Override
    public void getExam(Context context, String stdId) {

    }

    @Override
    public void setTimetable() {


        courseViewInterface.setTimetable();
    }

    @Override
    public void setAttendance() {

    }

    @Override
    public void setHomework() {

    }

    @Override
    public void setExam() {

    }
}
