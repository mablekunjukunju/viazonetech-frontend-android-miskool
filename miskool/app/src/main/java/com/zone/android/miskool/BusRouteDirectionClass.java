package com.zone.android.miskool;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.zone.android.miskool_Entitiy.Bus_Route;
import com.zone.android.miskool_db.AppDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Inspiron on 22-02-2018.
 */

public class BusRouteDirectionClass {

    public LatLng ORIGIN = new LatLng(10.07502,76.46005);

    //adding way points
    private  LatLng MARBHN_CHURCH12 = new LatLng(10.07458,76.46445);
    private  LatLng MARBHN_CHURCH13 = new LatLng(10.07929,76.46640);
    private  LatLng MARBHN_CHURCH14 = new LatLng(10.08201,76.46608);
    private  LatLng MARBHN_CHURCH15 = new LatLng(10.08495,76.46656);

    //adding destination
    private  LatLng DESTINATION = new LatLng(10.09080, 76.46823);

    //&key=YOUR_API_KEY

    private  String MAP_KEY = "AIzaSyDBGFOjUFioEzBA6GVnhU7zf0YJkMNsW0w";

    Context context;


    public BusRouteDirectionClass(Context context){
        this.context = context;
    }


    public String getMapsApiDirectionsUrl(final String StudentId) {

        //uncomment later


       /* ArrayList<LatLng> BustopPoints = new ArrayList<LatLng>();

        final List<Bus_Route> busstopsList=new ArrayList<>();
        final double orgin;
        double desination;
        String url;

        new Thread(new Runnable() {
            @Override
            public void run() {

                AppDatabase appdb = AppDatabase.getAppDatabase(context);
                List<Bus_Route> busstopsList1 = appdb.busRoute_dao().getBusStopsOfStudent(StudentId);

               for(int i=0;i<busstopsList1.size();i++){

                   Bus_Route bus_route=new Bus_Route();
                   bus_route=busstopsList1.get(i);
                   busstopsList.add(bus_route);
               }
            }
        }).start();

                double orginLat= Double.parseDouble(busstopsList.get(0).getStopLat().toString());
                double orginLon=Double.parseDouble(busstopsList.get(0).getStopLat().toString());


                String orginString= "origin="+orginLat+","+orginLon;


                 double destLat=Double.parseDouble(busstopsList.get(busstopsList.size()-1).getStopLat().toString());
                 double destLon=Double.parseDouble(busstopsList.get(busstopsList.size()-1).getStopLon().toString());

                 String destString="destination="+destLat+","+destLon;



                String waypoints = "waypoints=via:";
                for(int i=1;i<busstopsList.size()-1;i++){
                    Bus_Route bus_route=new Bus_Route();
                    bus_route=busstopsList.get(i);

                    double lat=Double.parseDouble(bus_route.getStopLat());
                    double lon=Double.parseDouble(bus_route.getStopLon());

                    if(i!=busstopsList.size()-2){
                        waypoints+= lat+ ","+lon+"|via:";
                    }else{

                        waypoints+= lat+ ","+lon;
                    }

                }

                String sensor = "sensor=false";
                String mapkey="key="+MAP_KEY;
                //String params = origin + "&" + waypoints + "&"  + destination + "&" + mapkey;

                String params = orginString + "&" + destString + "&"  + waypoints ;

                String output = "json";
                 url = "https://maps.googleapis.com/maps/api/directions/"
                        + output + "?" + params;
*/


        String origin = "origin=" + ORIGIN.latitude + "," + ORIGIN.longitude;



        String waypoints = "waypoints=via:" + MARBHN_CHURCH12.latitude + "," + MARBHN_CHURCH12.longitude + "|via:"
                +MARBHN_CHURCH13.latitude + "," + MARBHN_CHURCH13.longitude + "|via:"
                +MARBHN_CHURCH14.latitude + "," + MARBHN_CHURCH14.longitude + "|via:"
                +MARBHN_CHURCH15.latitude + "," + MARBHN_CHURCH15.longitude ;


        String destination = "destination=" + DESTINATION.latitude + "," + DESTINATION.longitude;

        String sensor = "sensor=false";
        String mapkey="key="+MAP_KEY;
        //String params = origin + "&" + waypoints + "&"  + destination + "&" + mapkey;

        String params = origin + "&" + destination + "&"  + waypoints ;

        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/"
                + output + "?" + params;
        return url;
    }



   /* public static ArrayList<LatLng> addBusStopMarkerPoints(final Context context, final String studenId){
        final ArrayList<LatLng> BustopPoints = new ArrayList<LatLng>();

       // BustopPoints.add(ORIGIN);
        //in actual scenario

       new Thread(new Runnable() {
           @Override
           public void run() {

               AppDatabase appdb = AppDatabase.getAppDatabase(context);
               List<Bus_Route> busstopsList = appdb.busRoute_dao().getRoutesOfStudent(studenId);
               for(int i=0;i<busstopsList.size();i++){

                  double Lat = Double.parseDouble(busstopsList.get(i).getStopLat());
                  double Long= Double.parseDouble(busstopsList.get(i).getStopLon());
                   BustopPoints.add(new LatLng(Lat,Long));
               }

           }

       }).start();


        return BustopPoints;
    }
*/


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
            Log.d("Exce", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
}
