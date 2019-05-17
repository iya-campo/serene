package com.example.grey.serene;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JournalEntry extends AppCompatActivity {

    public static Activity journalEntry;

    String content;
    boolean breakfast, lunch, dinner = false;
    String food_intake = "";
    String medicinal_intake = "";
    String recorded;
    DatabaseReference ref, countRef, userIDRef;
    TextView textView;
    Journal journal;
    Spinner hoursSlept;
    long maxid;
    int hours_slept;

    public String userID = Main.userID;
    public String journalDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entry);

        journalEntry = this;

        Intent myIntent = getIntent();
        if (myIntent.hasExtra("journalDate")) {
            journalDate = myIntent.getStringExtra("journalDate");
            TextView dateText = (TextView) findViewById(R.id.entryDateText);
            dateText.setText(journalDate);
        }

        textView = (TextView) findViewById(R.id.entryMACText);
        hoursSlept = (Spinner) findViewById(R.id.sleepSpinner);
        ref = FirebaseDatabase.getInstance().getReference().child("Journal").child(userID);
        userIDRef = FirebaseDatabase.getInstance().getReference().child("CurrentUserID");
        countRef = FirebaseDatabase.getInstance().getReference().child("JournalCounter").child("" + "" + userID);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxid = (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        hoursSlept = (Spinner) findViewById(R.id.sleepSpinner);
        List<String> list = new ArrayList<String>();
        list.add("01");
        list.add("02");
        list.add("03");
        list.add("04");
        list.add("05");
        list.add("06");
        list.add("07");
        list.add("08");
        list.add("09");
        list.add("10");
        list.add("11");
        list.add("12");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_style, list);
        hoursSlept.setAdapter(adapter);

        //Entry Header Buttons
        Button backButton = (Button) findViewById(R.id.backButton);
        Button saveEntryButton = (Button) findViewById(R.id.saveEntryButton);

        //Journal Buttons
        final Button breakfastButton = (Button) findViewById(R.id.breakfastButton);
        final Button lunchButton = (Button) findViewById(R.id.lunchButton);
        final Button dinnerButton = (Button) findViewById(R.id.dinnerButton);
        final Button yesButton = (Button) findViewById(R.id.yesButton);
        final Button noButton = (Button) findViewById(R.id.noButton);

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }

        });

        breakfastButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (breakfast == false) {
                    breakfast = true;
                    breakfastButton.setBackgroundResource(R.drawable.btn_journal_selected);
                } else {
                    breakfast = false;
                    breakfastButton.setBackgroundResource(R.drawable.btn_selector2);
                }
            }

        });

        lunchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (lunch == false) {
                    lunch = true;
                    lunchButton.setBackgroundResource(R.drawable.btn_journal_selected);
                } else {
                    lunch = false;
                    lunchButton.setBackgroundResource(R.drawable.btn_selector2);
                }
            }

        });

        dinnerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (dinner == false) {
                    dinner = true;
                    dinnerButton.setBackgroundResource(R.drawable.btn_journal_selected);
                } else {
                    dinner = false;
                    dinnerButton.setBackgroundResource(R.drawable.btn_selector2);
                }
            }

        });

        yesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (medicinal_intake.equals("") || medicinal_intake.equals("no")) {
                    medicinal_intake = "yes";
                    yesButton.setBackgroundResource(R.drawable.btn_journal_selected);
                    noButton.setBackgroundResource(R.drawable.btn_selector2);
                }
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (medicinal_intake.equals("") || medicinal_intake.equals("yes")) {
                    medicinal_intake = "no";
                    noButton.setBackgroundResource(R.drawable.btn_journal_selected);
                    yesButton.setBackgroundResource(R.drawable.btn_selector2);
                }
            }

        });

        saveEntryButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hours_slept = Integer.parseInt(hoursSlept.getSelectedItem().toString());
                content = textView.getText().toString();
                recorded = "no";

                if (breakfast == true) {
                    food_intake += " breakfast ";
                }
                if (lunch == true) {
                    food_intake += " lunch ";
                }
                if (dinner == true) {
                    food_intake += " dinner ";
                }
                if (food_intake.equals("")) {
                    food_intake = "-";
                }
                if (medicinal_intake.equals("")) {
                    medicinal_intake = "-";
                }

                journal = new Journal(maxid + 1, hours_slept, food_intake, medicinal_intake, journalDate, content, recorded);
                ref.child(journalDate).setValue(journal);

                try {
                    userIDRef.setValue(userID);
                    countRef.setValue(maxid + 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                finish();
            }
        }));

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    if(dataSnapshot.child(journalDate).child("hours_slept").getValue() != null){
                        hoursSlept.setSelection(Integer.parseInt(dataSnapshot.child(journalDate).child("hours_slept").getValue().toString()) - 1);
                    }
                    if (dataSnapshot.child(journalDate).child("content").getValue() != null) {
                        textView.setText(String.valueOf(dataSnapshot.child(journalDate).child("content").getValue()));
                    }
                    if (dataSnapshot.child(journalDate).child("food_intake").getValue() != null) {
                        if (dataSnapshot.child(journalDate).child("food_intake").getValue().toString().contains("breakfast")) {
                            breakfast = true;
                            breakfastButton.setBackgroundResource(R.drawable.btn_journal_selected);
                        }
                        if (dataSnapshot.child(journalDate).child("food_intake").getValue().toString().contains("lunch")) {
                            lunch = true;
                            lunchButton.setBackgroundResource(R.drawable.btn_journal_selected);
                        }
                        if (dataSnapshot.child(journalDate).child("food_intake").getValue().toString().contains("dinner")) {
                            dinner = true;
                            dinnerButton.setBackgroundResource(R.drawable.btn_journal_selected);
                        }
                    }
                    if (dataSnapshot.child(journalDate).child("medicinal_intake").getValue() != null) {
                        if (dataSnapshot.child(journalDate).child("medicinal_intake").getValue().toString().equals("yes")) {
                            medicinal_intake = "yes";
                            yesButton.setBackgroundResource(R.drawable.btn_journal_selected);
                        }
                        if (dataSnapshot.child(journalDate).child("medicinal_intake").getValue().toString().equals("no")) {
                            medicinal_intake = "no";
                            noButton.setBackgroundResource(R.drawable.btn_journal_selected);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


}

