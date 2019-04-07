package com.example.grey.serene;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class AccountRegisterCreate extends AppCompatActivity {

    Button submitButton;

    EditText username, email, password;
    String user_name, user_email, user_pass;
    Users user;
    long maxid;
    //FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register_create);

        getSupportActionBar().hide();

        //auth = FirebaseAuth.getInstance();

        submitButton = (Button) findViewById(R.id.submitButton);

        username = (EditText) findViewById(R.id.newUnField);
        email = (EditText) findViewById(R.id.newEmailField);
        password = (EditText) findViewById(R.id.newPwField);
        user = new Users();
        user.setID(0);
        maxid = user.getID();


        submitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                user_name = username.getText().toString();
                user_email = email.getText().toString().trim();
                user_pass = password.getText().toString().trim();

                if (TextUtils.isEmpty(user_email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(user_pass)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent showInfo = new Intent(getApplicationContext(), AccountRegisterInfo.class);

                Toast.makeText(AccountRegisterCreate.this, "data inserted successfully", Toast.LENGTH_LONG).show();
                showInfo.putExtra("myExtra", user_name);
                showInfo.putExtra("useremail", user_email);
                showInfo.putExtra("userpassword", user_pass);

                startActivity(showInfo);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                //create user
                /*auth.createUserWithEmailAndPassword(user_email, user_pass)
                    .addOnCompleteListener(AccountRegisterCreate.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(AccountRegisterCreate.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                            // If sign in fails, display a message to the user. If sign in succeeds
                            // the auth state listener will be notified and logic to handle the
                            // signed in user can be handled in the listener.
                            if (!task.isSuccessful()) {
                                Toast.makeText(AccountRegisterCreate.this, "Authentication failed." + task.getException(),
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                Intent showInfo = new Intent(getApplicationContext(), AccountRegisterInfo.class);

                                Toast.makeText(AccountRegisterCreate.this, "data inserted successfully", Toast.LENGTH_LONG).show();
                                showInfo.putExtra("myExtra", user_name);
                                showInfo.putExtra("useremail", user_email);
                                showInfo.putExtra("userpassword", user_pass);


                                startActivity(showInfo);
                                finish();
                            }
                        }
                    });*/
            }


        });
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
