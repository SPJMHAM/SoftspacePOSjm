package com.example.softspaceposjm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.softspaceposjm.Req_Service.status;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Offers extends AppCompatActivity {

    private ImageView btn_chico, back;

    DatabaseReference service_Info1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        final String TestCurrentUser;
        TestCurrentUser = "ggggjhjh";
        service_Info1 = FirebaseDatabase.getInstance().getReference().child("service_Info").child(TestCurrentUser);
        btn_chico = findViewById(R.id.chico_btn);
        back = findViewById(R.id.backImg);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //final DatabaseReference table_user = database.getReference("User1");
        Intent quote = getIntent();
        final String ServiceID=  quote.getStringExtra("ServiceID");
        final String furtherStatus=  quote.getStringExtra("furtherStatus");
        final String status=  quote.getStringExtra("status");
        final String addressInfo=  quote.getStringExtra("addressInfo");
        final String JobName=  quote.getStringExtra("JobName");
        final String NoOfGuard=  quote.getStringExtra("NoOfGuard");
        final String theDate=  quote.getStringExtra("theDate");

        System.out.println("Services ID2:"+ServiceID+":End");



        btn_chico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(Offers.this, chicoDetails.class);
                myIntent.putExtra("ServiceID", ServiceID);
                myIntent.putExtra("furtherStatus", furtherStatus);
                myIntent.putExtra("status", status);
                myIntent.putExtra("addressInfo", addressInfo);
                myIntent.putExtra("JobName", JobName);
                myIntent.putExtra("NoOfGuard", NoOfGuard);
                myIntent.putExtra("theDate", theDate);
                System.out.println("Services ID00:"+ServiceID+":End");
                startActivity(myIntent);



            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Offers.this, status.class));
            }
        });
    }
}
