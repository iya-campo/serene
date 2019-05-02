package com.example.grey.serene;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Settings extends AppCompatActivity {

    String userID;
    DatabaseReference databaseReference;
    String fbNickname;
    TextView name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        //Header Buttons
        Button profileButton = (Button) findViewById(R.id.profileButton);

        //Settings Buttons
        Button notifsButton = (Button) findViewById(R.id.notifsButton);
        Button tcButton = (Button) findViewById(R.id.tcButton);
        Button ppButton = (Button) findViewById(R.id.ppButton);
        Button dataButton = (Button) findViewById(R.id.dataButton);

        name = (TextView) findViewById(R.id.userText);

        Intent myIntent = getIntent();
        if (myIntent.hasExtra("userID")) {
            userID = myIntent.getStringExtra("userID");
        }

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fbNickname = dataSnapshot.child(userID).child("nickname").getValue().toString();
                name.setText(fbNickname);
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
                startActivity(showSettingsNotifs);
                showSettingsNotifs.putExtra("UserID", userID);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        tcButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showSettingsTC = new Intent(getApplicationContext(), SettingsTC.class);
                startActivity(showSettingsTC);
                showSettingsTC.putExtra("UserID", userID);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        ppButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showSettingsPP = new Intent(getApplicationContext(), SettingsPP.class);
                startActivity(showSettingsPP);
                showSettingsPP.putExtra("UserID", userID);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        dataButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showSettingsData = new Intent(getApplicationContext(), SettingsData.class);
                startActivity(showSettingsData);
                showSettingsData.putExtra("UserID", userID);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        logoutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent logout = new Intent(getApplicationContext(), AccountLogin.class);
                startActivity(logout);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
