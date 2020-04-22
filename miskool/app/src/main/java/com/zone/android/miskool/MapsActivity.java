package com.zone.android.miskool;

import android.*;
import android.Manifest;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.SquareCap;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.zone.android.miskool_Entitiy.RoutePoint;
import com.zone.android.miskool_Model.mapModelClass;
import com.zone.android.miskool_Model.mapModelInterface;
import com.zone.android.miskool_Services.BusTrackerService;
import com.zone.android.miskool_Util.AppSingleton;
import com.zone.android.miskool_Util.CallWebservice;
import com.zone.android.miskool_Util.Constants;
import com.zone.android.miskool_Util.HandleExtStorage;
import com.zone.android.miskool_Util.ServiceCallback;
import com.zone.android.miskool_View.mailViewClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.google.android.gms.maps.model.JointType.ROUND;
import static com.zone.android.miskool_Util.Constants.BROADCAST_ACTION;




public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback,mapViewInterface {

    private GoogleMap mMap;
    // The entry points to the Places API.
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;

    private static final String TAG = MapsActivity.class.getSimpleName();
    DirectionsURLClass directionsURLClass;
    public static ProgressDialog progressBar;

    RelativeLayout relativeOnee;
    private Menu menu;
    Geocoder geocoder;
    List<Address> addresses;

    List<RoutePoint> routeListStatis;

    ImageView buttonvehicleInfo;
    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient mFusedLocationProviderClient;

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;

    private Location mLastKnownLocation;
    private CameraPosition mCameraPosition;
    TextView txtnowreachedVal,txtnowreached;

    ProgressBar progressDialog;

    private List<LatLng> polyLineList;
    private PolylineOptions polylineOptions, blackPolylineOptions;
    private Polyline blackPolyline, greyPolyLine;

    private int index, next;
    private LatLng startPosition, endPosition;
    private float v;
    private double lat, lng;

    boolean reDrawPoly;
    double orginLat,originLon,desLat,desLon,curLat,curLon,prevLat,prevLon;
    String dir,timestamp;

    Runnable runnable;
    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    // A default location (Sydney, Australia) and default zoom to use when location permission is
    // not granted.
    private final LatLng mDefaultLocation = new LatLng(-33.8523341, 151.2106085);
    private static final int DEFAULT_ZOOM = 15;
    public static final int RequestPermissionCode = 1;

    //initializing request object
    LocationRequest mLocationRequest = new LocationRequest();
    protected Boolean mRequestingLocationUpdates;

    private LocationCallback mLocationCallback;
    private static final String c = "android.location.PROVIDERS_CHANGED";


    //REQUEST_CHECK_SETTINGS
    private static final int REQUEST_CHECK_SETTINGS = 2;
    Marker marker_device, marker_routes,marker_bus,marker_next,marker_previous,marker_default;

    BusRouteDirectionClass busdirectionsURLClass = new BusRouteDirectionClass(this);
    ArrayList<LatLng> markerPoints;

    IntentFilter statusIntentFilter,locationchangeFilter;

    // Instantiates a new DownloadStateReceiver
    DownloadStateReceiver mDownloadStateReceiver = new DownloadStateReceiver();

    //intiating intent request

   Intent mServiceIntent;

     SharedPreferences locationPreferences;
    Toolbar toolbar;
    ActionBar actionBar;

    SharedPreferences studentPreference,currentLocPreference ;
    String StudenId, StudentName;
    HandleExtStorage handlestorage ;

    Timer timer;
    TimerTask timerTask;

    final Handler handler = new Handler();

    static CallWebservice Callweb =        new CallWebservice();
    LinearLayout layout;

    private RadioGroup radioGroup;

    boolean isBusMode,isDevMode;

    mapModelInterface mapModelInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        setContentView(R.layout.activity_maptest);

        mapModelInterface=new mapModelClass();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        //setSupportActionBar(toolbar);


        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Tracking");
        txtnowreachedVal=(TextView)findViewById(R.id.txtnowreachedVal);

        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);


        // Construct a GeoDataClient.
        mGeoDataClient = Places.getGeoDataClient(this, null);
        progressDialog=new ProgressBar(getApplicationContext());

        // Construct a PlaceDetectionClient.
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);

        // Construct a FusedLocationProviderClient.
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        mRequestingLocationUpdates = false;
        markerPoints=new ArrayList<LatLng>();

     //   buttonLiveTracking=(Button)findViewById(R.id.  buttonLiveTracking) ;
        buttonvehicleInfo=(ImageView)findViewById(R.id.buttonvehicleInfo);

        txtnowreached=(TextView)findViewById(R.id.txtnowreached);

        layout= (LinearLayout) findViewById(R.id.linlaHeaderProgress);
        relativeOnee=(RelativeLayout)findViewById(R.id.relativeOnee);

        geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());


        //instantiatigng intent filter
        statusIntentFilter = new IntentFilter(Constants.BROADCAST_ACTION_BUS);

        locationchangeFilter = new IntentFilter(BROADCAST_ACTION);
    //    onRoute=false;


        handlestorage = new HandleExtStorage(this);
        directionsURLClass=new DirectionsURLClass(getApplicationContext());

        onClicks();



        isDevMode=true;

        studentPreference = getApplicationContext().getSharedPreferences(Constants.STUDENT_SELECTION_PREFERENCE,Context.MODE_PRIVATE);

        locationPreferences =getApplicationContext().getSharedPreferences(Constants.BUS_LOCATION_PREFERENCE,0);

        currentLocPreference=getApplicationContext().getSharedPreferences(Constants.CURR_BUS_LOCATION,Context.MODE_PRIVATE);



        StudenId = studentPreference.getString("studentid","default student from db");
        StudentName=studentPreference.getString("studentname", "");


        progressBar = new ProgressDialog(this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("Downloading location details");
        progressBar.setIndeterminate(true);

        progressBar.show();
        mapModelInterface.getRouteList(StudentName,getApplicationContext(),this);



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapbus);
        mapFragment.getMapAsync(this);


        getLocationPermission();
        getmLocationCallback();

       /* mServiceIntent=new Intent(MapsActivity.this, BusTrackerService.class);
        startService(mServiceIntent);


*/
        SharedPreferences.Editor editorloc = locationPreferences.edit();
        editorloc.putBoolean("firstTime",true);
        editorloc.commit();


    //   startTimer();
       // updateMenuTitles(StudentName);
        invalidateOptionsMenu();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = (RadioButton) radioGroup.findViewById(checkedId);

              //  if (null != rb && checkedId >1){


                    if(checkedId==R.id.radioDev){
                        isDevMode=true;
                        isBusMode=false;


                        getDeviceLocation(mLastKnownLocation);

                    }else if(checkedId==R.id.radioBus){
                        //get route location
                        isDevMode=false;
                        isBusMode=true;


                        draBusLocation();
                    }


              //  }

            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }



        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);



      //  updateLocationUI();
        getLastKnownLocation();
        createLocationRequest();

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if (mMap != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onResume() {
        super.onResume();

      //  registerReceiver(gpsLocationReceiver, new IntentFilter(BROADCAST_ACTION));//Register broadcast receiver to check the status of GPS

       /* LocalBroadcastManager.getInstance(MapsActivity.this).registerReceiver(
                mDownloadStateReceiver,
                locationchangeFilter);

        LocalBroadcastManager.getInstance(MapsActivity.this).registerReceiver(
                mDownloadStateReceiver,
                statusIntentFilter);
*/

        registerReceiver(mDownloadStateReceiver, statusIntentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
      //  timer.purge();
      //  timer.cancel();
       stopTimer();

        try {

            if (mDownloadStateReceiver != null) {
                unregisterReceiver(mDownloadStateReceiver);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        Log.e("StudentName","StudentName" +StudentName);
        menu.findItem(R.id.miAccount).setTitle(StudentName);
        return super.onPrepareOptionsMenu(menu);
    }

    private void getLastKnownLocation() {

        Log.e("locationpermission","locationpermission");

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationProviderClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.

                        if (location != null) {
                          mLastKnownLocation =location;

                            Log.e("Latlonglastknown "," Latlonglastknown "+ location.getLatitude()+ " "+location.getLongitude());

                            getDeviceLocation(location);

                          //  drawBusRoute();

                           // getBusLocation(location);

                        }
                    }
                });

      }



    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Log.e("grantresults "," grantResults "+grantResults);
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                  //  updateLocationUI();
                    getLastKnownLocation();
                    createLocationRequest();
                }
                else{
                    Toast.makeText(MapsActivity.this,"Permission Denied",Toast.LENGTH_LONG).show();
                }
            }
        }
      //  updateLocationUI();
    }


    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

//adding locationrequest client

    protected void createLocationRequest(){

        try {

            mLocationRequest.setInterval(10000);
            mLocationRequest.setFastestInterval(5000);
            mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(mLocationRequest);

            //this line is initializing thelocayionrequest object and checking if the client
            //enable location settigs on..

            SettingsClient client = LocationServices.getSettingsClient(this);
            Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());

            task.addOnSuccessListener(this, new OnSuccessListener<LocationSettingsResponse>() {
                @Override
                public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

                    // All
                    //
                    // settings are satisfied. The client can initialize
                    // location requests here.
                    // ...

                    mRequestingLocationUpdates = true;

                    startLocationUpdates();

                    //drawing static bus route

                  //  drawBusRoute();


                }
            });


            task.addOnFailureListener(this, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {


                    int statusCode = ((ApiException) e).getStatusCode();
                    Log.e("statuscode","statuscode "+statusCode);


                    switch (statusCode) {
                        case CommonStatusCodes.RESOLUTION_REQUIRED:
                            // Location settings are not satisfied, but this can be fixed
                            // by showing the user a dialog.
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                ResolvableApiException resolvable = (ResolvableApiException) e;
                                resolvable.startResolutionForResult(MapsActivity.this,
                                        REQUEST_CHECK_SETTINGS);
                            } catch (IntentSender.SendIntentException sendEx) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            // Location settings are not satisfied. However, we have no way
                            // to fix the settings so we won't show the dialog.
                            break;


                    }
                }
            });

        }catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("onActivityResult() ", Integer.toString(resultCode));
        Log.e("onActivityResult() ", Integer.toString(resultCode) + " " + Integer.toString(requestCode));

        switch (requestCode) {

            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case RESULT_OK: {
                        Log.e("onActivitRESULT_OK()", Integer.toString(resultCode));
                        // All required changes were successfully made
                        Toast.makeText(MapsActivity.this, "Location enabled by user!", Toast.LENGTH_LONG).show();

                        mRequestingLocationUpdates = true;

                        startLocationUpdates();

                        break;
                    }
                    case RESULT_CANCELED: {
                        // The user was asked to change settings, but chose not to
                        Toast.makeText(MapsActivity.this, "Location not enabled, user cancelled.", Toast.LENGTH_LONG).show();
                        break;
                    }
                    default: {
                        break;
                    }
                }
                break;

        }
    }

    private void getmLocationCallback(){

        Log.e("mcallback","mcallbck");
        ////fgfgfg

            mLocationCallback = new LocationCallback() {

                @Override
                public void onLocationResult(LocationResult locationResult) {
                    super.onLocationResult(locationResult);

                    for (Location location : locationResult.getLocations()) {

                        double currentLatitude = location.getLatitude();
                        double currentLongitude = location.getLongitude();

           Log.e("LatlongCallback "," LatlongCallback "+ "L");

                        if (location != null) {
                            mLastKnownLocation=location;

                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();

                            getDeviceLocation(location);
                           // getBusLocation(location);

                        } else {

                            double latitude = mLastKnownLocation.getLatitude();
                            double longitude = mLastKnownLocation.getLongitude();

                           getDeviceLocation(mLastKnownLocation);
                         //  getBusLocation(mLastKnownLocation);
                        }


                    }

                }
            };


        ///////////////////

    }

    private BroadcastReceiver gpsLocationReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            //If Action is Location
            if (intent.getAction().matches(BROADCAST_ACTION)) {
                LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                //Check if GPS is turned ON or OFF
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    Log.e("About GPS", "GPS is Enabled in your device");
                    //updateGPSStatus("GPS is Enabled in your device");

                } else {
                    //If GPS turned OFF show Location Dialog
                    //    new Handler().postDelayed(sendUpdatesToUI, 10);
                    // showSettingDialog();
                    //  updateGPSStatus("GPS is Disabled in your device");
                    Log.e("GPSStatus", "onReceivecreateLocationReques");
                    createLocationRequest();
                    Log.e("About GPS", "GPS is Disabled in your device");
                }

            }

        }
    };

    private void draBusLocation(){

        Log.e("draBusLocation ", "draBusLocation");

        final SharedPreferences currLocationPreference = getApplicationContext().getSharedPreferences(Constants.CURR_BUS_LOCATION,Context.MODE_PRIVATE);

        if(mLocationPermissionGranted){

           double deviceLat= Double.parseDouble(currLocationPreference.getString("current_lat",""));
           double deviceLong = Double.parseDouble(currLocationPreference.getString("current_long",""));

           /* double deviceLat= Double.parseDouble(routeListStatis.get(0).getRouteLat().toString());
            double deviceLong = Double.parseDouble(routeListStatis.get(0).getRouteLong().toString());
*/


            Log.e("current_lat ", "current_lat "+deviceLat +" "+deviceLong);

            final LatLng DESTINATION = new LatLng(deviceLat, deviceLong);

            if(isBusMode) {
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(DESTINATION)      // Sets the center of the map to Mountain View
                        .zoom(16)                   // Sets the zoom
                        .bearing(90)                // Sets the orientation of the camera to east
                        .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder

                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }
            if (marker_routes != null) {
                marker_routes.remove();
            }

            MarkerOptions options = new MarkerOptions()
                    .position(new LatLng(deviceLat,
                            deviceLong))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.busmarker))
                    .title("Bus");

            marker_routes = mMap.addMarker(options);
            marker_routes.showInfoWindow();
            mMap.getUiSettings().setZoomControlsEnabled(true);


        }

    }

    private void getDeviceLocation( Location location){
        if(mLocationPermissionGranted){
            if(location!=null){

                double deviceLat= location.getLatitude();
                double deviceLong = location.getLongitude();

                final LatLng DESTINATION = new LatLng(deviceLat, deviceLong);

                double busLat= DESTINATION.latitude;
                double busLong = DESTINATION.longitude;



              try {
                  addresses = geocoder.getFromLocation(busLat, busLong, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5



                  String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                  String city = addresses.get(0).getLocality();
                  String state = addresses.get(0).getAdminArea();
                  String country = addresses.get(0).getCountryName();
                  String postalCode = addresses.get(0).getPostalCode();
                  String knownName = addresses.get(0).getFeatureName(); //


                  Log.e("address ","address "+address);
                  txtnowreachedVal.setText(address);

              }catch(Exception e){
                  e.printStackTrace();
              }

               if(isDevMode) {
                   CameraPosition cameraPosition = new CameraPosition.Builder()
                           .target(DESTINATION)      // Sets the center of the map to Mountain View
                           .zoom(12)                   // Sets the zoom
                           .bearing(90)                // Sets the orientation of the camera to east
                           .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                           .build();                   // Creates a CameraPosition from the builder

                   mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

               }
                  if (marker_device != null) {
                      marker_device.remove();
                  }

                  MarkerOptions options = new MarkerOptions()
                          .position(new LatLng(busLat,
                                  busLong))
                          .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                          .title("ME");

                marker_device = mMap.addMarker(options);
                marker_device.showInfoWindow();
                  mMap.getUiSettings().setZoomControlsEnabled(true);

          //    }
                ////////////////



            }

        }


    }

    private void getBusLocation( Location location){
        if(mLocationPermissionGranted){
            if(location!=null){

                //adding destination
                final LatLng DESTINATION = new LatLng(10.09080, 76.46823);


                //replace the following with shared preference values
              /*  double busLat= location.getLatitude();
                double busLong = location.getLongitude();



                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(busLat,
                                busLong), DEFAULT_ZOOM));*/

                double busLat= DESTINATION.latitude;
                double busLong = DESTINATION.longitude;

              /*  mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                        new LatLng(busLat,busLong), DEFAULT_ZOOM));

*/
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(DESTINATION)      // Sets the center of the map to Mountain View
                        .zoom(15)                   // Sets the zoom
                        .bearing(90)                // Sets the orientation of the camera to east
                        .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder

                mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                if(marker_bus !=null){
                    marker_bus.remove();
                }

                MarkerOptions options = new MarkerOptions()
                        .position(new LatLng(busLat,
                                busLong))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                        .title("I am Bus!");

                marker_bus = mMap.addMarker(options);
                //  marker_device.showInfoWindow();
                mMap.getUiSettings().setZoomControlsEnabled(true);

            }

        }


    }

     void drawBusRoute(String url){
        //****imp**** get student id from shared prefs and add camera focus


        try {

         if(mLocationPermissionGranted){

              double lattitude=0;

              double longitude=0;

             //String EncURL=busdirectionsURLClass.getMapsApiDirectionsUrl(StudenId);

             String EncURL=url;

             Log.e("EncURL","EncURL"+EncURL);

            DownloadTask downloadTask = new DownloadTask();

             // Start downloading json data from Google Directions API
             downloadTask.execute(EncURL);



         }

        }catch (Exception e){

            e.printStackTrace();
        }

    }

    private void startLocationUpdates() {
        if (mLocationPermissionGranted) {

            try {
                mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest,
                        mLocationCallback,
                        null

                );
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }

    }

    private void stopLocationUpdates() {
        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
    }



    @Override
    public void setRouteId(String routeId) {

        StudentName=studentPreference.getString("studentname", "");
        mapModelInterface.deleteTable(StudentName,getApplicationContext(),this);
    }

    @Override
    public void setRouteList(List<RoutePoint> routeList) {

        //need to draw route
        //need to draw routeadd

        routeListStatis=routeList;

        progressBar.dismiss();


        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                addMarkerPoints();

                startTimer();
            }
        });

    }



    public  void addMarkerPoints() {

        try {

            final SharedPreferences currLocationPreference = getApplicationContext().getSharedPreferences(Constants.CURR_BUS_LOCATION,Context.MODE_PRIVATE);

            int lastIndex= currLocationPreference.getInt("last_index",0);
            int nextIndex=lastIndex+1;

            int listSize=routeListStatis.size();

            for(int i=0;i<listSize;i++ ){

                Double currLat=Double.parseDouble(routeListStatis.get(i).getRouteLat());

                Double currLong=Double.parseDouble(routeListStatis.get(i).getRouteLong());


               if(i==lastIndex){

                   if(lastIndex==listSize-1){
                       marker_previous = mMap.addMarker(new MarkerOptions().position(new LatLng(currLat, currLong)).icon(BitmapDescriptorFactory.fromResource(R.drawable.blue)).title("DESTINATION REACHED"));

                       marker_previous.showInfoWindow();
                   }else {
                       marker_previous = mMap.addMarker(new MarkerOptions().position(new LatLng(currLat, currLong)).icon(BitmapDescriptorFactory.fromResource(R.drawable.greyline)).title("PREVIOUS"));

                   }

               }else if(i==nextIndex){

                   if(nextIndex==listSize-1){
                       marker_next = mMap.addMarker(new MarkerOptions().position(new LatLng(currLat, currLong)).icon(BitmapDescriptorFactory.fromResource(R.drawable.blue)).title("DESTINATION"));

                       marker_next.showInfoWindow();
                   }else {

                       marker_next = mMap.addMarker(new MarkerOptions().position(new LatLng(currLat, currLong)).icon(BitmapDescriptorFactory.fromResource(R.drawable.pinkmarker)).title("NEXT"));

                       marker_next.showInfoWindow();

                   }

               }else if(lastIndex==-1){
                   marker_next = mMap.addMarker(new MarkerOptions().position(new LatLng(currLat, currLong)).icon(BitmapDescriptorFactory.fromResource(R.drawable.pinkmarker)).title("START"));

                   marker_next.showInfoWindow();
               }else{

                   marker_default = mMap.addMarker(new MarkerOptions().position(new LatLng(currLat, currLong)).icon(BitmapDescriptorFactory.fromResource(R.drawable.pinkmarker)).title("STOP "+i+1));

                   marker_default.showInfoWindow();

               }

            }



        }catch (Exception e){
            e.printStackTrace();
        }

    }







    @Override
    public void setMessage(int message) {

        StudentName=studentPreference.getString("studentname", "");

        if(message==Constants.PASS_VALIDATION){

            mapModelInterface.getStops(StudentName,getApplicationContext(),this);
        }else if(message==Constants.PASS_SERVICE){

           mapModelInterface.getStopsFromDB(StudentName,getApplicationContext(),this);
        }

    }

    @Override
    public void updateCurrentLoc(int msg) {


    }


    private class DownloadTask extends AsyncTask<String, Void, String> {

        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = busdirectionsURLClass.downloadUrl(url[0]);
                Log.e("dataresult","dataresult"+data);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();
            Log.e("DirectionsResult ","DirectionsResult "+result);

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }

    // * A class to parse the Google Places in JSON format




    private void drawPolyAnim( ArrayList<LatLng> points){


        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (LatLng latLng : points) {
            builder.include(latLng);
        }

        LatLngBounds bounds = builder.build();
        CameraUpdate mCameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 2);
        mMap.animateCamera(mCameraUpdate);

        polylineOptions = new PolylineOptions();

        // Adding all the points in the route to LineOptions
        Log.e("linepoints", "linepoints" + points);
        polylineOptions.addAll(points);
        polylineOptions.width(5);
        polylineOptions.color(Color.BLACK);
        mMap.addPolyline(polylineOptions);



        if (dir.contains("From")) {

            Log.e("busFrm","busFrm "+"busFrm");
            //start position :from school
            mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.school))
                    .position(points.get(0)));


            //end position
            mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.destin))
                    .position(points.get(points.size() - 1)));

        } else {

            //start position :to school

            Log.e("busTo","busTo"+"busTo");
            mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.destin))
                    .position(points.get(0)));


            //end position
            mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.school))
                    .position(points.get(points.size() - 1)));

        }

        drawAnimation();

    }


    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
            Log.e("ParserTaskResult","ParserTaskResult"+jsonData);

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);

                Log.e("ParserTaskResult","ParserTaskResult"+jsonData);

                DirectionsJSONParser parser = new DirectionsJSONParser();

                // Starts parsing data
                routes = parser.parse(jObject);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            String duration = "";


            Log.e("routesresult","routesresult"+result);
            // Traversing through all the routes

            ////Prone highly to force close/////////need to have a try catch here

            if(result!=null){
                for (int i = 0; i < result.size(); i++) {
                    points = new ArrayList<LatLng>();
                    lineOptions = new PolylineOptions();

                    // Fetching i-th route
                    List<HashMap<String, String>> path = result.get(i);

                    //to cler the previously drwan pliline options

                    if (lineOptions != null) {

                    }

                    // Fetching all the points in i-th route
                    for (int j = 0; j < path.size(); j++) {
                        HashMap<String, String> point = path.get(j);

                        //the following lines are added to calculate the distance

                        if(j==0){    // Get distance from the list
                            distance = (String)point.get("distance");
                            continue;
                        }else if(j==1){ // Get duration from the list
                            duration = (String)point.get("duration");
                            continue;
                        }

                        Log.e("Calcdistance", "point " + point);

                        Log.e("Calcduration", "point " + point);

                        double lat = Double.parseDouble(point.get("lat"));
                        double lng = Double.parseDouble(point.get("lng"));
                        LatLng position = new LatLng(lat, lng);

                        points.add(position);
                    }


                    drawPolyAnim(points);


                }

            }///end of for loop

            // Drawing polyline in the Google Map for the i-th route


/*

            try {
                mMap.addPolyline(lineOptions);
                Log.e("linepoints","linepoints"+points);






            }catch(Exception e){
                e.printStackTrace();

            }
*/

        }

    }//end of class here


    // Broadcast receiver for receiving status updates from the IntentService
    private class DownloadStateReceiver extends BroadcastReceiver
    {
        // Prevents instantiation
        private DownloadStateReceiver() {

        }

        @Override
        public void onReceive(Context context, Intent intent) {
            //If Action is Location

            Log.e("broadcast ", "broadcast ");



            if (intent.getAction().matches(BROADCAST_ACTION)) {
                LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
                //Check if GPS is turned ON or OFF
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    Log.e("About GPS", "GPS is Enabled in your device");
                    //updateGPSStatus("GPS is Enabled in your device");

                } else {
                    //If GPS turned OFF show Location Dialog
                    //    new Handler().postDelayed(sendUpdatesToUI, 10);
                    // showSettingDialog();
                    //  updateGPSStatus("GPS is Disabled in your device");
                    Log.e("GPSStatus", "onReceivecreateLocationReques");
                    createLocationRequest();
                    Log.e("About GPS", "GPS is Disabled in your device");
                }

            }else if(intent.getAction().matches(Constants.BROADCAST_ACTION_BUS)){

               int status=intent.getIntExtra(Constants.EXTENDED_DATA_STATUS,0);

                Log.e("statusinMap", "statusinMap "+status);

                if(status==Constants.PASS_SERVICE){

                    final SharedPreferences currLocationPreference = context.getSharedPreferences(Constants.CURR_BUS_LOCATION,Context.MODE_PRIVATE);

                    int prevlast_index=currLocationPreference.getInt("prevlast_index",-1);
                    int currLast_index=currLocationPreference.getInt("last_index",-1);

                    if(currLast_index>prevlast_index){
                        addMarkerPoints();

                        SharedPreferences.Editor editor = currLocationPreference.edit();
                        editor.putInt("prevlast_index",currLast_index);
                        editor.commit();
                    }

                    draBusLocation();

                }

                else if(status==Constants.ERROR_SERVICE){
                    Toast.makeText(getApplicationContext(),"bus details not available",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Check your internet",Toast.LENGTH_LONG).show();
                    layout.setVisibility(View.GONE);
                    relativeOnee.setVisibility(View.VISIBLE);
                    stopTimer();
                  //  onRoute=false;
                    txtnowreached.setText("Current Position");
                   // buttonLiveTracking.setText("Start Tracking");
                    getDeviceLocation(mLastKnownLocation);
                }


            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.map_menu, menu);

        menu=
        this.menu = menu;
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                //   mDrawerLayout.openDrawer(GravityCompat.START);
                MapsActivity.this.finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    private void updateMenuTitles( String Name) {
        MenuItem MenuItem = menu.findItem(R.id.miAccount);
        MenuItem.setTitle(Name);
    }



    public void startTimer() {
        //set a new Timer
        timer = new Timer();
        //initialize the TimerTask's job
        initializeTimerTask();
        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 0, 10000); //

    }



    public void stopTimer() {
        if (timer != null) {
            handler.removeCallbacks(runnable);
            timer.cancel();
            timer.purge();
            timer = null;
        }
    }

    public void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                handler.post(new Runnable() {
            public void run() {

                mServiceIntent=new Intent(MapsActivity.this, BusTrackerService.class);
                startService(mServiceIntent);
                    }

                });

            }

        };

    }


    private void drawPoliNew(){
       if(mLastKnownLocation!=null) {

         //  onRoute = true;
           double busOrginLat = mLastKnownLocation.getLatitude();
           double busOrginLong = mLastKnownLocation.getLongitude();

           // double busOrginLat =0.00;
           // double busOrginLong = 0.00;


           String requestUrl = null;


           try {


               orginLat = Double.parseDouble(locationPreferences.getString("orginLat", ""));
               originLon = Double.parseDouble(locationPreferences.getString("originLon", ""));

               desLat = Double.parseDouble(locationPreferences.getString("desLat", ""));
               desLon = Double.parseDouble(locationPreferences.getString("desLon", ""));

               // String curLat,curLon,dir;

               curLat = Double.parseDouble(locationPreferences.getString("curLat", ""));
               curLon = Double.parseDouble(locationPreferences.getString("curLon", ""));

               prevLat = Double.parseDouble(locationPreferences.getString("prevLat", ""));
               prevLon = Double.parseDouble(locationPreferences.getString("prevLon", ""));

               reDrawPoly = locationPreferences.getBoolean("routeDrawStatus", false);
               dir = locationPreferences.getString("dir", "null");
               txtnowreachedVal.setText("xxxxxx");


               Log.e("reDrawPolynew","reDrawPolynew "+reDrawPoly);

               /* if(marker_bus !=null){
                    marker_bus.remove();
                }

                MarkerOptions options = new MarkerOptions()
                        .position(new LatLng(curLat,
                                curLon))
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.busmarker))
                        .title("ME!!!!");

                marker_bus = mMap.addMarker(options);*/


           } catch (Exception e) {

               e.printStackTrace();

               orginLat = busOrginLat;
               originLon = busOrginLong;
               desLat = busOrginLat;
               desLon = busOrginLong;
               curLat = busOrginLat;
               curLon = busOrginLong;
               prevLat = busOrginLat;
               prevLon = busOrginLong;
               reDrawPoly = false;
               dir = "From";
               Toast.makeText(getApplicationContext(), "No bus route available", Toast.LENGTH_SHORT).show();
               layout.setVisibility(View.GONE);
               stopTimer();
               relativeOnee.setVisibility(View.VISIBLE);
               stopTimer();
               //onRoute=false;
               txtnowreached.setText("Current Position");
              // buttonLiveTracking.setText("Start Tracking");
               getDeviceLocation(mLastKnownLocation);
           }


           requestUrl = "https://maps.googleapis.com/maps/api/directions/json?" +
                   "mode=driving&"
                   + "transit_routing_preference=less_driving&"
                   + "origin=" + orginLat + "," + originLon + "&"
                   + "destination=" + desLat + "," + desLon + "&"
                   + "key=" + getResources().getString(R.string.google_maps_key);
           Log.d(TAG, requestUrl);


           // drawBusRoute(requestUrl);


     /*   String EncURL=directionsURLClass.getDirectionsUrl(new LatLng(orginLat,originLon),new LatLng(desLat,desLon));
        Log.d(TAG, EncURL);


        DownloadTask downloadTask = new DownloadTask();

        downloadTask.execute(requestUrl);*/
           String EncURL = directionsURLClass.getDirectionsUrl(new LatLng(orginLat, originLon), new LatLng(desLat, desLon));

           DownloadTask downloadTask = new DownloadTask();

           downloadTask.execute(EncURL);

       }else{

           txtnowreachedVal.setText("No data");
       }
    }




    public void animateMarker(final boolean hideMarker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = mMap.getProjection();

    //    Point startPoint = proj.toScreenLocation(marker.getPosition());

        final LatLng startLatLng = new LatLng(prevLat,prevLon);
        final long duration = 500;
        LatLng newPos;

        final Interpolator interpolator = new LinearInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);

                double lng = t * curLon + (1 - t)
                        * startLatLng.longitude;
                double lat = t * curLat + (1 - t)
                        * startLatLng.latitude;

                LatLng newPos = new LatLng(lat, lng);

                marker_bus.setPosition(newPos);


                marker_bus.setAnchor(0.5f, 0.5f);
                marker_bus.setRotation(getBearing(startLatLng, newPos));
                mMap.moveCamera(CameraUpdateFactory
                        .newCameraPosition
                                (new CameraPosition.Builder()
                                        .target(newPos)
                                        .zoom(12.5f)
                                        .build()));


                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                } else {
                    if (hideMarker) {
                        marker_bus.setVisible(false);
                    } else {
                        marker_bus.setVisible(true);
                    }
                }
            }
        });



        LatLng newPos1 = new LatLng(curLat, curLon);
        marker_bus.setPosition(newPos1);
    }



    private void drawPloliline(){
        if(mLastKnownLocation!=null) {

            //onRoute=true;
            double busOrginLat = mLastKnownLocation.getLatitude();
            double busOrginLong = mLastKnownLocation.getLongitude();

            String requestUrl = null;


            try {


                orginLat = Double.parseDouble(locationPreferences.getString("orginLat", ""));
                originLon = Double.parseDouble(locationPreferences.getString("originLon", ""));

                desLat = Double.parseDouble(locationPreferences.getString("desLat", ""));
                desLon = Double.parseDouble(locationPreferences.getString("desLon", ""));

                // String curLat,curLon,dir;

                curLat = Double.parseDouble(locationPreferences.getString("curLat", ""));
                curLon = Double.parseDouble(locationPreferences.getString("curLon", ""));

                prevLat = Double.parseDouble(locationPreferences.getString("prevLat", ""));
                prevLon = Double.parseDouble(locationPreferences.getString("prevLon", ""));

                reDrawPoly = locationPreferences.getBoolean("routeDrawStatus", false);
                dir = locationPreferences.getString("dir", "null");
                txtnowreachedVal.setText("xxxxxx");

                if(reDrawPoly){
                    Log.e("reDrawPolyTrue ", "reDrawPolyTrue " + reDrawPoly);
                    mMap.clear();

                }

                if(marker_bus==null){
                    //  marker_bus.remove();
                    Log.e("marker_bus==null","marker_bus==null");
                    marker_bus = mMap.addMarker(new MarkerOptions().position(new LatLng(curLat,curLon))
                            .flat(true)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.bussmall)));

                }


            }catch (Exception e){

                e.printStackTrace();

                orginLat=busOrginLat;
                originLon=busOrginLong;
                desLat=busOrginLat;
                desLon=busOrginLong;
                curLat=busOrginLat;
                curLon=busOrginLong;
                prevLat=busOrginLat;
                prevLon=busOrginLong;
                reDrawPoly=false;
                dir="From";
                Toast.makeText(getApplicationContext(),"No bus route available",Toast.LENGTH_SHORT).show();
                txtnowreachedVal.setText("No data");
            }


            try {


                requestUrl = "https://maps.googleapis.com/maps/api/directions/json?" +
                        "mode=driving&"
                        + "transit_routing_preference=less_driving&"
                        + "origin=" + orginLat + "," + originLon + "&"
                        + "destination=" + desLat + "," + desLon + "&"
                        + "key=" + getResources().getString(R.string.google_maps_key);
                Log.d(TAG, requestUrl);

                Log.e("reDrawPoly ", "reDrawPoly " + reDrawPoly);


                // if(reDrawPoly) {

                Log.e("volleyreq ", "volleyreq");

                if(marker_bus==null){
                //    marker_bus.remove();
                  //  Log.e("busremove","busremove "+"remove");
                    marker_bus = mMap.addMarker(new MarkerOptions().position(new LatLng(curLat,curLon))
                            .flat(true)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.bussmall)));


                }

                if(marker_bus!=null){
                    marker_bus.remove();
                  //  Log.e("busremove","busremove "+"remove");
                    marker_bus = mMap.addMarker(new MarkerOptions().position(new LatLng(curLat,curLon))
                            .flat(true)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.bussmall)));

                }


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, requestUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        Log.d(TAG, response + "");

                        Log.e("JsonObjectRequest ", "JsonObjectRequestresponse " + response);

                        Log.e("volleyres ", "volleyres");

                        try {

                            JSONArray jsonArray = response.getJSONArray("routes");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject route = jsonArray.getJSONObject(i);
                                JSONObject poly = route.getJSONObject("overview_polyline");
                                String polyline = poly.getString("points");
                                polyLineList = decodePoly(polyline);
                                Log.d(TAG, polyLineList + "");
                            }

                            Log.e("polyLineListnew", "polyLineListnew " + polyLineList);

                            final int indes = polyLineList.size() - 1;


                            ///copy from here ...................................
                            //Adjusting bounds
                            LatLngBounds.Builder builder = new LatLngBounds.Builder();
                            for (LatLng latLng : polyLineList) {
                                builder.include(latLng);
                            }

                            LatLngBounds bounds = builder.build();
                            CameraUpdate mCameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, 2);
                            mMap.animateCamera(mCameraUpdate);

                            polylineOptions = new PolylineOptions();
                            polylineOptions.color(Color.GRAY);
                            polylineOptions.width(5);
                            polylineOptions.startCap(new SquareCap());
                            polylineOptions.endCap(new SquareCap());
                            polylineOptions.jointType(ROUND);
                            polylineOptions.addAll(polyLineList);
                            greyPolyLine = mMap.addPolyline(polylineOptions);

                            blackPolylineOptions = new PolylineOptions();
                            blackPolylineOptions.width(5);
                            blackPolylineOptions.color(Color.BLACK);
                            blackPolylineOptions.startCap(new SquareCap());
                            blackPolylineOptions.endCap(new SquareCap());
                            blackPolylineOptions.jointType(ROUND);
                            blackPolyline = mMap.addPolyline(blackPolylineOptions);

                            //need to check the directiontype


                            if (dir.contains("From")) {

                                Log.e("busFrm", "busFrm " + "busFrm");
                                //start position :from school
                                mMap.addMarker(new MarkerOptions()
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.school))
                                        .position(polyLineList.get(0)));


                                //end position
                                mMap.addMarker(new MarkerOptions()
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.destin))
                                        .position(polyLineList.get(polyLineList.size() - 1)));

                            } else {

                                //start position :to school

                                Log.e("busTo", "busTo" + "busTo");
                                mMap.addMarker(new MarkerOptions()
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.destin))
                                        .position(polyLineList.get(0)));


                                //end position
                                mMap.addMarker(new MarkerOptions()
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.school))
                                        .position(polyLineList.get(polyLineList.size() - 1)));

                            }


                            if (marker_bus == null) {
                                //  marker_bus.remove();
                                Log.e("marker_bus==null", "marker_bus==null");
                                marker_bus = mMap.addMarker(new MarkerOptions().position(new LatLng(curLat, curLon))
                                        .flat(true)
                                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.bussmall)));

                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                    }

                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("JsonObjectRequestError ", "JsonObjectRequestError " + error);
                    }
                });


                   RequestQueue requestQueue = Volley.newRequestQueue(this);
                  // requestQueue.getCache().clear();
                   requestQueue.add(jsonObjectRequest);

              drawAnimation();



            } catch (Exception e) {
                e.printStackTrace();
            }

        }else{

            getLastKnownLocation();
        }

    }



    private void drawAnimation(){

      //  if(marker_bus!=null){

        double busOrginLat = mLastKnownLocation.getLatitude();
        double busOrginLong = mLastKnownLocation.getLongitude();


        try {


            orginLat = Double.parseDouble(locationPreferences.getString("orginLat", ""));
            originLon = Double.parseDouble(locationPreferences.getString("originLon", ""));

            desLat = Double.parseDouble(locationPreferences.getString("desLat", ""));
            desLon = Double.parseDouble(locationPreferences.getString("desLon", ""));

            // String curLat,curLon,dir;

            curLat = Double.parseDouble(locationPreferences.getString("curLat", ""));
            curLon = Double.parseDouble(locationPreferences.getString("curLon", ""));

            prevLat = Double.parseDouble(locationPreferences.getString("prevLat", ""));
            prevLon = Double.parseDouble(locationPreferences.getString("prevLon", ""));

            reDrawPoly = locationPreferences.getBoolean("routeDrawStatus", false);
            dir = locationPreferences.getString("dir", "null");
            txtnowreachedVal.setText("xxxxxx");


        } catch (Exception e) {

            layout.setVisibility(View.GONE);
            relativeOnee.setVisibility(View.VISIBLE);


            e.printStackTrace();

            orginLat = busOrginLat;
            originLon = busOrginLong;
            desLat = busOrginLat;
            desLon = busOrginLong;
            curLat = busOrginLat;
            curLon = busOrginLong;
            prevLat = busOrginLat;
            prevLon = busOrginLong;
            reDrawPoly = false;
            dir = "From";
            Toast.makeText(getApplicationContext(), "No bus route available", Toast.LENGTH_SHORT).show();
            txtnowreachedVal.setText("No data");
        }




      /*  marker_bus.remove();
            Log.e("busremove","busremove "+"drawAnimation");
            marker_bus = mMap.addMarker(new MarkerOptions().position(new LatLng(curLat,curLon))
                    .flat(true)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.busmarker)));*/

       // }


      /*  ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
                valueAnimator.setDuration(2000);
                valueAnimator.setInterpolator(new LinearInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator valueAnimator) {

                        v = valueAnimator.getAnimatedFraction();

                       *//* lng = v * endPosition.longitude + (1 - v)
                                * startPosition.longitude;
                        lat = v * endPosition.latitude + (1 - v)
                                * startPosition.latitude;*//*

                        startPosition=new LatLng(prevLat,prevLon);
                        LatLng newPos = new LatLng(curLat, curLon);

                        //this is the new position --current position ;lat long
                        marker_bus.setPosition(newPos);
                        marker_bus.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.busmarker));
                        marker_bus.setAnchor(0.5f, 0.5f);
                        marker_bus.setRotation(getBearing(startPosition, newPos));
                        mMap.moveCamera(CameraUpdateFactory
                                .newCameraPosition
                                        (new CameraPosition.Builder()
                                                .target(newPos)
                                                .zoom(12.5f)
                                                .build()));
                    }
                });
                valueAnimator.start();*/


        startPosition=new LatLng(prevLat,prevLon);
        LatLng newPos = new LatLng(curLat, curLon);

        //this is the new position --current position ;lat long
        /////////////////////////////////////

       /*  if(marker_bus !=null){
            marker_bus.remove();
        }

        MarkerOptions options = new MarkerOptions()
                .position(new LatLng(curLat,
                        curLon))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.busmarker))
                .anchor(0.5f, 0.5f)
                .rotation(getBearing(startPosition, newPos));

        Log.e("newPos","newPos "+newPos);

        marker_bus = mMap.addMarker(options);*/

////////////////////////////////////////////

          if(marker_bus !=null){
            marker_bus.remove();
        }

        MarkerOptions options = new MarkerOptions()
                .position(new LatLng(curLat,
                        curLon))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.bussmall))
                .anchor(0.5f, 0.5f)
                .rotation(bearingBetweenLocations(startPosition, newPos));

        Log.e("newPos","newPos "+newPos);

        marker_bus = mMap.addMarker(options);



        mMap.moveCamera(CameraUpdateFactory
                .newCameraPosition
                        (new CameraPosition.Builder()
                                .target(newPos)
                                .zoom(12.5f)
                                .build()));


        layout.setVisibility(View.GONE);
        relativeOnee.setVisibility(View.VISIBLE);

        try {
            addresses = geocoder.getFromLocation(curLat, curLon, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5


        }catch(Exception e){
            e.printStackTrace();
        }
        String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
        String city = addresses.get(0).getLocality();
        String state = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String knownName = addresses.get(0).getFeatureName(); //


        Log.e("address ","address "+address);
        txtnowreachedVal.setText(address);


        SharedPreferences.Editor editorloc = locationPreferences.edit();
        editorloc.putBoolean("firstTime",false);
        editorloc.commit();

        /*if(marker_bus !=null){
            marker_bus.remove();
        }

        MarkerOptions options = new MarkerOptions()
                .position(new LatLng(curLat,
                        curLon))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.busmarker))
                .title("ME!!!!");

        marker_bus = mMap.addMarker(options);*/


    }



    private List<LatLng> decodePoly(String encoded) {
        List<LatLng> poly = new ArrayList<>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)),
                    (((double) lng / 1E5)));
            poly.add(p);
        }

        return poly;
    }

    private float getBearing(LatLng begin, LatLng end) {
        double lat = Math.abs(begin.latitude - end.latitude);
        double lng = Math.abs(begin.longitude - end.longitude);

        if (begin.latitude < end.latitude && begin.longitude < end.longitude)
            return (float) (Math.toDegrees(Math.atan(lng / lat)));
        else if (begin.latitude >= end.latitude && begin.longitude < end.longitude)
            return (float) ((90 - Math.toDegrees(Math.atan(lng / lat))) + 90);
        else if (begin.latitude >= end.latitude && begin.longitude >= end.longitude)
            return (float) (Math.toDegrees(Math.atan(lng / lat)) + 180);
        else if (begin.latitude < end.latitude && begin.longitude >= end.longitude)
            return (float) ((90 - Math.toDegrees(Math.atan(lng / lat))) + 270);
        return -1;
    }


    private float bearingBetweenLocations(LatLng latLng1,LatLng latLng2) {

        double PI = 3.14159;
        double lat1 = latLng1.latitude * PI / 180;
        double long1 = latLng1.longitude * PI / 180;
        double lat2 = latLng2.latitude * PI / 180;
        double long2 = latLng2.longitude * PI / 180;

        double dLon = (long2 - long1);

        double y = Math.sin(dLon) * Math.cos(lat2);
        double x = Math.cos(lat1) * Math.sin(lat2) - Math.sin(lat1)
                * Math.cos(lat2) * Math.cos(dLon);

        double brng = Math.atan2(y, x);

        brng = Math.toDegrees(brng);
        brng = (brng + 360) % 360;

        return (float) brng;
    }


    public void comparePrevvalious(){

        orginLat=Double.parseDouble(locationPreferences.getString("lat",""));
        originLon=Double.parseDouble(locationPreferences.getString("lng",""));

        desLat = Double.parseDouble(locationPreferences.getString("lat",""));
        desLon=Double.parseDouble(locationPreferences.getString("lng",""));

        // String curLat,curLon,dir;

        curLat=Double.parseDouble(locationPreferences.getString("lat",""));
        curLon=Double.parseDouble(locationPreferences.getString("lng",""));

        dir=locationPreferences.getString("dir","");

        timestamp=locationPreferences.getString("timestamp","");


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());



    }

    void onClicks(){


        buttonvehicleInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDriver();
            }
        });

    }

    public void showDriver(){

// custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_mapdriverdetails);
       /* TextView textName = (TextView) dialog.findViewById(R.id.txtNme);
        ImageView image = (ImageView) dialog.findViewById(R.id.imgstudent);
        image.setImageResource(R.drawable.imgb);
*/
        Button btnLogout = (Button) dialog.findViewById(R.id.ok);

        dialog.show();
        dialog.setCancelable(false);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

    }



    class Task1 extends AsyncTask<String, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
            layout.setVisibility(View.VISIBLE);
            relativeOnee.setVisibility(View.GONE);
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean result) {
           // layout.setVisibility(View.GONE);
          //  relativeOnee.setVisibility(View.VISIBLE);
            super.onPostExecute(result);
        }

        @Override
        protected Boolean doInBackground(String... params) {

           /* try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }*/

           startTimer();
           return null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

            txtnowreached.setText("Current Position");

           // buttonLiveTracking.setText("Start Tracking");

            stopTimer();
            mMap.clear();
            getDeviceLocation(mLastKnownLocation);

    }
}
