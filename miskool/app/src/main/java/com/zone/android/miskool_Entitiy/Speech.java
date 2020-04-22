package com.zone.android.miskool_Entitiy;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Speech {

    @NonNull
    public int getSpeechId() {
        return speechId;
    }

    public void setSpeechId(@NonNull int speechId) {
        this.speechId = speechId;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull

    @ColumnInfo(name = "speech_id")
    private int speechId;

    @ColumnInfo(name = "thread_id")
    private String threadId;

    @ColumnInfo(name = "student_id")
    private String studentId;



    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }



    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
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

    @ColumnInfo(name = "message_date")
    private String messageDate;


    @ColumnInfo(name = "messages")
    private String messages;


    @ColumnInfo(name = "message_sender")
    private String messageSender;

    @ColumnInfo(name = "message_receiver")
    private String messageReceiver;

}
