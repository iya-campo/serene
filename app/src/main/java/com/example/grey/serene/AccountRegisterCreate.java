package com.example.grey.serene;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AccountRegisterCreate extends AppCompatActivity {

    public static Activity create;

    Button submitButton;
    EditText usernameField, emailField, passwordField;
    String username, email, password;
    Users user;
    long maxid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register_create);

        create = this;

        TextView tcText = (TextView) findViewById(R.id.tcText);
        submitButton = (Button) findViewById(R.id.submitButton);

        usernameField = (EditText) findViewById(R.id.newUnField);
        emailField = (EditText) findViewById(R.id.newEmailField);
        passwordField = (EditText) findViewById(R.id.newPwField);
        user = new Users();
        user.setID(0);
        maxid = user.getID();

        String tcPop = "<u>I accept the terms and conditions.</u>";
        tcText.setText(Html.fromHtml(tcPop));

        tcText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(AccountRegisterCreate.this, PopTC.class));
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                username = usernameField.getText().toString().trim().toLowerCase();
                email = emailField.getText().toString().trim();
                password = passwordField.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please provide the necessary information.", Toast.LENGTH_SHORT).show();
                    return;
                }

                CheckBox tcCheckbox = (CheckBox) findViewById(R.id.tcCheckbox);
                if (tcCheckbox.isChecked()){
                    Intent showInfo = new Intent(getApplicationContext(), AccountRegisterInfo.class);

                    showInfo.putExtra("username", username);
                    showInfo.putExtra("email", email);
                    showInfo.putExtra("password", password);

                    startActivity(showInfo);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please accept the terms and conditions.", Toast.LENGTH_SHORT).show();
                }
            }


        });
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
