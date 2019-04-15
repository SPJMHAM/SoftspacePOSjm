package com.example.softspaceposjm.Req_Service;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;

import com.example.softspaceposjm.R;

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
    }
}