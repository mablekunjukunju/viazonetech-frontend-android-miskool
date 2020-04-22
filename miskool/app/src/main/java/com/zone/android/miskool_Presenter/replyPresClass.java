package com.zone.android.miskool_Presenter;

import android.content.Context;

import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Message_Out;
import com.zone.android.miskool_Model.replyModelInterface;
import com.zone.android.miskool_Model.replyModelclass;
import com.zone.android.miskool_View.replyViewInterface;

import java.util.List;

/**
 * Created by Inspiron on 04-04-2018.
 */

public class replyPresClass implements replyPresInterface {
    replyViewInterface replyViewInterface;
    replyModelInterface replyModelInterface;

   public replyPresClass(replyViewInterface replyViewInterface ){
       this.replyViewInterface=replyViewInterface;
       this.replyModelInterface=new replyModelclass();
   }

    @Override
    public void getMessageServer(String Student_Id, Context context) {

    }

    @Override
    public void getMessages(String Student_Id, String thread_id, Context context) {

       replyModelInterface.getMessages(Student_Id,thread_id,this,context);
    }

    
    @Override
    public void setMessages(List<Message_In> messageList) {

       replyViewInterface.setMessages(messageList);
    }

    @Override
    public void sentReply(Message_In message_in, Context context) {

        replyModelInterface.sentReply(message_in,context,this);

    }

    @Override
    public void showMessage(int errorCode) {

        replyViewInterface.showMessage(errorCode);

    }
}
