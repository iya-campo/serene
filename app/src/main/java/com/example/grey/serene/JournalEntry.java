package com.example.grey.serene;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.google.firebase.firestore.auth.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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

    public String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/python";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_entry);

        Intent intent = getIntent();
        if (intent.hasExtra("date")) {
            date = intent.getStringExtra("date");
            TextView text = (TextView) findViewById(R.id.entryDateText);
            text.setText(date);
        }
        if(intent.hasExtra("userid")){
            user_id = Integer.parseInt(intent.getStringExtra("userid"));
        }

        textView = (TextView) findViewById(R.id.entryMACText);
        hoursSlept = (Spinner) findViewById(R.id.sleepSpinner);
        ref = FirebaseDatabase.getInstance().getReference().child("Journal").child("" + user_id);
        countRef = FirebaseDatabase.getInstance().getReference().child("CurrentUserID");
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
                food_intake += "breakfast ";
            }

        });

        lunchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Lunch input
                food_intake += "lunch ";
            }

        });

        dinnerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Dinner input
                food_intake += "dinner ";
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
                try {
                    countRef.setValue(user_id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                nltkFreq(String.valueOf(user_id));

                finish();
            }
        }));

    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public void nltkFreq(String UserID){
        try{
            String user = "UserID = " + UserID;
          //  InputStream iS = getApplicationContext().getAssets().open("/src/main/assets/freqJournal.py");
           // BufferedReader reader = new BufferedReader(new InputStreamReader(iS));
            String command = "python /src/main/assets/freqJournal.py";

            Process p = Runtime.getRuntime().exec(command);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ret = in.readLine();
            Toast.makeText(getApplicationContext(), "value is : " + ret, Toast.LENGTH_LONG).show();

        }catch (Exception e){
            Log.i("file", "can't see it");
        }
    }


}

