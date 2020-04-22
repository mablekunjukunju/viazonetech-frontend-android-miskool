package com.zone.android.miskool_Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Util.OnItemClickListener;
import com.zone.android.miskool_View.mailViewClass;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Inspiron on 13-01-2018.
 */


public class mailViewAdapter extends RecyclerView.Adapter<mailViewAdapter.ViewHolder>  {

    private List<Message_In> messageList;
    Context context;
    private final OnItemClickListener listener;


    public mailViewAdapter(List<Message_In> messageList, Context context,OnItemClickListener listener){

       this.messageList=messageList;
       this.context=context;
       this.listener=listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_recy_row, parent, false);
        context=parent.getContext();

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        //        viewHolder.tv_country.setText(countries.get(i));

        mailViewClass.progressBar.dismiss();
        holder.bind(messageList.get(position), listener);


        holder.tv_subject.setText(messageList.get(position).getMessageSub());
        holder.tv_time.setText(convertPrettyTime(messageList.get(position).getMessageDateOfCreated()));
        holder.tv_content.setText(messageList.get(position).getMessages());
        holder.tv_sender.setText("From: "+messageList.get(position).getMessageSender());

        String readflag=messageList.get(position).getReadFlag();

        if(readflag.equals(null)||readflag.equals("")||readflag.equals(" ")){

            holder.tv_subject.setTypeface(null, Typeface.BOLD);
            holder.tv_content.setTypeface(null, Typeface.BOLD);


        }else{

            holder.tv_subject.setTypeface(null, Typeface.NORMAL);
            holder.tv_content.setTypeface(null, Typeface.NORMAL);
        }


      /*  holder.relativeReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                *//*mailViewClass mailViewClass = new mailViewClass();

                Message_In message_in =new Message_In();

                message_in.setMessageInId(messageList.get(position).getMessageInId());
                message_in.setMessageSub(messageList.get(position).getMessageSub());
                message_in.setThreadId(messageList.get(position).getThreadId());
                message_in.setMessageSender(messageList.get(position).getMessageReceiver());*//*

               // mailViewClass.callBottomSheet(message_in,context);
                showreplyDialogue();

            }
        });
*/
    }

    @Override
    public int getItemCount() {
       // return messageList.size();
        return 6;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_subject, tv_time, tv_content,tv_sender;
        RelativeLayout relativeReply, relativeDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_subject = (TextView) itemView.findViewById(R.id.tv_subject);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_content = (TextView) itemView.findViewById(R.id.tv_content);
            tv_sender=(TextView)itemView.findViewById(R.id.textReceiver);



           /* relativeReply = (RelativeLayout) itemView.findViewById(R.id.relativeReply);
            relativeDelete = (RelativeLayout) itemView.findViewById(R.id.relativeDelete);
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


    void showreplyDialogue(){

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View content = inflater.inflate(R.layout.activity_customdialog, null);
        dialogBuilder.setView(content);

        final AlertDialog b = dialogBuilder.create();
        b.show();


        dialogBuilder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {

              Toast.makeText(context,"Message sent",Toast.LENGTH_LONG).show();

          }
       });

        dialogBuilder.setNegativeButton("", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                b.dismiss();

            }
        });


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

         datetime= p.format(convertedDate);


        //dd MMMM yyyy, hh:mm:ss.SSS a

       // String messagedatetime=(String)android.text.format.DateFormat.format("dd MM yyyy hh:mm:ss a", convertedDate);



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


    private String getMonthName(final int index, final Locale locale, final boolean shortName)
    {
        String format = "%tB";

        if (shortName)
            format = "%tb";

        Calendar calendar = Calendar.getInstance(locale);
        calendar.set(Calendar.MONTH, index);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        return String.format(locale, format, calendar);
    }
}
