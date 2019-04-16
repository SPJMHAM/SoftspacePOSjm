package com.example.softspaceposjm;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class FingerprintAccess extends AppCompatActivity {


    ImageButton btn_nexpage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint_access);

//        btn_login = (ImageButton) findViewById(R.id.btn_login);
        btn_nexpage = (ImageButton) findViewById(R.id.btn_fingerAccess);

        btn_nexpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FingerprintAccess.this, RegistrationComplete.class);
                startActivity(intent);
                finish();
            }
        });


}

}

