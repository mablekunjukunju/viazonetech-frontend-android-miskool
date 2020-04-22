package com.zone.android.miskool_View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Util.Constants;

public class regsiterActivity1 extends AppCompatActivity {
    SharedPreferences reg_preference;

    EditText edit_firstname,edit_lastname,edit_othername,edit_countrycode;

    Button btn_login;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity1);
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

        String firstname=reg_preference.getString("firstname","");
        String lastname=reg_preference.getString("lastname","");
        String othername=reg_preference.getString("othername","");

        //edit_countrycode

        String countrycode=reg_preference.getString("countrycode","");

        edit_firstname=(EditText)findViewById(R.id.edit_firstname);
        edit_lastname=(EditText)findViewById(R.id.edit_lastname);
        edit_othername=(EditText)findViewById(R.id.edit_othername);
        edit_countrycode=(EditText)findViewById(R.id.edit_countrycode);


        edit_firstname.setText(firstname);
        edit_lastname.setText(lastname);
        edit_othername.setText(othername);
        edit_countrycode.setText(countrycode);

        btn_login=(Button)findViewById(R.id.btn_login);

        getWindow().setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String firstName1=edit_firstname.getText().toString();
                String lastName1=edit_lastname.getText().toString();
                String othername1=edit_othername.getText().toString();

                String countrycode1=edit_countrycode.getText().toString();

               boolean isfirstName=isUserNameValid(firstName1);
               boolean isCountryCode=isUserNameValid(countrycode1);
               boolean isLastName=isUserNameValid(lastName1);
               boolean isOthername=isUserNameValid(othername1);

               if(isCountryCode){
               if(isfirstName){
                   if(isLastName){


                           SharedPreferences.Editor editor = reg_preference.edit();
                           editor.putString("firstname", firstName1);
                           editor.putString("countrycode", countrycode1);

                       editor.putString("lastname", lastName1);
                           editor.putString("othername", othername1);
                           editor.putString("address", "");
                           editor.putString("email", "");
                           editor.commit();
                           editor.commit();


                           editor.commit();

                           Intent intent=new Intent(regsiterActivity1.this,regsiterActivity2.class);
                           intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                           startActivity(intent);




                   }else{
                       edit_lastname.requestFocus();
                   }
               }else{
                   edit_firstname.requestFocus();

               }

               }else{
                   edit_countrycode.requestFocus();
               }


            }
        });



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

                SharedPreferences.Editor editor = reg_preference.edit();
                editor.putString("firstname", "");
                editor.putString("lastname", "");
                editor.putString("othername", "");
                editor.commit();

                regsiterActivity1.this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
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
    public void onBackPressed() {
        super.onBackPressed();

        SharedPreferences.Editor editor = reg_preference.edit();
        editor.putString("firstname", "");
        editor.putString("lastname", "");
        editor.putString("othername", "");
        editor.commit();


    }
}
