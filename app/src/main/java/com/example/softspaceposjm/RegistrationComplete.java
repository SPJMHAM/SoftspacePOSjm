package com.example.softspaceposjm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import com.example.softspaceposjm.services.serviceInfo_Activity;

public class RegistrationComplete extends AppCompatActivity {

    ImageButton btn_nextpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_complete);

        btn_nextpage = (ImageButton) findViewById(R.id.btn_RegisterComplete);

        btn_nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationComplete.this, serviceInfo_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
