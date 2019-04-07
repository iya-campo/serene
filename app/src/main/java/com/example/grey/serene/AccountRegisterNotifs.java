package com.example.grey.serene;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;

import java.util.Calendar;

public class AccountRegisterNotifs extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

  int hour, minute;

  Button timeButton;
  Button yes, no;

  String notif;
  String alarmTitle;
  String alarm;
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

    /**Commented for testing purposes**/
    /*
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
    time = (Spinner) findViewById(R.id.timeSpinner);
    day = (Spinner) findViewById(R.id.daySpinner);

    Intent myIntent = getIntent();

    if(myIntent.hasExtra("username")) {
      userName = myIntent.getStringExtra("myExtra");
    }
    if(myIntent.hasExtra("useremail")){
      userEmail = myIntent.getStringExtra("useremail");
    }
    if(myIntent.hasExtra("userpassword")){
      userPassword = myIntent.getStringExtra("userpassword");
    }
    if(myIntent.hasExtra("usernickname")){
      userNickname = myIntent.getStringExtra("usernickname");
    }
    if(myIntent.hasExtra("userage")){
      userAge = Integer.parseInt(myIntent.getStringExtra("userage"));
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
    */
    timeButton.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View view){
        Calendar c = Calendar.getInstance();
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(AccountRegisterNotifs.this,
          (TimePickerDialog.OnTimeSetListener) AccountRegisterNotifs.this, hour, minute, false);
        timePickerDialog.show();
      }
    });
    next2Button.setOnClickListener((new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent showLogin = new Intent(getApplicationContext(), AccountLogin.class);
        /**Commented for testing purposes**/
        /*
        alarm = time.getSelectedItem().toString() + " " + day.getSelectedItem().toString();
        alarmTitle = alarmName.getText().toString();

        user = new Users(maxid, userName, userEmail, userNickname, userAge, alarmTitle, alarm, notif, userPassword);

        ref.child(String.valueOf(maxid+1)).setValue(user);
        Toast.makeText(AccountRegisterNotifs.this, "Congratulations! You have signed up.", Toast.LENGTH_LONG).show();
        */
        startActivity(showLogin);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
      }
    }));
  }

  @Override
  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    int hour = hourOfDay % 12;
    if(hour == 0)
      hour = 12;
    timeButton.setText(String.format("%02d:%02d %s", hour, minute, hourOfDay < 12 ? "am" : "pm"));
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
    }
    */
    public void finish(){
      super.finish();
      overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
