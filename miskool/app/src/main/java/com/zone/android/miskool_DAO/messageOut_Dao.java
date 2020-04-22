package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Message;
import com.zone.android.miskool_Entitiy.Message_Out;

import java.util.List;

/**
 * Created by Inspiron on 03-03-2018.
 */

@Dao
public interface messageOut_Dao {

    @Query("SELECT * FROM Message_Out")
    List<Message_Out> getAll();


    @Query("SELECT * FROM Message_Out")
    List<Message_Out> getAllMessages();

    @Query("SELECT MAX(student_id) FROM Message_Out")
      int getMaxMessageOutID();

    @Insert
    void insertAll(Message_Out... messages);
}
