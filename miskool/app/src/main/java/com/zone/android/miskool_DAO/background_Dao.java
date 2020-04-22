package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Attributes;
import com.zone.android.miskool_Entitiy.backGroundDB;

import java.util.List;

/**
 * Created by Inspiron on 30-06-2018.
 */

@Dao
public interface background_Dao {

    @Query("SELECT * FROM backGroundDB")
    List<backGroundDB> getServiceCount();

    @Insert
    void insertAll(backGroundDB... servics);


}
