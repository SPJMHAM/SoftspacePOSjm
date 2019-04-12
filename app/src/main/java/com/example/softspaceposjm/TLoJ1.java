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

public class TLoJ1 extends AppCompatActivity {
    ListView listView;

    FirebaseDatabase database;
    DatabaseReference jobinfo;
    FloatingActionButton search_item;
    RecyclerView recycle_menu;
    RecyclerView.LayoutManager layoutManager;

     String names;

    ArrayList<JobInfo1> items = new ArrayList<>();
    FirebaseRecyclerAdapter <JobInfo1, MenuViewHolder> adapter;


    private View viewTable;

    private Dialog dialogTable;
    private TextView eventid1,eventName,eventName2,eventDate,eventPrice,eventCapacity,eventLocation,eventContact;
    private ImageView eventImage;


    ArrayList<JobInfo1> EDate = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list__of__jobs);


        recycle_menu = (RecyclerView) findViewById(R.id.recycle_menu);
        recycle_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);

        recycle_menu.setLayoutManager(layoutManager);


        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
        jobinfo = FirebaseDatabase.getInstance().getReference().child("ListOfJob1");

        loadMenu();

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
                System.out.println("Test Name:"+model.getJobName1()+":End");
                holder.txtTheNoOfGuard1.setText(model.getNoOfGuard1());
                holder.txtTheDate1.setText(model.getDate1());
                holder.txtTheLocation1.setText(model.getLocation1());
                holder.txtTheStatus1.setText(model.getStatus1());
                holder.txtTheType1.setText(model.getType1());
            }


        };
        recycle_menu.setAdapter(adapter);
    }


    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}