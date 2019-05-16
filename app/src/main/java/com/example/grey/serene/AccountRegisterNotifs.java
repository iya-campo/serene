package com.example.grey.serene;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AccountRegisterNotifs extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    Button timeButton;
    Button yes, no;

    Users user;
    long maxid;
    String username, password, email, nickname;
    String alarm, notif, interpreter, startDate;
    int age;

    DatabaseReference ref, userNumberRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register_notifs);

        timeButton = (Button) findViewById(R.id.timeButton);
        Button next2Button = (Button) findViewById(R.id.next2Button);

        final EditText alarmField = (EditText) findViewById(R.id.alarmTitleField);
        final EditText psychField = (EditText) findViewById(R.id.psychField);

        maxid = 0;
        ref = FirebaseDatabase.getInstance().getReference().child("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxid = dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Intent myIntent = getIntent();

        if (myIntent.hasExtra("username")) {
            username = myIntent.getStringExtra("username");
        }
        if (myIntent.hasExtra("email")) {
            email = myIntent.getStringExtra("email");
        }
        if (myIntent.hasExtra("password")) {
            password = myIntent.getStringExtra("password");
        }
        if (myIntent.hasExtra("nickname")) {
            nickname = myIntent.getStringExtra("nickname");
        }
        if (myIntent.hasExtra("age")) {
            age = myIntent.getIntExtra("age", age);
        }

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        notif = "no";

        yes = (Button) findViewById(R.id.yes1Button);
        no = (Button) findViewById(R.id.no1Button);

        yes.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                notif = "yes";
            }
        });
        no.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                notif = "no";
            }
        });

        next2Button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showLogin = new Intent(getApplicationContext(), AccountLogin.class);

                alarm = alarmField.getText().toString();
                if (alarm.equals("")) {
                    alarm = "Untitled Alarm";
                }
                interpreter = psychField.getText().toString();

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat mdformat = new SimpleDateFormat("dd MMMM, yyyy");
                startDate = mdformat.format(calendar.getTime());

                user = new Users(maxid + 1, username, password, email, nickname, age, notif, alarm, interpreter, startDate);
                ref.push().setValue(user);

                Toast.makeText(AccountRegisterNotifs.this, "Registration successful!", Toast.LENGTH_LONG).show();

                Intent login = new Intent(getApplicationContext(), AccountLogin.class);
                startActivity(login);
                finishAffinity();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        int hour = hourOfDay % 12;
        if (hour == 0)
            hour = 12;
        timeButton.setText(String.format("%02d:%02d %s", hour, minute, hourOfDay < 12 ? "am" : "pm"));
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
