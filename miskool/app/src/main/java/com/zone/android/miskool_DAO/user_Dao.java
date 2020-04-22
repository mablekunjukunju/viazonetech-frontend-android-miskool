package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.User;

import java.util.List;

/**
 * Created by Inspiron on 04-01-2018.
 */

@Dao
public interface user_Dao {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Insert
    void insertAll(User... users);
}
