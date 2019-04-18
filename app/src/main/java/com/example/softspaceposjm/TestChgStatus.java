package com.example.softspaceposjm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TestChgStatus extends AppCompatActivity {

    Button btnTestChg;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String TestCurrentUser = "ggggjhjh";
    final DatabaseReference table_user = database.getReference().child("service_Info").child(TestCurrentUser);

    // service_Info = FirebaseDatabase.getInstance().getReference().child("service_Info").child(TestCurrentUser);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_chg_status);





    }
}
