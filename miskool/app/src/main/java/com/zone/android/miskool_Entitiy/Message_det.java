package com.zone.android.miskool_Entitiy;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Inspiron on 15-01-2018.
 */

@Entity(foreignKeys = @ForeignKey(entity = Message_In.class,
        parentColumns = "messagein_id",
        childColumns = "msg_id"),indices = {@Index("instance_id"),@Index("msg_id")})
public class Message_det {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "instance_id")
    private String instanceId;

    @ColumnInfo(name = "msg_id")
    private String msgId;

    @ColumnInfo(name = "messages")
    private String messages;

    @ColumnInfo(name = "message_type")
    private String messageTyp;

    @ColumnInfo(name = "message_timeRecent")
    private String messageTimRec;

    @ColumnInfo(name = "message_sender")
    private String messageSender;

    @ColumnInfo(name = "message_receiver")
    private String messageReceiver;



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



    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessageTyp(String messageTyp) {
        this.messageTyp = messageTyp;
    }

    public String getMessageTyp() {
        return messageTyp;
    }

    public void setMessageTimRec(String messageTimRec) {
        this.messageTimRec = messageTimRec;
    }

    public String getMessageTimRec() {
        return messageTimRec;
    }
}
