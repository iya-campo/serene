package com.example.grey.serene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsTC extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings_tc);

    getSupportActionBar().hide();

    //Header Buttons
    Button profileButton = (Button) findViewById(R.id.profileButton);
    Button settingsButton = (Button) findViewById(R.id.settingsButton);

    //Return Button
    Button settingsTitleButton = (Button) findViewById(R.id.settingsTitleButton);

    profileButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showProfile = new Intent(getApplicationContext(), Profile.class);
        startActivity(showProfile);
      }

    });

    settingsButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showSettings = new Intent(getApplicationContext(), Settings.class);
        startActivity(showSettings);
      }

    });

    settingsTitleButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showSettings = new Intent(getApplicationContext(), Settings.class);
        startActivity(showSettings);
      }

    });
  }
}
