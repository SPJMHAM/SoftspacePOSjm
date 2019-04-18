package com.example.softspaceposjm;
import com.example.softspaceposjm.Req_Service.status;
import com.example.softspaceposjm.services.Services_Activity;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class bookingSuccess extends AppCompatActivity {

    private Button btn_book, home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_success);

        btn_book = findViewById(R.id.btn_book);
        home = findViewById(R.id.homeBtn);
        home.setPaintFlags(home.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        btn_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(bookingSuccess.this, status.class));
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(bookingSuccess.this, Services_Activity.class));
            }
        });
    }
}
