package com.example.grey.serene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsNotifs extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_settings_notifs);

    getSupportActionBar().hide();

    //Header Buttons
    Button profileButton = (Button) findViewById(R.id.profileButton);

    //Return Button
    Button settingsTitleButton = (Button) findViewById(R.id.settingsTitleButton);

    //Settings Buttons
    Button addButton = (Button) findViewById(R.id.addButton);
    Button deleteButton = (Button) findViewById(R.id.deleteButton);
    Button onButton = (Button) findViewById(R.id.onButton);
    Button offButton = (Button) findViewById(R.id.offButton);

    profileButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showProfile = new Intent(getApplicationContext(), Profile.class);
        startActivity(showProfile);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
      }

    });

    settingsTitleButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        finish();
      }

    });

    addButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Function to add alarm
      }

    });

    deleteButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Function to delete alarm
      }

    });

    onButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Function to turn notifications ON
      }

    });

    offButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Function to turn notifications OFF
      }

    });
  }
  public void finish(){
    super.finish();
    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
  }
}
