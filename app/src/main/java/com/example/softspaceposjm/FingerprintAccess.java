package com.example.softspaceposjm;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class FingerprintAccess extends AppCompatActivity {
    private Button maybe;
    private ImageView

            btn_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_access);

        btn_next = findViewById(R.id.btn_allow);
        maybe = findViewById(R.id.maybe);
        maybe.setPaintFlags(maybe.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FingerprintAccess.this,RegistrationComplete.class));
            }
        });
    }
}
