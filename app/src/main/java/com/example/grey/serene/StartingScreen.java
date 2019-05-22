package com.example.grey.serene;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartingScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                CheckSession();
            }
        }, SPLASH_TIME_OUT);

    }

    public void CheckSession() {
        Boolean check = Boolean.valueOf(SharedPrefs.readSharedSetting(StartingScreen.this, "Serene", "true"));
        SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences("userID", 0);

        if (check) {
            Intent showLogin = new Intent(StartingScreen.this, AccountLogin.class);
            showLogin.putExtra("Serene", check);
            startActivity(showLogin);
        } else {
            Intent showMain = new Intent(StartingScreen.this, Main.class);
            showMain.putExtra("userID", sharedPrefs.getString("userID", null));
            startActivity(showMain);
        }

        finish();
    }
}
