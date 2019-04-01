package com.example.grey.serene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsData extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings_data);

    getSupportActionBar().hide();

    //Header Buttons
    Button profileButton = (Button) findViewById(R.id.profileButton);

    //Return Button
    Button settingsTitleButton = (Button) findViewById(R.id.settingsTitleButton);

    //Settings Buttons
    Button saveButton = (Button) findViewById(R.id.saveButton);
    Button deactivateButton = (Button) findViewById(R.id.deactivateButton);

    profileButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showProfile = new Intent(getApplicationContext(), Profile.class);
        startActivity(showProfile);
      }

    });

    settingsTitleButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showSettings = new Intent(getApplicationContext(), Settings.class);
        startActivity(showSettings);
      }

    });

    saveButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Function to save changes
      }

    });

    deactivateButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Function to deactivate account
      }

    });
  }
}
