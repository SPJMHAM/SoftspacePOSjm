package com.example.softspaceposjm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import  com.example.softspaceposjm.Model.service_Info;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class chicoDetails extends AppCompatActivity {
    DatabaseReference service_Info1;
    private ImageView buttonBack, buttonNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chico_details);
        final String TestCurrentUser;
        TestCurrentUser = "ggggjhjh";
        service_Info1 = FirebaseDatabase.getInstance().getReference().child("service_Info").child(TestCurrentUser);
        buttonBack = findViewById(R.id.btn_back);
        buttonNext = findViewById(R.id.btn_next);
        Intent quote = getIntent();
        final String ServiceID=  quote.getStringExtra("ServiceID");
        final String furtherStatus=  quote.getStringExtra("furtherStatus");
        final String status=  quote.getStringExtra("status");
        final String addressInfo=  quote.getStringExtra("addressInfo");
        final String JobName=  quote.getStringExtra("JobName");
        final String NoOfGuard=  quote.getStringExtra("NoOfGuard");
        final String theDate=  quote.getStringExtra("theDate");

        System.out.println("Services ID3:"+ServiceID+":End");
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(chicoDetails.this, Offers.class));
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(chicoDetails.this, ServiceBooked.class));


                service_Info1.addValueEventListener(new ValueEventListener(){

                    public void onDataChange(DataSnapshot dataSnapshot) {



                        //service_Info user = dataSnapshot.child(ServiceID.toString()).getValue(service_Info.class);

                      //  if(status.equals("Request")) {
                        if(status!="booked") {
                            service_Info SInfo = new service_Info("ggggjhjh", ServiceID, "Armed", "Foreigners", "booked", NoOfGuard, addressInfo, theDate, JobName, "");

                            service_Info1.child(ServiceID).setValue(SInfo);
//                        System.out.println("tEST USER GET:"+user.getServiceID().toString()+":eND");
                            //    System.out.println("tEST USER GET:"+user.getNoOfPax().toString()+":eND");

                        }else{
                            startActivity(new Intent(chicoDetails.this, chicoDetails.class));
                        }

                        return;
                    }
                    public void onCancelled(DatabaseError databaseError){

                    }
                });
                startActivity(new Intent(chicoDetails.this, ServiceBooked.class));

            }
        });
    }
}
