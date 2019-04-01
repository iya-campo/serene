package com.example.grey.serene;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AccountLogin extends AppCompatActivity {

  EditText pass, usr;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_account_login);

    getSupportActionBar().hide();

    Button loginButton = (Button) findViewById(R.id.loginButton);
    Button registerButton = (Button) findViewById(R.id.registerButton);

    /**Commented for testing purposes**/
    /*
    MyDBHandler db = new MyDBHandler(this);
    usr = (EditText) findViewById(R.id.unField);
    pass = (EditText) findViewById(R.id.pwField);

    //String userName = usr.getText().toString();
    //String passWord = pass.getText().toString();
    //final Boolean login = db.Login(userName, passWord);

    Toast.makeText(this, "Firebase Connection success!", Toast.LENGTH_LONG).show();
    */

    loginButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showMain = new Intent(getApplicationContext(), Main.class);
        startActivity(showMain);
      }

    });

    registerButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showCreate = new Intent(getApplicationContext(), AccountRegisterCreate.class);
        startActivity(showCreate);
      }

    });
  }

  /**Commented for testing purposes**/
  /*
  public void loginBtn(View view){
      String user = usr.getText().toString();
      String pw = pass.getText().toString();
      String method = "login";
      tutorial tutorial1 = new tutorial(this);
      tutorial1.execute(method,user,pw);
  }
  */

}
