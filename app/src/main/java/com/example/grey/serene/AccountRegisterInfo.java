package com.example.grey.serene;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;




public class AccountRegisterInfo extends AppCompatActivity {

  EditText usernickname, age;
  String userNickname, userName, userEmail, userPassword;
  int user_age;
  Users user;
  long maxid;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account_register_info);

    getSupportActionBar().hide();

    Button next1Button = (Button) findViewById(R.id.next1Button);


    usernickname = (EditText) findViewById(R.id.answer1Field);
    age  = (EditText) findViewById(R.id.answer2Field);
    user = new Users();
    user.setID(0);
    maxid = user.getID();


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
      userPassword = myIntent.getStringExtra("userpassword");
    }


    next1Button.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showNotifs = new Intent(AccountRegisterInfo.this, AccountRegisterNotifs.class);

        userNickname = usernickname.getText().toString();
        user_age = Integer.parseInt(age.getText().toString());


        Toast.makeText(AccountRegisterInfo.this, "data inserted successfully", Toast.LENGTH_LONG).show();

        showNotifs.putExtra("username", userName);
        showNotifs.putExtra("useremail", userEmail);
        showNotifs.putExtra("userpassword", userPassword);
        showNotifs.putExtra("usernickname", userNickname);
        showNotifs.putExtra("userage", user_age);


        startActivity(showNotifs);
      }

    });
  }

}
