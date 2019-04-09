package com.example.softspaceposjm;

import com.example.softspaceposjm.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class Details extends AppCompatActivity {

    TextView edtName, edtCompany,   edtPrice,   edtQuote, edtDescription;
    String name, company, quote, desc, price;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_user = database.getReference("GuardInfo");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        edtName = (TextView)findViewById(R.id.edtName);
        edtCompany = (TextView) findViewById(R.id.edtCompany);
        edtDescription = (TextView)findViewById(R.id.edtDescription);
        edtPrice = (TextView)findViewById(R.id.edtPrice);
        edtQuote = (TextView)findViewById(R.id.edtQuote);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("GuardInfo");




    }

    public void dataRetrieve(DataSnapshot dataSnapshot){
//        for(DataSnapshot ds : dataSnapshot.getChildren()){
//            String name = ds.child("Name").getValue().toString();
//            String company = ds.child("Company").getValue().toString();
//            String quote = ds.child("Quote").getValue().toString();
//            String price = ds.child("Price").getValue().toString();
//            String desc = ds.child("Description").getValue().toString();
//        }

        edtName.setText(dataSnapshot.child("Name").getValue().toString());
        edtQuote.setText(dataSnapshot.child("Quote").getValue().toString());
        edtPrice.setText(dataSnapshot.child("Price").getValue().toString());
        edtDescription.setText(dataSnapshot.child("Description").getValue().toString());
        edtCompany.setText(dataSnapshot.child("Company").getValue().toString());


    }
}
