package com.example.grey.serene;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainJournal extends Fragment {

    public MainJournal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_journal, container, false);

        Button addEntryButton = (Button) view.findViewById(R.id.addEntryButton);

        addEntryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showJournalEntry = new Intent(getActivity().getApplicationContext(), JournalEntry.class);
                startActivity(showJournalEntry);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        return view;
    }
}
