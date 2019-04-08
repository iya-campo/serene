package com.example.grey.serene;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainJournal extends Fragment {

    private CalendarView calendarView;
    private String curDate, userID;

    public MainJournal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_journal, container, false);

        calendarView = (CalendarView) view.findViewById(R.id.calendar);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
                int d = dayOfMonth;
                int m = month;
                int y = year;
                curDate = String.valueOf(m) + " " + String.valueOf(d) + " " + String.valueOf(y);
            }
        });

        userID = getArguments().getString("id");

        Button addEntryButton = (Button) view.findViewById(R.id.addEntryButton);

        addEntryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showJournalEntry = new Intent(getActivity().getApplicationContext(), JournalEntry.class);
                showJournalEntry.putExtra("date", curDate);
                showJournalEntry.putExtra("userid", userID);
                startActivity(showJournalEntry);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        return view;
    }
}
