package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Alerts;
import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Person_det;

import java.util.List;

/**
 * Created by Inspiron on 01-03-2018.
 */
@Dao
public interface alert_Dao {
    @Query("SELECT * FROM Alerts")
    List<Alerts> getAlerts();

    @Query("SELECT * FROM Alerts where student_id= :StudentID order by alert_date desc")
    List<Alerts> getAlertsStudent(String StudentID);

    @Query("SELECT COUNT(alert_id) FROM Alerts where student_id= :student_id")
    int getAlertCount(String student_id);


 @Query("select count(alert_id) from Alerts")
    int getAlertCount();

    @Query("select student_id,alert_id,alert_date from Alerts group by student_id  order by alert_date desc")
    List<Alerts> getlastStudentIdAlertIds();

    @Query("DELETE FROM Alerts")
    public abstract int DeleteToken();

    @Query("DELETE FROM Alerts where student_id= :studentId")
    public abstract int DeleteUserAlerts(String studentId);




    @Insert
    void insertAll(Alerts... alerts);
}
