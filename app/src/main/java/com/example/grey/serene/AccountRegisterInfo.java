package com.example.grey.serene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

public class AccountRegisterInfo extends AppCompatActivity {

  EditText usernickname, age;
  String userNickname, userName, userEmail, userPassword;
  int user_age;
  DatabaseReference ref;
  Users user;
  long maxid;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account_register_info);

    getSupportActionBar().hide();

    Button next1Button = (Button) findViewById(R.id.next1Button);

    /**Commented for testing purposes**/
    /*
    usernickname = (EditText) findViewById(R.id.answer1Field);
    age  = (EditText) findViewById(R.id.answer2Field);
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

    Intent myIntent = getIntent();

    if(myIntent.hasExtra("myExtra")) {
      userName = myIntent.getStringExtra("myExtra");
      TextView text = (TextView) findViewById(R.id.welcomeText);
      text.setText("Hi " + userName + " welcome to Serene!");
    }
    if(myIntent.hasExtra("useremail")){
      userEmail = myIntent.getStringExtra("useremail");
    }
    if(myIntent.hasExtra("userpassword")){
      userPassword = myIntent.getStringExtra(userPassword);
    }
    */

    next1Button.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showNotifs = new Intent(getApplicationContext(), AccountRegisterNotifs.class);
        /**Commented for testing purposes**/
        /*
        userNickname = usernickname.getText().toString();
        user_age = Integer.parseInt(age.getText().toString());

        showNotifs.putExtra("username", userName);
        showNotifs.putExtra("useremail", userEmail);
        showNotifs.putExtra("userpassword", userPassword);
        showNotifs.putExtra("usernickname", userNickname);
        showNotifs.putExtra("userage", user_age);

        Toast.makeText(AccountRegisterInfo.this, "data inserted successfully", Toast.LENGTH_LONG).show();
        */
        startActivity(showNotifs);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
      }

    });
  }
  /*
  public void addFruits(View view) {
    String userNickname = usernickname.getText().toString();
    String user_age = age.getText().toString();
    String method = "register_info";
    tutorial tutorial = new tutorial(this);
    tutorial.execute(method, userNickname, user_age);
    finish();

    usernickname.setText("");
    age.setText("");
   }
   */
  public void finish(){
    super.finish();
    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
  }
}
