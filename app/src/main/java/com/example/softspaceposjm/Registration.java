package com.example.softspaceposjm;

import com.example.softspaceposjm.Login;
import com.example.softspaceposjm.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText edtUserName,edtICno,edtAddress,edtPassword,edtConfirmPassword,edtEmail;
    Button btnRegister, btnLogin;
    ImageButton btnNext;
    String matchPassword ;
    String matchPassword1 ;

    //    FirebaseDatabase database = FirebaseDatabase.getInstance();
//    final DatabaseReference table_user = database.getReference("User1");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // edtName = (MaterialEditText)findViewById(R.id.edtName);
        edtUserName = (EditText)findViewById(R.id.edtname);
        edtICno = (EditText)findViewById(R.id.edtIC);
//        edtAddress = (EditText)findViewById(R.id.edtAddress);
        edtPassword = (EditText)findViewById(R.id.edtpassword);
        edtConfirmPassword = (EditText)findViewById(R.id.edtcpassword);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        btnNext = (ImageButton)findViewById(R.id.btn_next);
//        btnRegister = (Button)findViewById(R.id.btn_next);
//        btnLogin = (Button)findViewById(R.id);
        System.out.println("Test329y932493229834");
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);
                finish();
            }
        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matchPassword = edtPassword.getText().toString();
                matchPassword1 = edtConfirmPassword.getText().toString();


                System.out.println("Test1111:"+edtUserName.getText() +"End/");
                if (edtUserName.length() < 8 || edtUserName.length() > 15) {
//                    mDialog.dismiss();

                    Toast.makeText(Registration.this, "User Name should be between 8 to 15", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (edtICno.length() < 12 || edtICno.length() > 12) {
//                    mDialog.dismiss();
                    Toast.makeText(Registration.this, "IC not valid", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(edtAddress==null){
                    //             mDialog.dismiss();
                    Toast.makeText(Registration.this, "Please enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edtPassword==null){
                    //             mDialog.dismiss();
                    Toast.makeText(Registration.this, "Please enter Password", Toast.LENGTH_SHORT).show();

                    return;
                }

                if(edtPassword.length() < 8 || edtPassword.length() > 20){
                    //             mDialog.dismiss();
                    Toast.makeText(Registration.this, "Password should be between 8 to 20", Toast.LENGTH_SHORT).show();

                    return;
                }
                //--
                if(!matchPassword.equals(matchPassword1)){
                    //             mDialog.dismiss();
                    Toast.makeText(Registration.this, "Password not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                //--0--
                if(edtEmail==null){
                    //             mDialog.dismiss();
                    Toast.makeText(Registration.this, "Please enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
//-------------------------------------

//                table_user.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if (dataSnapshot.child(edtUserName.getText().toString()).exists()) {
//                            //   mDialog.dismiss();
//                            //storageReference.child("profileImageUrl").child(fileName).delete();
//                            Toast.makeText(Registration.this, "Name already register", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//                        // EditText edtUserName,edtICno,edtAddress,edtPassword,edtConfirmPassword,edtEmail;
//                        Intent intent = new Intent(Registration.this, Login.class);
//                        startActivity(intent);
//                        finish();
//                        //  String Email = edtEmail.getText().toString().trim();
//                        //    String Password = edtPassword.getText().toString().trim();
//                        System.out.println("Test2:"+edtUserName);
//                        User user = new User(edtICno.getText().toString(), edtAddress.getText().toString(),edtPassword.getText().toString(), edtEmail.getText().toString());
//                        table_user.child(edtUserName.getText().toString()).setValue(user);
//                        Toast.makeText(Registration.this, "Sign up successfully!!", Toast.LENGTH_SHORT).show();
//                        finish();
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//                        //storageReference.child("profileImageUrl").child(fileName).delete();
//                    }
//                });




            }
        });


    }
}