package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Speech;

import java.util.List;

@Dao
public interface speech_DAO {

    @Query("SELECT * FROM Speech")
    List<Speech> getAll();


    @Query("SELECT * FROM Speech where student_id= :student_id")
    List<Speech> getAllMessagesStudent(String student_id);

    @Query("SELECT COUNT(speech_id) FROM Speech where message_receiver= :student_id")
    int getMessageCunt(String student_id);

    @Query("SELECT * FROM Speech where student_id= :student_id " +
            "order by message_date asc")
    List<Speech> getInboxMessagesStudentThread(String student_id);


    @Query("DELETE FROM Speech where student_id= :studentId")
    public abstract int DeleteUserSender(String studentId);


    @Query("DELETE FROM Speech")
    public abstract int DeleteToken();

    @Insert
    void insertAll(Speech... speeches);
}
