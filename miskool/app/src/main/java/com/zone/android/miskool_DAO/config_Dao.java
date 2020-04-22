package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Config_det;
import com.zone.android.miskool_Entitiy.Message_In;

import java.util.List;

/**
 * Created by Inspiron on 01-02-2018.
 */
@Dao
public interface config_Dao {

    @Query("SELECT * FROM Config_det")
    List<Config_det> getAll();


    @Query("SELECT * FROM Config_det")
    List<Config_det> getAllMessages();

    @Insert
    void insertAll(Config_det... services);
}
