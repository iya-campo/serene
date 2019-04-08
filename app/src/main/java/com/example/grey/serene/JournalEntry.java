package com.example.grey.serene;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.languageid.FirebaseLanguageIdentification;

import java.util.ArrayList;
import java.util.List;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

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
        ref = FirebaseDatabase.getInstance().getReference().child("Journal");
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

        Intent intent = getIntent();
        if (intent.hasExtra("date")) {
            date = intent.getStringExtra("myExtra");
            TextView text = (TextView) findViewById(R.id.entryDateText);
            text.setText(date);
        }

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
                hours_slept = Integer.parseInt(hoursSlept.getSelectedItem().toString());
                journal = new Journal(maxid, hours_slept, food_intake, medicinal_intake, date, content, user_id);

                ref.child(String.valueOf(maxid + 1)).setValue(journal);

            }
        }));

    }

    private void identifyText(String text){
        FirebaseLanguageIdentification languageIdentifier =
                FirebaseNaturalLanguage.getInstance().getLanguageIdentification();
        languageIdentifier.identifyLanguage(text)
                .addOnSuccessListener(
                        new OnSuccessListener<String>() {
                            @Override
                            public void onSuccess(@Nullable String languageCode) {
                                if (languageCode != "und") {
                                    Toast.makeText(getApplicationContext(), languageCode, Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Can't identify language.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Model couldn’t be loaded or other internal error.
                                // ...
                            }
                        });
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
