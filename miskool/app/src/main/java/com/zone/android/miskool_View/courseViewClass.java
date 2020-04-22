package com.zone.android.miskool_View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.zone.android.miskool.R;
import com.zone.android.miskool.attendFragment;
import com.zone.android.miskool.examFragment;
import com.zone.android.miskool.homeFragment;
import com.zone.android.miskool_Adapters.slidingPanelAdapter;
import com.zone.android.miskool_Adapters.timeTableAdapter;
import com.zone.android.miskool_Entitiy.Person_det;
import com.zone.android.miskool_Entitiy.timetable;
import com.zone.android.miskool_Presenter.coursePresClass;
import com.zone.android.miskool_Presenter.coursePresInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Inspiron on 20-09-2018.
 */

public class courseViewClass extends AppCompatActivity implements courseViewInterface {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    coursePresInterface coursePresInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

      /*  actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.homenew);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Inbox");*/

       // getSupportActionBar().setTitle("Course");
        getSupportActionBar().setDisplayShowTitleEnabled(false);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        createViewPager(viewPager);
        coursePresInterface=new coursePresClass(this);
        coursePresInterface.getTimeTable(getApplicationContext(),"studentid");

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        createTabIcons();


    }

    private void createTabIcons() {

        TextView tabOne = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabOne.setText("Attendance");
        tabOne.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_account, 0, 0);
        tabLayout.getTabAt(0).setCustomView(tabOne);

        TextView tabTwo = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabTwo.setText("Homework");
        tabTwo.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_send, 0, 0);
        tabLayout.getTabAt(1).setCustomView(tabTwo);

        TextView tabThree = (TextView) LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        tabThree.setText("Exam");
        tabThree.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_lib, 0, 0);
        tabLayout.getTabAt(2).setCustomView(tabThree);
    }

    private void createViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new attendFragment(), "Attendance");
        adapter.addFrag(new homeFragment(), "Homework");
        adapter.addFrag(new examFragment(), "Exam");
        viewPager.setAdapter(adapter);
    }



    @Override
    public void setTimetable() {
       /* runOnUiThread(new Runnable() {
            @Override
            public void run() {
*/
              /* List<timetable> timeList = new ArrayList<>();

                for(int i=0;i<5;i++){

                    timetable timetable=new timetable();

                    switch (i){
                        case 0:
                        timetable.setTime("9:30");
                        timetable.setSub("SSc");


                        case 1:
                            timetable.setTime("10:30");
                            timetable.setSub("Maths");
                           // timeList.add(i,timetable);

                        case 2:
                            timetable.setTime("11:30");
                            timetable.setSub("Mal");
                           // timeList.add(i,timetable);

                        case 3:
                            timetable.setTime("01:30");
                            timetable.setSub("Eng");
                          //  timeList.add(i,timetable);

                        case 4:
                            timetable.setTime("2:30");
                            timetable.setSub("Science");
                            //timeList.add(i,timetable);
                    }
                    timeList.add(i,timetable);

                }

               // Log.e("personList ", "personList " + personList.size());
                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.slideViewcourse);
                recyclerView.setHasFixedSize(true);
                LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getApplicationContext());
                MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(MyLayoutManager);


                RecyclerView.Adapter adapter = new timeTableAdapter(timeList, getApplicationContext());

                recyclerView.setAdapter(adapter);

            }
        });*/
    }

    @Override
    public void setAttendance() {

    }

    @Override
    public void setHomework() {

    }

    @Override
    public void setExam() {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {

            Log.e("position","position "+position);
              switch (position){

                  case 0:
                      Bundle bundle = new Bundle();
                      bundle.putString("attendance", "atten");

                      attendFragment attendFragment = new attendFragment();
                      attendFragment.setArguments(bundle);

                  case 1:

                      Bundle bundle1 = new Bundle();
                      bundle1.putString("home", "home");
                      mFragmentList.get(position).setArguments(bundle1);

                  case 2:

                      Bundle bundle2 = new Bundle();
                      bundle2.putString("exam", "exam");
                      mFragmentList.get(position).setArguments(bundle2);

              }

            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

    }
}

