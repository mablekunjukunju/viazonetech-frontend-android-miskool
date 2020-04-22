package com.zone.android.miskool_View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Util.OssItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Inspiron on 24-06-2018.
 */

public class OssAttribActivity extends AppCompatActivity {
    ActionBar actionBar;
    Toolbar toolbar;
    private Context cxt;
    private static final String APACHE_LISC = "Apache 2.0";
    private static final String MISC_LISC = "BSD, MIT, Apache 2.0";
    private static final String MIT = "MIT License";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os_attrib);
        setupToolbar();
        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerview);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new OssItemAdapter(this, this, getOssList()));

    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Open Source Libraries");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Set Toolbar title here

     //   collapsingToolbar.setTitle("Open Source Libraries");


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private static class OssItemAdapter  extends RecyclerView.Adapter<OssItemAdapter.ViewHolder>{
        private List<OssItem> mValues;
        private Activity mActivity;

        public OssItemAdapter(Activity mActivityIn, Context context, List<OssItem> items) {
            mActivity = mActivityIn;
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.oss_list_item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            holder.mBoundString = mValues.get(position).libName;
            holder.libName.setText(mValues.get(position).libName);
            holder.licType.setText(mValues.get(position).licType);
            holder.copyright.setText(mValues.get(position).copyright);

           /* if (!mValues.get(position).libUrl.equals("")) {
                holder.ossLayout.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.white));
                holder.libName.setTypeface(null, Typeface.NORMAL);
                holder.libName.setTypeface(null, Typeface.ITALIC);
                holder.libName.setAllCaps(false);
            } else {
                holder.ossLayout.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.offwhite));
                holder.libName.setTypeface(null, Typeface.BOLD);
                holder.libName.setAllCaps(true);
            }*/

            if (mValues.get(position).copyright.equals("")){

                holder.copyright.setVisibility(View.GONE);
            }

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mValues.get(position).licType.equals("")) {
                        // Ignore this, since this is for a header click
                    } else {
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(mValues.get(position).libUrl));
                        v.getContext().startActivity(i);
                    }
                }
            });

        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public String mBoundString;

            public final View mView;
            public final LinearLayout ossLayout;
            public final TextView libName;
            public final TextView licType;
            public final TextView copyright;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                ossLayout = (LinearLayout) view.findViewById(R.id.oss_item_layout);
                libName = (TextView) view.findViewById(R.id.oss_lib_name);
                licType = (TextView) view.findViewById(R.id.oss_license_type);
                copyright=(TextView)view.findViewById(R.id.copyrightNotices);
            }
        }


    }


    private List<OssItem>  getOssList() {
        List<OssItem> items = new ArrayList<>(15);

        items.add(new OssItem("Plain-pie",APACHE_LISC,"https://github.com/zurche/plain-pie","Copyright 2018 Alejandro Zürcher"));
        items.add(new OssItem("Yalantis:Context-Menu", APACHE_LISC ,"https://github.com/Yalantis/Context-Menu.Android","Copyright 2017, Yalantis"));
        items.add(new OssItem("Yalantis:Side-Menu", APACHE_LISC ,"https://github.com/Yalantis/Side-Menu.Android","Copyright 2017, Yalantis"));
        items.add(new OssItem("MyDynamicCalendarLibrary", APACHE_LISC ,"https://github.com/vatsaldesai92/MyDynamicCalendarLibrary","Copyright 2016"));
        items.add(new OssItem("Httpcomponents:httpmime", APACHE_LISC ,"https://hc.apache.org/httpcomponents-client-ga/httpmime/dependencies.html",""));
        items.add(new OssItem("Httpcomponents:httpclient", APACHE_LISC,"https://hc.apache.org/httpcomponents-client-4.3.x/android-port.html",""));
        items.add(new OssItem("Roger.catloadinglibrary", MIT ,"https://github.com/Rogero0o/CatLoadingView","Copyright © 2015 Roger"));
      //  items.add(new OssItem("Android.volley", "" ,"https://github.com/google/volley",""));
       // items.add(new OssItem("Recyclerview", "" ,"https://developer.android.com/topic/libraries/support-library/packages",""));
      //  items.add(new OssItem("Cardview", "" ,"https://developer.android.com/topic/libraries/support-library/packages",""));
        items.add(new OssItem("slidinguppanel", APACHE_LISC ,"https://github.com/umano/AndroidSlidingUpPanel",""));
        items.add(new OssItem("Prettytime", APACHE_LISC ,"https://github.com/ocpsoft/prettytime",""));
      //  items.add(new OssItem("ANDROID","","",""));
      //  items.add(new OssItem("Firebase", APACHE_LISC ,"https://firebase.google.com/docs/android/setup",""));

        return items;

    }
}
