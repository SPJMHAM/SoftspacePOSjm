package com.example.softspaceposjm.ViewHolder;

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

import com.example.softspaceposjm.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;

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
        //-map-
        ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        }

        recycle_menu = (RecyclerView) findViewById(R.id.recycle_menu);
        recycle_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        recycle_menu.setLayoutManager(layoutManager);
        searchBar1 = (MaterialEditText) findViewById(R.id.searchBar);

        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        jobinfo = FirebaseDatabase.getInstance().getReference().child("ListOfJob1");
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
                holder.txtTheJobName1.setText(model.getJobName1());
                holder.txtTheNoOfGuard1.setText(model.getNoOfGuard1());
                holder.txtTheDate1.setText(model.getDate1());
                holder.txtTheLocation1.setText(model.getLocation1());
                holder.txtTheStatus1.setText(model.getStatus1());
                holder.txtTheType1.setText(model.getType1());

            }

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
        MenuItem searchItem = menu.findItem(R.id.searchBar);

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
    private void getLocation() {

    }

}