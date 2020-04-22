package com.zone.android.miskool_View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.franmontiel.attributionpresenter.entities.Attribution;
import com.franmontiel.attributionpresenter.entities.LicenseInfo;
import com.franmontiel.attributionpresenter.listeners.OnAttributionClickListener;
import com.franmontiel.attributionpresenter.listeners.OnLicenseClickListener;
import com.zone.android.miskool.R;
import com.zone.android.miskool_Util.AttributionPresenterCreator;

/**
 * Created by Inspiron on 15-06-2018.
 */

public class ListActivity extends AppCompatActivity {

    Toolbar toolbar;
    ActionBar actionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_list);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Open Source Libraries");
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        ListView list = (ListView) findViewById(R.id.list);

        list.setAdapter(AttributionPresenterCreator.create(
                this,
                new OnAttributionClickListener() {
                    @Override
                    public boolean onAttributionClick(Attribution attribution) {
                        Toast.makeText(getApplicationContext(), "Attribution click: " + attribution.getName(), Toast.LENGTH_SHORT).show();
                        return false;
                    }
                },
                new OnLicenseClickListener() {
                    @Override
                    public boolean onLicenseClick(LicenseInfo licenseInfo) {
                        Toast.makeText(getApplicationContext(), "License click: " + licenseInfo.getName(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }).getAdapter());


       /* list.setAdapter(AttributionPresenterCreator.create(this,
                R.layout.custom_item_attribution,
                R.layout.custom_license_text).getAdapter());*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                //   mDrawerLayout.openDrawer(GravityCompat.START);
                ListActivity.this.finish();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
