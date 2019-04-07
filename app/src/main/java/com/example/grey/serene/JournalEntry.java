package com.example.grey.serene;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class JournalEntry extends AppCompatActivity {

  String content;
  String food_intake;
  String medicinal_intake;
  DatabaseReference ref;
  TextView textView;
  Journal journal;
  Spinner hoursSlept;
  long maxid;
  int hours_slept;
  long user_id;
  String date;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_journal_entry);

    getSupportActionBar().hide();

    textView = (TextView) findViewById(R.id.entryMACText);
    content = textView.getText().toString();
    hoursSlept = (Spinner) findViewById(R.id.sleepSpinner);
    hours_slept = Integer.parseInt(hoursSlept.getSelectedItem().toString());
    ref= FirebaseDatabase.getInstance().getReference().child("Journal");
    ref.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if(dataSnapshot.exists()){
          maxid = (dataSnapshot.getChildrenCount());
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });

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
        food_intake += "breakfast";
      }

    });

    lunchButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Lunch input
        food_intake += "lunch";
      }

    });

    dinnerButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Dinner input
        food_intake += "dinner";
      }

    });

    yesButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Yes input
        medicinal_intake = "yes";
      }

    });

    noButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //No input
        medicinal_intake = "no";
      }

    });

    saveEntryButton.setOnClickListener((new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        journal = new Journal(maxid, hours_slept, food_intake, medicinal_intake, date, content, user_id);

        ref.child(String.valueOf(maxid+1)).setValue(journal);

      }
    }));

  }
}
