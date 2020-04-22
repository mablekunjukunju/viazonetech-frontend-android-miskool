package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Alerts;
import com.zone.android.miskool_Entitiy.Contacts;

import java.util.List;

@Dao
public interface contacts_Dao {

    @Query("SELECT * FROM Contacts")
    List<Contacts> getAlerts();

    @Query("DELETE FROM Contacts")
    public abstract int DeleteToken();

    @Insert
    void insertAll(Contacts... contacts);


}
