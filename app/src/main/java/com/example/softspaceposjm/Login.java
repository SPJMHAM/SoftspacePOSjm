package com.example.softspaceposjm;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.example.softspaceposjm.services.*;
import com.example.softspaceposjm.Common.CUser;
import com.example.softspaceposjm.services.serviceInfo_Activity;
import com.example.softspaceposjm.Model.User;
import com.example.softspaceposjm.services.Services_Activity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    EditText edtUsername, edtPassword;
    Button btn_register, btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = (EditText) findViewById(R.id.username);
        edtPassword = (EditText) findViewById(R.id.password);
        btn_login = findViewById(R.id.loginBtn);
        btn_register = (Button) findViewById(R.id.regBtn);
        btn_register.setPaintFlags(btn_register.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User1");

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Login.this, Registration.class));
            }
        });


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    final ProgressDialog mDialog = new ProgressDialog(login_page1.this);
                //  mDialog.setMessage("Please waiting...");
                //mDialog.show();
                String UName = edtUsername.getText().toString();
               System.out.println("Test user name display:"+UName+":End.");
                System.out.println("Test user name display2:"+edtUsername+":End.");
               System.out.println("Test user password display:"+edtPassword.getText().toString()+":End.");
                if(edtUsername==null){
                       //   mDialog.dismiss();
                    Toast.makeText(Login.this, "Please enter User name", Toast.LENGTH_SHORT).show();
                 return;
                }
                if(edtPassword==null){
                    //             mDialog.dismiss();
                    Toast.makeText(Login.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(edtUsername != null && edtPassword != null){

                    //      if(1==1){

                    table_user.addValueEventListener(new ValueEventListener(){

                        public void onDataChange(DataSnapshot dataSnapshot) {


                            //Check if user not exist in database
                            if (dataSnapshot.child(edtUsername.getText().toString()).exists()) {

                                //get user information
                                //     mDialog.dismiss();

                                User user = dataSnapshot.child(edtUsername.getText().toString()).getValue(User.class);

                                System.out.println("tEST USER GET:"+user+":eND");

                                //---
                                if( edtPassword.length() ==0 || edtUsername.length() ==0){
                                    Toast.makeText(Login.this, "Please enter user name & password", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                else{


                                    if (user.getPassword().equals(edtPassword.getText().toString())) {


                                        Toast.makeText(Login.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                                        Intent home = new Intent(Login.this, Services_Activity.class);
                                        //    MainMenuPage.putExtra("loginName",1324557);
                                        // home.putExtra("Name",adapter.get)

                                            CUser.currentUser = user;
                                        //  edtEmail.getText().toString()
                                        //    Common.getCurrentEmail = user;
                                        //    Common.getCurrentImage = user;

                                        startActivity(home);
                                        finish();
                                    } else {
                                        Toast.makeText(Login.this, "Login In fail!, password not match", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                }
                                //---

                            } else {
                                Toast.makeText(Login.this, "Sorry User not exist!", Toast.LENGTH_SHORT).show();
                                return;
                            }

                        }
                        public void onCancelled(DatabaseError databaseError){

                        }
                    });
                }else{

                    Toast.makeText(Login.this, "Please enter data", Toast.LENGTH_SHORT).show();
                    return;


                }
            }
        });
    }
}
