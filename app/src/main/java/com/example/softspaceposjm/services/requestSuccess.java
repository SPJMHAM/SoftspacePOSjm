package com.example.softspaceposjm.services;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.softspaceposjm.R;
import com.example.softspaceposjm.Req_Service.status;

public class requestSuccess extends AppCompatActivity {

    private Button btn_request;
    private ImageView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_success);

        btn_request = findViewById(R.id.backBtn);
        view = findViewById(R.id.viewBtn);
        btn_request.setPaintFlags(btn_request.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requestSuccess.this, Services_Activity.class));
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(requestSuccess.this, status.class));
            }
        });
    }
}
