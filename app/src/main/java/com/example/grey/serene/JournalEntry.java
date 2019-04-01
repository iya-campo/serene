package com.example.grey.serene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class JournalEntry extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_journal_entry);

    getSupportActionBar().hide();

    //Entry Header Buttons
    Button backButton = (Button) findViewById(R.id.backButton);
    Button saveEntryButton = (Button) findViewById(R.id.saveEntryButton);

    //Journal Buttons
    Button breakfastButton = (Button) findViewById(R.id.breakfastButton);
    Button lunchButton = (Button) findViewById(R.id.lunchButton);
    Button dinnerButton = (Button) findViewById(R.id.dinnerButton);
    Button yesButton = (Button) findViewById(R.id.yesButton);
    Button noButton = (Button) findViewById(R.id.noButton);

    backButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showJournal = new Intent(getApplicationContext(), MainJournal.class);
        startActivity(showJournal);
      }

    });

    saveEntryButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Function to save journal entry
      }

    });

    breakfastButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Breakfast input
      }

    });

    lunchButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Lunch input
      }

    });

    dinnerButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Dinner input
      }

    });

    yesButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Yes input
      }

    });

    noButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //No input
      }

    });

  }
}
