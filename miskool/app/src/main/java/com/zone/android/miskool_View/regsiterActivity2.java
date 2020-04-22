package com.zone.android.miskool_View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Util.Constants;

public class regsiterActivity2 extends AppCompatActivity {

    EditText edit_address,edit_email;

    Button btn_login;

    SharedPreferences reg_preference;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity2);

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

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        reg_preference=getApplicationContext().getSharedPreferences(Constants.REG_DETAILS_PREFERENCE, Context.MODE_PRIVATE);

        String address=reg_preference.getString("address","");
        String email=reg_preference.getString("email","");


        edit_address=(EditText)findViewById(R.id.edit_address);
        edit_email=(EditText)findViewById(R.id.edit_email);
        getWindow().setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);


        edit_address.setText(address);
        edit_email.setText(email);

        btn_login=(Button)findViewById(R.id.btn_login);

edit_email.addTextChangedListener(new TextWatcher() {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
edit_email.setError(null);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
});

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String ads=edit_address.getText().toString();
                String eml=edit_email.getText().toString();

                if(isUserNameValid(ads)){

                    if(eml.matches(emailPattern)){


                        SharedPreferences.Editor editor = reg_preference.edit();

                        editor.putString("address", ads);
                        editor.putString("email", eml);

                        editor.putString("starttime", "");
                        editor.putString("startdate", "");
                        editor.commit();

                        Intent intent=new Intent(regsiterActivity2.this,registerActivity3.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                        startActivity(intent);

                    }else {
                        edit_email.requestFocus();
                        edit_email.setError("email not valid");

                    }
                }else{
                    edit_address.requestFocus();
                }



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

                regsiterActivity2.this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
