package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Message_det;

import java.util.List;

/**
 * Created by Inspiron on 15-01-2018.
 */

@Dao
public interface messagedet_Dao {

    @Query("SELECT * FROM Message_det")
    List<Message_det> getAll();


    @Query("SELECT * FROM Message_det")
    List<Message_det> getAllMessages();



    @Insert
    void insertAll(Message_det... messages);
}
