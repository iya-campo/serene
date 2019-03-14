package com.example.grey.serene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class StartingScreen extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_starting_screen);

    getSupportActionBar().hide();

    //ImageView SereneLogo = (ImageView) findViewById(R.id.SereneLogo);
    Button startButton = (Button) findViewById(R.id.startButton);

    startButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showMain = new Intent(getApplicationContext(), Main.class);
        startActivity(showMain);
      }

    });
  }
}
