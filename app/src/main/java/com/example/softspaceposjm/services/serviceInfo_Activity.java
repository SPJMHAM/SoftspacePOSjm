package com.example.softspaceposjm.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.softspaceposjm.R;
import com.example.softspaceposjm.Common.CUser;
import com.example.softspaceposjm.Model.service_Info;
import com.example.softspaceposjm.TestM1;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class serviceInfo_Activity extends AppCompatActivity {

    private ImageView imageLogo;
    private TextView guard, security, suitable, danger, pubFigure, mild, characteristics, eqType, nationality, agent, drag;
    private Button next;
    private RadioGroup group, group2;
    private RadioButton local,foreigner, arm, unarm;
    private SeekBar NoOfAgent;
    int progress_value;

    private FirebaseDatabase database;
    private DatabaseReference userRef, service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_info_);

        final String TestCurrentUser;
        TestCurrentUser = "ggggjhjh";
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("User");
        service = database.getReference("service_Info");

        imageLogo = findViewById(R.id.guardLogo);
        guard = findViewById(R.id.guardTxt);
        security = findViewById(R.id.riskTxt);
        suitable = findViewById(R.id.suitTxt);
        danger = findViewById(R.id.dangerTxt);
        mild = findViewById(R.id.mildTxt);
        pubFigure = findViewById(R.id.publicTxt);
        characteristics = findViewById(R.id.characteristicsTxt);
        eqType = findViewById(R.id.eqTypeTxt);
        nationality = findViewById(R.id.nationTxt);
        eqType = findViewById(R.id.eqTypeTxt);
        drag = findViewById(R.id.dragTxt);

        next = findViewById(R.id.nextBtn);
        group = findViewById(R.id.radioGroup);
        group2 = findViewById(R.id.radioGroup2);

        local = findViewById(R.id.localBtn);
        foreigner = findViewById(R.id.foreignersBtn);
        arm = findViewById(R.id.armBtn);
        unarm = findViewById(R.id.unarmBtn);
        seekbar();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = null;
                String nationality = null;
                String getStatus = null;
                String reqDate = "4 April - 8 April 2019, 9am - 6pm";
                String jobTitle = "Public Figure Protection";
                if(arm.isChecked()){
                    type = "Armed";
                }
                else if(unarm.isChecked()){
                    type = "Unarmed";
                }
                if (local.isChecked()){
                    nationality = "Local";
                }
                else if(foreigner.isChecked()){
                    nationality = "Foreigners";
                }

                if(type != null && nationality != null && NoOfAgent != null)
                {
                    getStatus = "Request";
                }
                String pax = Integer.toString(progress_value);

                Intent intent = new Intent(serviceInfo_Activity.this, TestM1.class);
                intent.putExtra("Type", type);
                intent.putExtra("nationality", nationality);
                intent.putExtra("status", getStatus);
                intent.putExtra("pax", pax);
                intent.putExtra("date", reqDate);
                intent.putExtra("job", jobTitle);
                startActivity(intent);

            }


        });

    }
    public void seekbar(){
        NoOfAgent = findViewById(R.id.seekBar3);
        agent = findViewById(R.id.paxTxt);
        agent.setText(NoOfAgent.getProgress() + "/" +NoOfAgent.getMax() + "Pax");

        NoOfAgent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress_value = progress;
                agent.setText(NoOfAgent.getProgress() + "/" +NoOfAgent.getMax() + "Pax");
                //Toast.makeText(serviceInfo_Activity.this, "SeekBar in progress", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(serviceInfo_Activity.this, "SeekBar in StartTracking", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                agent.setText(NoOfAgent.getProgress() + "/" +NoOfAgent.getMax() + "Pax");
                //Toast.makeText(serviceInfo_Activity.this, "SeekBar in StopTracking", Toast.LENGTH_LONG).show();
            }
        });
    }
}
