package com.zone.android.miskool_View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Adapters.mailViewAdapter;
import com.zone.android.miskool_Adapters.outMessageAdapter;
import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Presenter.outMailPresClass;
import com.zone.android.miskool_Presenter.outMailPresInterface;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.OnItemClickListener;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Inspiron on 26-03-2018.
 */

public class ouMailViewClass extends AppCompatActivity implements outMailViewInterface {
    outMailPresInterface outMailPresInterface;
    SharedPreferences studentPreference;
    String StudenId;
    public static ProgressDialog progressBar;
    CoordinatorLayout coordinatorlayout;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

      /*  actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.homenew);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Inbox");*/

        coordinatorlayout=(CoordinatorLayout)findViewById(R.id.coordinatorlayout);
        getSupportActionBar().setTitle("Outbox");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        studentPreference = getApplicationContext().getSharedPreferences(Constants.STUDENT_SELECTION_PREFERENCE, Context.MODE_PRIVATE);
        StudenId = studentPreference.getString("studentid", "");

        String studentname= studentPreference.getString("studentname", "");


        outMailPresInterface = new outMailPresClass(this);


    }


    @Override
    protected void onStart() {
        super.onStart();
        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Downloading messages");
        progressBar.setIndeterminate(true);

        progressBar.show();

        String studentname= studentPreference.getString("studentname", "");
        outMailPresInterface.deleteTablesMessage(studentname, getApplicationContext());
    }

    @Override
    public void setMessagesServer(int errorCode) {

        if(errorCode==Constants.PASS_SERVICE){

            String studentname= studentPreference.getString("studentname", "");
            outMailPresInterface.getMessages(studentname, getApplicationContext());
        }
        else if(errorCode==Constants.NO_MESSAGE){
            Snackbar snackbar = Snackbar
                    .make(coordinatorlayout, "no sent messages", Snackbar.LENGTH_LONG);
            snackbar.show();
            progressBar.dismiss();
        }
    }

    @Override
    public void deleteTablesMessageRes(int errorCode) {


        String studentname= studentPreference.getString("studentname", "");
        outMailPresInterface.getMessageServer(studentname, getApplicationContext());

    }

    @Override
    public void setMessages(final List<Message_In> messageList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {



                RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                recyclerView.setHasFixedSize(true);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);


                //  RecyclerView.Adapter adapter = new mailViewAdapter(messageList,getApplicationContext());

               /* RecyclerView.Adapter adapter = new outMessageAdapter(messageList, getApplicationContext());
                recyclerView.setAdapter(adapter);
                */
                recyclerView.setAdapter(new outMessageAdapter(messageList, getApplicationContext(), new OnItemClickListener() {
                    @Override
                    public void onItemClick(Message_In item) {
                      //  Toast.makeText(getApplicationContext(), "Item Clicked", Toast.LENGTH_LONG).show();


                        showreplyDialogue(item);
                    }
                }));


            }
        });

    }

    @Override
    public void showMessage(String Message) {

    }


    void showreplyDialogue(Message_In item){

        final Dialog dialog = new Dialog(ouMailViewClass.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_customdialog);
       // dialog.setTitle("Title...");



        TextView customName,tv_date1,customFeedback,tv_recip;

        Button buton_OK;

        customName=(TextView)dialog.findViewById(R.id.customName);
        tv_date1=(TextView)dialog.findViewById(R.id.tv_date1);
        customFeedback=(TextView)dialog.findViewById(R.id.customFeedback);
        buton_OK=(Button)dialog.findViewById(R.id.buton_OK);
        tv_recip=(TextView)dialog.findViewById(R.id.tv_recip);


        String date=item.getMessageDateOfCreated();
        tv_date1.setText(""+convertPrettyTime(item.getMessageDateOfCreated()));
        customFeedback.setText(item.getMessages());
        customName.setText(item.getMessageSub());
        tv_recip.setText(item.getMessageReceiver());


        dialog.show();
        buton_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();


            }
        });

    }


    public String convertPrettyTime(String date){

        // String dateString="2018-04-23 15:00:47";

        String datetime="";

        String dateString=date;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        java.util.Date convertedDate = new java.util.Date();


        try {
            convertedDate = dateFormat.parse(dateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        PrettyTime p  = new PrettyTime();

        datetime= p.format(convertedDate);


        //dd MMMM yyyy, hh:mm:ss.SSS a

        // String messagedatetime=(String)android.text.format.DateFormat.format("dd MM yyyy hh:mm:ss a", convertedDate);



        String messagedatetime=(String)android.text.format.DateFormat.format("dd MM yyyy", convertedDate);

        String currentmessagedatetime=(String)android.text.format.DateFormat.format("dd MM yyyy", new Date());

        if(messagedatetime.equals(currentmessagedatetime)){
            datetime= (String)android.text.format.DateFormat.format("hh:mm a", convertedDate);
        }else{

            datetime= (String)android.text.format.DateFormat.format("MMM dd", convertedDate);

        }

        String testdate=(String)android.text.format.DateFormat.format("MMM dd", convertedDate);


        return datetime;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:

                ouMailViewClass.this.finish();
                return true;


        }
        return super.onOptionsItemSelected(item);
    }
}