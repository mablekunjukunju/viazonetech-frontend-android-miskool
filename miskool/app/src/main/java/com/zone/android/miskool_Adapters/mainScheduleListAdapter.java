package com.zone.android.miskool_Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Entitiy.Alerts;
import com.zone.android.miskool_Entitiy.Schedule;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class mainScheduleListAdapter extends RecyclerView.Adapter<mainScheduleListAdapter.MyViewHolder>  {


    private Context context;
    private List<Schedule> scheduleList;


    public mainScheduleListAdapter(Context context, List<Schedule> scheduleList) {
        this.context = context;
        this.scheduleList = scheduleList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_list_schedule, parent, false);
        context=parent.getContext();

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Schedule schedule=scheduleList.get(position);
        holder.sub.setText(schedule.getSubject());
        holder.startTime.setText(formatDate(schedule.getStarttime().toString()));
        holder.endTime.setText(formatDate(schedule.getEndtime().toString()));



    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
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


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView sub, startTime,endTime;
        public final View mView;

        public MyViewHolder(View view) {
            super(view);
            mView = view;

            sub = (TextView) view.findViewById(R.id.textSub);
            startTime = (TextView) view.findViewById(R.id.startTime);
            endTime = (TextView) view.findViewById(R.id.endTime);



        }
    }
}
