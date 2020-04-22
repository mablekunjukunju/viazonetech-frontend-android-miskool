package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Config_det;
import com.zone.android.miskool_Entitiy.timetable;

import java.util.List;

/**
 * Created by Inspiron on 21-09-2018.
 */

@Dao
public interface timetable_Dao {

    @Query("SELECT * FROM timetable")
    List<timetable> getAll();


    @Query("SELECT * FROM timetable")
    List<timetable> getAllMessages();
}
