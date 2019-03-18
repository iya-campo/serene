package com.example.grey.serene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AccountRegisterCreate extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account_register_create);

    getSupportActionBar().hide();

    Button submitButton = (Button) findViewById(R.id.submitButton);

    submitButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showInfo = new Intent(getApplicationContext(), AccountRegisterInfo.class);
        startActivity(showInfo);
      }

    });
  }
}
