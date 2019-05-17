package com.example.grey.serene;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ProfileJourney extends Fragment {

    private static final String TAG = "ProfileJourney";

    private TextView startDateDetails;
    private TextView durationDetails;
    private Button medicineButton;
    private Button sleepButton;
    long duration;
    int medStats, sleepStats, entryCount;

    String userID = Main.userID;
    String date = Main.date;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_journey, container, false);

        startDateDetails = (TextView) view.findViewById(R.id.startDateDetails);
        durationDetails = (TextView) view.findViewById(R.id.durationDetails);
        medicineButton = (Button) view.findViewById(R.id.medicineButton);
        sleepButton = (Button) view.findViewById(R.id.sleepButton);

        medStats = 0;
        sleepStats = 0;

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        final DatabaseReference journalRef = FirebaseDatabase.getInstance().getReference().child("Journal").child(userID);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String startDate = dataSnapshot.child("startDate").getValue().toString();
                startDateDetails.setText(startDate);
                try {
                    SimpleDateFormat myFormat = new SimpleDateFormat("MMM dd, yyyy");
                    Date date1 = myFormat.parse(startDate);
                    Date date2 = myFormat.parse(date);
                    long diff = date2.getTime() - date1.getTime();
                    duration = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                    durationDetails.setText(duration + " Day/s");
                } catch (Exception e) {
                    //  Block of code to handle errors
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        journalRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                entryCount = Integer.parseInt(String.valueOf(dataSnapshot.getChildrenCount()));
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String meds = snapshot.child("medicinal_intake").getValue().toString();
                    if (meds.equals("yes")) {
                        medStats += 1;
                    }
                    String sleep = snapshot.child("hours_slept").getValue().toString();
                    sleepStats += Integer.parseInt(sleep);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        medicineButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showMedsPop = new Intent(getActivity().getApplicationContext(), PopMeds.class);
                String medString = String.valueOf(medStats);
                showMedsPop.putExtra("MedStats", medString);
                startActivity(showMedsPop);
            }

        });

        sleepButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showSleepPop = new Intent(getActivity().getApplicationContext(), PopSleep.class);
                String sleepString = String.valueOf(sleepStats);
                String entryString = String.valueOf(entryCount);
                showSleepPop.putExtra("SleepStats", sleepString);
                showSleepPop.putExtra("EntryCount", entryString);
                startActivity(showSleepPop);
            }

        });

        return view;
    }

}
