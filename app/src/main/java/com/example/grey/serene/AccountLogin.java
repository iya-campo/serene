package com.example.grey.serene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountLogin extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account_login);

    getSupportActionBar().hide();

    Button loginButton = (Button) findViewById(R.id.loginButton);
    Button registerButton = (Button) findViewById(R.id.registerButton);

    loginButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showMain = new Intent(getApplicationContext(), Main.class);
        startActivity(showMain);
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
