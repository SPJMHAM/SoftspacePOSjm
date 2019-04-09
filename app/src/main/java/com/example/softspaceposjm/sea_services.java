package com.example.softspaceposjm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sea_services extends AppCompatActivity {

    Button btnSecurity1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sea_services);


        btnSecurity1 = (Button)findViewById(R.id.btnSecurity1);

        btnSecurity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(sea_services.this, security_guard_info1.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
