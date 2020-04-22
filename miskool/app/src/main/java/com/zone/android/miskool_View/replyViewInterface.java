package com.zone.android.miskool_View;

import com.zone.android.miskool_Entitiy.Message_In;

import java.util.List;

/**
 * Created by Inspiron on 04-04-2018.
 */

public interface replyViewInterface {
    void setMessages(List<Message_In> messageList);
    void showMessage(int error);

}
