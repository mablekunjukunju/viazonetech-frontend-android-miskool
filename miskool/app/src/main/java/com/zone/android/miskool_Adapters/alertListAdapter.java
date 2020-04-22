package com.zone.android.miskool_Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Entitiy.Alerts;
import com.zone.android.miskool_Util.Methods;

import java.util.List;

/**
 * Created by Inspiron on 26-05-2018.
 */

public class alertListAdapter extends RecyclerView.Adapter<alertListAdapter.MyViewHolder> {

    private Context context;
    private List<Alerts> alertList;


    public alertListAdapter(Context context, List<Alerts> alertList) {
        this.context = context;
        this.alertList = alertList;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.alert_list_itemswipe, parent, false);
        context=parent.getContext();

        return new MyViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Alerts alert = alertList.get(position);

        Log.e("alert.getAlertDate ","alert.getAlert "+alert.getAlertDate());

        holder.sub.setText(alert.getAlertSub());
        holder.time.setText(Methods.convertPrettyTime(alert.getAlertEndDate()));
       // final String message=alert.getAlertMsg();

      //  holder.alertContent.setText(message);



      /*  holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLocationDialog(alert.getAlertSub(),message,context);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return alertList.size();
    }


    public void removeItem(int position) {
        alertList.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreItem(Alerts item, int position) {
        alertList.add(position, item);
        // notify item added by position
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
       public RelativeLayout view_foreground,view_background;
        public TextView sub, time,alertContent;
        public final View mView;

        public MyViewHolder(View view) {
            super(view);
            mView = view;
            view_foreground =(RelativeLayout)view.findViewById(R.id.view_foreground);
            view_background=(RelativeLayout)view.findViewById(R.id.view_background);

            sub = (TextView) view.findViewById(R.id.textSub);
            time = (TextView) view.findViewById(R.id.alertTime);
         //   alertContent=(TextView)view.findViewById(R.id.alertContent);

        }
    }

    private void showLocationDialog( String title, String dialog_message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(dialog_message);

        String positiveText = "Ok";
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
    }


}