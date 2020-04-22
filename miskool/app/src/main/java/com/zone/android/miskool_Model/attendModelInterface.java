package com.zone.android.miskool_Model;

import android.content.Context;

import com.zone.android.miskool.attendFragmentInterface;

/**
 * Created by Inspiron on 04-10-2018.
 */

public interface attendModelInterface  {

    void getAttendanceDetails(String studentId,Context context,attendFragmentInterface attendFragmentInterface);
}
