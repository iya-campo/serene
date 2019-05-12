package com.example.grey.serene;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ProfileInterpretations extends Fragment {

    private static final String TAG = "ProfileInterpretations";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_interpretations, container, false);

        TextView interp = (TextView) view.findViewById(R.id.interpText);
        interp.setText("Your progress is yet to be interpreted. :(");

        return view;
    }
}
