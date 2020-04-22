package com.zone.android.miskool_Presenter;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Speech;

import java.util.List;

public interface speechPresInterface {

    void deleteTable(Context context);
    void getMessages(String Student_Id, Context context);
    void setMessages(List<Speech> speechList);

    void sentReply(Speech speech, Context context);

    void sentMessage(int error);
}
