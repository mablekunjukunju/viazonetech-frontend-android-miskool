package com.zone.android.miskool_View;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.zone.android.miskool.Manifest;
import com.zone.android.miskool.MapsActivity;
import com.zone.android.miskool.R;
import com.zone.android.miskool_Adapters.replyViewAdapter;
import com.zone.android.miskool_Adapters.speechViewAdapter;
import com.zone.android.miskool_Entitiy.Message_In;
import com.zone.android.miskool_Entitiy.Speech;
import com.zone.android.miskool_Presenter.speechPresClass;
import com.zone.android.miskool_Presenter.speechPresInterface;
import com.zone.android.miskool_Util.Constants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.RECORD_AUDIO;

public class speechViewClass extends AppCompatActivity implements speechViewInterface {

    ImageButton img_send;
    EditText ed_replycontent;
    ImageView img_click;

    public static ProgressDialog progressBar;

    SharedPreferences studentPreference;
    speechPresInterface speechPresInterface;

    private final int REQ_CODE_SPEECH_INPUT = 100;

    private static final int PERMISSIONS_REQUEST_ACCESS_RECORD_AUDIO = 1;

    RecyclerView recyclerView;

    boolean isMicOn;

List<Speech> speechList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ask Mimi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        speechPresInterface= new speechPresClass(this);

        img_send=(ImageButton)findViewById(R.id.img_send);
        ed_replycontent=(EditText)findViewById(R.id.ed_replycontent);

        studentPreference = getApplicationContext().getSharedPreferences(Constants.STUDENT_SELECTION_PREFERENCE, Context.MODE_PRIVATE);


        isMicOn=false;
        img_click=(ImageView)findViewById(R.id.img_click);
        checkPermission();

        final SpeechRecognizer mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);


        final Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                Locale.getDefault());


        mSpeechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle bundle) {
                //getting all the matches
                ArrayList<String> matches = bundle
                        .getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

                //displaying the first match
                if (matches != null)
                    ed_replycontent.setText(matches.get(0));



                sendReply();

            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }
        });


        //sending

        img_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            sendReply();

            }
        });


        findViewById(R.id.img_send).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_UP:
                        mSpeechRecognizer.stopListening();

                        ed_replycontent.setHint("You will see input here");

                        break;

                    case MotionEvent.ACTION_DOWN:
                        mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
                        ed_replycontent.setText("");

                        isMicOn=true;
                        ed_replycontent.setHint("Listening...");
                        break;
                }
                return false;
            }
        });

        ed_replycontent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if(s.length() != 0) {

                        img_click.setVisibility(View.VISIBLE);

            }
            else{
                    img_click.setVisibility(View.INVISIBLE);
                }


            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        speechPresInterface.deleteTable(getApplicationContext());
    }

    @Override
    public void setMessages(List<Speech> speechList) {


       runOnUiThread(new Runnable() {
           @Override
           public void run() {

               try{

                   if(progressBar.isShowing()){
                       progressBar.dismiss();
                   }



                   recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                   recyclerView.setHasFixedSize(true);
                   //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

                   LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                   recyclerView.setLayoutManager(layoutManager);

                   RecyclerView.Adapter adapter = new speechViewAdapter(speechList, getApplicationContext());
                   recyclerView.setAdapter(adapter);

                   layoutManager.scrollToPositionWithOffset(recyclerView.getAdapter().getItemCount() - 1,0);

               }catch (Exception e){
                   e.printStackTrace();
               }
           }
       });
    }

    @Override
    public void sentMessage(int error) {

        String studentname= studentPreference.getString("studentname", "");
        if(error==Constants.PASS_VALIDATION ){
            speechPresInterface.getMessages(studentname,getApplicationContext());
        }else if(error==Constants.PASS_SERVICE){


           runOnUiThread(new Runnable() {
               @Override
               public void run() {

                   ed_replycontent.setText("");
                   progressBar.dismiss();
               }
           });

            speechPresInterface.getMessages(studentname,getApplicationContext());


        }


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



    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    RECORD_AUDIO)
                    == PackageManager.PERMISSION_GRANTED) {

            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{RECORD_AUDIO},
                        PERMISSIONS_REQUEST_ACCESS_RECORD_AUDIO);
            }


        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_RECORD_AUDIO: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                else{
                    Toast.makeText(speechViewClass.this,"Permission Denied",Toast.LENGTH_LONG).show();
                }
            }
        }
        //  updateLocationUI();
    }

    public void sendReply(Speech speech){

        speechPresInterface.sentReply(speech,getApplicationContext());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                //   mDrawerLayout.openDrawer(GravityCompat.START);

                speechViewClass.this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);

    }

  private void sendReply(){


        String studentname= studentPreference.getString("studentname", "");

        String message=ed_replycontent.getText().toString();

        if(SubcheckValidation(message)){

            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String strDate = sdf.format(c.getTime());



            Speech speech = new Speech();
            speech.setStudentId(studentname);
            // message_in.setMessageInId(messagein_id);
            speech.setMessages(message);
            speech.setMessageDate(strDate);
            speech.setMessageReceiver("ai");
            speech.setMessageSender(studentname);

            //    Toast.makeText(getApplicationContext(),"message sent",Toast.LENGTH_SHORT).show();
            progressBar = new ProgressDialog(speechViewClass.this);
            progressBar.setCancelable(true);//you can cancel it by pressing back button
            progressBar.setMessage("Sending message");
            progressBar.setIndeterminate(true);

            progressBar.show();

            sendReply(speech);
           // ed_replycontent.setText("");
            img_click.setVisibility(View.INVISIBLE);

        }
    }
}
