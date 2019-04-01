package com.example.grey.serene;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountRegisterInfo extends AppCompatActivity {

  EditText usernickname, age;
  String userNickname, userName, userEmail, userPassword;
  int user_age;
  DatabaseReference ref;
  Users user;
  long maxid;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account_register_info);

    getSupportActionBar().hide();
    usernickname = (EditText) findViewById(R.id.answer1Field);
    age  = (EditText) findViewById(R.id.answer2Field);
    user = new Users();
    user.setID(0);
    maxid = user.getID();
    ref= FirebaseDatabase.getInstance().getReference().child("Users");
    ref.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.exists()){
          maxid = (dataSnapshot.getChildrenCount());
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });
    Button next1Button = (Button) findViewById(R.id.next1Button);
    Intent myIntent = getIntent();
      if(myIntent.hasExtra("myExtra")) {
        userName = myIntent.getStringExtra("myExtra");
        TextView text = (TextView) findViewById(R.id.welcomeText);
        text.setText("Hi " + userName + " welcome to Serene!");
      }
      if(myIntent.hasExtra("useremail")){
        userEmail = myIntent.getStringExtra("useremail");
      }
      if(myIntent.hasExtra("userpassword")){
        userPassword = myIntent.getStringExtra(userPassword);
      }

    next1Button.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

        userNickname = usernickname.getText().toString();
        user_age = Integer.parseInt(age.getText().toString());

        Intent showNotifs = new Intent(getApplicationContext(), AccountRegisterNotifs.class);
        startActivity(showNotifs);
        showNotifs.putExtra("username", userName);
        showNotifs.putExtra("useremail", userEmail);
        showNotifs.putExtra("userpassword", userPassword);
        showNotifs.putExtra("usernickname", userNickname);
        showNotifs.putExtra("userage", user_age);

        Toast.makeText(AccountRegisterInfo.this, "data inserted successfully", Toast.LENGTH_LONG).show();
      }

    });
  }
/*
  public void addFruits(View view) {
    String userNickname = usernickname.getText().toString();
    String user_age = age.getText().toString();
    String method = "register_info";
    tutorial tutorial = new tutorial(this);
    tutorial.execute(method, userNickname, user_age);
    finish();

    usernickname.setText("");
    age.setText("");
   }*/
}
