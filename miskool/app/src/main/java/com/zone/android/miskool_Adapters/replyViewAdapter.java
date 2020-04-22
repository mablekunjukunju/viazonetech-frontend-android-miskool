package com.zone.android.miskool_Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Util.Constants;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Inspiron on 05-04-2018.
 */

public class replyViewAdapter extends RecyclerView.Adapter<replyViewAdapter.ViewHolder> {

    private List<Message_In> messageList;
    Context context;
    SharedPreferences studentPreference;

   public replyViewAdapter(List<Message_In> messageList,Context context){
       this.messageList=messageList;
       this.context=context;


   }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reply_row, parent, false);
        context=parent.getContext();


        return new replyViewAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        String prefstudentname=studentPreference.getString("studentname",messageList.get(position).getMessageReceiver());
        String messagesender=messageList.get(position).getMessageSender();

        if(prefstudentname.equals(messagesender)){

            holder.relativeTop.setVisibility(View.INVISIBLE);
            holder.relativesender.setVisibility(View.VISIBLE);
            holder.textimesender.setText(convertPrettyTime(messageList.get(position).getMessageDateOfCreated()));
            holder.textsend.setText(messageList.get(position).getMessages());
        }else{

            holder.relativesender.setVisibility(View.INVISIBLE);
            holder.relativeTop.setVisibility(View.VISIBLE);
            holder.tv_time.setText(convertPrettyTime(messageList.get(position).getMessageDateOfCreated()));
            holder.tv_content.setText(messageList.get(position).getMessages());

        }



    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_time,tv_content,tv_receiver,textimesender,textsend;
        RelativeLayout relativesender,relativeTop;
        public ViewHolder(View itemView) {
            super(itemView);

            studentPreference= context.getSharedPreferences(Constants.STUDENT_SELECTION_PREFERENCE, Context.MODE_PRIVATE);

            tv_time = (TextView)itemView.findViewById(R.id.tv_subject);
            tv_content=(TextView)itemView.findViewById(R.id.tv_content);

            textimesender = (TextView)itemView.findViewById(R.id.textimesender);
            textsend = (TextView)itemView.findViewById(R.id.textsend);

            relativesender = (RelativeLayout) itemView.findViewById(R.id.relativesender);
            relativeTop = (RelativeLayout) itemView.findViewById(R.id.relativeTop);
//

        }
    }


    public String convertPrettyTime(String date){

        // String dateString="2018-04-23 15:00:47";

        String datetime="";

        String dateString=date;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

        java.util.Date convertedDate = new java.util.Date();


        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        PrettyTime p  = new PrettyTime();

        datetime= p.format(convertedDate);


        return datetime;
    }
}
