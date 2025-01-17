package com.example.grey.serene;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class SettingsNotifs extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    public static Activity settingsNotifs;

    Button addButton, deleteButton, onButton, offButton;
    DatabaseReference ref;

    String changeNotifications;
    String currentName, currentTime, changedTime;
    Boolean clicked = false;

    String userID = Main.userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_notifs);

        settingsNotifs = this;

        //Header Buttons
        Button profileButton = (Button) findViewById(R.id.profileButton);

        //Return Button
        Button settingsTitleButton = (Button) findViewById(R.id.settingsTitleButton);

        //Settings Buttons
        addButton = (Button) findViewById(R.id.addButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        onButton = (Button) findViewById(R.id.onButton);
        offButton = (Button) findViewById(R.id.offButton);

        final TextView userText = (TextView) findViewById(R.id.userText);

        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fbNickname = dataSnapshot.child("nickname").getValue().toString();
                String alarmName = dataSnapshot.child("alarm").getValue().toString();
                String alarmTime = dataSnapshot.child("alarmTime").getValue().toString();
                changeNotifications = dataSnapshot.child("notifications").getValue().toString();
                userText.setText(fbNickname);

                if (alarmName.equals("") && alarmTime.equals("")) {
                    addButton.setText("Add");
                } else {
                    addButton.setText("Change");
                }

                if (!clicked) {
                    currentName = alarmName;
                    currentTime = alarmTime;
                }

                if (changeNotifications.equals("yes")) {
                    onButton.setBackgroundResource(R.drawable.btn_journal_selected);
                } else {
                    offButton.setBackgroundResource(R.drawable.btn_journal_selected);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        profileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showProfile = new Intent(getApplicationContext(), Profile.class);
                startActivity(showProfile);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }

        });

        settingsTitleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }

        });

        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Function to add alarm
                clicked = true;
                currentName = "my alarm";
                TimePickerFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Function to delete alarm
                ref.child("alarmTime").setValue("");
                deleteAlarm();
            }

        });

        onButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (changeNotifications.equals("no")) {
                    changeNotifications = "yes";
                    ref.child("notifications").setValue(changeNotifications);
                    onButton.setBackgroundResource(R.drawable.btn_journal_selected);
                    offButton.setBackgroundResource(R.drawable.btn_selector2);
                }
            }

        });

        offButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (changeNotifications.equals("yes")) {
                    changeNotifications = "no";
                    ref.child("notifications").setValue(changeNotifications);
                    offButton.setBackgroundResource(R.drawable.btn_journal_selected);
                    onButton.setBackgroundResource(R.drawable.btn_selector2);
                }
            }

        });
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        int hour = hourOfDay % 12;
        if (hour == 0)
            hour = 12;
        addButton.setText(String.format("%02d:%02d %s", hour, minute, hourOfDay < 12 ? "am" : "pm"));
        changedTime = addButton.getText().toString();
    }

    private void deleteAlarm() {
        AlarmManager ALARM1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(), 1, myIntent, 0);

        ALARM1.cancel(pendingIntent);
    }
/*
    private void changeAlarm(String changedTime) {
        int sHour = Integer.valueOf(changedTime.substring(0, 2));
        int sMin = Integer.valueOf(changedTime.substring(3, 5));
        AlarmManager ALARM1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent myIntent = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(), 1, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar calendar = Calendar.getInstance();
        long currentSDL = calendar.getTimeInMillis();
        calendar.set(calendar.HOUR_OF_DAY, sHour);
        calendar.set(calendar.MINUTE, sMin);
        calendar.set(calendar.SECOND, 0);
        calendar.set(calendar.MILLISECOND, 0);
        long sdl = calendar.getTimeInMillis();


        ALARM1.setInexactRepeating(AlarmManager.RTC_WAKEUP, sdl,
                AlarmManager.INTERVAL_DAY, pendingIntent);

        if(currentSDL > sdl){
            ALARM1.cancel(pendingIntent);
        }



    } */

    public void finish() {
        if (clicked) {
            ref.child("alarm").setValue(currentName);
            ref.child("alarmTime").setValue(changedTime);
            if (changeNotifications.equals("yes")) {
                //changeAlarm(changedTime);
            }
        } else {
            ref.child("alarm").setValue(currentName);
            ref.child("alarmTime").setValue(currentTime);
        }
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
