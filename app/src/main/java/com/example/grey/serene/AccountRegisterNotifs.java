package com.example.grey.serene;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountRegisterNotifs extends AppCompatActivity {

    Button yes, no;
    String notif, alarmTitle, alarm;
    EditText alarmName;
    DatabaseReference ref;
    Spinner time, day;
    Users user;
    long maxid;

    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_account_register_notifs);

      getSupportActionBar().hide();

      Button next2Button = (Button) findViewById(R.id.next2Button);
      alarmName = (EditText) findViewById(R.id.alarmTitleField);
      user = new Users();
      user.setID(0);
      maxid = user.getID();
      ref= FirebaseDatabase.getInstance().getReference().child("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxid = (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


      yes = (Button) findViewById(R.id.yes1Button);
      no = (Button) findViewById(R.id.no1Button);
      time=(Spinner) findViewById(R.id.timeSpinner);
      day=(Spinner) findViewById(R.id.daySpinner);

      yes.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View v) {
              notif = "yes";
          }

      });

      no.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View v) {
              notif = "no";
          }

      });

      next2Button.setOnClickListener((new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent showNotifs = new Intent(getApplicationContext(), AccountLogin.class);
              startActivity(showNotifs);
              alarm = time.getSelectedItem().toString() + " " + day.getSelectedItem().toString();
              alarmTitle = alarmName.getText().toString();

              user.setAlarmName(alarmTitle);
              user.setAlarm(alarm);
              user.setNotifications(notif);

              ref.child(String.valueOf(maxid+1)).setValue(user);
              Toast.makeText(AccountRegisterNotifs.this, "Congratulations! You have signed up.", Toast.LENGTH_LONG).show();
          }
      }));
  }
/*
    public void addFruits(View view) {
        EditText userNickname=(EditText) findViewById(R.id.answer1Field);
        MyDBHandler dbHandler = new MyDBHandler(this);

        String userName = userNickname.getText().toString();
        String method = "register_notif";
        tutorial tutorial = new tutorial(this);
        tutorial.execute(method, userName, alarm, notif);
        finish();



        userNickname.setText("");

    }*/
}
