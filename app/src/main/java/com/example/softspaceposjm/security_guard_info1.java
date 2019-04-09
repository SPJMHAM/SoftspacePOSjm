package com.example.softspaceposjm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class security_guard_info1 extends AppCompatActivity {
Button btnSubmit2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_guard_info1);


        btnSubmit2 = (Button)findViewById(R.id.btnSubmit2);

        btnSubmit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(security_guard_info1.this, TestM1.class);
                startActivity(intent);
                finish();
            }
        });

    }
}
