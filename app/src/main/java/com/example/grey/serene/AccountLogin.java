package com.example.grey.serene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountLogin extends AppCompatActivity {

    EditText pass, user;
    String fbUsername, fbPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);

        getSupportActionBar().hide();

        user = (EditText) findViewById(R.id.unField);
        pass = (EditText) findViewById(R.id.pwField);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button registerButton = (Button) findViewById(R.id.registerButton);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference userRef = database.getReference().child("Users");

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String username = user.getText().toString();
                final String password = pass.getText().toString();

                userRef.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(final com.google.firebase.database.DataSnapshot dataSnapshot) {
                        long refCount = dataSnapshot.getChildrenCount();
                        //Log.i("myTag", String.valueOf(refCount));

                        if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
                            Toast.makeText(getApplicationContext(), "Please enter your username and password.", Toast.LENGTH_SHORT).show();
                        } else {
                            for (int i = 1; i <= refCount; i++) {
                                fbUsername = (String) dataSnapshot.child(String.valueOf(i)).child("username").getValue();
                                fbPassword = (String) dataSnapshot.child(String.valueOf(i)).child("password").getValue();
                                //Log.i("myTag", String.valueOf(fbUsername));

                                if ((username.equals(fbUsername)) && (password.equals(fbPassword))) {
                                    Intent showMain = new Intent(getApplicationContext(), Main.class);
                                    startActivity(showMain);
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                } else {
                                    Toast.makeText(getApplicationContext(), "Incorrect username or password.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showCreate = new Intent(getApplicationContext(), AccountRegisterCreate.class);
                startActivity(showCreate);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });
    }

}
