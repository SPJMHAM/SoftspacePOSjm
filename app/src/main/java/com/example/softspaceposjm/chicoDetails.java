package com.example.softspaceposjm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class chicoDetails extends AppCompatActivity {

    private ImageView buttonBack, buttonNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chico_details);

        buttonBack = findViewById(R.id.btn_back);
        buttonNext = findViewById(R.id.btn_next);

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(chicoDetails.this, Offers.class));
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(chicoDetails.this, ServiceBooked.class));
            }
        });
    }
}
