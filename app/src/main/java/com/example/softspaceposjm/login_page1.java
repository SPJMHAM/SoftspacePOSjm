package com.example.softspaceposjm;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.softspaceposjm.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login_page1 extends AppCompatActivity {
    EditText edtUsername,edtPassword;
    Button btnCLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page1);
        /*
        *
           edtPassword =(MaterialEditText)findViewById(R.id.edtPassword);
        edtPhone =(MaterialEditText)findViewById(R.id.edtPhone);
        btnSignIn = (Button)findViewById(R.id.btnSignIn);
        * */
        edtUsername = (EditText)findViewById(R.id.edtUsername);
        edtPassword = (EditText)findViewById(R.id.edtPassword);
        btnCLogin = (Button) findViewById(R.id.btnCLogin);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User1");


        btnCLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    final ProgressDialog mDialog = new ProgressDialog(login_page1.this);
              //  mDialog.setMessage("Please waiting...");
                //mDialog.show();

                if(edtUsername==null){
                    //             mDialog.dismiss();
                    Toast.makeText(login_page1.this, "Please enter User name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(edtPassword==null){
                    //             mDialog.dismiss();
                    Toast.makeText(login_page1.this, "Please enter password", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(login_page1.this, "Please enter user name & password", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                                else{


                                if (user.getPassword().equals(edtPassword.getText().toString())) {


                                    Toast.makeText(login_page1.this, "Login Successfully!", Toast.LENGTH_SHORT).show();
                                    Intent home = new Intent(login_page1.this, user_dashboard.class);
                                    //    MainMenuPage.putExtra("loginName",1324557);
                                    // home.putExtra("Name",adapter.get)

                                //    Common.currentUser = user;
                                    //  edtEmail.getText().toString()
                                //    Common.getCurrentEmail = user;
                                //    Common.getCurrentImage = user;
                                    startActivity(home);
                                    finish();
                                } else {
                                    Toast.makeText(login_page1.this, "Login In fail!, password not match", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                }
                                //---

                            } else {
                                Toast.makeText(login_page1.this, "Sorry User not exist!", Toast.LENGTH_SHORT).show();
                                return;
                            }

                    }
                    public void onCancelled(DatabaseError databaseError){

                    }
                });
}else{

    Toast.makeText(login_page1.this, "Please enter data", Toast.LENGTH_SHORT).show();
    return;


}
            }
        });
    }
}
