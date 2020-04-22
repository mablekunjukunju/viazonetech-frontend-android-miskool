package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Bus_Route;
import com.zone.android.miskool_Entitiy.Message_In;

import java.util.List;

/**
 * Created by Inspiron on 23-02-2018.
 */
@Dao
public interface BusRoute_Dao {

    @Query("SELECT * FROM Bus_Route")
    List<Bus_Route> getAll();


    @Query("SELECT * FROM Bus_Route")
    List<Bus_Route> getAllRoutes();

    @Query("SELECT * FROM Bus_Route where student_id = :studentId")
    List<Bus_Route> getRoutesOfStudent(String studentId);


    @Insert
    void insertAll(Bus_Route... routes);
}
