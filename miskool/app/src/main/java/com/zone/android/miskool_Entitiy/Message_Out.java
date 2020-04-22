package com.zone.android.miskool_Entitiy;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Inspiron on 03-03-2018.
 */

/*
@Entity(foreignKeys = @ForeignKey(entity = Message_In.class,parentColumns = "messagein_id",
        childColumns = "messagein_id"))
*/
@Entity
  public class Message_Out {

    public String getOutId() {
        return outId;
    }

    public void setOutId(String outId) {
        this.outId = outId;
    }

    @PrimaryKey()
    @NonNull
    @ColumnInfo(name = "out_id")
    private String outId;

    @ColumnInfo(name = "student_id")
    private String studentId;

    @ColumnInfo(name = "message_date")
    private String messageDate;

    @ColumnInfo(name = "message_sub")
    private String messageSub;


    @ColumnInfo(name = "message_content")
    private String messageContent;


    @ColumnInfo(name = "message_receiver")
    private String messageReceiver;




    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public String getMessageSub() {
        return messageSub;
    }

    public void setMessageSub(String messageSub) {
        this.messageSub = messageSub;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageReceiver() {
        return messageReceiver;
    }

    public void setMessageReceiver(String messageReceiver) {
        this.messageReceiver = messageReceiver;
    }




}
