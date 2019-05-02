package com.example.grey.serene;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsNotifs extends AppCompatActivity {

    String userID;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_notifs);

        final TextView userText = (TextView) findViewById(R.id.userText);

        Intent myIntent = getIntent();
        if (myIntent.hasExtra("userID")) {
            userID = myIntent.getStringExtra("userID");
        }

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fbNickname = dataSnapshot.child(userID).child("nickname").getValue().toString();
                userText.setText(fbNickname);
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
        Button addButton = (Button) findViewById(R.id.addButton);
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
            }

        });

        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Function to delete alarm
            }

        });

        onButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Function to turn notifications ON
            }

        });

        offButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Function to turn notifications OFF
            }

        });
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
