package com.zone.android.miskool_Entitiy;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Inspiron on 01-03-2018.
 */
@Entity
public class Message {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "messageIn_id")
    private String messageInId;

    @ColumnInfo(name = "thread_id")
    private String threadId;

    @ColumnInfo(name = "student_id")
    private String studentId;

    @ColumnInfo(name = "message_created")
    private String messageCreated;

    @ColumnInfo(name = "message_type")
    private String messageType;

    public String getMessageInId() {
        return messageInId;
    }

    public void setMessageInId(String messageInId) {
        this.messageInId = messageInId;
    }

    @ColumnInfo(name = "messages")
    private String messages;

    @ColumnInfo(name = "message_sub")
    private String messageSub;

    @ColumnInfo(name = "message_timeRecent")
    private String messageTimRec;

    @ColumnInfo(name = "message_sender")
    private String messageSender;

    @ColumnInfo(name = "message_receiver")
    private String messageReceiver;


    public String getMessageId() {
        return messageInId;
    }

    public void setMessageId(String messageId) {
        this.messageInId = messageId;
    }

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

    public String getMessageCreated() {
        return messageCreated;
    }

    public void setMessageCreated(String messageCreated) {
        this.messageCreated = messageCreated;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getMessageSub() {
        return messageSub;
    }

    public void setMessageSub(String messageSub) {
        this.messageSub = messageSub;
    }

    public String getMessageTimRec() {
        return messageTimRec;
    }

    public void setMessageTimRec(String messageTimRec) {
        this.messageTimRec = messageTimRec;
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


}
