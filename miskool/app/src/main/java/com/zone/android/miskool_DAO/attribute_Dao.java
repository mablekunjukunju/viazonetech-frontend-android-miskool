package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Alerts;
import com.zone.android.miskool_Entitiy.Attributes;
import com.zone.android.miskool_Entitiy.Person_det;

import java.util.List;

/**
 * Created by Inspiron on 17-05-2018.
 */
@Dao
public interface attribute_Dao {
    @Query("SELECT * FROM Attributes")
    List<Attributes> getAtts();

    @Query("SELECT * FROM Attributes where studentId= :StudentID")
    List<Attributes> getAttsStudent(String StudentID);

    //delete from sqlite_sequence where name='your_table';

    @Query("DELETE FROM Attributes")
    public abstract void DeleteSequence();


    @Insert
    void insertAll(Attributes... atts);

}
