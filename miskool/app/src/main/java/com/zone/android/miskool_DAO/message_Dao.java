package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Message;
import com.zone.android.miskool_Entitiy.Message_det;

import java.util.List;

/**
 * Created by Inspiron on 01-03-2018.
 */

@Dao
public interface message_Dao {

    @Query("SELECT * FROM Message")
    List<Message> getAll();


    @Query("SELECT * FROM Message")
    List<Message> getAllMessages();

    @Insert
    void insertAll(Message... messages);
}
