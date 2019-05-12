package com.example.grey.serene;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";

    private SectionsPageAdapter mSectionPageAdapter;

    private ViewPager mViewPager;

    DatabaseReference ref;

    private String userId;
    String fbNickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent myIntent = getIntent();

        if (myIntent.hasExtra("userID")) {
            userId = myIntent.getStringExtra("userID");
        }

        final TextView userText = (TextView) findViewById(R.id.userText);

        //Header Buttons
        Button settingsButton = (Button) findViewById(R.id.settingsButton);

        settingsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showSettings = new Intent(getApplicationContext(), Settings.class);
                showSettings.putExtra("userID", userId);
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

        ref = FirebaseDatabase.getInstance().getReference().child("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fbNickname = dataSnapshot.child(userId).child("nickname").getValue().toString();
                userText.setText(fbNickname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProfileJourney(), "Journey");
        adapter.addFragment(new ProfileInterpretations(), "Interpretations");
        viewPager.setAdapter(adapter);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
