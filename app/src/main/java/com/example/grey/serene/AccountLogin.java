package com.example.grey.serene;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
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

import org.w3c.dom.Text;

public class AccountLogin extends AppCompatActivity {

    EditText pass, user;
    String userKey;
    String fbUsername, fbPassword;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userRef = database.getReference().child("Users");

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_login);

        user = (EditText) findViewById(R.id.unField);
        pass = (EditText) findViewById(R.id.pwField);
        Button loginButton = (Button) findViewById(R.id.loginButton);
        Button registerButton = (Button) findViewById(R.id.registerButton);
        TextView forgotPWText = (TextView) findViewById(R.id.forgotPWText);

        sharedPref = getSharedPreferences("loginRef", MODE_PRIVATE);
        editor = sharedPref.edit();

        String forgotPW = "<u>Forgot password?</u>";
        forgotPWText.setText(Html.fromHtml(forgotPW));

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CheckUser();
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

        forgotPWText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showForgotPw = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(showForgotPw);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    public void CheckUser() {
        final String username = user.getText().toString();
        final String password = pass.getText().toString();

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please enter your username and password.", Toast.LENGTH_SHORT).show();
                } else {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        userKey = userSnapshot.getKey();
                        fbUsername = (String) dataSnapshot.child(userKey).child("username").getValue();
                        fbPassword = (String) dataSnapshot.child(userKey).child("password").getValue();

                        if ((username.toLowerCase().equals(fbUsername)) && (password.equals(fbPassword))) {
                            Login();
                            break;
                        }
                    }
                    if (!(username.toLowerCase().equals(fbUsername)) || !(password.equals(fbPassword))) {
                        Toast.makeText(getApplicationContext(), "Incorrect username or password.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void Login() {
        /** Session Start **/
        SharedPrefs.saveSharedSetting(AccountLogin.this, "Serene", "false");
        SharedPrefs.SharedPrefSave(getApplicationContext(), userKey);

        Intent showMain = new Intent(getApplicationContext(), Main.class);
        showMain.putExtra("userID", userKey);
        startActivity(showMain);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }
}
