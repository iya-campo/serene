package com.example.grey.serene;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class ProfileFriends extends Fragment {

  private static final String TAG = "ProfileFriends";

  private Button btnTest2;
  private Button addFriendsButton;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_profile_friends, container, false);

    //Button Test
    btnTest2 = (Button) view.findViewById(R.id.btnTest2);
    btnTest2.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Toast.makeText(getActivity(), "Testing Button Click 2", Toast.LENGTH_SHORT).show();
      }

    });

    addFriendsButton = (Button) view.findViewById(R.id.addFriendsButton);
    addFriendsButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        //Function to add friends
      }

    });

    return view;
  }
}
