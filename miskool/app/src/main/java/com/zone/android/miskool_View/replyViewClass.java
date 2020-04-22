package com.zone.android.miskool_View;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zone.android.miskool.R;
import com.zone.android.miskool_Adapters.mailViewAdapter;
import com.zone.android.miskool_Adapters.replyViewAdapter;
import com.zone.android.miskool_Entitiy.Message;
import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Message_Out;
import com.zone.android.miskool_Presenter.replyPresClass;
import com.zone.android.miskool_Presenter.replyPresInterface;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.Methods;

import java.util.List;

/**
 * Created by Inspiron on 04-04-2018.
 */

public class replyViewClass extends AppCompatActivity implements replyViewInterface{

    replyPresInterface replyPresInterface;
    SharedPreferences studentPreference;
    RelativeLayout coordinatorlayout;
    public static ProgressDialog progressBar;
    SharedPreferences masterPreference;

    RecyclerView recyclerView;
    ImageView img_send;
    EditText ed_replycontent;
     String student_id,thread_id,messagein_id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_replyview);
        replyPresInterface=new replyPresClass(this);
        studentPreference = getApplicationContext().getSharedPreferences(Constants.STUDENT_SELECTION_PREFERENCE, Context.MODE_PRIVATE);

        masterPreference= getApplicationContext().getSharedPreferences(Constants.MASTER_USER_PREFERENCE,Context.MODE_PRIVATE);

        coordinatorlayout=(RelativeLayout)findViewById(R.id.coordinatorlayoutnew);

        //{ token: _token, touser: _username2, subject: _subject, text: _text, thread_id: _message_id })
        Bundle bundle = getIntent().getExtras();
         student_id = bundle.getString("student_id");
        thread_id = bundle.getString("thread_id");
        messagein_id=bundle.getString("messagein_id");
        final String message_sub=bundle.getString("subject");
       final String touser=bundle.getString("touser");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

      /*  actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.mipmap.homenew);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Inbox");*/

        getSupportActionBar().setTitle(message_sub);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // replyPresInterface.getMessages(student_id,thread_id,getApplicationContext());

        //messagein_id

        img_send=(ImageView)findViewById(R.id.img_send);
        ed_replycontent=(EditText)findViewById(R.id.ed_replycontent);


       //chechking thread_id

        try{
            if(thread_id.equals("")||thread_id.equals(null)||thread_id.equals(" ")||thread_id.equals("null")){

                replyPresInterface.getMessages(student_id,messagein_id,getApplicationContext());
            }else{

                replyPresInterface.getMessages(student_id,thread_id,getApplicationContext());
            }
        }catch (Exception e){
            e.printStackTrace();
            replyPresInterface.getMessages(student_id,messagein_id,getApplicationContext());

        }





        String mastername = masterPreference.getString("mastername", "");
        String studentname1= studentPreference.getString("studentname", "");


        if(!studentname1.equals(mastername)){
            ed_replycontent.setEnabled(false);
        }else{
            ed_replycontent.setEnabled(true);
        }


            img_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String mastername = masterPreference.getString("mastername", "");
                String studentname1= studentPreference.getString("studentname", "");


                if(studentname1.equals(mastername)){



                String message=ed_replycontent.getText().toString();

                if(SubcheckValidation(message)){

                    Message_In message_in = new Message_In();
                    message_in.setStudentId(student_id);
                   // message_in.setMessageInId(messagein_id);
                    message_in.setMessageSub(message_sub);
                    message_in.setMessages(message);
                    message_in.setThreadId(messagein_id);
                    message_in.setMessageReceiver(touser);

                //    Toast.makeText(getApplicationContext(),"message sent",Toast.LENGTH_SHORT).show();
                    progressBar = new ProgressDialog(replyViewClass.this);
                    progressBar.setCancelable(true);//you can cancel it by pressing back button
                    progressBar.setMessage("Sending message");
                    progressBar.setIndeterminate(true);

                    progressBar.show();
                    sendReply(message_in);
                    ed_replycontent.setText("");

                }

                }else{


                    Snackbar snackbar = Snackbar
                            .make(coordinatorlayout, "You are not allowed to send message", Snackbar.LENGTH_LONG);
                    snackbar.show();

                }

            }


        });
    }

    @Override
    public void setMessages(final List<Message_In> messageList) {

      runOnUiThread(new Runnable() {
          @Override
          public void run() {

              //need to put try catch

              try {


                  recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                  recyclerView.setHasFixedSize(true);
                  //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

                  LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                  recyclerView.setLayoutManager(layoutManager);

                  RecyclerView.Adapter adapter = new replyViewAdapter(messageList, getApplicationContext());
                  recyclerView.setAdapter(adapter);

                  layoutManager.scrollToPositionWithOffset(recyclerView.getAdapter().getItemCount() - 1,0);
                  layoutManager.setStackFromEnd(true);

               //   adapter.notifyDataSetChanged();


              }catch (Exception e){
                  e.printStackTrace();
                  //Toast.makeText(" "+e.printStackTrace();,getApplicationContext())
              }

          }
      });

    }

    public boolean SubcheckValidation(String content){
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

    @Override
    public void showMessage(int error) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

        progressBar.dismiss();


       if(error==Constants.PASS_SERVICE){
           Snackbar snackbar = Snackbar
                   .make(coordinatorlayout, "message sent", Snackbar.LENGTH_LONG);
           snackbar.show();
           try{
               if(thread_id.equals("")||thread_id.equals(null)||thread_id.equals(" ")||thread_id.equals("null")){

                   replyPresInterface.getMessages(student_id,messagein_id,getApplicationContext());
               }else{

                   replyPresInterface.getMessages(student_id,thread_id,getApplicationContext());
               }
           }catch (Exception e){
               e.printStackTrace();
               replyPresInterface.getMessages(student_id,messagein_id,getApplicationContext());

           }

           Methods.updatereadFlagOnrepley(messagein_id, getApplicationContext(), student_id);


       }
       else{

           Snackbar snackbar = Snackbar
                   .make(coordinatorlayout, "message not sent", Snackbar.LENGTH_LONG);
           snackbar.show();
       }


            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                //   mDrawerLayout.openDrawer(GravityCompat.START);

                replyViewClass.this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);

    }

    public void sendReply(Message_In message_in){

        replyPresInterface.sentReply(message_in,getApplicationContext());
    }
}
