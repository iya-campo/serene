package com.example.grey.serene;

import android.content.Intent;
import android.graphics.Paint;
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

public class ForgotPassword extends AppCompatActivity {

    EditText nickname, age;
    String userKey;
    String ansNickname, ansAge;
    boolean confirmed;
    FirebaseDatabase database;
    DatabaseReference userRef;
    Button confirmSecurityAnswers;
    Long ageConvert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        nickname = (EditText) findViewById(R.id.answer1Field);
        age = (EditText) findViewById(R.id.answer2Field);

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference().child("Users");

        confirmSecurityAnswers = (Button) findViewById(R.id.confirmButton);

        confirmSecurityAnswers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nicknameAns = nickname.getText().toString();
                final String ageAns = age.getText().toString();

                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {

                        if (TextUtils.isEmpty(nicknameAns) && TextUtils.isEmpty(ageAns)) {
                            Toast.makeText(getApplicationContext(), "Please answer the following questions.", Toast.LENGTH_SHORT).show();
                        } else {
                            for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                userKey = userSnapshot.getKey();
                                ansNickname = (String) dataSnapshot.child(userKey).child("nickname").getValue();
                                ageConvert = (Long) dataSnapshot.child(userKey).child("age").getValue();
                                ansAge = ageConvert.toString();

                                confirmed = false;

                                if ((nicknameAns.equals(ansNickname)) && (ageAns.equals(ansAge))) {
                                    confirmed = true;
                                    Intent showChangePass = new Intent(getApplicationContext(), ChangePassword.class);
                                    showChangePass.putExtra("userID", userKey);
                                    startActivity(showChangePass);
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                    break;
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

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
