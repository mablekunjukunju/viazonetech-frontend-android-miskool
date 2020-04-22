package com.zone.android.miskool_View;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;


import com.zone.android.miskool.R;
import com.zone.android.miskool_Adapters.alertListAdapter;
import com.zone.android.miskool_Adapters.replyViewAdapter;
import com.zone.android.miskool_Entitiy.Alerts;
import com.zone.android.miskool_Presenter.alertPresClass;
import com.zone.android.miskool_Presenter.alertPresInterface;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.RecyclerItemTouchHelper;
import com.zone.android.miskool_Util.eventModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Inspiron on 25-05-2018.
 */

public class alertViewClassNew extends AppCompatActivity implements alertViewInterface,RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private Toolbar toolbar;

    alertPresInterface alertPresInterface;
    SharedPreferences studentPreference;
    String StudenId;
    private List<Alerts> AlertList;

    CoordinatorLayout coordinator_layout;
    public static ProgressDialog progressBar;

    RecyclerView recyclerView;
   private alertListAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_alertsmain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Alerts");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        coordinator_layout=(CoordinatorLayout)findViewById(R.id.coordinator_layout);

        AlertList = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);


        alertPresInterface=new alertPresClass(this);

        studentPreference = getApplicationContext().getSharedPreferences(Constants.STUDENT_SELECTION_PREFERENCE, Context.MODE_PRIVATE);
        StudenId = studentPreference.getString("studentid", "");



    }

    @Override
    protected void onStart() {
        super.onStart();

        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Downloading alerts");
        progressBar.setIndeterminate(true);

        progressBar.show();

        String studentname=studentPreference.getString("studentname", "");
        alertPresInterface.deleteTablesMessage(studentname,this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                //   mDrawerLayout.openDrawer(GravityCompat.START);
                this.finish();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void deleteTableMessageResp(int errorcode) {

        if(errorcode==Constants.PASS_VALIDATION){

            String studentname=studentPreference.getString("studentname", "");
            alertPresInterface.getMessageServer(studentname,this);

        }
    }

    @Override
    public void setMessagesServer(int errorCode) {

        if(errorCode==Constants.PASS_SERVICE){

            String studentname=studentPreference.getString("studentname", "");
            alertPresInterface.getAlerts(studentname,this);

        }else if(errorCode==Constants.NO_MESSAGE){

            Snackbar snackbar = Snackbar
                    .make(coordinator_layout, "no new alerts", Snackbar.LENGTH_LONG);
            snackbar.show();

            if(progressBar.isShowing()) {
                progressBar.dismiss();
            }
        }
    }

    @Override
    public void setMessages(List<Alerts> alertList) {

     runOnUiThread(new Runnable() {
         @Override
         public void run() {

             AlertList=alertList;

             if(progressBar.isShowing()) {
                 progressBar.dismiss();
             }

             adapter = new alertListAdapter(getApplicationContext(),AlertList);
             recyclerView.setHasFixedSize(true);
             RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
             recyclerView.setLayoutManager(layoutManager);

             recyclerView.setAdapter(adapter);

           adapter.notifyDataSetChanged();
         }
     });


        }


    private static List<Date> getDates(String dateString1, String dateString2)
    {
        ArrayList<Date> dates = new ArrayList<Date>();
        DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");

        Date date1 = null;
        Date date2 = null;

        try {
            date1 = df1 .parse(dateString1);
            date2 = df1 .parse(dateString2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);


        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        while(!cal1.after(cal2))
        {
            dates.add(cal1.getTime());
            cal1.add(Calendar.DATE, 1);
        }
        return dates;
    }






    public void printDifference(Date startDate, Date endDate) {
        //milliseconds
        long different = endDate.getTime() - startDate.getTime();


        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        System.out.printf(
                "%d days, %d hours, %d minutes, %d seconds%n",
                elapsedDays, elapsedHours, elapsedMinutes, elapsedSeconds);
    }

    void prepareAlerts(){

    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof alertListAdapter.MyViewHolder) {
            // get the removed item name to display it in snack bar
            String name = AlertList.get(viewHolder.getAdapterPosition()).getAlertId();

            // backup of removed item for undo purpose
            final Alerts deletedItem = AlertList.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            // remove the item from recycler view

            adapter.removeItem(viewHolder.getAdapterPosition());

            // showing snack bar with Undo option
            Snackbar snackbar = Snackbar
                    .make(coordinator_layout, name + " removed from alerts!", Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // undo is selected, restore the deleted item
                    adapter.restoreItem(deletedItem, deletedIndex);
                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();
        }
    }
}