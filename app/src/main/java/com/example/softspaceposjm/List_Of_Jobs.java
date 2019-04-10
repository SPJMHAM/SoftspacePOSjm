package com.example.softspaceposjm;

import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.bumptech.glide.Glide;
import com.example.softspaceposjm.Model.JobInfo1;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.softspaceposjm.ViewHolder.MenuViewHolder;
import com.google.firebase.database.Query;
import java.util.ArrayList;
import java.util.Calendar;
import com.rengwuxian.materialedittext.MaterialEditText;

public class List_Of_Jobs extends AppCompatActivity {
    ListView listView;

    FirebaseDatabase database;
    DatabaseReference jobinfo;
    FloatingActionButton search_item;
    RecyclerView recycle_menu;
    RecyclerView.LayoutManager layoutManager;
    //   MaterialSearchBar searchBar;
    MaterialEditText searchBar1;
    String names;
    //ArrayList<String> items;
    ArrayList<JobInfo1> items = new ArrayList<>();
    FirebaseRecyclerAdapter <JobInfo1, MenuViewHolder> adapter;

    // String arr[] = ListInfo.
    //   txtEmail = (TextView) headerView1.findViewById(R.id.txtEmail);
    //    txtEmail.setText(Common.getCurrentEmail.getEmail());

    //--
    String latitude,longitude;
    //-
    private View viewTable;
    // int confirmEnrol = 0 ;
    private Dialog dialogTable;
    private TextView eventid1,eventName,eventName2,eventDate,eventPrice,eventCapacity,eventLocation,eventContact;
    private ImageView eventImage;
    //  private MapView mMapView;
    private static final int REQUEST_LOCATION = 1;
    TextView CurrentLocation;
    LocationManager locationManager;
    //  MapView map1;
    private GoogleMap map;
    // String EDate;
//EDate =  eventinfo.child("mostafa_farahat22@yahoo.com").child("_email")
    ArrayList<JobInfo1> EDate = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_list__of__jobs);

        //===

        boolean alarm = (PendingIntent.getBroadcast(this, 0, new Intent("ALARM"), PendingIntent.FLAG_NO_CREATE) == null);

        if(alarm){
            Intent itAlarm = new Intent("ALARM");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,itAlarm,0);
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, 3);
            AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),60000, pendingIntent);
        }


        //===
        //   mMapView = (MapView) findViewById(R.id.MapView01);

        // mMapView.setTraffic(true);

        //back from payment success----------------------------

        //    Intent backtoeventlist = getIntent();
        //     String Confirmed =  backtoeventlist.getStringExtra("confirm");
        //confirmEnrol  Double.parseDouble(price);
//        confirmEnrol =   Integer.parseInt(Confirmed);


//
//        if (confirmEnrol == 1 ){
//            //----Store In enroled list
//
//            Toast.makeText(List_Of_Event.this, "1111", Toast.LENGTH_SHORT).show();
//
//            //done, reset  confirmEnrol
//            confirmEnrol = 0;
//        }else{
//            Toast.makeText(List_Of_Event.this, "0000", Toast.LENGTH_SHORT).show();
//        }




        //---
        //-map-
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        //     CurrentLocation = (TextView)findViewById(R.id.EventLocation);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }

//--
        //--Dialog--

      //  viewTable = getLayoutInflater().inflate(R.layout.activity_detail_event_info,null);    //======
//        eventid1 = viewTable.findViewById(R.id.EventId1);
//        eventName = viewTable.findViewById(R.id.EventName1);
//        eventName2 = viewTable.findViewById(R.id.EventName2);
//        eventDate = viewTable.findViewById(R.id.EventDate1);
//        eventPrice = viewTable.findViewById(R.id.EventPrice1);
//        eventCapacity =   viewTable.findViewById(R.id.EventCapacity);
//        eventLocation =  viewTable.findViewById(R.id.EventLocation);
//        eventContact =  viewTable.findViewById(R.id.EventContact);
//        dialogTable = new Dialog(this,R.style.full_screen_dialog);
//        dialogTable.setContentView(viewTable);
        //--

        recycle_menu = (RecyclerView) findViewById(R.id.recycle_menu);
        recycle_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        recycle_menu.setLayoutManager(layoutManager);
        searchBar1 = (MaterialEditText) findViewById(R.id.searchBar);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        jobinfo = FirebaseDatabase.getInstance().getReference().child("ListOfJob1");
        // Detatil View
        /*
        recycle_menu.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), recycle_menu ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
// Show detail Information
                        Button btnGetMap,btnChatRoom, btnEnroll,btnFeedback;
                        btnGetMap = (Button) dialogTable.findViewById(R.id.btnMap);
                        btnChatRoom = (Button) dialogTable.findViewById(R.id.btnEnterChatRoom);
                        btnEnroll = (Button) dialogTable.findViewById(R.id.btnEnroll);
                        btnFeedback = (Button) dialogTable.findViewById(R.id.btnFeedback);

                        final String id = ((TextView)view.findViewById(R.id.RegisterEventId)).getText().toString();
                        eventid1.setText(id);
                        final String name = ((TextView)view.findViewById(R.id.RegisterEventName)).getText().toString();
                        eventName.setText(name);
                        final String name2 = ((TextView)view.findViewById(R.id.RegisterEventName)).getText().toString();
                        eventName2.setText(name2);
                        final String date = ((TextView)view.findViewById(R.id.RegisterEventStartDate)).getText().toString();
                        eventDate.setText(date);

                        final String location = ((TextView)view.findViewById(R.id.RegisterEventLocation)).getText().toString();
                        eventLocation.setText(location);
                        final String contact = ((TextView)view.findViewById(R.id.contact_number)).getText().toString();
                        eventContact.setText(contact);

                        //--
                        final String price = ((TextView)view.findViewById(R.id.Price)).getText().toString();
                        eventPrice.setText(price);
                        final String capacity = ((TextView)view.findViewById(R.id.Capacity)).getText().toString();
                        eventCapacity.setText(capacity);

                        //Glide.with(getBaseContext()).load(model.getImageToUpload()).into(holder.imageView);
//--

                        dialogTable.show();
                        // int intArray[] = {1,2,3,4};
                        //   Intent in = new Intent(this, DetailEventInfo.class);
                        // in.putExtra("id", id);
                        //startActivity(in);
                        btnChatRoom.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent discuss = new Intent(List_Of_Event.this, DiscussionRoom.class);
//   User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);

                                //  discuss.putExtra("name", name);
                                DEventName.EName = name;


                                //    Discuss.putExtra("location", location);
                                startActivity(discuss);
                            }
                        });


                        btnGetMap.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent mapsActivity = new Intent(List_Of_Event.this, MapsActivity.class);

                                mapsActivity.putExtra("location", location);
                                startActivity(mapsActivity);
                                //MapsActivity
                            }
                        });


                        btnEnroll.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent payment_select = new Intent(List_Of_Event.this, payment_select.class);

                                payment_select.putExtra("price", price);
                                payment_select.putExtra("TheEventName", name);
                                payment_select.putExtra("id",id);
                                payment_select.putExtra("date",date);
                                payment_select.putExtra("location",location);
                                payment_select.putExtra("contact",contact);

                                payment_select.putExtra("capacity1",capacity);
                                startActivity(payment_select);
                                //MapsActivity
                            }
                        });

                        btnFeedback.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent FeedbackBox = new Intent(List_Of_Event.this, FeedbackBox.class);

                                // FeedbackBox.putExtra("ID", id);
                                EventCreater.Eid = id;
                                startActivity(FeedbackBox);
                                //MapsActivity
                            }
                        });

                        //     Toast.makeText(getBaseContext(),"Deleting of event id "+id,Toast.LENGTH_SHORT).show();
                        //   StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                        //    storageReference.child("profileImageUrl").child(filename).delete();
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        // Detail View ended
*/
        // items = new ArrayList<>();
        //  items.add();
//---

        //);



//-------------------------------------------------------------------------------------------------------------------------------

        loadMenu();

    }

    private void SearchEvents(String searchBar){
        FirebaseRecyclerAdapter <JobInfo1, MenuViewHolder> adapter;
    }

    //========
    public void loadMenu(){


        Query query = jobinfo.orderByKey();
        FirebaseRecyclerOptions firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<JobInfo1>().setQuery(query, JobInfo1.class).build();
        adapter = new FirebaseRecyclerAdapter<JobInfo1, MenuViewHolder>(firebaseRecyclerOptions) {
            @NonNull
            @Override


            public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_list,viewGroup,false);
                return new MenuViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull MenuViewHolder holder, int position, @NonNull JobInfo1 model) {
//TheJobName1,TheNoOfGuard1,TheDate1,TheLocation1,TheStatus1,TheType1
              //  holder.txtRegisterEventId.setText(model.getRegisterEventId());
                holder.txtTheJobName1.setText(model.getJobName1());
                holder.txtTheNoOfGuard1.setText(model.getNoOfGuard1());
                holder.txtTheDate1.setText(model.getDate1());
                holder.txtTheLocation1.setText(model.getLocation1());
                holder.txtTheStatus1.setText(model.getStatus1());
                holder.txtTheType1.setText(model.getType1());
//                holder.txtCapacity.setText(model.getEventCapacity());
//                holder.fileName.setText(model.getFileName());

                //Picasso.with(getBaseContext()).load(model.getImageToUpload()).into(viewHolder.imageView);
                //Glide.with(getBaseContext()).load(model.getImageToUpload()).into(viewHolder.imageView);

//                Glide.with(getBaseContext()).load(model.getImageToUpload()
//                ).into(holder.imageView);
//                System.out.println(model.getRegisterEventName());
//                System.out.println(model.getImageToUpload());
            }

//            @Override
//            protected void populateViewHolder(MenuViewHolder viewHolder, EventInfo model, int position) {
//
//
//            }
        };
        recycle_menu.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //   MenuInflater inflater = getMenuInflater();
        //   inflater.inflate(R.layout.activity_list__of__event,menu);

        MenuItem searchItem = menu.findItem(R.id.searchBar);

//        SearchView searchView = (SearchView)MenuItemCompat.getActionView(searchItem);

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
////          //    String  items  = Common.currentUser;
////                ArrayList<String> templist = new ArrayList<>();
////
////                for(String temp: items){
////                    if(temp.toLowerCase().contains(newText.toLowerCase())){
////                        templist.add(temp);
////                    }
////                }
////                ArrayAdapter<String> adapter = new ArrayAdapter<>(List_Of_Event.this,android.R.layout.simple_list_item_1.templist);
////                listView.setAdapter(adapter);
//
//
//                return true;
//            }
//        });



        return super.onCreateOptionsMenu(menu);
    }


    protected void buildAlertMessageNoGps() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn ON your GPS Connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

//public void updateList(List<String> newList){
//       names = new ArrayList<>();
//        ((ArrayList) names).addAll(newList);
//        notifyDataSetChanged();
//
//}




    private void getLocation() {
//
//String   adderess = String.valueOf(eventLocation);
//  //  final LatLng latLng = new LatLng(adderess.getLatitude(), adderess.getLongitude());
//// get address in string for used location for the map
//
//    /* get latitude and longitude from the adderress */
//
//    Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
//    try
//    {
//        List<Address> addresses = geoCoder.getFromLocationName(adderess, 5);
//        if (addresses.size() > 0)
//        {
//            Double lat = (double) (addresses.get(0).getLatitude());
//            Double lon = (double) (addresses.get(0).getLongitude());
//
//            Log.d("lat-long", "" + lat + "......." + lon);
//            final LatLng user = new LatLng(lat, lon);
//            /*used marker for show the location */
//            Marker hamburg = map.addMarker(new MarkerOptions()
//                    .position(user)
//                    .title(adderess)
//                    .icon(BitmapDescriptorFactory
//                            .fromResource(R.drawable.ic_update_black_24dp)));
//            // Move the camera instantly to hamburg with a zoom of 15.
//            map.moveCamera(CameraUpdateFactory.newLatLngZoom(user, 15));
//
//            // Zoom in, animating the camera.
//            map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
//
//            //            double latti = location1.getLatitude();
////            double longi = location1.getLongitude();
//            latitude = String.valueOf(lat);
//            longitude = String.valueOf(lon);
//            final    LatLng latLng = new LatLng(addresses.get(0).getLatitude(), addresses.get(0).getLongitude());
//            mMapView.getMapAsync(new OnMapReadyCallback() {
//                @Override
//                public void onMapReady(GoogleMap googleMap) {
//                    final float ZOOM_MAP = 17.0f;
//                    CameraUpdate myLocation = CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_MAP);
//                    googleMap.animateCamera(myLocation);
//                 //   System.out.println("Test Script" + myLocation);
//                    googleMap.getUiSettings().setAllGesturesEnabled(false
//                    );
//                }
//            });
//
//        }
//    }
//    catch (IOException e)
//    {
//        e.printStackTrace();
//    }


//    if (ActivityCompat.checkSelfPermission(com.example.jmham.eventuser.List_Of_Event.this, Manifest.permission.ACCESS_FINE_LOCATION)
//            != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
//            (com.example.jmham.eventuser.List_Of_Event.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//
//        ActivityCompat.requestPermissions(com.example.jmham.eventuser.List_Of_Event.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
//
//    } else {
//        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//
//        Location location1 = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//
//        Location location2 = locationManager.getLastKnownLocation(LocationManager. PASSIVE_PROVIDER);
//
//        if (location != null) {
//            double latti = location.getLatitude();
//            double longi = location.getLongitude();
//
//            latitude = String.valueOf(latti);
//            longitude = String.valueOf(longi);
//
//     //       CurrentLocation.setText("Lat = " + latitude + "\n" + "Lon = " + longitude);
////                CurrentLocation.setText("latitude"+latitude);
////                CurrentLocation.setText("longitude"+longitude);
//
//            final float ZOOM_MAP = 17.0f;
//            final LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//            mMapView.getMapAsync(new OnMapReadyCallback() {
//                @Override
//                public void onMapReady(GoogleMap googleMap) {
//
//                    CameraUpdate myLocation = CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_MAP);
//                    googleMap.animateCamera(myLocation);
//                    googleMap.getUiSettings().setAllGesturesEnabled(false
//                    );
//                }
//            });
//
//
////                CameraUpdate myLocation = CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_MAP);
////                map.animateCamera(myLocation);
//
//
////                CameraUpdate myLocation = CameraUpdateFactory.newLatLngZoom(latLng, ZOOM_MAP);
////                System.out.println("Test Script" + myLocation);
////                map.animateCamera(myLocation);
//
//
//        } else  if (location1 != null) {
//            double latti = location1.getLatitude();
//            double longi = location1.getLongitude();
//            latitude = String.valueOf(latti);
//            longitude = String.valueOf(longi);
////                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
////                System.out.println("Test Script: " +latLng);
//
//       //     CurrentLocation.setText("Lat = " + latitude + "\n" + "Lon = " + longitude);
//
//        } else  if (location2 != null) {
//            double latti = location2.getLatitude();
//            double longi = location2.getLongitude();
//            latitude = String.valueOf(latti);
//            longitude = String.valueOf(longi);
//
//        //    CurrentLocation.setText("Lat = " + latitude + "\n" + "Lon = " + longitude);
//
//        }else{
//
//            Toast.makeText(this,"Unble to Trace your location",Toast.LENGTH_SHORT).show();
//
//        }
//    }
    }

}