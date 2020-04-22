package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Account;
import com.zone.android.miskool_Entitiy.Person_det;

import java.util.List;

/**
 * Created by Inspiron on 06-03-2018.
 */

@Dao
public interface account_Dao {

    @Query("SELECT * FROM Account")
    List<Account> getAccountDetails();

    @Query("select count(person_id) from Account")
    int getAccountCount();

    @Query("select person_id from Account where 'rowid' = (select max('rowid') from Account)")
    String getAccountId();


    @Insert
    void insertAll(Account... accounts);

}
