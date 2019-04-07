package com.example.grey.serene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class JournalEntry extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_journal_entry);

    getSupportActionBar().hide();

    Spinner spinner = (Spinner) findViewById(R.id.sleepSpinner);
    List<String> hoursOfSleep = new ArrayList<String>();
    hoursOfSleep.add("01");
    hoursOfSleep.add("02");
    hoursOfSleep.add("03");
    hoursOfSleep.add("04");
    hoursOfSleep.add("05");
    hoursOfSleep.add("06");
    hoursOfSleep.add("07");
    hoursOfSleep.add("08");
    hoursOfSleep.add("09");
    hoursOfSleep.add("10");
    hoursOfSleep.add("11");
    hoursOfSleep.add("12");
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_style, hoursOfSleep);
    spinner.setAdapter(adapter);

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
        finish();
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
  public void finish(){
    super.finish();
    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
  }

  @Override
  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    String text = parent.getItemAtPosition(position).toString();
    Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onNothingSelected(AdapterView<?> parent) {

  }
}
