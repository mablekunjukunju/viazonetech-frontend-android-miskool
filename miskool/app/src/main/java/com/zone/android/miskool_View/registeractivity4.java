package com.zone.android.miskool_View;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.Methods;

import org.json.JSONObject;

public class registeractivity4 extends AppCompatActivity implements registerInterface {
    EditText edit_username,edit_password;

    Button btn_login;
    SharedPreferences reg_preference;
    public static ProgressDialog progressBar;
    RelativeLayout relative;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity4);

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


        edit_username=(EditText)findViewById(R.id.edit_username);
        edit_password=(EditText)findViewById(R.id.edit_password);

        relative=(RelativeLayout)findViewById(R.id.relative);



        btn_login=(Button)findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstname=reg_preference.getString("firstname","");
                String countrycode=reg_preference.getString("countrycode","");

                String lastname=reg_preference.getString("lastname","");
                String othername=reg_preference.getString("othername","");

                String address=reg_preference.getString("address","");
                String email=reg_preference.getString("email","");
                String starttimeendtime=reg_preference.getString("starttimeendtime","");
                String gender=reg_preference.getString("gender","");



                JSONObject jsonObject= new JSONObject();

                try{
                    jsonObject.put("mobile",edit_username.getText().toString());
                    jsonObject.put("password",edit_password.getText().toString());
                    jsonObject.put("dob",starttimeendtime);
                    jsonObject.put("gender",gender);
                    jsonObject.put("firstname","+"+countrycode+firstname);
                    jsonObject.put("lastname",lastname);
                    jsonObject.put("othernames",othername);
                    jsonObject.put("address",address);
                    jsonObject.put("email",email);


                }catch (Exception e){
                    e.printStackTrace();
                }

                progressBar = new ProgressDialog(registeractivity4.this);
                progressBar.setCancelable(true);//you can cancel it by pressing back button
                progressBar.setMessage("Saving");
                progressBar.setIndeterminate(true);

                progressBar.show();

                Methods.registerNewUser(jsonObject,registeractivity4.this);
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

    @Override
    public void showMessage(int message) {
        progressBar.dismiss();

        /*Snackbar snackbar = Snackbar
                .make(relative, "Success! Please check Email", Snackbar.LENGTH_LONG);
        snackbar.show();

        this.finish();*/

edit_password.setText("");
edit_username.setText("");
        AlertDialog.Builder builder = new AlertDialog.Builder(
                registeractivity4.this);
        builder.setCancelable(false);
        builder.setTitle("Success!");
        builder.setMessage("Please check email");
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                       registeractivity4.this.finish();

                        SharedPreferences.Editor editor = reg_preference.edit();
                        editor.putString("username", "");
                        editor.putString("password","");
                        editor.putString("dob", "");
                        editor.putString("gender","");
                        editor.putString("firstname", "");
                        editor.putString("countrycode", "");
                        editor.putString("lastname","");
                        editor.putString("othername","");
                        editor.putString("startdate","");
                        editor.putString("startdate","");
                        editor.putString("starttimeendtime","");


                        editor.commit();

                        Intent intent=new Intent(registeractivity4.this,loginViewClass.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                });
        builder.show();
    }



}
