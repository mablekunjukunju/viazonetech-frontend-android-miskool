package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Message_InMessage_det;

import java.util.List;

/**
 * Created by Inspiron on 19-01-2018.
 */
@Dao
public interface Message_InMessage_det_Dao {


@Query("select * from Message_In")List<Message_In> fetchMessageDetails();
}
