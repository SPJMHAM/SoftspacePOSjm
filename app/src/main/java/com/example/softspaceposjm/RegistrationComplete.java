package com.example.softspaceposjm;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.softspaceposjm.services.Services_Activity;

public class RegistrationComplete extends AppCompatActivity {

    private ImageView

            btn_next1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_complete);

        btn_next1 = findViewById(R.id.hire_btn);

        btn_next1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationComplete.this, Login.class));
            }
        });
    }
}
