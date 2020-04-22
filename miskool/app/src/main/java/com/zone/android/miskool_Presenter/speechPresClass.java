package com.zone.android.miskool_Presenter;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Speech;
import com.zone.android.miskool_Model.speechModelClass;
import com.zone.android.miskool_Model.speechModelInterface;
import com.zone.android.miskool_View.speechViewClass;
import com.zone.android.miskool_View.speechViewInterface;

import java.util.List;

public class speechPresClass implements  speechPresInterface {

    speechViewInterface speechViewInterface;
    speechModelInterface speechModelInterface;

    public speechPresClass(speechViewInterface speechViewInterface){
        this.speechViewInterface=speechViewInterface;
        this.speechModelInterface= new speechModelClass();
    }

    @Override
    public void deleteTable(Context context) {
        speechModelInterface.deleteTable(context,this);
    }

    @Override
    public void getMessages(String Student_Id, Context context) {
speechModelInterface.getMessages(Student_Id,this,context);
    }

    @Override
    public void setMessages(List<Speech> speechList) {
        speechViewInterface.setMessages(speechList);

    }

    @Override
    public void sentReply(Speech speech, Context context) {

        speechModelInterface.sentReply(speech,context,this);
    }

    @Override
    public void sentMessage(int error) {
        speechViewInterface.sentMessage(error);
    }
}
