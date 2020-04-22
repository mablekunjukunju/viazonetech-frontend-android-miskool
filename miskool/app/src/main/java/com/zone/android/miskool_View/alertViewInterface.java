package com.zone.android.miskool_View;

import com.zone.android.miskool_Entitiy.Alerts;
import com.zone.android.miskool_Entitiy.Message_In;

import java.util.List;

/**
 * Created by Inspiron on 04-03-2018.
 */

public interface alertViewInterface {

    void deleteTableMessageResp(int errorcode);
    void setMessagesServer(int errorCode);
    void setMessages(List<Alerts> alertList);

}
