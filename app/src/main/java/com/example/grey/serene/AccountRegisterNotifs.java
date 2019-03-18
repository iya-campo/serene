package com.example.grey.serene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountRegisterNotifs extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account_register_notifs);

    getSupportActionBar().hide();

    Button next2Button = (Button) findViewById(R.id.next2Button);

    next2Button.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showLogin = new Intent(getApplicationContext(), AccountLogin.class);
        startActivity(showLogin);
      }

    });
  }
}
