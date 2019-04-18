package com.example.softspaceposjm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.softspaceposjm.Req_Service.status;

public class Offers extends AppCompatActivity {

    private ImageView btn_chico, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        btn_chico = findViewById(R.id.chico_btn);
        back = findViewById(R.id.backImg);

        btn_chico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Offers.this, chicoDetails.class));
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Offers.this, status.class));
            }
        });
    }
}
