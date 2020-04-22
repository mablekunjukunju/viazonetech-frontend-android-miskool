package com.zone.android.miskool_Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Message_InMessage_det;

import java.util.List;

/**
 * Created by Inspiron on 19-01-2018.
 */

public class messageViewAdapter extends RecyclerView.Adapter<messageViewAdapter.ViewHolder> {
    private List<Message_InMessage_det> messageList;


    public messageViewAdapter(List<Message_InMessage_det> messageList){

        this.messageList=messageList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_row_list, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

     //   holder.tv_subject.setText(messageList.get(position).getMessage_sub());
        holder.tv_time.setText(messageList.get(position).getMessage_timeRecent());
        holder.tv_content.setText(messageList.get(position).getMessages());
        holder.tv_sender.setText(messageList.get(position).getMessage_sender());
      // holder.tv_receiver.setText(messageList.get(position).getMessage_receiver());

       holder.tv_receiver.setText(messageList.get(position).getMessage_receiver());


    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_subject,tv_time,tv_content,tv_sender,tv_receiver;

        public ViewHolder(View itemView) {
            super(itemView);
       //     tv_subject = (TextView)itemView.findViewById(R.id.tv_subject);
            tv_time = (TextView)itemView.findViewById(R.id.tv_time);
            tv_content = (TextView)itemView.findViewById(R.id.tv_content);
            tv_sender = (TextView)itemView.findViewById(R.id.tv_sender);
            tv_receiver = (TextView)itemView.findViewById(R.id.tv_receiver);




        }
    }
}
