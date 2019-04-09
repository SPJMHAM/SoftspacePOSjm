package com.example.softspaceposjm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class user_dashboard extends AppCompatActivity {

    ImageButton ibtnLand;
    Button btnHome1,btnJob1,btnNotification1,btnProfile1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);


        ibtnLand = (ImageButton)findViewById(R.id.ibtnLand);
        btnHome1 = (Button)findViewById(R.id.btnHome1);
        btnNotification1 = (Button)findViewById(R.id.btnNotification1);
        btnProfile1 = (Button)findViewById(R.id.btnProfile1);

        ibtnLand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_dashboard.this, sea_services.class);
                startActivity(intent);
                finish();
            }
        });



    }
}
