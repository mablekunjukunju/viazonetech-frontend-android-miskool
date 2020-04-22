package com.zone.android.miskool;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Inspiron on 12-10-2017.
 */

public class DirectionsURLClass {

  //  private static final LatLng ORIGIN = new LatLng(10.07825,76.45028);

    private static final LatLng MARBHN_CHURCH = new LatLng(10.07736,76.45017);
    private static final LatLng MARBHN_CHURCH1 = new LatLng(10.07724,76.44940);

    private static final LatLng MARBHN_CHURCH2 = new LatLng(10.07412,76.44983);
    private static final LatLng MARBHN_CHURCH3 = new LatLng(10.07464,76.45352);

    private static final LatLng MARBHN_CHURCH4 = new LatLng(10.07496,76.45504);

    private static final LatLng MARBHN_CHURCH5 = new LatLng(10.07553,76.45679);

    private static final LatLng MARBHN_CHURCH6 = new LatLng(10.07521,76.45887);

    private static final LatLng MARBHN_CHURCH7 = new LatLng(10.07498,76.46001);

    private static final LatLng MARBHN_CHURCH8 = new LatLng(10.07558,76.46079);

    private static final LatLng MARBHN_CHURCH9 = new LatLng(10.08152,76.46008);
    private static final LatLng MARBHN_CHURCH10 = new LatLng(10.08116,76.46258);


    public static final LatLng ORIGIN = new LatLng(10.07502,76.46005);

    //adding way points
    private static final LatLng MARBHN_CHURCH12 = new LatLng(10.07458,76.46445);
    private static final LatLng MARBHN_CHURCH13 = new LatLng(10.07929,76.46640);
    private static final LatLng MARBHN_CHURCH14 = new LatLng(10.08201,76.46608);
    private static final LatLng MARBHN_CHURCH15 = new LatLng(10.08495,76.46656);

    //adding destination
    private static final LatLng DESTINATION = new LatLng(10.09080, 76.46823);
    //&key=YOUR_API_KEY

    private static final String MAP_KEY = "AIzaSyCpMzQyQL5G-RgrhY6G6v9C0uEXVUoS84A";



    Context context;
   public DirectionsURLClass(Context context){
    this.context = context;
     }



    public static String getMapsApiDirectionsUrl() {
        String origin = "origin=" + ORIGIN.latitude + "," + ORIGIN.longitude;

        String waypoints = "waypoints=via:" + MARBHN_CHURCH12.latitude + "," + MARBHN_CHURCH12.longitude + "|via:"
                +MARBHN_CHURCH13.latitude + "," + MARBHN_CHURCH13.longitude + "|via:"
                +MARBHN_CHURCH14.latitude + "," + MARBHN_CHURCH14.longitude + "|via:"
                +MARBHN_CHURCH15.latitude + "," + MARBHN_CHURCH15.longitude ;


        String destination = "destination=" + DESTINATION.latitude + "," + DESTINATION.longitude;



        String params = origin + "&" + destination + "&"  + waypoints ;

        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/"
                + output + "?" + params;
        return url;
    }

    public String getDirectionsUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";
        String mode = "mode=driving";


        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;


        return url;
    }







    /** A method to download json data from url */
    public String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();urlConnection.setReadTimeout(15000 /* milliseconds */);
            urlConnection.setConnectTimeout(1500 /* milliseconds */);
            urlConnection.setDoInput(true);

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while( ( line = br.readLine()) != null){
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        }catch(Exception e){

        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
}
