package com.example.grey.serene;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Main extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static Activity main;

    private static final String TAG = "MainActivity";

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    private MainHome homeFragment;
    private MainJournal journalFragment;
    private MainInsights insightsFragment;

    private NotificationManagerCompat notificationManager;
    DatabaseReference ref;
    String notif;

    public static String userID;
    public static String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main = this;

        Intent myIntent = getIntent();
        if (myIntent.hasExtra("userID")) {
            userID = myIntent.getStringExtra("userID");
        }

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("MMMM dd, yyyy");
        date = mdformat.format(calendar.getTime());

        Button profileButton = (Button) findViewById(R.id.profileButton);
        profileButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showProfile = new Intent(getApplicationContext(), Profile.class);
                startActivity(showProfile);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });


        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        frameLayout = findViewById(R.id.container);

        homeFragment = new MainHome();
        journalFragment = new MainJournal();
        insightsFragment = new MainInsights();

        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                notif = (String) dataSnapshot.child("notifications").getValue();
                String alarmTime = (String) dataSnapshot.child("alarmTime").getValue();
                DateFormat df = new SimpleDateFormat("hh:mm aa");
                DateFormat outputFormat = new SimpleDateFormat("HH:mm");
                Date date = null;
                String output = "";
                int hr = 0;
                int min = 0;

                try {
                    date = df.parse(alarmTime);
                    //Changing the format of date and storing it in String
                    output = outputFormat.format(date);
                    //Displaying the date
                    hr = Integer.valueOf(output.substring(0, 2));
                    min = Integer.valueOf(output.substring(3, 5));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if ((notif.equals("yes"))) {
                    startAlarm(hr, min);
                    Log.i("alarm", "this is working");
                    Log.i("alarm", currentDate);

                }


                if(alarmTime.equals("")){
                    deleteAlarm();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                setFragment(homeFragment);
                return true;
            case R.id.navigation_journal:
                setFragment(journalFragment);
                return true;
            case R.id.navigation_insights:
                setFragment(insightsFragment);
                return true;
        }
        return false;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

    private void startAlarm(int sHour, int sMin) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.HOUR_OF_DAY, sHour);
        calendar.set(calendar.MINUTE, sMin);
        calendar.set(calendar.SECOND, 0);
        calendar.set(calendar.MILLISECOND, 0);
        long sdl = calendar.getTimeInMillis();

        AlarmManager ALARM1 = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);
        final int _id = (int) System.currentTimeMillis();
        PendingIntent sender = PendingIntent.getBroadcast(this, _id, intent, PendingIntent.FLAG_ONE_SHOT);
        ALARM1.setRepeating(AlarmManager.RTC_WAKEUP, sdl,
                AlarmManager.INTERVAL_DAY, sender);

    }

    private void deleteAlarm(){
        ALARM1 = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(getApplicationContext(), NotificationReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getApplicationContext(), 1, myIntent, 0);

        ALARM1.cancel(pendingIntent);
    }


    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
