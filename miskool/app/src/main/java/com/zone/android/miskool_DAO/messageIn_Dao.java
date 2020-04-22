package com.zone.android.miskool_DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Message_det;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Inspiron on 04-01-2018.
 */
@Dao
public interface  messageIn_Dao {
    @Query("SELECT * FROM Message_In")
    List<Message_In> getAll();


    @Query("SELECT * FROM Message_In where student_id= :student_id")
    List<Message_In> getAllMessagesStudent(String student_id);

    @Query("SELECT COUNT(messagein_id) FROM Message_In where message_receiver= :student_id")
     int getMessageCunt(String student_id);



       @Query("SELECT message_dateCreated,message_sub,thread_id ,messagein_id,messages,message_sender,message_receiver,read_flag FROM Message_In where message_receiver= :student_id " +
            "order by message_dateCreated desc")
    List<Message_In> getInboxMessagesStudent(String student_id);



    @Query("SELECT * FROM Message_In where thread_id= :messagein_id or messagein_id= :messagein_id " +
            "order by message_dateCreated asc")
    List<Message_In> getInboxMessagesStudentThread(String messagein_id);






    @Query("UPDATE Message_In SET read_flag = 1 where message_receiver= :student_id and thread_id= :Thread_id ")
    void updateFlag(String student_id, String Thread_id);

    @Query("UPDATE Message_In SET read_flag = :read_flag where message_receiver= :student_id and messagein_id= :messagein_id ")
    void updateReadFlag(String student_id, String messagein_id, String read_flag);


    @Query("SELECT message_dateCreated,message_sub,thread_id,messagein_id ,messages,message_sender,message_receiver FROM Message_In where message_sender= :student_id " +
            "order by message_dateCreated desc")
    List<Message_In> getOutMessagesStudent(String student_id);


    @Query("select count(messagein_id) from Message_In")
    int getMessageCount();

    @Query("select student_id from Message_In where 'rowid' = (select max('rowid') from Message_In)")
    String getlastStudentId();


    @Query("select messagein_id,student_id,message_dateCreated from Message_In group by student_id  order by message_dateCreated desc")
    List<Message_In> getlastStudentIdMessageIds();

    @Query("DELETE FROM Message_In")
    public abstract int DeleteToken();

    @Query("DELETE FROM Message_In where messagein_id= :messageId")
    public abstract int DeleteMessageId(String messageId);

    @Query("DELETE FROM Message_In where student_id= :studentId")
    public abstract int DeleteMessageAllUser(String studentId);

    @Query("DELETE FROM Message_In where message_receiver= :studentId")
    public abstract int DeleteUserReceive(String studentId);

    @Query("DELETE FROM Message_In where message_sender= :studentId")
    public abstract int DeleteUserSender(String studentId);


    @Insert
    void insertAll(Message_In... messages);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Message_In... messages);
}
