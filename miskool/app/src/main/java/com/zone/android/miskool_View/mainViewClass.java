package com.zone.android.miskool_View;

import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.RingtoneManager;
import android.net.Network;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.zone.android.miskool.MapsActivity;
import com.zone.android.miskool.R;
import com.zone.android.miskool_Adapters.alertListAdapter;
import com.zone.android.miskool_Adapters.mainScheduleListAdapter;
import com.zone.android.miskool_Adapters.slidingPanelAdapter;
import com.zone.android.miskool_Entitiy.Alerts;
import com.zone.android.miskool_Entitiy.Person_det;
import com.zone.android.miskool_Entitiy.Schedule;
import com.zone.android.miskool_Presenter.mainPresClass;
import com.zone.android.miskool_Presenter.mainPresInterface;
import com.zone.android.miskool_Services.BusTrackerService;
import com.zone.android.miskool_Services.ReminderService;
import com.zone.android.miskool_Services.backGroundServices;

import com.zone.android.miskool_Util.Alarm;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.ContentFragment;
import com.zone.android.miskool_Util.Methods;
import com.zone.android.miskool_Util.MyReceiver;
import com.zone.android.miskool_Util.MyWorker;


import org.ocpsoft.prettytime.PrettyTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import yalantis.com.sidemenu.interfaces.Resourceble;
import yalantis.com.sidemenu.interfaces.ScreenShotable;
import yalantis.com.sidemenu.model.SlideMenuItem;
import yalantis.com.sidemenu.util.ViewAnimator;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;


/**
 * Created by Inspiron on 07-12-2017.
 */

public class mainViewClass extends AppCompatActivity implements mainViewInterface,OnMenuItemClickListener,ViewAnimator.ViewAnimatorListener{

    LinearLayout locationLayout, relativeMessage, relativeAlert,relativeSchedule;
    ImageButton imageloc, imgmsg, imgalert;
    slidingPanelAdapter slidingPanelAdapter;
    public static TextView txtNme;

    SharedPreferences studentPreference;
    SharedPreferences credentialPreference, loginPreference;
    ActionBar actionBar;

     boolean flag = false;

    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;

    private PendingIntent pendingIntent;
    mainPresInterface mainPresInterface;
    Toolbar toolbar;
    private int READ_CALENDER_PERMISSION_CODE = 23;

    Timer timer;
    TimerTask timerTask;

    final Handler handler1 = new Handler();


    /* private PlaceHolderView mDrawerView;
     private DrawerLayout mDrawer;
 */
    public static Activity ma;
    private SlidingUpPanelLayout mLayout;
    private static final String TAG = "DemoActivity";

    //added from yalantis context menu

    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;
   DownloadStateReceiver mDownloadStateReceiver = new DownloadStateReceiver();
    IntentFilter messagestatusIntentFilter;

    Intent mServiceIntent;


    //yalatis side menu

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private List<SlideMenuItem> list = new ArrayList<>();
    private ContentFragment contentFragment;
    private ViewAnimator viewAnimator;
    private int res = R.drawable.content_music;
    private LinearLayout linearLayout;
    RelativeLayout relMic;
    ImageButton btnSpeak;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainlist);

        fragmentManager = getSupportFragmentManager();
        //initToolbar();
        initMenuFragment();
        invalidateOptionsMenu();


      /*  mDrawer = (DrawerLayout)findViewById(R.id.drawerLayout);
        mDrawerView = (PlaceHolderView)findViewById(R.id.drawerView);
     */
        ma = this;
        mLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        relMic=(RelativeLayout)findViewById(R.id.relMic);
        btnSpeak=(ImageButton) findViewById(R.id.btnSpeak);

        mainPresInterface = new mainPresClass(this);

        studentPreference = getApplicationContext().getSharedPreferences(Constants.STUDENT_SELECTION_PREFERENCE, Context.MODE_PRIVATE);
        loginPreference = getApplicationContext().getSharedPreferences(Constants.LOGIN_PREFERENCE, Context.MODE_PRIVATE);

        txtNme = (TextView) findViewById(R.id.txtNme);

     //   relativeInfo = (LinearLayout) findViewById(R.id.linearChild1);
        locationLayout = (LinearLayout) findViewById(R.id.linearChild2);
        relativeMessage = (LinearLayout) findViewById(R.id.linearChild3);
        relativeAlert = (LinearLayout) findViewById(R.id.linearChild4);
        //relativeCourse =(LinearLayout)findViewById(R.id.linearChild5);
        relativeSchedule =(LinearLayout)findViewById(R.id.linearChild6);


       // imginfo = (ImageButton) findViewById(R.id.imginfo);
        imageloc = (ImageButton) findViewById(R.id.imageloc);
        imgmsg = (ImageButton) findViewById(R.id.imgmsg);
        imgalert = (ImageButton) findViewById(R.id.imgalert);

        Alarm alarm = new Alarm();

        //alarm.setAlarm(getApplicationContext());


        timer = new Timer();


       // createNotificationChannel();

        Methods.copyFile(getApplicationContext());


        onClickListners();

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            scheduleJobFirebaseToRoomDataUpdate();

        }

        else{

            startTimer();
        }

 // getWorkeManager();

        //setAlarm();



      //  startTimer();
        //starting job scheduler

       /* if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            scheduleJobFirebaseToRoomDataUpdate();
        }else{
            ffff
        }
*/

      //  getWorkeManager();

      //  Methods.copyFile(getApplicationContext());

       /* List<Person_det>stList= Methods.getDefaultStudent(getApplicationContext());
        Person_det person_det=new Person_det();
        Log.e("outgetDfltStudent ","outgetDfltStudent ");
        person_det=stList.get(0);

        String dbstudentId=person_det.getStudentId();
        String dbstudentname=person_det.getFirstName();*/


       //getting yalatis side menu details

        messagestatusIntentFilter=new IntentFilter(Constants.BROADCAST_ACTION_MSG);

        contentFragment = ContentFragment.newInstance(R.drawable.content_music);
       /* getSupportFragmentManager().beginTransaction()
                .replace(R.id.sliding_layout, contentFragment)
                .commit();
*/
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerLayout.setScrimColor(Color.TRANSPARENT);

        linearLayout = (LinearLayout) findViewById(R.id.left_drawer);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        setActionBar();
        createMenuList();
        viewAnimator = new ViewAnimator<>(this, list, contentFragment, drawerLayout, this);

//////////////////////////////ending yalantis menu here


        String studentId = studentPreference.getString("studentid", "");
        String studentname = studentPreference.getString("studentname", "");


        txtNme.setText(studentname);

        mainPresInterface.getStudentList(getApplicationContext());
        mainPresInterface.getAlertList(getApplicationContext(), studentname);

        //  setupDrawer(getApplicationContext());

        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

                //0.890121

                if(slideOffset>=0.890121){

                    mLayout.setAnchorPoint(0.890121f); // slide up 50% then stop
                    mLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);

               }

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {


            /*   if(newState==SlidingUpPanelLayout.PanelState.ANCHORED){
                   mLayout.setAnchorPoint(0.5f);
               }
*/
               /* if (mLayout != null) {
                    mLayout.setAnchorPoint(0.5f); // slide up 50% then stop
                    mLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
                }*/


            }
        });


        mLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });



/*

        if(isReadStorageAllowed()){
            //If permission is already having then showing the toast
            Toast.makeText(getApplicationContext(),"You already have the permission",Toast.LENGTH_LONG).show();
            showReminderOneDayBeforeEvent(getApplicationContext());
            //Existing the method with return
            return;
        }

        //If the app has not the permission then asking for the permission
        requestStoragePermission();
*/


    }


    @Override
    protected void onResume() {
        super.onResume();



        String studentId = studentPreference.getString("studentid", "");
        String studentname = studentPreference.getString("studentname", "");




    }

    @Override
    protected void onStart() {
        super.onStart();

        LocalBroadcastManager.getInstance(mainViewClass.this).registerReceiver(
                mDownloadStateReceiver,messagestatusIntentFilter
        );

        String studentId = studentPreference.getString("studentid", "");
        String studentname = studentPreference.getString("studentname", "");



    }

    @Override
    protected void onStop() {
        super.onStop();

        try {

            if (mDownloadStateReceiver != null) {
                unregisterReceiver(mDownloadStateReceiver);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

       /* if(!flag) //check if backbutton is not pressed
        {
            try{
                Intent intent = new Intent(getApplicationContext(), splashViewClass.class);

                Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);


                int notificationId = 1;
                String channelId = "channel-01";
                String channelName = "Channel Name";
                int importance = NotificationManager.IMPORTANCE_HIGH;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    NotificationChannel mChannel = new NotificationChannel(
                            channelId, channelName, importance);
                    notificationManager.createNotificationChannel(mChannel);
                }


                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                        .setSmallIcon(R.drawable.alert)
                        .setContentTitle("New notification")
                        .setSound(alarmSound)
                        .setAutoCancel(true)
                        .setContentText("You have a notification");

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                stackBuilder.addNextIntent(intent);
                PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
                mBuilder.setContentIntent(resultPendingIntent);

                notificationManager.notify(notificationId, mBuilder.build());
                flag = false; //reset you flag
            }catch(Exception e){}
        }*/
    }

    void onClickListners() {
        setSlidingPanel();


        locationLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mainViewClass.this, MapsActivity.class);

                // startActivity(intent);
                startActivity(intent);
               // overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });


        //should be repalced by course screeen

        relativeSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mainViewClass.this,scheduleViewClass.class);
                startActivity(intent);
            }
        });

relMic.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(mainViewClass.this, speechViewClass.class);
        // startActivity(intent);
        startActivity(intent);
      //  overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);

    }


});

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(mainViewClass.this, speechViewClass.class);

                startActivity(intent);


            }
        });


        relativeMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(mainViewClass.this, mailViewClass.class);
                // startActivity(intent);
                startActivity(intent);
               // overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });


        relativeAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mainViewClass.this, alertViewClassNew.class);
                // startActivity(intent);
                startActivity(intent);
              //  overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        imgalert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mainViewClass.this, alertViewClassNew.class);
                // startActivity(intent);
                startActivity(intent);
              //  overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });

        imgmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(mainViewClass.this, mailViewClass.class);
                // startActivity(intent);
                startActivity(intent);
               // overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });


        //should be repalced by course screeen


        imageloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainViewClass.this, MapsActivity.class);

                // startActivity(intent);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
            }
        });


    }

    void getTime() {

        DateFormat dateFormatter = new SimpleDateFormat("yyyyMMdd hhmmss");
        dateFormatter.setLenient(false);
        Date today = new Date();
        String s = dateFormatter.format(today);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    void convertTimeStamp() {

        String isodate = "2018-03-11T16:57:04Z";

        long timestamp = 0;

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        dateformat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {

            Date date = dateformat.parse(isodate);
            timestamp = date.getTime();

            // DateFormat sdf = new SimpleDateFormat("hh:mm:ss yyyy-MM-dd");

            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            Date netDat = new Date(timestamp);

            String finalDate = dateFormatter.format(netDat);


            StringTokenizer tk = new StringTokenizer(finalDate);

            String date1 = tk.nextToken();  // <---  yyyy-mm-dd
            String time = tk.nextToken();  // <---  hh:mm:ss


            String[] datearray = date1.split("-");
            String[] timearray = time.split(":");

            String year, month = "", dayofmonth, hour, minute = "", scond;

            if (datearray.length > 2) {
                year = datearray[0];
                month = datearray[1];
                dayofmonth = datearray[2];
            }

            if (timearray.length > 2) {
                hour = timearray[0];
                minute = timearray[1];
                scond = timearray[2];
            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    void preparingAlerts() {


        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2018);
        calendar.set(Calendar.MONTH, 03);
        calendar.set(Calendar.DAY_OF_MONTH, 15);

        calendar.set(Calendar.HOUR_OF_DAY, 03);
        calendar.set(Calendar.MINUTE, 45);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.AM_PM, Calendar.PM);

        Intent myIntent = new Intent(mainViewClass.this, MyReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(mainViewClass.this, 0, myIntent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);

    }


    private void scheduleNotification(Notification notification, int delay) {



        Intent notificationIntent = new Intent(this, MyReceiver.class);
        notificationIntent.putExtra(MyReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyReceiver.NOTIFICATION, notification);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar calendar = Calendar.getInstance();

        //  calendar.set(Calendar.YEAR,2018);
        calendar.set(Calendar.MONTH, 2); //month -1
        calendar.set(Calendar.DAY_OF_MONTH, 23);

        calendar.set(Calendar.HOUR_OF_DAY, 11);
        calendar.set(Calendar.MINUTE, 11);
        calendar.set(Calendar.SECOND, 00);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() - 5, pendingIntent);

        // alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);

    }


   /* private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Scheduled Notification");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.alert);
        return builder.build();
    }*/

    public void setSlidingPanel() {

              /*List<Person_det> personList1 = new  ArrayList<Person_det>();
              personList1=Methods.getStudentList(getApplicationContext());
*/



        String Fruits[] = {"Org1", "Org2", "Org3"};

        List<Person_det> personList = new ArrayList<Person_det>();

        for (int i = 0; i < Fruits.length; i++) {
            Person_det name = new Person_det();
            name.setFirstName(Fruits[i]);
            name.setStudentId(""+i);
            personList.add(name);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sliderecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getApplicationContext());
        MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(MyLayoutManager);

              /*AppDatabase appdb = AppDatabase.getAppDatabase(getApplicationContext());
              personList =appdb.person_dao().getPersonDetails();*/


    /*  SharedPreferences  studentPreference=getApplicationContext().getSharedPreferences(Constants.STUDENT_SELECTION_PREFERENCE, Context.MODE_PRIVATE);

      SharedPreferences.Editor editorstudent = studentPreference.edit();
      editorstudent.putString("studentid", "st0001");
      editorstudent.putString("studentname","mable");
      editorstudent.commit();
*/
        RecyclerView.Adapter adapter = new slidingPanelAdapter(personList, this);


        // slidingPanelAdapter=new slidingPanelAdapter(personList);
        recyclerView.setAdapter(adapter);
        // adapter.  notifyDataSetChanged();

    }


    void screenHeight() {

        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);

        float density = getResources().getDisplayMetrics().density;
        float dpHeight = outMetrics.heightPixels / density;
        float dpWidth = outMetrics.widthPixels / density;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
           /* case R.id.action_settings:
                return true;*/
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
/*
            default:
                return super.onOptionsItemSelected(item);*/
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setStudentList(final List<Person_det> person_detList) {

       // Log.e("personList1Size ","personList1Size "+person_detList.size());

try {
    runOnUiThread(new Runnable() {
        @Override
        public void run() {
            List<Person_det> personList = person_detList;

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sliderecyclerView);
            recyclerView.setHasFixedSize(true);
            LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getApplicationContext());
            MyLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setLayoutManager(MyLayoutManager);


            RecyclerView.Adapter adapter = new slidingPanelAdapter(personList, getApplicationContext());

            recyclerView.setAdapter(adapter);
        }
    });
}catch (Exception e){
    e.printStackTrace();
}

    }

    @Override
    public void setAlerts(final List<Schedule> alertList) {

        try {

            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    //  Log.e("alertlist"," alerts "+alertList.size());

                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sliderecyclerAlerts);
                    recyclerView.setHasFixedSize(true);
                    LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(MyLayoutManager);
                    RecyclerView.Adapter adapter = new mainScheduleListAdapter(getApplicationContext(), alertList);
                    recyclerView.setAdapter(adapter);

                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void logOut() {

        SharedPreferences.Editor editor = loginPreference.edit();
        editor.putBoolean("islogin", false);
        editor.commit();


        Intent intent = new Intent(mainViewClass.this, loginViewClass.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


    public void convertPrettyTime(){
        String dateString="2018-04-23 15:00:47";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-vdd kk:mm:ss");

        Date convertedDate = new Date();


        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        PrettyTime p  = new PrettyTime();

        String datetime= p.format(convertedDate);



    }



    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        menuParams.setAnimationDuration(1);

        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
       // mMenuDialogFragment.setItemLongClickListener(this);
    }

    private List<MenuObject> getMenuObjects() {
        // You can use any [resource, bitmap, drawable, color] as image:
        // item.setResource(...)
        // item.setBitmap(...)
        // item.setDrawable(...)
        // item.setColor(...)
        // You can set image ScaleType:
        // item.setScaleType(ScaleType.FIT_XY)
        // You can use any [resource, drawable, color] as background:
        // item.setBgResource(...)
        // item.setBgDrawable(...)
        // item.setBgColor(...)
        // You can use any [color] as text color:
        // item.setTextColor(...)
        // You can set any [color] as divider color:
        // item.setDividerColor(...)

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_close);

        MenuObject send = new MenuObject("Send message");
        send.setResource(R.drawable.icn_1);

        MenuObject like = new MenuObject("Upload Pending");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.ic_ref);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject("Add persons");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.icn_3));
        addFr.setDrawable(bd);

     /*   MenuObject addFav = new MenuObject("Add to favorites");
        addFav.setResource(R.drawable.icn_4);
*/
        MenuObject block = new MenuObject("Open source libs");
     //    block.setResource(R.drawable.icn_5);
        block.setResource(R.drawable.ic_lib);


        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(block);
        menuObjects.add(like);
        menuObjects.add(addFr);
     //   menuObjects.add(addFav);
        return menuObjects;
    }


    @Override
    public void onMenuItemClick(View view, int position) {
      //  Toast.makeText(this, "Clicked on position: " + position, Toast.LENGTH_SHORT).show();
        switch (position){
            case 1:
                Intent intent = new Intent(mainViewClass.this, mailViewClass.class);
                startActivity(intent);
               // overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
                break;

            case 2:
                Intent intent1 = new Intent(getApplicationContext(), OssAttribActivity.class);
                startActivity(intent1);
                break;
            case 3:

                mServiceIntent=new Intent(mainViewClass.this, backGroundServices.class);
                startService(mServiceIntent);
                break;

            case 4:

                String message1="20 second delay";
            //    Toast.makeText(getApplicationContext(),message1,Toast.LENGTH_SHORT).show();
              //  Methods.scheduleNotificationNew(Methods.getNotificationNew(message1,getApplicationContext()),getApplicationContext());
                break;


        }

    }

public void confirmLogout(){

// custom dialog
    final Dialog dialog = new Dialog(this);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.activity_logout);

    TextView textName = (TextView) dialog.findViewById(R.id.txtNme);
    ImageView image = (ImageView) dialog.findViewById(R.id.imgstudent);
    image.setImageResource(R.drawable.imgb);

    TextView btnLogout = (TextView) dialog.findViewById(R.id.btnLogout);
    TextView btnCancel = (TextView) dialog.findViewById(R.id.btnCancel);
    dialog.show();
    dialog.setCancelable(false);

    btnLogout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
           Methods.testLogOut(getApplicationContext(),mainViewClass.this);
            dialog.dismiss();
        }
    });

    btnCancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            dialog.dismiss();
        }
    });

}



    private void createMenuList() {
        SlideMenuItem menuItem0 = new SlideMenuItem(ContentFragment.CLOSE, R.drawable.icn_close);
        list.add(menuItem0);

        SlideMenuItem menuItem5 = new SlideMenuItem(ContentFragment.SHOP, R.drawable.ic_account);
        list.add(menuItem5);

        SlideMenuItem menuItem2 = new SlideMenuItem(ContentFragment.BOOK, R.drawable.ic_set);
        list.add(menuItem2);
     /*   SlideMenuItem menuItem3 = new SlideMenuItem(ContentFragment.PAINT, R.drawable.icn_3);
        list.add(menuItem3);*/

        SlideMenuItem menuItem = new SlideMenuItem(ContentFragment.LOGOUT, R.drawable.icn_logout);
        list.add(menuItem);

        SlideMenuItem menuItem4 = new SlideMenuItem(ContentFragment.CASE, R.drawable.icn_4);
        list.add(menuItem4);


    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
     //   mainViewClass.this.finish();
        flag = true; //set to true when you pressd back button
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
      //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


    private void setActionBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                drawerLayout,         /* DrawerLayout object */
                toolbar,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                linearLayout.removeAllViews();
                linearLayout.invalidate();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                if (slideOffset > 0.6 && linearLayout.getChildCount() == 0)
                    viewAnimator.showMenuContent();
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public ScreenShotable onSwitch(Resourceble slideMenuItem, ScreenShotable screenShotable, int position) {
        switch (slideMenuItem.getName()) {
            case ContentFragment.CLOSE:
                return screenShotable;

            case ContentFragment.LOGOUT:
               // Toast.makeText(getApplicationContext(),"hii",Toast.LENGTH_LONG).show();
                confirmLogout();
                return screenShotable;

            case ContentFragment.BOOK:
               // Toast.makeText(getApplicationContext(),"settings",Toast.LENGTH_LONG).show();
               // confirmLogout();

                Intent intent1 = new Intent(getApplicationContext(), SettingsClass.class);
                startActivity(intent1);

                return screenShotable;

            case ContentFragment.CASE:
               // Toast.makeText(getApplicationContext(),"hii",Toast.LENGTH_LONG).show();
                // confirmLogout();
                return screenShotable;

            case ContentFragment.SHOP:
              //  Toast.makeText(getApplicationContext(),"Infoclass",Toast.LENGTH_LONG).show();

                Intent intent2 = new Intent(getApplicationContext(), informationViewClass.class);
                startActivity(intent2);

                return screenShotable;


        }
        return null;
    }

    @Override
    public void disableHomeButton() {
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public void enableHomeButton() {

        getSupportActionBar().setHomeButtonEnabled(true);
        drawerLayout.closeDrawers();
    }

    @Override
    public void addViewToContainer(View view) {
        linearLayout.addView(view);
    }


    public  void showReminderOneDayBeforeEvent(Context context) {

        String eventTitle = "Ready Android"; //This is event title
        String eventDescription = "Always happy to help u :"; //This is event description
        String eventDate = "29/06/2018"; //This is the event date
        String eventLocation = "Taj Mahal, Agra, Uttar Pradesh 282001"; //This is the address ffor your event location

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date dEventDate = dateFormat.parse(eventDate); //Date is formatted to standard format “MM/dd/yyyy”
            cal.setTime(dEventDate);
            cal.add(Calendar.DATE, -1); //It will return one day before calendar of eventDate
        } catch (Exception e) {
            e.printStackTrace();
        }

        String reminderDate = dateFormat.format(cal.getTime());

        String reminderDayStart = reminderDate +  " 00:00:00";
        String reminderDayEnd = reminderDate + " 23:59:59";
        long startTimeInMilliseconds = 0, endTimeInMilliseconds = 0;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            Date SDate = formatter.parse(reminderDayStart);
            Date EDate = formatter.parse(reminderDayEnd);
            startTimeInMilliseconds = SDate.getTime();
            endTimeInMilliseconds = EDate.getTime();


        } catch (Exception e) {
            e.printStackTrace();
        }

        ContentResolver cr = context.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Events.CALENDAR_ID, 1);
        values.put(CalendarContract.Events.DTSTART, startTimeInMilliseconds);
        values.put(CalendarContract.Events.DTEND, endTimeInMilliseconds);
        values.put(CalendarContract.Events.TITLE, eventTitle);
        values.put(CalendarContract.Events.DESCRIPTION, eventDescription);
        values.put(CalendarContract.Events.EVENT_LOCATION, eventLocation);

        TimeZone timeZone = TimeZone.getDefault();
        values.put(CalendarContract.Events.EVENT_TIMEZONE, timeZone.getID());
        values.put(CalendarContract.Events.RRULE, "FREQ=HOURLY;COUNT=1");
        values.put(CalendarContract.Events.HAS_ALARM, 1);
        Uri eventUri;

        if (Build.VERSION.SDK_INT >= 8) {
            eventUri = Uri.parse("content://com.android.calendar/events");
        } else {
            eventUri = Uri.parse("content://calendar/events");
        }
// insert event to calendar
        Uri uri = cr.insert(eventUri, values);


//add reminder for event
//This reminder will be show for 12/11/2013, because event date is 13/11/2013
        try {
            Uri REMINDERS_URI;
            long id = -1;
            id = Long.parseLong(uri.getLastPathSegment());
            ContentValues reminders = new ContentValues();
            reminders.put(CalendarContract.Reminders.EVENT_ID, id);
            reminders.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
            reminders.put(CalendarContract.Reminders.MINUTES, 1);
            if (Build.VERSION.SDK_INT >= 8) {
                REMINDERS_URI = Uri.parse("content://com.android.calendar/reminders");
            } else {
                REMINDERS_URI = Uri.parse("content://calendar/reminders");
            }
            Uri remindersUri = context.getContentResolver().insert(REMINDERS_URI, reminders);
        } catch (Exception e) {
            e.printStackTrace();
        }



       /* Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, "29/06/2018 08:30:30");
        intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,"30/06/2018 08:30:30");
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        intent.putExtra(CalendarContract.Events.TITLE, "Neel Birthday");
        intent.putExtra(CalendarContract.Events.DESCRIPTION, "This is a sample description");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "My Guest House");
       // intent.putExtra(CalendarContract.Events.RRULE, "FREQ=YEARLY");
        startActivity(intent);*/


    }


    //We are calling this method to check the permission status
    private boolean isReadStorageAllowed() {
        //Getting the permission status
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
            return true;

        //If permission is not granted returning false
        return false;
    }

    //Requesting permission
    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CALENDAR)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALENDAR},READ_CALENDER_PERMISSION_CODE);
    }





    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == READ_CALENDER_PERMISSION_CODE){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }

    private class DownloadStateReceiver extends BroadcastReceiver{

        // Prevents instantiation
        private DownloadStateReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent.getAction().matches(Constants.BROADCAST_ACTION_MSG)) {

                int status=intent.getIntExtra(Constants.EXTENDED_DATA_STATUS_MSG,8);

                if(status ==Constants.NO_SERVICE){

                    toastMessage("No Pending Service");

                }else if(status ==Constants.SERVICE_UPDATED){

                    toastMessage("Service Updated");

                }

            }

        }
    }


    void toastMessage(String message){

        Snackbar snackbar = Snackbar
                .make(drawerLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();

    }


    public void startTimer() {
        //set a new Timer

        //initialize the TimerTask's job
        initializeTimerTask();
        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 1000, 5000); //

    }





    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                handler1.post(new Runnable() {
                    public void run() {

                        mServiceIntent=new Intent(mainViewClass.this, backGroundServices.class);
                        startService(mServiceIntent);
                    }

                });

            }

        };

    }


    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "channel_miskool";
            String description = "channel_desc";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("miskool_2018", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

/*
private void startingJob(){

    Driver driver = new GooglePlayDriver(getApplicationContext());
    FirebaseJobDispatcher firebaseJobDispatcher = new FirebaseJobDispatcher(driver);

    Job constraintReminderJob = firebaseJobDispatcher.newJobBuilder()
            .setService(ReminderService.class)
            .setTag("location_service")
            .setLifetime(Lifetime.FOREVER)
            .setRecurring(true)
            .setTrigger(Trigger.executionWindow(
                   1,
                    1
            ))
            .setReplaceCurrent(true)
            .build();

    firebaseJobDispatcher.schedule(constraintReminderJob);
    }
*/


    private void scheduleJobFirebaseToRoomDataUpdate(){
       /* JobScheduler jobScheduler = (JobScheduler)getApplicationContext()
                .getSystemService(JOB_SCHEDULER_SERVICE);

        ComponentName componentName = new ComponentName(this,
                ReminderService.class);

        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setRequiredNetworkType(
                        JobInfo.NETWORK_TYPE_ANY)
                .setPeriodic(31000)
                .setPersisted(true).build();
        jobScheduler.schedule(jobInfo);*/



        JobScheduler jobScheduler =
                (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        jobScheduler.schedule(new JobInfo.Builder(1,
                new ComponentName(this, ReminderService.class))
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .build());
    }


    private void getWorkeManager(){

        // Create a Constraints object that defines when the task should run
        Constraints myConstraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                // Many other constraints are available, see the
                // Constraints.Builder reference
                .build();



        PeriodicWorkRequest.Builder photoWorkBuilder = new PeriodicWorkRequest.Builder(MyWorker.class, 1,
                        TimeUnit.SECONDS).setPeriodStartTime(30,TimeUnit.SECONDS).addTag("location_service").setConstraints(myConstraints);
// ...if you want, you can apply constraints to the builder here...
// Create the actual work object:
        PeriodicWorkRequest myWork = photoWorkBuilder.build();

// Then enqueue the recurring task:
        WorkManager.getInstance().enqueue(myWork);
    }



}
