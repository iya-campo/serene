package com.example.grey.serene;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Settings extends AppCompatActivity {

    public static Activity settings;

    DatabaseReference databaseReference;
    TextView name, email;

    String userID = Main.userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings = this;

        //Header Buttons
        Button profileButton = (Button) findViewById(R.id.profileButton);

        //Settings Buttons
        Button notifsButton = (Button) findViewById(R.id.notifsButton);
        Button tcButton = (Button) findViewById(R.id.tcButton);
        Button ppButton = (Button) findViewById(R.id.ppButton);
        Button dataButton = (Button) findViewById(R.id.dataButton);

        name = (TextView) findViewById(R.id.userText);
        email = (TextView) findViewById(R.id.myEmailText);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fbNickname = dataSnapshot.child(userID).child("nickname").getValue().toString();
                String fbEmail = dataSnapshot.child(userID).child("email").getValue().toString();
                name.setText(fbNickname);
                email.setText(fbEmail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        //Logout Button
        Button logoutButton = (Button) findViewById(R.id.logoutButton);

        profileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }

        });

        notifsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showSettingsNotifs = new Intent(getApplicationContext(), SettingsNotifs.class);
                showSettingsNotifs.putExtra("userID", userID);
                startActivity(showSettingsNotifs);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        tcButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showSettingsTC = new Intent(getApplicationContext(), SettingsTC.class);
                showSettingsTC.putExtra("userID", userID);
                startActivity(showSettingsTC);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        ppButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showSettingsPP = new Intent(getApplicationContext(), SettingsPP.class);
                showSettingsPP.putExtra("userID", userID);
                startActivity(showSettingsPP);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        dataButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showSettingsData = new Intent(getApplicationContext(), SettingsData.class);
                showSettingsData.putExtra("userID", userID);
                startActivity(showSettingsData);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                /** Session End **/
                SharedPrefs.saveSharedSetting(Settings.this, "Serene", "true");

                AlarmManager ALARM1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent myIntent = new Intent(getApplicationContext(), NotificationReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        getApplicationContext(), 1, myIntent, 0);

                ALARM1.cancel(pendingIntent);

                Intent logout = new Intent(getApplicationContext(), AccountLogin.class);
                startActivity(logout);
                finishAffinity();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
