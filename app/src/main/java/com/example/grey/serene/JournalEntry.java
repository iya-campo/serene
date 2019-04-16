package com.example.grey.serene;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class JournalEntry extends AppCompatActivity {

    String content;
    String food_intake = "";
    String medicinal_intake;
    DatabaseReference ref, countRef;
    TextView textView;
    Journal journal;
    Spinner hoursSlept;
    long maxid, user_id;
    int hours_slept, count;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entry);

        getSupportActionBar().hide();

        Intent intent = getIntent();
        if (intent.hasExtra("date")) {
            date = intent.getStringExtra("date");
            TextView text = (TextView) findViewById(R.id.entryDateText);
            text.setText(date);
            Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT).show();
        }
        if(intent.hasExtra("userid")){
            user_id = Integer.parseInt(intent.getStringExtra("userid"));
            Toast.makeText(getApplicationContext(), "" + user_id, Toast.LENGTH_SHORT).show();
        }


        textView = (TextView) findViewById(R.id.entryMACText);
        hoursSlept = (Spinner) findViewById(R.id.sleepSpinner);
        ref = FirebaseDatabase.getInstance().getReference().child("Journal").child("" + user_id);
        countRef = FirebaseDatabase.getInstance().getReference().child("Journal_Count");
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
        try {
            countRef.setValue(count);
        } catch (Exception e) {
            e.printStackTrace();
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
                content = textView.getText().toString();

                journal = new Journal(maxid+1, hours_slept, food_intake, medicinal_intake, date, content);
                ref.child(String.valueOf(maxid+1)).setValue(journal);

                Intent home = new Intent(getApplicationContext(), Main.class);
                startActivity(home);
            }
        }));

    }


    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}

