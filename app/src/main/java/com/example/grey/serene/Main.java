package com.example.grey.serene;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class Main extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

  private static final String TAG = "MainActivity";

  private BottomNavigationView bottomNavigationView;
  private FrameLayout frameLayout;

  private MainHome homeFragment;
  private MainJournal journalFragment;
  private MainInsights insightsFragment;

  private NotificationManagerCompat notificationManager;
  DatabaseReference ref;
  String notif,userId;

  Boolean menuState;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getSupportActionBar().hide();


    //Header Buttons
    Button profileButton = (Button) findViewById(R.id.profileButton);

    profileButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showProfile = new Intent(getApplicationContext(), Profile.class);
        startActivity(showProfile);
      }

    });

    bottomNavigationView = findViewById(R.id.bottom_navigation_view);
    frameLayout = findViewById(R.id.container);

    homeFragment = new MainHome();
    journalFragment = new MainJournal();
    insightsFragment = new MainInsights();

    bottomNavigationView.setOnNavigationItemSelectedListener(this);

    bottomNavigationView.setSelectedItemId(R.id.navigation_home);


    Intent myIntent = getIntent();

    if(myIntent.hasExtra("userID")) {
      userId = myIntent.getStringExtra("userID");
     }


    ref = FirebaseDatabase.getInstance().getReference().child("Users").child("1");
    ref.addValueEventListener(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        notif = (String)dataSnapshot.child("notifications").getValue();
        System.out.print(dataSnapshot.child("usersName").getValue());
        Calendar calendar = Calendar.getInstance();

        if(notif.equals("yes")){
          startAlarm(calendar);
        }
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }
    });



  }

  @Override
  public boolean onNavigationItemSelected(@NonNull MenuItem item) {

    switch(item.getItemId()){
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

  private void setFragment(Fragment fragment){

    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
    fragmentTransaction.replace(R.id.container, fragment);
    fragmentTransaction.commit();

  }

  /**Commented for testing purposes**/
  /*
  public void sendOnChannel1(){
    String title = "Hello Test!";
    String message = "Hello user! It's time to use Serene!";

    Intent activityIntent = new Intent(this, Main.class);
    PendingIntent contentIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

    Intent broadcastIntent = new Intent(this, NotificationReceiver.class);
    broadcastIntent.putExtra("toastMessage", message);
    PendingIntent actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID).setSmallIcon(R.drawable.ic_event_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setColor(Color.BLUE)
            .setContentIntent(contentIntent)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .addAction(R.mipmap.ic_launcher, "Toast", actionIntent)
            .build();
    notificationManager.notify(1,notification);
  }
  */

  private void startAlarm(Calendar c) {
    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    Intent intent = new Intent(this, NotificationReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

    if (c.before(Calendar.getInstance())) {
      c.add(Calendar.DATE, 1);
    }

    alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
  }


}
