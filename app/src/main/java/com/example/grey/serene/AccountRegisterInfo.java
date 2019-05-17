package com.example.grey.serene;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AccountRegisterInfo extends AppCompatActivity {

    public static Activity info;

    EditText nicknameField, ageField;
    String nickname, username, email, password;
    int age;
    Users user;
    long maxid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register_info);

        info = this;

        Button next1Button = (Button) findViewById(R.id.next1Button);

        nicknameField = (EditText) findViewById(R.id.answer1Field);
        ageField = (EditText) findViewById(R.id.answer2Field);
        user = new Users();
        user.setID(0);
        maxid = user.getID();

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

        TextView welcomeText = (TextView) findViewById(R.id.welcomeText);
        welcomeText.setText("Hi " + username + ", welcome to Serene!");

        next1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nicknameField.getText().toString().trim().isEmpty() || ageField.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your name and age.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (!TextUtils.isDigitsOnly(ageField.getText().toString().trim())) {
                        Toast.makeText(getApplicationContext(), "Please enter a valid age.", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        nickname = nicknameField.getText().toString();
                        age = Integer.parseInt(ageField.getText().toString());

                        Intent showNotifs = new Intent(AccountRegisterInfo.this, AccountRegisterNotifs.class);

                        showNotifs.putExtra("username", username);
                        showNotifs.putExtra("email", email);
                        showNotifs.putExtra("password", password);
                        showNotifs.putExtra("nickname", nickname);
                        showNotifs.putExtra("age", age);

                        startActivity(showNotifs);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                }
            }
        });
    }

    public void finish(){
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
