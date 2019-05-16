package com.example.grey.serene;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SettingsData extends AppCompatActivity {

    public static Activity settingsData;

    DatabaseReference ref;

    String userID = Main.userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_data);

        settingsData = this;

        final TextView userText = (TextView) findViewById(R.id.userText);
        final EditText userName = (EditText) findViewById(R.id.nameEditText);
        final EditText userEmail = (EditText) findViewById(R.id.emailEditText);
        final EditText userPassword = (EditText) findViewById(R.id.passwordEditText);

        ref = FirebaseDatabase.getInstance().getReference().child("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String fbNickname = dataSnapshot.child(userID).child("nickname").getValue().toString();
                String fbEmail = dataSnapshot.child(userID).child("email").getValue().toString();
                String fbPassword = dataSnapshot.child(userID).child("password").getValue().toString();
                userText.setText(fbNickname);
                userName.setText(fbNickname);
                userEmail.setText(fbEmail);
                userPassword.setText(fbPassword);
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
        Button saveButton = (Button) findViewById(R.id.saveButton);
        Button deactivateButton = (Button) findViewById(R.id.deactivateButton);

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

        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String changeName = userName.getText().toString();
                String changeEmail = userEmail.getText().toString();
                String changePassword = userPassword.getText().toString();

                ref.child(userID).child("nickname").setValue(changeName);
                ref.child(userID).child("email").setValue(changeEmail);
                ref.child(userID).child("password").setValue(changePassword);

                finish();

            }

        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference userRef = database.getReference().child("Users");

        deactivateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent login = new Intent(getApplicationContext(), AccountLogin.class);
                startActivity(login);
                finishAffinity();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
//                userRef.child(userID).removeValue();

                Toast.makeText(getApplicationContext(), "Your account has been deactivated.", Toast.LENGTH_SHORT).show();
            }

        });
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
