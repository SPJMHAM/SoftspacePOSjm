package com.example.softspaceposjm.services;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.softspaceposjm.Req_Service.status;
import com.example.softspaceposjm.services.Services_Activity;
import com.example.softspaceposjm.R;

public class req_done extends AppCompatActivity {
    private Button btBack, btReq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_done);

        btBack = findViewById(R.id.backBtn);
        btReq = findViewById(R.id.viewReq);
        btBack.setPaintFlags(btBack.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(req_done.this, Services_Activity.class));
            }
        });

        btReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(req_done.this, status.class));
            }
        });
    }
}
