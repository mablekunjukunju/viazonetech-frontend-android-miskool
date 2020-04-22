package com.zone.android.miskool_Model;

import android.content.Context;

import com.zone.android.miskool.examFragmentInterface;

/**
 * Created by Inspiron on 04-10-2018.
 */

public interface examModelInterface {
    void getExamDetails(String studentid, Context context, examFragmentInterface examFragmentInterface);
}
