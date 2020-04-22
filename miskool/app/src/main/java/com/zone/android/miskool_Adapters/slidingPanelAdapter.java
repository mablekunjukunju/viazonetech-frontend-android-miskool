package com.zone.android.miskool_Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Entitiy.Message_InMessage_det;
import com.zone.android.miskool_Entitiy.Person_det;
import com.zone.android.miskool_Util.App;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.Methods;
import com.zone.android.miskool_View.mainViewClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Inspiron on 02-03-2018.
 */

public class slidingPanelAdapter extends RecyclerView.Adapter<slidingPanelAdapter.ViewHolder> {
    private List<Person_det> personList;

    private int selectedIndex = -1;
    private int selectedColor = Color.parseColor("#1b1b1b");
    public static RecyclerViewClickListener itemListener;


    Context context;

   SharedPreferences studentPreference;

    public slidingPanelAdapter(final List<Person_det> personList,Context context){

       this.personList=personList;
        this.itemListener = itemListener;
        context = this.context;

    }

    public void setSelectedIndex(int ind)
    {
        selectedIndex = ind;

    }

    @Override
    public slidingPanelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slidingrownew, parent, false);

        context=parent.getContext();
        return new slidingPanelAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final slidingPanelAdapter.ViewHolder holder, final int position) {

      // setSelectedIndex(holder.getAdapterPosition());
       // holder.imageStudent.setImageResource(R.mipmap.composeplus);
     // Log.e("studentid","studentPreference "+studentPreference.getString("studentid","nvalue"));

      //  Log.e("studentname","studentPreference "+studentPreference.getString("studentname","nvalue"));


        ////////////////////////////////////////////////////////////////////////

        Person_det person_det=new Person_det();
        person_det=personList.get(0);

        String prefstudentId=studentPreference.getString("studentid",person_det.getStudentId());
        String prefstudentname=studentPreference.getString("studentname",person_det.getFirstName());


        holder.tvName.setText(personList.get(position).getFirstName());



        if(selectedIndex==position) {

            holder.tvName.setTextColor(Color.parseColor("#404040"));

/*

*/

            holder.lay1.setBackgroundResource(R.drawable.round_layoutblue);


////imp lines

             String name=personList.get(position).getFirstName();
             String id=personList.get(position).getStudentId();

             mainViewClass.txtNme.setText(personList.get(position).getFirstName());

            SharedPreferences.Editor editorstudent = studentPreference.edit();
            editorstudent.putString("studentid", id);
            editorstudent.putString("studentname",name);
            editorstudent.commit();

        }

        else {


            holder.tvName.setTextColor(Color.parseColor("#8e8d8d"));


             holder.lay1.setBackgroundResource(R.drawable.round_layoutgreylight);



            String prefstudentId1=studentPreference.getString("studentid",person_det.getStudentId());
            String prefstudentname1=studentPreference.getString("studentname",person_det.getFirstName());



            String student_id=personList.get(position).getStudentId();


            if(selectedIndex==-1){

                if(prefstudentId1.equals(student_id)) {


                    holder.tvName.setTextColor(Color.parseColor("#404040"));

                    holder.lay1.setBackgroundResource(R.drawable.round_layoutblue);


                }
            }

          }



        holder.lay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedIndex=position;
                notifyDataSetChanged();

                //holder.tvName.setTextColor(Color.parseColor("#ffffff"));
            }
        });




    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SparseBooleanArray selectedItems = new SparseBooleanArray();

        ImageView imageStudent;TextView tvName;
        RelativeLayout lay1;
        public ViewHolder(View itemView) {
            super(itemView);

            imageStudent=(ImageView)itemView.findViewById(R.id.studentImage);
            tvName=(TextView)itemView.findViewById(R.id.txtname);
            lay1=(RelativeLayout)itemView.findViewById(R.id.lay1);

            try {
                  studentPreference= context.getSharedPreferences(Constants.STUDENT_SELECTION_PREFERENCE, Context.MODE_PRIVATE);

            }catch (Exception e){
                e.printStackTrace();
            }



        }


    }
}
