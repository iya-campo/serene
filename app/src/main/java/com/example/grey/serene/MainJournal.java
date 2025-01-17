package com.example.grey.serene;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainJournal extends Fragment {

    private CalendarView calendarView;
    private DatabaseReference refDate;
    private FirebaseDatabase database;

    boolean editable = true;

    String userID = Main.userID;
    String date = Main.date;

    String journalDate;

    public MainJournal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_journal, container, false);

        final TextView activities = (TextView) view.findViewById(R.id.activitiesDone);
        final TextView activityDate = (TextView) view.findViewById(R.id.journalDateText);
        final Button addEntry = (Button) view.findViewById(R.id.addEntryButton);

        database = FirebaseDatabase.getInstance();
        refDate = database.getReference().child("Journal").child(userID);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("MMM dd, yyyy");
        journalDate = mdformat.format(calendar.getTime());
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        activityDate.setText(getDayName(day) + journalDate);

        //Reads from DB as soon as activity runs.
        refDate.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Journal journal = snapshot.getValue(Journal.class);
                    editable = true;
                    if (journalDate.equals(journal.getDate())) {
                        if (journalDate.equals(date)) {
                            activities.setText(
                                    "Amount of Hours Slept:\n" + journal.getHours_slept() + "\n" +
                                            "Food Intake:\n" + journal.getFood_intake() + "\n" +
                                            "Medicine Intake:\n" + journal.getMedicinal_intake());
                            addEntry.setText("✎");
                            break;
                        }
                    } else {
                        activities.setText("This day's journal entry is empty.");
                        addEntry.setText("+");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        calendarView = (CalendarView) view.findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);
                journalDate = getMonthForInt(month) + " " + String.valueOf(dayOfMonth) + ", " + String.valueOf(year);
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                activityDate.setText(getDayName(day) + journalDate);

                //Reads from DB only after an event has occurred.
                refDate.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        addEntry.setVisibility(View.GONE);
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Journal journal = snapshot.getValue(Journal.class);
                            if (journalDate.equals(journal.getDate())) {
                                activities.setText(
                                        "Amount of Hours Slept:\n" + journal.getHours_slept() + "\n" +
                                                "Food Intake:\n" + journal.getFood_intake() + "\n" +
                                                "Medicine Intake:\n" + journal.getMedicinal_intake());
                                addEntry.setVisibility(View.VISIBLE);
                                if (journalDate.equals(date)) {
                                    addEntry.setText("✎");
                                    editable = true;
                                } else {
                                    addEntry.setText("➥");
                                    editable = false;
                                }
                                break;
                            } else {
                                if (journalDate.equals(date)) {
                                    addEntry.setVisibility(View.VISIBLE);
                                    editable = true;
                                }
                                activities.setText("This day's journal entry is empty.");
                                addEntry.setText("+");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                refDate.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (!dataSnapshot.exists()) {
                            if (journalDate.equals(date)) {
                                addEntry.setVisibility(View.VISIBLE);
                                editable = true;
                            } else {
                                addEntry.setVisibility(View.GONE);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        Button addEntryButton = (Button) view.findViewById(R.id.addEntryButton);

        addEntryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String stringEditable = String.valueOf(editable);
                Intent showJournalEntry = new Intent(getActivity().getApplicationContext(), JournalEntry.class);
                showJournalEntry.putExtra("journalDate", journalDate);
                showJournalEntry.putExtra("editable", stringEditable);
                startActivity(showJournalEntry);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        return view;
    }

    String getMonthForInt(int num) {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11) {
            month = months[num];
        }
        return month;
    }

    String getDayName(int day) {
        String nameOfDay = "";
        switch (day) {
            case Calendar.SUNDAY:
                nameOfDay = "Sunday, ";
                break;
            case Calendar.MONDAY:
                nameOfDay = "Monday, ";
                break;
            case Calendar.TUESDAY:
                nameOfDay = "Tuesday, ";
                break;
            case Calendar.WEDNESDAY:
                nameOfDay = "Wednesday, ";
                break;
            case Calendar.THURSDAY:
                nameOfDay = "Thursday, ";
                break;
            case Calendar.FRIDAY:
                nameOfDay = "Friday, ";
                break;
            case Calendar.SATURDAY:
                nameOfDay = "Saturday, ";
                break;
        }
        return nameOfDay;
    }
}
