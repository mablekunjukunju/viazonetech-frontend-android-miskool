package com.zone.android.miskool_Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.zone.android.miskool.R;

import org.w3c.dom.Text;

import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by Inspiron on 16-05-2018.
 */

public class informationAdapter extends RecyclerView.Adapter<informationAdapter.ViewHolder>  {

    private List<com.zone.android.miskool_Entitiy.Attributes> attList;

    Context context;
    public informationAdapter(Context context,List<com.zone.android.miskool_Entitiy.Attributes> attList){

        this.context=context;
        this.attList=attList;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_inforow, parent, false);
        context=parent.getContext();

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.text_attribute.setText(attList.get(position).getAttName());
        holder.text_value.setText(attList.get(position).getSttValue());

    }

    @Override
    public int getItemCount() {
        return attList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_attribute,text_value;

        public ViewHolder(View itemView) {
            super(itemView);

            text_attribute=(TextView)itemView.findViewById(R.id.text_attribute);
            text_value=(TextView)itemView.findViewById(R.id.text_value);


        }
    }
}
