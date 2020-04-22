package com.zone.android.miskool_Model;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Speech;
import com.zone.android.miskool_Presenter.replyPresInterface;
import com.zone.android.miskool_Presenter.speechPresInterface;

public interface speechModelInterface {

    void deleteTable(Context context,speechPresInterface speechPresInterface);

    void getMessages(String Student_Id, speechPresInterface speechPresInterface, Context context);

    void sentReply(Speech speech, Context context, speechPresInterface speechPresInterface);


}
