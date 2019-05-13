package com.example.grey.serene;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainHome extends Fragment {

    //Recent Article ListView
    ListView recentArticleList;
    String[] recentArticleName = {"good posture may ease symptoms of depression", "good morning, heartache", "meet the real narcissists (they're not what you think)"};
    String curDate, userID;

    public MainHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        userID = getArguments().getString("id");
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd MMMM, yyyy");
        curDate = mdformat.format(calendar.getTime());

        TextView dateToday = view.findViewById(R.id.dateTodayText);
        dateToday.setText(curDate);

        recentArticleList = (ListView) view.findViewById(R.id.recentArticleListView);
        ListViewAdapter listViewAdapter = new ListViewAdapter(this.getActivity(), recentArticleName);
        recentArticleList.setAdapter(listViewAdapter);

        Button homeJournalButton = (Button) view.findViewById(R.id.homeJournalButton);

        homeJournalButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showJournalEntry = new Intent(getActivity().getApplicationContext(), JournalEntry.class);
                showJournalEntry.putExtra("date", curDate);
                showJournalEntry.putExtra("userID", userID);
                startActivity(showJournalEntry);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        return view;
    }

}
