package com.example.grey.serene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountRegisterInfo extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account_register_info);

    getSupportActionBar().hide();

    Button next1Button = (Button) findViewById(R.id.next1Button);

    next1Button.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showNotifs = new Intent(getApplicationContext(), AccountRegisterNotifs.class);
        startActivity(showNotifs);
      }

    });
  }
}
