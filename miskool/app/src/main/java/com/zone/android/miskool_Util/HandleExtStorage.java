package com.zone.android.miskool_Util;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Inspiron on 11-10-2017.
 */

public class HandleExtStorage {
    String filename = "LatLong";
    Activity activity;

    public HandleExtStorage(Activity activity){
       this.activity=activity;
    }

    public void WriteTointernalstorage(Context context, String LatLong){

        FileOutputStream outputStream;

        try {
            outputStream = activity.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(LatLong.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void WritetoExternalSorage(Context context, String LatLong) throws IOException {

        Log.e("LatLong","Latlong"+LatLong);


        File root = Environment.getExternalStorageDirectory();
        File outDir = new File( root.getAbsolutePath() + File.separator + "MiSkool" );
        if ( !outDir.isDirectory() )
        {
            outDir.mkdir();
        }

        File out;
        OutputStreamWriter outStreamWriter = null;
        FileOutputStream outStream = null;

        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy" );
        SimpleDateFormat TimeFormat = new SimpleDateFormat( "HH:mm" );
        Calendar cal = Calendar.getInstance();
        String Currentdate = sdf.format( cal.getTime() );
        String CurrentTime = TimeFormat.format( cal.getTime() );

        out = new File( new File( outDir.getAbsolutePath() ), "LatLong" + "_" + Currentdate );

        if ( out.exists() == false )
        {
            out.createNewFile();
        }

        outStream = new FileOutputStream( out, true );
        outStreamWriter = new OutputStreamWriter( outStream );

        outStreamWriter.append( "\n\n" + CurrentTime + " : " + LatLong );
        outStreamWriter.flush();
        outStreamWriter.close();
        outStream.close();

    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
}
