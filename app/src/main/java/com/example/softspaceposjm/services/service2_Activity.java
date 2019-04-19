package com.example.softspaceposjm.services;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import com.example.softspaceposjm.R;

public class service2_Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private Button datetime, BtnSubmit;
    private TextView shwDateTime;
    int day, month, year, hour, minute;
    int day_x, month_x, year_X, hour_x, minute_x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service2_);

        datetime = findViewById(R.id.datetimeBtn);
        shwDateTime = findViewById(R.id.DateTimeTxt);
        BtnSubmit = findViewById(R.id.btSubmit);

        datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar =  Calendar.getInstance();
                year = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(service2_Activity.this, service2_Activity.this,year,month,day);
                datePickerDialog.show();
            }
        });

        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(service2_Activity.this, confirmation.class));
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        year_X = year;
        month_x = month;
        day_x = dayOfMonth;
        Calendar calendar = Calendar.getInstance();
        hour = calendar.get(Calendar.HOUR);
        minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(service2_Activity.this,service2_Activity.this,hour,minute, true);
        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hour_x = hourOfDay;
        minute_x = minute;
        shwDateTime.setText("Date: "+ year_X+ "/"+ month_x+ "/" + day_x + "\nTime: " + hour_x + ":" + minute_x);
    }
}
