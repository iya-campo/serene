package com.example.grey.serene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChangePassword extends AppCompatActivity {

    EditText newPwField, confirmPwField;
    String userKey;
    String userID;
    boolean confirmed;
    FirebaseDatabase database;
    DatabaseReference userRef;
    Button confirmNewPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        newPwField = (EditText) findViewById(R.id.newPwField);
        confirmPwField = (EditText) findViewById(R.id.confirmPwField);

        Intent forgotPassIntent = getIntent();
        userID = forgotPassIntent.getStringExtra("userID");

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference().child("Users");

        confirmNewPw = (Button) findViewById(R.id.confirm2Button);

        confirmNewPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String newPass = newPwField.getText().toString();
                final String confirmPass = confirmPwField.getText().toString();

                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {

                        if (TextUtils.isEmpty(newPass) && TextUtils.isEmpty(confirmPass)) {
                            Toast.makeText(getApplicationContext(), "Please answer the following questions.", Toast.LENGTH_SHORT).show();
                        } else {
                            if(newPass.equals(confirmPass)) {
                                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                    userKey = userSnapshot.getKey();
                                    confirmed = false;

                                    if (userKey.equals(userID)) {
                                        confirmed = true;
                                        try {
                                            userRef.child(userKey).child("password").setValue(newPass);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        Intent login = new Intent(getApplicationContext(), AccountLogin.class);
                                        startActivity(login);
                                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                        finish();
                                        break;
                                    }
                                }
                            }
                            if (!confirmed) {
                                Toast.makeText(getApplicationContext(), "Incorrect answers, please try again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
