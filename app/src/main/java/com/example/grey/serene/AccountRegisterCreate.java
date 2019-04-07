package com.example.grey.serene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;


public class AccountRegisterCreate extends AppCompatActivity {

  Button submitButton;

  EditText username, email, password;
  String user_name, user_email, user_pass;
  DatabaseReference ref;
  Users user;
  long maxid;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account_register_create);

      getSupportActionBar().hide();

      submitButton = (Button) findViewById(R.id.submitButton);

      /**Commented for testing purposes**/
      /*
      final MyDBHandler db = new MyDBHandler(this);
      username=(EditText) findViewById(R.id.newUnField);
      email=(EditText) findViewById(R.id.newEmailField);
      password=(EditText) findViewById(R.id.newPwField);
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
      */

      submitButton.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
          Intent showInfo = new Intent(getApplicationContext(), AccountRegisterInfo.class);
          /**Commented for testing purposes**/
          /*
          user_name = username.getText().toString();
          user_email = email.getText().toString();
          user_pass = password.getText().toString();

          Toast.makeText(AccountRegisterCreate.this, "data inserted successfully", Toast.LENGTH_LONG).show();
          showInfo.putExtra("myExtra", user_name);
          showInfo.putExtra("useremail", user_email);
          showInfo.putExtra("userpassword", user_pass);
          */

          startActivity(showInfo);
          overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
          //List<Users> usersList = db.getAllUsersList();
          //for(Users users:usersList){
          //  String frDet = "\n\nName: " + users.getUsersName() + "\n\tEmail: " + users.getEmail() + "\n\tPassword: " + users.getPassword();
          //  tv.append("\n" + frDet);
          //}
        }
      });
    }

    /**Commented for testing purposes**/
    /*
    public void addFruits(View view) {
      MyDBHandler dbHandler = new MyDBHandler(this);
      String method = "register";
      tutorial tutorial = new tutorial(this);
      tutorial.execute(method, user_name, user_email, user_pass);
      finish();

      username.setText("");
      email.setText("");
      password.setText("");
    }
    */
    public void finish(){
      super.finish();
      overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
