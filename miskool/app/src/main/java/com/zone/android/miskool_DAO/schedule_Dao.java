package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;


import com.zone.android.miskool_Entitiy.Schedule;

import java.util.List;

/**
 * Created by Inspiron on 22-10-2018.
 */

@Dao
public interface schedule_Dao {

    @Query("SELECT * FROM Schedule where student_id = :studentId")
    List<Schedule> getSchedulesStudent(String studentId);



    @Query("SELECT COUNT(pkey) FROM Schedule where student_id = :student_id")
    int getScheduleCount(String student_id);



    @Query("SELECT * FROM Schedule where student_id = :studentId and active = :active or active = :Active")
    List<Schedule> getActiveSchedules(String active,String Active, String studentId);


    @Query("SELECT * FROM Schedule where `endtime_null` >= :date and `starttime_null` <= :date and student_id = :studentId and active = :active or active = :Active ")
    List<Schedule> getSelEvents(String active,String Active, String studentId, String date);


    @Query("SELECT * FROM Schedule where `endtime` >= :date and `starttime` <= :date and student_id = :studentId and active = :active or active = :Active ")
    List<Schedule> getSelEventTime(String active,String Active, String studentId, String date);


    @Insert
    void insertAll(Schedule... schedules);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertOnConflict(Schedule... schedules);

    @Query("DELETE FROM Schedule")
    public abstract int DeleteToken();

    @Query("DELETE FROM Schedule where pkey = :p_key and student_id = :studentId")
    public abstract int DeleteSchedule(String p_key,String studentId );


    @Query("DELETE FROM Schedule where student_id= :studentId")
    public abstract int DeleteScheduleAllUser(String studentId);


}
