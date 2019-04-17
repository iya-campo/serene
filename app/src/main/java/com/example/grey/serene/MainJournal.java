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
    private String curDate, userID;
    private DatabaseReference refDate;
    private FirebaseDatabase database;
    private TextView activityDate;

    public MainJournal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        userID = getArguments().getString("id");
        View view = inflater.inflate(R.layout.fragment_main_journal, container, false);
        final TextView activities = (TextView) view.findViewById(R.id.activitiesDone);
        activityDate = (TextView) view.findViewById(R.id.journalDateText);


        database = FirebaseDatabase.getInstance();
        refDate = database.getReference().child("Journal").child("" + userID);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("MMMM dd, yyyy");
        curDate = mdformat.format(calendar.getTime());
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String nameOfDay = getDayName(day);



        activityDate.setText(nameOfDay + curDate);

        refDate.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Journal journal = snapshot.getValue(Journal.class);
                    if (curDate.equals(journal.getDate())) {
                        Toast.makeText(getContext(), "" + journal.getDate(), Toast.LENGTH_SHORT).show();
                        activities.setText("Amount of Hours Slept:\n" + journal.getHours_slept() + "\n" +
                                            "Food Intake:\n" + journal.getFood_intake() + "\n" +
                                            "Medicine Intake:\n" + journal.getMedicinal_intake());

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
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            int dayOfMonth) {
             curDate = getMonthForInt(month) + " " + String.valueOf(dayOfMonth) + ", " + String.valueOf(year);
             activityDate.setText(getDayName(dayOfMonth) + curDate);
             refDate.addListenerForSingleValueEvent(new ValueEventListener() {

             @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                         Journal journal = snapshot.getValue(Journal.class);
                         if (curDate.equals(journal.getDate())) {
                              //TextView activitiesDone = (TextView) ViewG.findViewById(R.id.activitiesDone);
                              activities.setText("Amount of Hours Slept:\n" + journal.getHours_slept() + "\n" +
                                                    "Food Intake:\n" + journal.getFood_intake() + "\n" +
                                                    "Medicine Intake:\n" + journal.getMedicinal_intake());
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
                Intent showJournalEntry = new Intent(getActivity().getApplicationContext(), JournalEntry.class);
                showJournalEntry.putExtra("date", curDate);
                showJournalEntry.putExtra("userid", userID);
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
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }

    String getDayName(int day){
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
