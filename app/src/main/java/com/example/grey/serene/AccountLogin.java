package com.example.grey.serene;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountLogin extends AppCompatActivity {

  EditText pass, usr;
  //FirebaseAuth auth;
  ProgressBar progressBar;
  String email, password, userid, firebaseEmail, firebasePassword, child;
  Boolean login = false;
  long UserId;
  long childID = 1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account_login);

    getSupportActionBar().hide();
    child = String.valueOf(childID);
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference usersChildRef = database.getReference("Users").child(child);

    //auth = FirebaseAuth.getInstance();
  /*
    if(auth.getCurrentUser() != null){
      startActivity(new Intent(AccountLogin.this, Main.class));
      finish();
    }*/

    usr = (EditText) findViewById(R.id.unField);
    pass = (EditText) findViewById(R.id.pwField);
    progressBar = (ProgressBar) findViewById(R.id.progressBar);
    Button loginButton = (Button) findViewById(R.id.loginButton);
    Button registerButton = (Button) findViewById(R.id.registerButton);



    usersChildRef.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {
      @Override
      public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
        firebaseEmail = (String)dataSnapshot.child("usersName").getValue();
        firebasePassword = (String)dataSnapshot.child("password").getValue();

        while(dataSnapshot.hasChildren()){
          if(firebaseEmail == email && firebasePassword == password){
            login = true;
          } else {
            childID++;
          }

        }


      }

      @Override
      public void onCancelled(DatabaseError databaseError) {
      }
    });



    //getting Firebase auth instance
    //auth = FirebaseAuth.getInstance();

    loginButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        email = usr.getText().toString();
        password = pass.getText().toString();

        //Toast.makeText(getApplicationContext(), email + " = " + test, Toast.LENGTH_LONG).show();
        if(TextUtils.isEmpty(email)){
          Toast.makeText(getApplicationContext(), "Enter email or password.", Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
          Toast.makeText(getApplicationContext(), "Enter email or password.", Toast.LENGTH_SHORT).show();
        }


        if(login){
          Intent showMain = new Intent(getApplicationContext(), Main.class);
          userid = Long.toString(UserId);
          showMain.putExtra("userID", userid);
          startActivity(showMain);
        } else {
          Toast.makeText(getApplicationContext(), password + " = " + firebasePassword, Toast.LENGTH_LONG).show();
        }

      }

    });

    registerButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showCreate = new Intent(getApplicationContext(), AccountRegisterCreate.class);
        startActivity(showCreate);
      }

    });
  }


}
