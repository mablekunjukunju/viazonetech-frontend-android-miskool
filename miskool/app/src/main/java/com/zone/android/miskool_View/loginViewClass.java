package com.zone.android.miskool_View;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.roger.catloadinglibrary.CatLoadingView;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Entitiy.Person_det;
import com.zone.android.miskool_Entitiy.Schedule;
import com.zone.android.miskool_Presenter.loginPresClass;
import com.zone.android.miskool_Presenter.loginPresInterface;
import com.zone.android.miskool_Presenter.loginTestClass;
import com.zone.android.miskool_Util.CallWebservice;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.Methods;

import org.angmarch.views.NiceSpinner;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;



/**
 * Created by Inspiron on 13-11-2017.
 */

public class loginViewClass extends AppCompatActivity implements loginViewInterface {
    //declaring edittest in login module
    EditText editUser,editPassword;
    Button buttonLogin;
    TextView textView;
    //declaring httpurlconnection class
    loginPresInterface loginPresInterface ;
    private int mYear, mMonth, mDay, mHour, mMinute,mSecond;
    public static ProgressDialog progressBar;
    SharedPreferences reg_preference;

    int network=0;
    int service=1;

    CallWebservice Callweb =        new CallWebservice();

    CatLoadingView mView;
    ScrollView scrolLayout;

    SharedPreferences credentialPreference,loginPreference;
    String userNamePref;
    String passWordPref;

    SharedPreferences studentPreference;
    String StudenId;
    static String gender;
    Button btn_register;
    NiceSpinner spinner1;

    public static ProgressDialog progressBarNew;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_loginneww);

        setContentView(R.layout.activity_logintest);

        loginPresInterface = new loginPresClass(this);
        credentialPreference=getApplicationContext().getSharedPreferences(Constants.CREDENTIAL_PREFERENCE, Context.MODE_PRIVATE);
        loginPreference=getApplicationContext().getSharedPreferences(Constants.LOGIN_PREFERENCE, Context.MODE_PRIVATE);

        reg_preference=getApplicationContext().getSharedPreferences(Constants.REG_DETAILS_PREFERENCE, Context.MODE_PRIVATE);

       //intitalizing views
        editUser =(EditText)findViewById(R.id.edit_username);
        editPassword=(EditText)findViewById(R.id.edit_password);
        buttonLogin=(Button)findViewById(R.id.btn_login);
        btn_register=(Button)findViewById(R.id.btn_register) ;
        scrolLayout =(ScrollView)findViewById(R.id.scrolLayout) ;

        studentPreference = getApplicationContext().getSharedPreferences(Constants.STUDENT_SELECTION_PREFERENCE,Context.MODE_PRIVATE);

      //  StudenId = studentPreference.getString("studentId","default student from db");





        onclickListneres();

       mView = new CatLoadingView();


    }

    @Override
    public void setNavigation() {


        Intent intent = new Intent(loginViewClass.this,mainViewClass.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();

        SharedPreferences.Editor editor1 = reg_preference.edit();
        editor1.putString("firstname", "");
        editor1.putString("lastname", "");
        editor1.putString("othername", "");
        editor1.commit();



        stopProgressBr();

        SharedPreferences.Editor editor = loginPreference.edit();
        editor.putBoolean("islogin", true);
        editor.commit();


    }

    public void startProgressbar(){

        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(false);//you can cancel it by pressing back button
        progressBar.setMessage("Logging in");
        progressBar.setIndeterminate(true);

        progressBar.show();
    }

    public void stopProgressBr(){

        progressBar.dismiss();
    }
    @Override
    public void showMessage(int code) {
        switch(code){
            case Constants.PASS_VALIDATION:

                boolean checknewtwork=Methods.checknewtwork(getApplicationContext());

            if(checknewtwork) {

                startProgressbar();

                //call getTest Service test
                String username = editUser.getText().toString();
                String passWord = editPassword.getText().toString();
                //loginPresInterface.getTestServices(username,passWord,getApplicationContext());

                loginPresInterface.getAccessToken(username, passWord, getApplicationContext());

                SharedPreferences.Editor editor = credentialPreference.edit();
                editor.putString("username", username);
                editor.putString("password", passWord);
                editor.commit();

            }else{

                toastMessageWithAction("No internet",network);
            }
               return;


            case Constants.PASS_NETWORK:
                //call getConfig services

                String username1=editUser.getText().toString();
                String passWord1=editPassword.getText().toString();
                loginPresInterface.getServices(username1,passWord1,getApplicationContext());

               return;

            case Constants.NEW_USER:
                toastMessage("Success! Please check email");
                return;

           /* case Constants.:
                stopProgressBr();
                setNavigation();*/

        }
    }

    @Override
    public void showError(int errorCode) {

        switch (errorCode){
            case Constants.ERROR_USER_NAME_NULL:

               // editUser.setError("enter username");
                editUser.requestFocus();
                return;

            case Constants.ERROR_PASS_WORD_NULL:
               // editPassword.setError("enter password");
                editPassword.requestFocus();
               return ;

            case Constants.ERROR_NETWORK:
                //editPassword.setError("enter password");
                //need to show network error......
                return;
            case Constants.ERROR_CREDENTIALS:
                //editPassword.setError("enter password");
                //need to show network error......
                stopProgressBr();
                toastMessage("Check credentials");

                editUser.setText("");
                editPassword.setText("");
                editUser.requestFocus();


                return;

            case Constants.ERROR_SERVICE:
                stopProgressBr();
                toastMessageWithAction("Service Error",1);
                return;

            default:
                return;
        }

     //  Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG);

    }
    void toastMessage(String message){

        Snackbar snackbar = Snackbar
                .make(scrolLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();

    }

    void toastMessageWithAction( String toastMessage, int source){

     if(source==service) {
         Snackbar snackbar = Snackbar
                 .make(scrolLayout, toastMessage, Snackbar.LENGTH_LONG)
                 .setActionTextColor(Color.parseColor("#f75f44"))
                 .setAction("retry", new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         String username = credentialPreference.getString("username", "");
                         String password = credentialPreference.getString("password", "");
                         startProgressbar();

                         loginPresInterface.getAccessToken(username, password, getApplicationContext());

                     }
                 });
         ;

         snackbar.show();


     }else{

         final Snackbar snackbar = Snackbar
                 .make(scrolLayout, toastMessage, Snackbar.LENGTH_LONG)
                 .setActionTextColor(Color.parseColor("#f75f44"));
                  snackbar.show();
     }



    }

    void onclickListneres(){


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  registerNewUser();

                SharedPreferences.Editor editor = reg_preference.edit();
                editor.putString("firstname", "");
                editor.putString("lastname", "");
                editor.putString("othername", "");
                editor.commit();

                Intent register1=new Intent(loginViewClass.this,regsiterActivity1.class);
                startActivity(register1);
            }
        });

        //enababling setonclick listener
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName=editUser.getText().toString();
                String passWord=editPassword.getText().toString();

                SharedPreferences servicePreference = getApplicationContext().getSharedPreferences(Constants.SERVICE_STATUS_PREFERENCE,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = servicePreference.edit();
                editor.putInt("service", 0);
                editor.commit();

                loginPresInterface.getLogin(userName,passWord, getApplicationContext());

          //  Methods.copyFile(getApplicationContext());

              //  startProgressbar();


               // Methods.insertToken("alzaxtf23vgggfg",getApplicationContext());

              //  Methods.getPersonDetaisl(getApplicationContext());

               /* loginTestClass loginTestClass = new loginTestClass();
                loginTestClass.copyFile();
*/



            }
        });

    }

    void registerNewUser(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm:ss");
        String currentDate = sdf.format(new Date());

        String currentTime = sdf1.format(new Date());
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_registerdialogue);

        EditText edit_title = (EditText) dialog.findViewById(R.id.edit_username);
        EditText edit_desc = (EditText) dialog.findViewById(R.id.edit_password);
        //   edit_address edit_email
        EditText edit_firstName = (EditText) dialog.findViewById(R.id.edit_firstName);

        EditText edit_lastName = (EditText) dialog.findViewById(R.id.edit_lastName);
        EditText edit_otherName = (EditText) dialog.findViewById(R.id.edit_otherName);
        EditText edit_address = (EditText) dialog.findViewById(R.id.edit_address);

        EditText edit_email = (EditText) dialog.findViewById(R.id.edit_email);


        Spinner spinner1=(Spinner) dialog.findViewById(R.id.course_spinner);

        TextView textStartDate = (TextView) dialog.findViewById(R.id.textStartDate);
        TextView textStartTime = (TextView) dialog.findViewById(R.id.textStartTime);


        ImageView imgClose = (ImageView) dialog.findViewById(R.id.imgClose);
        //imgSave imgDelete
        ImageView imgSave = (ImageView) dialog.findViewById(R.id.imgSave);
        ImageView imgDelete = (ImageView) dialog.findViewById(R.id.imgDelete);



        dialog.show();

        List routes = new ArrayList();
        routes.add("MALE");
        routes.add("FEMALE");

        gender="MALE";

      /*  ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,routes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner1.setAdapter(arrayAdapter);
*/

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(loginViewClass.this, R.layout.spinner_item, routes);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dataAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                try{

                    String item = parent.getItemAtPosition(position-1).toString();

                     gender=item;

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        //need to check if new or edit


            textStartDate.setText(currentDate);
            textStartTime.setText(currentTime);


        imgSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject= edit_title.getText().toString();
                String text=edit_desc.getText().toString();

                String starttime=textStartDate.getText().toString()+" "+textStartTime.getText().toString();


                SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

                Date date1 = null; Date date2 = null;

                try {
                    date1 = format1.parse(starttime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                String firstname=edit_firstName.getText().toString();
                String lastname=edit_lastName.getText().toString();
                String address=edit_address.getText().toString();
                String othername=edit_otherName.getText().toString();
                String email=edit_email.getText().toString();


                if(SubcheckValidation(subject)){
                    if(SubcheckValidation(text)){
                        if(SubcheckValidation(starttime)) {


                            String studentname = studentPreference.getString("studentname", "");


                            String userName = subject;
                            String pssWord = text;
                            String dob = starttime;
                            String gender1 = gender;

                            JSONObject jsonObject= new JSONObject();

                            try{
                                jsonObject.put("username",userName);
                                jsonObject.put("password",pssWord);
                                jsonObject.put("dob",dob);
                                jsonObject.put("gender",gender1);
                                jsonObject.put("firstname",firstname);
                                jsonObject.put("lastname",lastname);
                                jsonObject.put("othernames",othername);
                                jsonObject.put("address",address);
                                jsonObject.put("email",email);


                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            progressBar = new ProgressDialog(loginViewClass.this);
                            progressBar.setCancelable(true);//you can cancel it by pressing back button
                            progressBar.setMessage("Saving");
                            progressBar.setIndeterminate(true);

                            progressBar.show();

                       //    Methods.registerNewUser(jsonObject,loginViewClass.this);
                            dialog.dismiss();


                        }else{
                            textStartDate.setFocusable(true);
                        }
                        /////////////////
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


                DatePickerDialog datePickerDialog = new DatePickerDialog(loginViewClass.this,
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(loginViewClass.this,
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



}
