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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ProfileInterpretations extends Fragment {

    private static final String TAG = "ProfileInterpretations";

    private ListView listViewInterp;
    FirebaseDatabase database;
    DatabaseReference ref;
    FirebaseListAdapter adapterInt;

    String userID = Main.userID;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_interpretations, container, false);

        listViewInterp = (ListView) view.findViewById(R.id.interpListView);

        Query query = FirebaseDatabase.getInstance().getReference().child("Interpretations");
        FirebaseListOptions<Interpretations> articlesFirebaseListOptions = new FirebaseListOptions.Builder<Interpretations>()
                .setLayout(R.layout.interp_listview_layout)
                .setQuery(query, Interpretations.class)
                .build();

        ref = database.getReference().child("Interpretations");

        adapterInt = new FirebaseListAdapter(articlesFirebaseListOptions) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                final TextView interpDate = v.findViewById(R.id.interpText);
                final Button interpButton = v.findViewById(R.id.interpButton);

                FirebaseDatabase.getInstance().getReference().child("Interpretations").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String title = dataSnapshot.getValue().toString();
                        interpDate.setText(title);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                interpButton.setTag(position);
                interpButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final int position = (Integer) v.getTag() + 1;

                        ref.child(String.valueOf(position)).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Intent showInterp = new Intent(getActivity().getApplicationContext(), PopInterp.class);

                               showInterp.putExtra("observation", dataSnapshot.child("Observation").getValue(String.class));

                                startActivity(showInterp);
                                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });
            }
        };

        listViewInterp.setAdapter(adapterInt);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapterInt.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapterInt.stopListening();
    }
}
