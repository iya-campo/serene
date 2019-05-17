package com.example.grey.serene;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class PopMeds extends Activity {

    String meds;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_meds);

        Intent myIntent = getIntent();
        if (myIntent.hasExtra("MedStats")) {
            meds = myIntent.getStringExtra("MedStats");
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.6), (int)(height * 0.2));

        TextView medsContentText = (TextView) findViewById(R.id.medsContent);
        medsContentText.setText(Html.fromHtml("Number of Medications Taken: " + meds));
    }
}
