package com.example.softspaceposjm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class PermissionAccess extends AppCompatActivity {

    ImageButton btn_permitAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_access);

        btn_permitAccess = (ImageButton) findViewById(R.id.btn_permitAccess);

        btn_permitAccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PermissionAccess.this, FingerprintAccess.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
