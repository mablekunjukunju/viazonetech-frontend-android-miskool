package com.zone.android.miskool_Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Entitiy.Schedule;
import com.zone.android.miskool_Util.OnItemClickListener;
import com.zone.android.miskool_Util.scheduleOnclickListner;
import com.zone.android.miskool_View.scheduleViewClass;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Inspiron on 21-10-2018.
 */

public class scheduleAdapter extends RecyclerView.Adapter<scheduleAdapter.ViewHolder> {


    private List<Schedule> scheduleList;
    Context context;
    private final scheduleOnclickListner listener;


    public scheduleAdapter(List<Schedule> scheduleList, Context context,scheduleOnclickListner listener){

        this.scheduleList=scheduleList;
        this.context=context;
        this.listener=listener;

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_schedule_row, parent, false);
        context=parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat format2 = new SimpleDateFormat("MMM-dd");
        String finlDate="";


        try {
            Date date1 = format1.parse(scheduleViewClass.lastSelDate);

            finlDate=format2.format(date1);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        String[] items1 = finlDate.split("-");


        Schedule schedule = scheduleList.get(position);
        holder.bind(scheduleList.get(position), listener);



       SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
       String curreDate=formatter.format(new Date());

        Date date1=null;String lastdt="";

        try {
            date1 = formatter.parse(scheduleViewClass.lastSelDate);
             lastdt=formatter.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }


       if (curreDate.contains(lastdt)){
            holder.relativeTwo.setBackgroundResource(R.drawable.schedule_click);
        }else {

           holder.relativeTwo.setBackgroundResource(R.drawable.scheduleoffclick);
       }

        if(position!=0){
            holder.relativeOne.setVisibility(View.GONE);
        }else{
            holder.relativeOne.setVisibility(View.VISIBLE);
        }

        holder.textMonthName.setText(items1[1].toString());
        holder.textMonthDate.setText(items1[0].toString());

        holder.remText.setText(schedule.getSubject());
        holder.textStarTime.setText(formatDate(schedule.getStarttime()));
        holder.textEndTime.setText(formatDate(schedule.getEndtime()));


    }


    String formatDate(String dateString){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM hh:mm aa");
        String finlDate="";

        try {
            Date date1 = format1.parse(dateString);

            finlDate=format2.format(date1);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return finlDate;
    }


    String formatDateMonth(String dateString){

        ArrayList<String> dates= new ArrayList<>();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat format2 = new SimpleDateFormat("MMM-dd");
        String finlDate="";


        try {
            Date date1 = format1.parse(dateString);

            finlDate=format2.format(date1);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        String[] items1 = finlDate.split("/");

        return finlDate;
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout relativeOne,relativeTwo;
        TextView remText,textStarTime,textEndTime,textMonthName,textMonthDate;

        public ViewHolder(View itemView) {
            super(itemView);
            remText=(TextView)itemView.findViewById(R.id.remText);
            textStarTime=(TextView)itemView.findViewById(R.id.textStarTime);
            textEndTime=(TextView)itemView.findViewById(R.id.textEndTime);

            textMonthName=(TextView)itemView.findViewById(R.id.textMonthName);
            textMonthDate=(TextView)itemView.findViewById(R.id.textMonthDate);


            relativeOne=(RelativeLayout)itemView.findViewById(R.id.relativeOne);
            relativeTwo=(RelativeLayout)itemView.findViewById(R.id.relativeTwo);






        }

        public void bind(final Schedule schedule, final scheduleOnclickListner listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClickSchedule(schedule);
                }
            });
        }

    }



}
