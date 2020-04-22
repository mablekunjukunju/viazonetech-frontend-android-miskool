package com.zone.android.miskool_View;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Util.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class registerActivity3 extends AppCompatActivity {
  TextView textStartDate,textStartTime;
  Spinner spinner1;
    static String gender1;

    Button btn_login;
    private int mYear, mMonth, mDay, mHour, mMinute,mSecond;
    SharedPreferences reg_preference;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

      /*  actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.homenew);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Inbox");*/

        getSupportActionBar().setTitle("New user");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        reg_preference=getApplicationContext().getSharedPreferences(Constants.REG_DETAILS_PREFERENCE, Context.MODE_PRIVATE);


        String starttime=reg_preference.getString("starttime","");
        String startdate=reg_preference.getString("startdate","");
        String gender=reg_preference.getString("gender","");


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
        String currentDate = sdf.format(new Date());

        String currentTime = sdf1.format(new Date());

        if(startdate.equals("")||startdate.equals(" ")||startdate.equals(null)){
             startdate=currentDate;
        }

        if(starttime.equals("")||starttime.equals(" ")||starttime.equals(null)){
            starttime=currentTime;
        }


        spinner1=(Spinner) findViewById(R.id.course_spinner);

         textStartDate = (TextView) findViewById(R.id.textStartDate);
         textStartTime = (TextView) findViewById(R.id.textStartTime);

        textStartDate.setText(startdate);
        textStartTime.setText(starttime);

        List routes = new ArrayList();

        if(gender.contains("FE")){
            routes.add("FEMALE");
            routes.add("MALE");

            spinner1.setSelection(0);
        }else{
            routes.add("MALE");
            routes.add("FEMALE");

            spinner1.setSelection(1);


        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(registerActivity3.this, R.layout.spinner_item, routes);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

              try {


                  String item = parent.getItemAtPosition(position - 1).toString();

                  gender1 = item;

                  SharedPreferences.Editor editor = reg_preference.edit();
                  editor.putString("gender", gender1);

              }catch (Exception e){
                  e.printStackTrace();
              }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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


                DatePickerDialog datePickerDialog = new DatePickerDialog(registerActivity3.this,
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






        textStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);
                //  mSecond=c.get(Calendar.SECOND);
                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(registerActivity3.this,
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





        btn_login=(Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String starttimeendtime=textStartDate.getText().toString()+" "+textStartTime.getText().toString();
                String genderFinal=spinner1.getSelectedItem().toString();


                SharedPreferences.Editor editor = reg_preference.edit();
                editor.putString("starttimeendtime", starttimeendtime);
                editor.putString("gender", gender1);
                editor.putString("username", "");
                editor.putString("password","");
                editor.commit();

                Intent intent=new Intent(registerActivity3.this,registeractivity4.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);





            }
        });



    }


    boolean isUserNameValid( String username){
        boolean validString = true;
        if(username.equals(null)||username.equals("")||username.equals("null")){
            validString = false;
        }else{
            validString = true;
        }

        return validString;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {


            case android.R.id.home:
                //   mDrawerLayout.openDrawer(GravityCompat.START);

                registerActivity3.this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
