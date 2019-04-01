package com.example.grey.serene;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class ProfileJourney extends Fragment {

  private static final String TAG = "ProfileJourney";

  private Button btnTest1;
  private Button medicineButton;
  private Button sleepButton;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
    View view = inflater.inflate(R.layout.fragment_profile_journey, container, false);

    //Button Test
    btnTest1 = (Button) view.findViewById(R.id.btnTest1);
    btnTest1.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Toast.makeText(getActivity(),"Testing Button Click 1", Toast.LENGTH_SHORT).show();
      }

    });

    medicineButton = (Button) view.findViewById(R.id.medicineButton);
    sleepButton = (Button) view.findViewById(R.id.sleepButton);

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
