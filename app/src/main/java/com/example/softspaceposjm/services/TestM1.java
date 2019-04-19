package com.example.softspaceposjm.services;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softspaceposjm.Model.service_Info;
import com.example.softspaceposjm.R;
import com.example.softspaceposjm.Req_Service.status;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;

public class TestM1 extends FragmentActivity implements OnMapReadyCallback,
        LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    TextView placeNameText;
    TextView placeAddressText;
    WebView attributionText;
    Button getPlaceButton;
    ImageView btnMNext, back;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    private final static int PLACE_PICKER_REQUEST = 1;

    private GoogleMap mMap;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;

    private FirebaseDatabase database;
    private DatabaseReference userRef, service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_m1);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        requestPermission();


        placeNameText = (TextView) findViewById(R.id.tvPlaceName);
        placeAddressText = (TextView) findViewById(R.id.tvPlaceAddress);
        getPlaceButton = (Button) findViewById(R.id.btGetPlace);
        btnMNext = findViewById(R.id.btnMNext);
        back = findViewById(R.id.backBTN);


        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("User");
        service = database.getReference("service_Info");


        //final int progressValue = Integer.parseInt(NoOfPax);



        System.out.println("Testing111");
        getPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Testing222");
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    Intent intent = builder.build(TestM1.this);
                    startActivityForResult(intent, PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }



            }
        });
//        Intent intent = new Intent(TestM1.this, TestM1.class);
//        startActivity(intent);
//        finish();
//tvPlaceAddress
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(TestM1.this, serviceInfo_Activity.class));
                }
            });



    }
//---




    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

    }
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onLocationChanged(Location location) {

        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        System.out.println("Test Place name:"+placeNameText.getText().toString()+":End;");
        if(placeNameText.getText().toString().equals("Place Name")) {
            System.out.println("TT11");
            //Place current location marker
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title("Current Position");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
        }else{
            System.out.println("TT22");
//            //Place current location marker
//            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//            MarkerOptions markerOptions = new MarkerOptions();
//            markerOptions.position(latLng);
//            markerOptions.title("Current Position");
//            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
//            mCurrLocationMarker = mMap.addMarker(markerOptions);
            String location11 =null;
            location11=  placeNameText.getText().toString();
            //  EditText locationSearch = (EditText) findViewById(R.id.editText); // --- get location search
            //   String location = locationSearch.getText().toString();
            List<Address> addressList = null;

            if (location11 != null || !location11.equals("")) {
                Geocoder geocoder = new Geocoder(this);
                try {
                    addressList = geocoder.getFromLocationName(location11, 1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(latLng).title(location11));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                //move map camera
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
                address = null;
            }




        }
        //stop location updates
//        if (mGoogleApiClient != null) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//        }

        //    searchLocation();

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    //---
    private void requestPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "This app requires location permissions to be granted", Toast.LENGTH_LONG).show();
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST){
            if (resultCode == RESULT_OK){
                Place place = PlacePicker.getPlace(TestM1.this, data);
                placeNameText.setText(place.getName());
                placeAddressText.setText(place.getAddress());

                final String TestCurrentUser;
                //TestCurrentUser = CUser.currentUser.getUserName();
                TestCurrentUser = "ggggjhjh";
                Intent b = this.getIntent();

                final String type = b.getStringExtra("Type");
                final String nationality = b.getStringExtra("nationality");
                final String getStatus = b.getStringExtra("status");
                final String NoOfPax = b.getStringExtra("pax");
                final String Address1 =   placeAddressText.getText().toString() ;
                final String reqDate = b.getStringExtra("date");
                final String jobTitle = b.getStringExtra("job");
//Guards(Land Based)
                final String NoOfGuards1= (NoOfPax + "Guards(Land Based)");
                final String furtherStatus  = "New!";
                btnMNext.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(TestM1.this, service2_Activity.class));
                      //  service_Info SInfo=new service_Info("ggggjhjh",ServiceID,"Armed","Foreigners",status,NoOfGuard,addressInfo,theDate,JobName,"Quoted");
                        service_Info.LogFirebase(service, userRef, "ggggjhjh", type, nationality,getStatus, NoOfGuards1,Address1, reqDate, jobTitle,furtherStatus);
                    }
                });


                if (place.getAttributions() == null) {
                //    attributionText.loadData("no attribution", "text/html; charset=utf-8", "UFT-8");
                } else {
               //     attributionText.loadData(place.getAttributions().toString(), "text/html; charset=utf-8", "UFT-8");
                }
            }
        }
    }
}

