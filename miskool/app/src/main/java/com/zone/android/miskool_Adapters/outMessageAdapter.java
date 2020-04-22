package com.zone.android.miskool_Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Util.Methods;
import com.zone.android.miskool_Util.OnItemClickListener;
import com.zone.android.miskool_View.ouMailViewClass;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Inspiron on 26-03-2018.
 */

public class outMessageAdapter extends RecyclerView.Adapter<outMessageAdapter.ViewHolder>  {


    private List<Message_In> messageList;
    Context context;
    private  OnItemClickListener listener;


    public outMessageAdapter(List<Message_In> messageList, Context context,OnItemClickListener listener){

        this.messageList=messageList;
        this.context=context;
        this.listener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listout_recy_row, parent, false);
        context=parent.getContext();

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bind(messageList.get(position), listener);
        ouMailViewClass.progressBar.dismiss();

        holder.tv_subject.setText(messageList.get(position).getMessageSub());
        holder.tv_time.setText(convertPrettyTime(messageList.get(position).getMessageDateOfCreated()));
        holder.tv_content.setText(messageList.get(position).getMessages());
        holder.tv_receiver.setText(messageList.get(position).getMessageReceiver());



        final String messageId=(messageList.get(position).getMessageInId());

       /* holder.relativeDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);

                final AlertDialog b = builder.create();
                b.show();

                builder.setTitle("Delete Message");
                builder.setMessage(messageList.get(position).getMessages());
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Methods.deleteMessage(messageId,context);
                    }
                });


                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        b.dismiss();
                    }
                });
                builder.show();
            }
        });*/
    }



    @Override
    public int getItemCount() {
        return  messageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_subject,tv_time,tv_content,tv_receiver;
        RelativeLayout relativeReply,relativeDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_subject = (TextView)itemView.findViewById(R.id.tv_subject);
            tv_time = (TextView)itemView.findViewById(R.id.tv_time);
            tv_content = (TextView)itemView.findViewById(R.id.tv_content);
            tv_receiver = (TextView)itemView.findViewById(R.id.tv_receiver);

           /* relativeReply =(RelativeLayout)itemView.findViewById(R.id.relativeReply);
            relativeDelete=(RelativeLayout)itemView.findViewById(R.id.relativeDelete);
*/

        }


        public void bind(final Message_In item, final OnItemClickListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }

    public String convertPrettyTime(String date){

        // String dateString="2018-04-23 15:00:47";

        String datetime="";

        String dateString=date;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        java.util.Date convertedDate = new java.util.Date();


        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        PrettyTime p  = new PrettyTime();



        String messagedatetime=(String)android.text.format.DateFormat.format("dd MM yyyy", convertedDate);

        String currentmessagedatetime=(String)android.text.format.DateFormat.format("dd MM yyyy", new Date());

        if(messagedatetime.equals(currentmessagedatetime)){
            datetime= (String)android.text.format.DateFormat.format("hh:mm a", convertedDate);
        }else{

            datetime= (String)android.text.format.DateFormat.format("MMM dd", convertedDate);

        }

        String testdate=(String)android.text.format.DateFormat.format("MMM dd", convertedDate);


        return datetime;
    }
}
