package com.zone.android.miskool_View;

import com.zone.android.miskool_Entitiy.Speech;

import java.util.List;

public interface speechViewInterface {
    void setMessages(List<Speech> speechList);

    void sentMessage(int error);
}
