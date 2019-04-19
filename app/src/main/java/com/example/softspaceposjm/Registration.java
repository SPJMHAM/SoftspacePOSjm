package com.example.softspaceposjm;

import com.example.softspaceposjm.Model.User;
import com.example.softspaceposjm.Req_Service.status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Registration extends AppCompatActivity {
    EditText edtUserName,edtICno,edtAddress,edtPassword,edtConfirmPassword,edtEmail;
    Button btnRegister, btnLogin;
    ImageView image;
    ImageView btnNext, back;
    String matchPassword ;
    String matchPassword1 ;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_user = database.getReference("User1");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        btnNext = findViewById(R.id.btn_next);
        image = findViewById(R.id.imageButton2);
        // edtName = (MaterialEditText)findViewById(R.id.edtName);
        edtUserName = (EditText)findViewById(R.id.edtUserName);
        edtICno = (EditText)findViewById(R.id.edtICno);
      //  edtAddress = (EditText)findViewById(R.id.edtAddress);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        edtConfirmPassword = (EditText)findViewById(R.id.edtConfirmPassword);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        back = findViewById(R.id.backBtn);
       // btnLogin = (Button)findViewById(R.id.btnLogin);
        System.out.println("Test329y932493229834");



        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matchPassword = edtPassword.getText().toString();
                matchPassword1 = edtConfirmPassword.getText().toString();


                System.out.println("Test1111:"+edtUserName.getText() +"End/");

//-------------------------------------

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.child(edtUserName.getText().toString()).exists()) {
                            //   mDialog.dismiss();
                            //storageReference.child("profileImageUrl").child(fileName).delete();
                            Toast.makeText(Registration.this, "Name already register", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent intent = new Intent(Registration.this, PermissionAccess.class);
                        startActivity(intent);
                        finish();
                        String TempText = "Empty";
                        System.out.println("Test2:"+edtUserName);
                        User user = new User(edtUserName.getText().toString(),edtICno.getText().toString(), TempText,edtPassword.getText().toString(), edtEmail.getText().toString());
                        table_user.child(edtUserName.getText().toString()).setValue(user);
                        Toast.makeText(Registration.this, "Sign up successfully!!", Toast.LENGTH_SHORT).show();
                        finish();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //storageReference.child("profileImageUrl").child(fileName).delete();
                    }
                });




            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivity(intent);
                    }
                });
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Registration.this, Login.class));
                    }
                });
            }
        });


    }
}
