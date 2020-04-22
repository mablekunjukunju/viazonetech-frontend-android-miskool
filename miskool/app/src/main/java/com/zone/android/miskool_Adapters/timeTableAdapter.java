package com.zone.android.miskool_Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.timetable;
import com.zone.android.miskool_Util.OnItemClickListener;

import java.util.List;

/**
 * Created by Inspiron on 21-09-2018.
 */

public class timeTableAdapter extends RecyclerView.Adapter<timeTableAdapter.ViewHolder> {

    private List<timetable> timeList;
    Context context;

    public timeTableAdapter(List<timetable> timeList, Context context){

        this.timeList=timeList;
        this.context=context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.timetablerow, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_time.setText(timeList.get(position).getTime());
        holder.tv_sub.setText(timeList.get(position).getSub());
    }

    @Override
    public int getItemCount() {
        return timeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_time,tv_sub;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_time=(TextView)itemView.findViewById(R.id.textTime);
            tv_sub=(TextView)itemView.findViewById(R.id.textSub);


        }
    }
}
