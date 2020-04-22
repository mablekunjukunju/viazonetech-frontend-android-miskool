package com.zone.android.miskool_View;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.zone.android.miskool.R;

/**
 * Created by Inspiron on 29-06-2018.
 */

public class SettingsClass extends AppCompatActivity {
    RelativeLayout relativeOpen,relativeCredit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Admin");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        relativeOpen=(RelativeLayout)findViewById(R.id.relativeOpen);
        relativeCredit=(RelativeLayout)findViewById(R.id.relativeCredit);

        relativeOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(getApplicationContext(), OssAttribActivity.class);
                startActivity(intent1);
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

                this.finish();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }
}
