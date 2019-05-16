package com.example.grey.serene;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileInterpretations extends Fragment {

    private static final String TAG = "ProfileInterpretations";

    FirebaseDatabase database;
    DatabaseReference ref;

    String userID = Main.userID;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_interpretations, container, false);

        final TextView interp = (TextView) view.findViewById(R.id.interpText);

//        userID = Profile.userID;
//        database = FirebaseDatabase.getInstance();
//        ref = database.getReference().child("Journal").child(userID);
//        ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()) {
//                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                        String interpretation = snapshot.getValue(String.class);
//                        interp.setText(interpretation);
//                    }
//                } else {
//                    interp.setText("No interpretations are presented as of the moment.");
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        return view;
    }
}
