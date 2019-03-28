package com.example.grey.serene;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class Main extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

  private static final String TAG = "MainActivity";

  private BottomNavigationView bottomNavigationView;
  private FrameLayout frameLayout;

  private MainHome homeFragment;
  private MainJournal journalFragment;
  private MainInsights insightsFragment;
  private MainAccount accountFragment;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    getSupportActionBar().hide();

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
    accountFragment = new MainAccount();

    bottomNavigationView.setOnNavigationItemSelectedListener(this);

    bottomNavigationView.setSelectedItemId(R.id.navigation_home);

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
      /*case R.id.navigation_account:
        setFragment(accountFragment);
        return true;
      */
    }
    return false;
  }

  private void setFragment(Fragment fragment){

    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
    fragmentTransaction.replace(R.id.container, fragment);
    fragmentTransaction.commit();

  }

}
