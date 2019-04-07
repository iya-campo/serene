package com.example.grey.serene;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Profile extends AppCompatActivity {

  private static final String TAG = "ProfileActivity";

  private SectionsPageAdapter mSectionPageAdapter;

  private ViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);

    //Header Buttons
    Button settingsButton = (Button) findViewById(R.id.settingsButton);

    settingsButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        Intent showSettings = new Intent(getApplicationContext(), Settings.class);
        startActivity(showSettings);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
      }

    });

    Log.d(TAG, "onCreate: Starting.");

    mSectionPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

    mViewPager = (ViewPager) findViewById(R.id.container);
    setupViewPager(mViewPager);

    TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(mViewPager);
  }

  private void setupViewPager(ViewPager viewPager) {
    SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
    adapter.addFragment(new ProfileJourney(), "Journey");
    adapter.addFragment(new ProfileFriends(), "Friends");
    viewPager.setAdapter(adapter);
  }
  public void finish(){
    super.finish();
    overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
  }
}
