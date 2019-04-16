package com.example.softspaceposjm.services;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.softspaceposjm.R;
import com.example.softspaceposjm.Req_Service.status;

import android.content.Intent;

import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class Services_Activity extends AppCompatActivity {
    private ImageView landView, marineView, privateInvest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_);

        landView = findViewById(R.id.LandView);
        marineView = findViewById(R.id.MarineView);
        privateInvest = findViewById(R.id.PrivateView);
        BottomNavigationView bottomNav = findViewById(R.id.btm_nav);

        landView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Services_Activity.this, serviceInfo_Activity.class));
            }
        });

        marineView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Services_Activity.this, serviceInfo_Activity.class));
            }
        });

        privateInvest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Services_Activity.this, serviceInfo_Activity.class));
            }
        });

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.navigation_dashboard) {
                    Intent home = new Intent(Services_Activity.this, status.class);
                    startActivity(home);
                }
                return true;
            }
        });
    }
}
