package com.example.grey.serene;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
public class Profile extends AppCompatActivity {

  private static final String TAG = "ProfileActivity";

  private SectionsPageAdapter mSectionPageAdapter;

  private ViewPager mViewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);

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
}
