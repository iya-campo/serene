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

public class SettingsNotifs extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    public static Activity settingsNotifs;

    DatabaseReference ref;

    String userID = Main.userID;

    Button addButton;

    String currentTime, changedTime;

    Boolean clicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_notifs);

        settingsNotifs = this;

        final TextView userText = (TextView) findViewById(R.id.userText);

        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fbNickname = dataSnapshot.child("nickname").getValue().toString();
                String alarmName = dataSnapshot.child("alarm").getValue().toString();
                String alarmTime = dataSnapshot.child("alarmTime").getValue().toString();
                userText.setText(fbNickname);


                if((alarmName != null) && (alarmTime != null)){
                    addButton.setText("Change");
                }
                if(!clicked){
                    currentTime = alarmTime;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        //Header Buttons
        Button profileButton = (Button) findViewById(R.id.profileButton);

        //Return Button
        Button settingsTitleButton = (Button) findViewById(R.id.settingsTitleButton);

        //Settings Buttons
        addButton = (Button) findViewById(R.id.addButton);
        Button deleteButton = (Button) findViewById(R.id.deleteButton);
        Button onButton = (Button) findViewById(R.id.onButton);
        Button offButton = (Button) findViewById(R.id.offButton);

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
                TimePickerFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
                clicked = true;
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
                String changeNotifications = "yes";
                ref.child("notifications").setValue(changeNotifications);
            }

        });

        offButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String changeNotifications = "no";
                ref.child("notifications").setValue(changeNotifications);

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

    private void deleteAlarm(){
        AlarmManager ALARM1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(), 1, myIntent, 0);

        ALARM1.cancel(pendingIntent);
    }

    public void finish() {
        if(clicked) {
            ref.child("alarmTime").setValue(changedTime);
        }else{
            ref.child("alarmTime").setValue(currentTime);
        }
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
