package com.example.grey.serene;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.reflect.Member;
import java.util.List;


public class AccountRegisterCreate extends AppCompatActivity {

    EditText username, email, password;
    String user_name, user_email, user_pass;
    Button submitButton;
    DatabaseReference ref;
    Users user;
    long maxid;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account_register_create);
      getSupportActionBar().hide();
      final TextView tv = (TextView) findViewById(R.id.textView);
      final MyDBHandler db = new MyDBHandler(this);
       username=(EditText) findViewById(R.id.newUnField);
       email=(EditText) findViewById(R.id.newEmailField);
       password=(EditText) findViewById(R.id.newPwField);
       user = new Users();
       user.setID(0);
       maxid = user.getID();


      submitButton = (Button) findViewById(R.id.submitButton);
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

      submitButton.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View v) {
              user_name = username.getText().toString();
              user_email = email.getText().toString();
              user_pass = password.getText().toString();

              user.setUsersName(user_name);
              user.setEmail(user_email);
              user.setPassword(user_pass);

               Toast.makeText(AccountRegisterCreate.this, "data inserted successfully", Toast.LENGTH_LONG).show();
              Intent showInfo = new Intent(getApplicationContext(), AccountRegisterInfo.class);
              showInfo.putExtra("myExtra", user_name);
              startActivity(showInfo);
             /* List<Users> usersList = db.getAllUsersList();
              for(Users users:usersList){
                  String frDet = "\n\nName: " + users.getUsersName() + "\n\tEmail: " + users.getEmail() + "\n\tPassword: " + users.getPassword();
                  tv.append("\n" + frDet);
              }*/
          }

      });
    }

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




}


