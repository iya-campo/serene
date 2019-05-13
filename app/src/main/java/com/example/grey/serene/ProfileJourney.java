package com.example.grey.serene;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileJourney extends Fragment {

    private static final String TAG = "ProfileJourney";

    private TextView startDateDetails;
    private Button medicineButton;
    private Button sleepButton;
    public String userID;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_journey, container, false);

        userID = Profile.userID;
        startDateDetails = (TextView) view.findViewById(R.id.startDateDetails);
        medicineButton = (Button) view.findViewById(R.id.medicineButton);
        sleepButton = (Button) view.findViewById(R.id.sleepButton);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String startDate = dataSnapshot.child("startDate").getValue().toString();
                startDateDetails.setText(startDate);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        medicineButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Function to view stats on medicine
            }

        });

        sleepButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Function to view stats on sleep
            }

        });

        return view;
    }

}
