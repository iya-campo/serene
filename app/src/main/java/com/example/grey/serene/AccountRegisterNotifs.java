package com.example.grey.serene;

import android.app.AlarmManager;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;

public class AccountRegisterNotifs extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    int hour, minute;

    Button timeButton;
    Button yes, no;

    String notif;
    String alarmTitle;
    String userName;
    String userEmail;
    String userPassword;
    String userNickname;
    int userAge;
    EditText alarmName;
    DatabaseReference ref;
    Users user;
    long maxid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_register_notifs);

        getSupportActionBar().hide();

        timeButton = (Button) findViewById(R.id.timeButton);
        Button next2Button = (Button) findViewById(R.id.next2Button);

        alarmName = (EditText) findViewById(R.id.alarmTitleField);
        maxid = 0;
        ref = FirebaseDatabase.getInstance().getReference().child("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxid = (dataSnapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        yes = (Button) findViewById(R.id.yes1Button);
        no = (Button) findViewById(R.id.no1Button);

        Intent myIntent = getIntent();

        if (myIntent.hasExtra("username")) {
            userName = myIntent.getStringExtra("username");
        }
        if (myIntent.hasExtra("useremail")) {
            userEmail = myIntent.getStringExtra("useremail");
        }
        if (myIntent.hasExtra("userpassword")) {
            userPassword = myIntent.getStringExtra("userpassword");
        }
        if (myIntent.hasExtra("usernickname")) {
            userNickname = myIntent.getStringExtra("usernickname");
        }
        if (myIntent.hasExtra("userage")) {
            userAge = myIntent.getIntExtra("userage", userAge);
        }

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

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                hour = c.get(Calendar.HOUR);
                minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(AccountRegisterNotifs.this,
                        (TimePickerDialog.OnTimeSetListener) AccountRegisterNotifs.this, hour, minute, false);
                timePickerDialog.show();
                TimePickerFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
        next2Button.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showLogin = new Intent(getApplicationContext(), AccountLogin.class);

                //alarm = time.getSelectedItem().toString() + " " + day.getSelectedItem().toString();
                alarmTitle = alarmName.getText().toString();

                user = new Users(maxid + 1, userName, userEmail, userNickname, userAge, alarmTitle, notif, userPassword);

                ref.child(String.valueOf(maxid + 1)).setValue(user);
                Toast.makeText(AccountRegisterNotifs.this, "Congratulations! You have signed up.", Toast.LENGTH_LONG).show();

                startActivity(showLogin);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        int hour = hourOfDay % 12;
        if (hour == 0)
            hour = 12;
        timeButton.setText(String.format("%02d:%02d %s", hour, minute, hourOfDay < 12 ? "am" : "pm"));
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
