package com.zone.android.miskool_Model;

import android.content.Context;

import com.zone.android.miskool_Presenter.coursePresInterface;

/**
 * Created by Inspiron on 20-09-2018.
 */

public interface cpurseModelInterface {

    void getTimeTable(coursePresInterface coursePresInterface,  Context context, String stdId);
    void getAttendance(coursePresInterface coursePresInterface,Context context, String stdId);
    void getHomeWork(coursePresInterface coursePresInterface,Context context, String stdId);
    void getExam(coursePresInterface coursePresInterface,Context context, String stdId);
}
