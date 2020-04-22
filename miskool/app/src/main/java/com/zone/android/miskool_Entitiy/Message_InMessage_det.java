package com.zone.android.miskool_Entitiy;

/**
 * Created by Inspiron on 19-01-2018.
 */

public class Message_InMessage_det {
 String message_id;

    public String getMessage_sender() {
        return message_sender;
    }

    public void setMessage_sender(String message_sender) {
        this.message_sender = message_sender;
    }

    public String getMessage_receiver() {
        return message_receiver;
    }

    public void setMessage_receiver(String message_receiver) {
        this.message_receiver = message_receiver;
    }

    String message_sender;
 String message_receiver;


    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    String messages;

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getMessage_sub() {
        return message_sub;
    }

    public void setMessage_sub(String message_sub) {
        this.message_sub = message_sub;
    }

    public String getInstance_id() {
        return instance_id;
    }

    public void setInstance_id(String instance_id) {
        this.instance_id = instance_id;
    }

    public String getMessage_type() {
        return message_type;
    }

    public void setMessage_type(String message_type) {
        this.message_type = message_type;
    }

    public String getMessage_timeRecent() {
        return message_timeRecent;
    }

    public void setMessage_timeRecent(String message_timeRecent) {
        this.message_timeRecent = message_timeRecent;
    }

    String message_sub;
 String instance_id;
 String message_type;
 String message_timeRecent;

}
