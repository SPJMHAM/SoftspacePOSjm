package com.example.softspaceposjm.Req_Service;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.example.softspaceposjm.R;
import com.example.softspaceposjm.services.Services_Activity;

public class status extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private statusAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        tabLayout = findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.viewpager_id);
        adapter = new statusAdapter(getSupportFragmentManager());
        BottomNavigationView bottomNav = findViewById(R.id.btm_nav);
        //add fragment
        adapter.Addfragment(new request_Fragment(),"Request");
        adapter.Addfragment(new booked_Fragment(),"Booked");
        adapter.Addfragment(new past_Fragment(),"Past");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if(itemId == R.id.navigation_home){
                    Intent home = new Intent(status.this, Services_Activity.class);
                    startActivity(home);
                }

//                switch (menuItem.getItemId()){
//                    case R.id.navigation_home:
//                        Intent home = new Intent(status.this, Services_Activity.class);
//                        startActivity(home);
//                        break;
//                    case R.id.navigation_dashboard:
//                        Intent dashb = new Intent(status.this, status.class);
//                        startActivity(dashb);
//                        break;
//                }
                return true;
            }
        });

    }
}