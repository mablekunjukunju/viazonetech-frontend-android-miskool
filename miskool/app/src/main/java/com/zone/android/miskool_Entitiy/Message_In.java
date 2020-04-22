package com.zone.android.miskool_Entitiy;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.sql.Date;


/**
 * Created by Inspiron on 04-01-2018.
 */
@Entity(indices = @Index("messagein_id"))
public class Message_In {


    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "messagein_id")
    private String messageInId;

    @ColumnInfo(name = "thread_id")
    private String threadId;

    @ColumnInfo(name = "student_id")
    private String studentId;

    @ColumnInfo(name = "message_dateOfArrival")
    private String messageDateOfArrival;

    @ColumnInfo(name = "message_sub")
    private String messageSub;

    @ColumnInfo(name = "messages")
    private String messages;

    @ColumnInfo(name = "message_type")
    private String messageTyp;

    @ColumnInfo(name = "message_dateCreated")
    private String messageDateOfCreated;

    @ColumnInfo(name = "message_sender")
    private String messageSender;

    @ColumnInfo(name = "message_receiver")
    private String messageReceiver;

    @ColumnInfo(name = "read_flag")
    private String readFlag;



    public String getMessageDateOfArrival() {
        return messageDateOfArrival;
    }

    public void setMessageDateOfArrival(String messageDateOfArrival) {
        this.messageDateOfArrival = messageDateOfArrival;
    }

    public String getMessageDateOfCreated() {
        return messageDateOfCreated;
    }

    public void setMessageDateOfCreated(String messageDateOfCreated) {
        this.messageDateOfCreated = messageDateOfCreated;
    }




    public String getReadFlag() {
        return readFlag;
    }

    public void setReadFlag(String readFlag) {
        this.readFlag = readFlag;
    }


    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }


    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }


    public String getMessageTyp() {
        return messageTyp;
    }

    public void setMessageTyp(String messageTyp) {
        this.messageTyp = messageTyp;
    }


   public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }


    public String getMessageReceiver() {
        return messageReceiver;
    }

    public void setMessageReceiver(String messageReceiver) {
        this.messageReceiver = messageReceiver;
    }

    /*@ColumnInfo(name = "message_cont")
    private String messageCont;*/

    public String getMessageInId() {
        return messageInId;
    }

    public void setMessageInId(String messageInId) {
        this.messageInId = messageInId;
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }


    public String getMessageSub() {
        return messageSub;
    }

    public void setMessageSub(String messageSub) {
        this.messageSub = messageSub;
    }


}
