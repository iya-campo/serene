package com.example.grey.serene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings);

    getSupportActionBar().hide();

    //Header Buttons
    Button profileButton = (Button) findViewById(R.id.profileButton);
    Button settingsButton = (Button) findViewById(R.id.settingsButton);

    //Settings Buttons
    Button notifsButton = (Button) findViewById(R.id.notifsButton);
    Button tcButton = (Button) findViewById(R.id.tcButton);
    Button ppButton = (Button) findViewById(R.id.ppButton);
    Button dataButton = (Button) findViewById(R.id.dataButton);

    //Logout Button
    Button logoutButton = (Button) findViewById(R.id.logoutButton);

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
        Intent showProfile = new Intent(getApplicationContext(), Profile.class);
        startActivity(showProfile);
      }

    });

    notifsButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showSettingsNotifs = new Intent(getApplicationContext(), SettingsNotifs.class);
        startActivity(showSettingsNotifs);
      }

    });

    tcButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showSettingsTC = new Intent(getApplicationContext(), SettingsTC.class);
        startActivity(showSettingsTC);
      }

    });

    ppButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showSettingsPP = new Intent(getApplicationContext(), SettingsPP.class);
        startActivity(showSettingsPP);
      }

    });

    dataButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showSettingsData = new Intent(getApplicationContext(), SettingsData.class);
        startActivity(showSettingsData);
      }

    });

    logoutButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Function to logout account
      }

    });

  }
}
