package com.example.softspaceposjm.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.softspaceposjm.R;

public class confirmation extends AppCompatActivity {

    private ImageView btn_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        btn_confirm = findViewById(R.id.confirmBtn);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(confirmation.this, requestSuccess.class));
            }
        });
    }
}
