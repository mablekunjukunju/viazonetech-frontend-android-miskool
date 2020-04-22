package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Person_det;
import com.zone.android.miskool_Entitiy.Token_det;

import java.util.List;

/**
 * Created by Inspiron on 01-03-2018.
 */
@Dao
public interface person_Dao {

    @Query("SELECT * FROM Person_det")
    List<Person_det> getPersonDetails();

    @Query("SELECT * FROM Person_det where student_id= :StudentID")
    List<Person_det> getPersonDetailsStudent(String StudentID);


    @Query("select count(student_id) from Person_det")
    int getStudentCount();

    @Query("select student_id from Person_det where 'rowid' = (select max('rowid') from Person_det)")
    String getlastStudentId();

    @Query("SELECT * FROM Person_det  LIMIT 1")
    List<Person_det> getDefaultStudent();

    @Query("DELETE FROM Person_det")
    public abstract int DeleteToken();

    @Insert
    void insertAll(Person_det... person_dets);
}
