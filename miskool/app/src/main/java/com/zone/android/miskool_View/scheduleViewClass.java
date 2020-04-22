package com.zone.android.miskool_View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;
import com.zone.android.miskool.R;
import com.zone.android.miskool_Adapters.scheduleAdapter;
import com.zone.android.miskool_Entitiy.Schedule;
import com.zone.android.miskool_Presenter.schedulePresClass;
import com.zone.android.miskool_Presenter.schedulePresInterface;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.Methods;
import com.zone.android.miskool_Util.scheduleOnclickListner;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by Inspiron on 13-10-2018.
 */

public class scheduleViewClass extends AppCompatActivity implements scheduleViewInterface  {

    private static final String LOG_TAG = scheduleViewClass.class.getSimpleName();

    Toolbar mToolbar;
    schedulePresInterface schedulePresInterface;
    CoordinatorLayout coordinatorlayout;

    SharedPreferences studentPreference;
    public static ProgressDialog progressBar;

    boolean isNew=false;

    public static String lastSelDate;
    private boolean undo = false;
    TextView textView;
    SimpleDateFormat formatter;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;

    FloatingActionButton fab;
    private int mYear, mMonth, mDay, mHour, mMinute,mSecond;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedules);


        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

      /*  actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.homenew);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Inbox");*/

        getSupportActionBar().setTitle("Schedules");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        studentPreference = getApplicationContext().getSharedPreferences(Constants.STUDENT_SELECTION_PREFERENCE, Context.MODE_PRIVATE);

        schedulePresInterface=new schedulePresClass(this);
        coordinatorlayout=(CoordinatorLayout)findViewById(R.id.coordinatorlayout);

        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        lastSelDate=formatter.format(new Date());

        fab=(FloatingActionButton)findViewById(R.id.fab);

        // Setup caldroid fragment
        // **** If you want normal CaldroidFragment, use below line ****
        caldroidFragment = new CaldroidFragment();

        Methods.copyFile(getApplicationContext());

        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState,
                    "CALDROID_SAVED_STATE");
        }
        // If activity is created from fresh
        else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);

            // Uncomment this to customize startDayOfWeek
            // args.putInt(CaldroidFragment.START_DAY_OF_WEEK,
            // CaldroidFragment.TUESDAY); // Tuesday

            // Uncomment this line to use Caldroid in compact mode
            // args.putBoolean(CaldroidFragment.SQUARE_TEXT_VIEW_CELL, false);

            // Uncomment this line to use dark theme
//          args.putInt(CaldroidFragment.THEME_RESOURCE, com.caldroid.R.style.CaldroidDefaultDark);


            caldroidFragment.setArguments(args);

        }

      //  setCustomResourceForDates();

        // Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();

        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {
             //   Toast.makeText(getApplicationContext(), formatter.format(date),
                       // Toast.LENGTH_SHORT).show();

                String seldate=formatter.format(date);
                lastSelDate=seldate;

               String studentname= studentPreference.getString("studentname", "");

                progressBar = new ProgressDialog(getApplicationContext());
                progressBar.setCancelable(true);//you can cancel it by pressing back button
                progressBar.setMessage("Updating schedules");
                progressBar.setIndeterminate(true);


                schedulePresInterface.getSelEvents(studentname,seldate,getApplicationContext());
            }

            @Override
            public void onChangeMonth(int month, int year) {
                String text = "month: " + month + " year: " + year;
               // Toast.makeText(getApplicationContext(), text,
                       // Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClickDate(Date date, View view) {
               // Toast.makeText(getApplicationContext(),
                       // "Long click " + formatter.format(date),
                        //Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCaldroidViewCreated() {
                if (caldroidFragment.getLeftArrowButton() != null) {
                    //Toast.makeText(getApplicationContext(),
                            //"Caldroid view is created", Toast.LENGTH_SHORT)
                            //.show();
                }
            }

        };


        caldroidFragment.setCaldroidListener(listener);
        caldroidFragment.setTextColorForDate(R.color.bg_row_background,new Date());

       // textView = (TextView) findViewById(R.id.textview);

      //  custmizations();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String studentname= studentPreference.getString("studentname", "");
                Schedule schedule = new Schedule();
                eventDialogues(schedule,Constants.EVENT_NEW,studentname);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

       caldroidFragment.refreshView();
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Downloading schedules");
        progressBar.setIndeterminate(true);

        progressBar.show();
        String studentname= studentPreference.getString("studentname", "");
        schedulePresInterface.deleteTablesMessage(studentname,getApplicationContext());



    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
            //   mDrawerLayout.openDrawer(GravityCompat.START);

            scheduleViewClass.this.finish();
            return true;


            default:
                return super.onOptionsItemSelected(item);
        }


    }




    @Override
    public void deleteTablesMessageRes(int errorCode) {
      if(errorCode==Constants.PASS_VALIDATION){
          String studentname= studentPreference.getString("studentname", "");
          schedulePresInterface.getMessageServer(studentname,getApplicationContext());
      }
    }

    @Override
    public void updateReadFlagResponse(Schedule schedule) {

    }

    @Override
    public void setMessagesServer(int errorCode) {
      if(errorCode==Constants.PASS_SERVICE){

          String studentname= studentPreference.getString("studentname", "");
          schedulePresInterface.getMessages(studentname,getApplicationContext());
      }else if(errorCode==Constants.PASS_NEW){


          isNew=true;

          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
          SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

          String seldate=formatter.format(new Date());

          Date date1=null;
          try {
              date1=formatter.parse(seldate);
          } catch (ParseException e) {
              e.printStackTrace();
          }
          String finaldate=formatter1.format(date1);

          lastSelDate=finaldate;


          String studentname= studentPreference.getString("studentname", "");
          schedulePresInterface.getMessages(studentname,getApplicationContext());




      } else if(errorCode==Constants.PASS_UP){

          String studentname= studentPreference.getString("studentname", "");
          schedulePresInterface.getMessages(studentname,getApplicationContext());
          Snackbar snackbar = Snackbar
                  .make(coordinatorlayout, "Updated", Snackbar.LENGTH_LONG);
          snackbar.show();
         // schedulePresInterface.getMessages(studentname,getApplicationContext());

      }


      else if(errorCode==Constants.NO_MESSAGE){
          progressBar.dismiss();

          Snackbar snackbar = Snackbar
                  .make(coordinatorlayout, "No schedules added", Snackbar.LENGTH_LONG);
          snackbar.show();


      }else if(errorCode==Constants.ERROR_SERVICE){
          progressBar.dismiss();

          Snackbar snackbar = Snackbar
                  .make(coordinatorlayout, "Error service", Snackbar.LENGTH_LONG);
          snackbar.show();

      }
    }

    @Override
    public void setMessages(List<Schedule> scheduleList) {
                //custmizations();


       // Methods.copyFile(getApplicationContext());


        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                listAsyns listAsyns=new listAsyns();
                listAsyns.schedules=scheduleList;
                listAsyns.execute();



            }
        });


              //  myCalendar.deleteAllEvent();

              /*  for(int i=0;i<scheduleList.size();i++){
                    Schedule schedule=scheduleList.get(i);

                    String startDate=schedule.getStarttime();
                    String endDate=schedule.getEndtime();
                    List<Date> dates = Methods.getDates(startDate, endDate);

                    Log.e("datesSize ","datesSize "+dates.size());

                    if(dates.size()>1){

                        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
                        SimpleDateFormat format2 = new SimpleDateFormat("dd-MM-yyyy");

                        try {
                            Date dateStart = format1.parse(startDate);
                            Date dateEnd=format1.parse(endDate);

                            String stringStart=format2.format(dateStart);
                            String stringEnd=format2.format(dateEnd);
                            listEventsNew(stringStart,stringEnd);


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                    } else {

                        for (Date date : dates) {
                            System.out.println(date);

                            //19-10-2018
                            SimpleDateFormat spf = new SimpleDateFormat("dd-MM-yyyy");
                            String dateFormatted = spf.format(date);

                            String[] date1 = dateFormatted.split("-");

                            int day = Integer.parseInt(date1[0]);
                            int month = Integer.parseInt(date1[1]);
                            int year = Integer.parseInt(date1[2]);

                            listEvents(day, month, year);


                            Log.e("dateFormatted ", "dateFormatted " + dateFormatted);
                            // myCalendar.addEvent(dateFormatted, "8:00", "8:15", "Today Event 1");

                        }

                    }


                }*/


             ////   caldroidFragment.refreshView();
               // progressBar.dismiss();



    }

    @Override
    public void setSelEvents(List<Schedule> scheduleList) {

        scheduleList.size();


        Methods.copyFile(getApplicationContext());

runOnUiThread(new Runnable() {
    @Override
    public void run() {


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new scheduleAdapter(scheduleList, getApplicationContext(), new scheduleOnclickListner() {
            @Override
            public void onItemClickSchedule(Schedule schedule) {
                String studentname= studentPreference.getString("studentname", "");

                String id=schedule.getSubject();

                      eventDialogues(schedule,Constants.EVENT_UPDATE,studentname);

            }
        });


       if(!isNew) {
           recyclerView.setAdapter(adapter);
           adapter.notifyDataSetChanged();
       }else{
           isNew=false;
           recyclerView.setAdapter(adapter);
           adapter.notifyDataSetChanged();
       }

        progressBar.dismiss();
    }
});
    }


    private class listAsyns extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;
       private   List<Schedule>schedules;


        @Override
        protected Void doInBackground(Void... voids) {





            for(int i=0;i<schedules.size();i++){

            }
            int counter =0;
            for(int i=0;i<schedules.size();i++){
                Schedule schedule=schedules.get(i);

                String startDate=schedule.getStarttime();
                String endDate=schedule.getEndtime();
                List<Date> dates = Methods.getDates(startDate, endDate);



                    for (Date date : dates) {
                        System.out.println(date);

                        //19-10-2018
                        SimpleDateFormat spf = new SimpleDateFormat("dd-MM-yyyy");
                        String dateFormatted = spf.format(date);

                        String[] date1 = dateFormatted.split("-");

                        int day = Integer.parseInt(date1[0]);
                        int month = Integer.parseInt(date1[1]);
                        int year = Integer.parseInt(date1[2]);


                        listEvents(day, month, year);


                      //  Log.e("dateFormatted ", "dateFormatted " + dateFormatted);
                        // myCalendar.addEvent(dateFormatted, "8:00", "8:15", "Today Event 1");

                    }

              //  }


            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            progressBar.dismiss();

            progressBar = new ProgressDialog(scheduleViewClass.this);
            progressBar.setCancelable(true);//you can cancel it by pressing back button
            progressBar.setMessage("Updating calender");
            progressBar.setIndeterminate(true);

            progressBar.show();


           /* dialog = new ProgressDialog(scheduleViewClass.this);
            dialog.setTitle("Updating...");
            dialog.setMessage("Please wait...");
            dialog.setIndeterminate(true);
            dialog.show();*/
        }



        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            caldroidFragment.refreshView();

           // progressBar.dismiss();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            String seldate=formatter.format(new Date());

            Date date1=null;
            try {
                 date1=formatter.parse(seldate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String finaldate=formatter1.format(date1);

            lastSelDate=finaldate;


            String studentname= studentPreference.getString("studentname", "");

            schedulePresInterface.getSelEvents(studentname,finaldate,getApplicationContext());
        }
    }


    void listEventsNew(String start, String end){


        String[] dateStartAray = start.split("-");

        int day=Integer.parseInt(dateStartAray[0]);
        int month=Integer.parseInt(dateStartAray[1]);
        int year=Integer.parseInt(dateStartAray[2]);


        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH,day);
        cal.set(Calendar.MONTH,month-1);
        cal.set(Calendar.YEAR,year);
        Date fromDate = cal.getTime();

        // Set selected dates

        // To Date

        String[] dateEndAray = end.split("-");

        int day1=Integer.parseInt(dateEndAray[0]);
        int month1=Integer.parseInt(dateEndAray[1]);
        int year1=Integer.parseInt(dateEndAray[2]);

        cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH,day1);
        cal.set(Calendar.MONTH,month1-1);
        cal.set(Calendar.YEAR,year1);

        Date toDate = cal.getTime();

        caldroidFragment.setSelectedDates(fromDate,toDate);
       //caldroidFragment.refreshView();
    }

    void listEvents(int day,int month,int year){


                Calendar cal = Calendar.getInstance();

                // Set selected dates
                // From Date
                cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH,day);
                cal.set(Calendar.MONTH,month-1);
                cal.set(Calendar.YEAR,year);
                Date evenDate = cal.getTime();


                if (caldroidFragment != null) {
                    ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.colorPrimaryLightest));
                    caldroidFragment.setBackgroundDrawableForDate(blue, evenDate);

                }

                caldroidFragment.setSelectedDate(evenDate);
              //  caldroidFragment.refreshView();
               // progressBar.dismiss();



    }

    @Override
    public void showMessage(int error) {

    }

    @Override
    public void sentResponse(int errorCode) {

    }

    @Override
    public void sentUpresponse(String Student_id, Schedule schedule) {
        schedulePresInterface.insertUpSchedule(Student_id,schedule,getApplicationContext() );
    }


    String formatDate(String dateString){
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        SimpleDateFormat format2 = new SimpleDateFormat("MMM-dd");
        String finlDate="";

        try {
            Date date1 = format1.parse(dateString);

            finlDate=format2.format(date1);


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return finlDate;
    }

    void eventDialogues(Schedule schedule,int status,String studentId){


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
        String currentDate = sdf.format(new Date());

        String currentTime = sdf1.format(new Date());
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_eventdialogue);

        TextView textStatus = (TextView) dialog.findViewById(R.id.textStatus);

        EditText edit_title = (EditText) dialog.findViewById(R.id.edit_username);
        EditText edit_desc = (EditText) dialog.findViewById(R.id.edit_password);
        EditText edit_loc = (EditText) dialog.findViewById(R.id.edit_loc);

        TextView textStartDate = (TextView) dialog.findViewById(R.id.textStartDate);
        TextView textStartTime = (TextView) dialog.findViewById(R.id.textStartTime);
        TextView textEndDate = (TextView) dialog.findViewById(R.id.textEndDate);
        TextView textEndTime = (TextView) dialog.findViewById(R.id.textEndTime);


        ImageView imgClose = (ImageView) dialog.findViewById(R.id.imgClose);
        //imgSave imgDelete
        ImageView imgSave = (ImageView) dialog.findViewById(R.id.imgSave);
        ImageView imgDelete = (ImageView) dialog.findViewById(R.id.imgDelete);

        if(status==Constants.EVENT_NEW){
            imgDelete.setVisibility(View.INVISIBLE);
            textStatus.setText("Add Event");
        }else{
            textStatus.setText("Edit Event");

            edit_title.setText(schedule.getSubject().toString());
            edit_desc.setText(schedule.getText().toString());

            SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm:ss");

            Date date1 = null, date2=null, time1=null,time2 =null;
            try {
                date1= originalFormat.parse(schedule.getStarttime().toString());
                time1=originalFormat.parse(schedule.getStarttime().toString());
                date2=originalFormat.parse(schedule.getEndtime().toString());
                time2=originalFormat.parse(schedule.getEndtime().toString());

            } catch (ParseException e) {
                e.printStackTrace();
            }

            textStartDate.setText(dateformat.format(date1));
            textEndDate.setText(dateformat.format(date2));
            textStartTime.setText(timeformat.format(time1));
            textEndTime.setText(timeformat.format(time2));


        }

        dialog.show();

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

       //need to check if new or edit

        if(status==Constants.EVENT_NEW) {
            textStartDate.setText(currentDate);
            textStartTime.setText(currentTime);
            textEndDate.setText(currentDate);
            textEndTime.setText(currentTime);
        }

        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject= edit_title.getText().toString();
                String text=edit_desc.getText().toString();

                String starttime=textStartDate.getText().toString()+" "+textStartTime.getText().toString();

                String endtime=textEndDate.getText().toString()+" "+textEndTime.getText().toString();

                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                Date date1 = null; Date date2 = null;

                try {
                    date1 = format1.parse(starttime);
                    date2=format1.parse(endtime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }






                if(SubcheckValidation(subject)){
                    if(SubcheckValidation(text)){

                        String studentname= studentPreference.getString("studentname", "");

                     Schedule schedule1 = new Schedule();
                     schedule1.setPkey(schedule.getPkey());
                     schedule1.setSubject(subject);
                     schedule1.setText(text);
                     schedule1.setStarttime(format2.format(date1));
                     schedule1.setEndtime(format2.format(date2));

                        progressBar = new ProgressDialog(scheduleViewClass.this);
                        progressBar.setCancelable(true);//you can cancel it by pressing back button
                        progressBar.setMessage("Saving");
                        progressBar.setIndeterminate(true);

                        progressBar.show();

                       if(status==Constants.EVENT_NEW) {
                           schedulePresInterface.sentNewEvent(studentname, schedule1, getApplicationContext());
                       } else if (status == Constants.EVENT_UPDATE) {
                           schedulePresInterface.sentUpEvent(studentname,schedule1,getApplicationContext());

                       }
                     dialog.dismiss();


                    }else{
                        edit_desc.setFocusable(true);
                    }
                }else{
                    edit_title.setFocusable(true);
                }

            }
        });


        textStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(scheduleViewClass.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                String sDate=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                                SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");

                                Date date = null;

                                try {
                                    date = format1.parse(sDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                              //  System.out.println(format2.format(date));

                                textStartDate.setText(format2.format(date));

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        textEndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(scheduleViewClass.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {


                                String sDate=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                                SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                                SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");

                                Date date = null;

                                try {
                                    date = format1.parse(sDate);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                //  System.out.println(format2.format(date));

                                textEndDate.setText(format2.format(date));


                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });




        textStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
              //  mSecond=c.get(Calendar.SECOND);
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(scheduleViewClass.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                textStartTime.setText(hourOfDay + ":" + minute+":"+"00");
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();


            }
        });

        textEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(scheduleViewClass.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                textEndTime.setText(hourOfDay + ":" + minute+":"+"00");
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();


            }
        });

    }


    boolean SubcheckValidation(String content){
        boolean isValid=false;
        try {

            if(content.equals("")||content.equals(" ")){
                isValid=false;
            }else{
                isValid=true;
            }

        }catch (Exception e){
            e.printStackTrace();
            isValid=false;
        }
        return isValid;
    }



    boolean checkIfToShow(){
        boolean isne=false;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String curreDate=formatter.format(new Date());

        Date date1=null;String lastdt="";

        try {
            date1 = formatter.parse(scheduleViewClass.lastSelDate);
            lastdt=formatter.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  isne;
    }

}


