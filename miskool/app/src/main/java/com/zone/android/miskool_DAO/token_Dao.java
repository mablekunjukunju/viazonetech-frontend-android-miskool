package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Token_det;

import java.util.List;

/**
 * Created by Inspiron on 07-02-2018.
 */

@Dao
public interface token_Dao {

    @Query("SELECT * FROM Token_det")
    List<Token_det> getAll();


    @Query("SELECT * FROM Token_det")
    List<Token_det> getTokenDetails();


    @Query("UPDATE Token_det SET token_inc= :inc")
    public abstract int incrementToken( int inc);

    @Query("DELETE FROM Token_det")
    public abstract int DeleteToken();

    @Delete
    public void deleteUsers(Token_det... token_dets);

   /* @Query("select token_id  from Token_det where 'rowid'=(select max('rowid') from Token_det)")
    public abstract String getLastTokeId();

*/
    @Insert
    void insertAll(Token_det... token_dets);


}
