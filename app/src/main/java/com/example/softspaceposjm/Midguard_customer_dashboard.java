package com.example.softspaceposjm;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.softspaceposjm.R;

public class Midguard_customer_dashboard extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemReselectedListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_midguard_customer_dashboard);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) this);
        navigation.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.fragmentservice:
                            selectedFragment = new ServiceFragment();
                            break;
                        case R.id.fragmentnotification:
                            selectedFragment = new NotificationFragment();
                            break;
                        case R.id.aboutfragment:
                            selectedFragment = new AboutFragment();
                            break;

                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

//    private boolean loadFragment(Fragment fragment){
//        if(fragment != null){
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.navigation_dashboard, fragment)
//                    .commit();
//
//            return true;
//
//        }
//        return false;
//
//    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
        Fragment fragment =null;
        switch(menuItem.getItemId()){
            case R.menu.navigation:
                fragment = new ServiceFragment();
                break;



        }

    }

}
