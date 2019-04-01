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
public class MainHome extends Fragment {


  public MainHome() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_main_home, container, false);

    Button homeJournalButton = (Button) view.findViewById(R.id.homeJournalButton);

    homeJournalButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showJournalEntry = new Intent(getActivity().getApplicationContext(), JournalEntry.class);
        startActivity(showJournalEntry);
      }

    });
    return view;
  }

}
