package com.example.grey.serene;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class PopSleep extends Activity {

    String sleep, entry;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_sleep);

        Intent myIntent = getIntent();
        if (myIntent.hasExtra("SleepStats") && myIntent.hasExtra("EntryCount")) {
            sleep = myIntent.getStringExtra("SleepStats");
            entry = myIntent.getStringExtra("EntryCount");
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.6), (int)(height * 0.2));

        int avgSleep = Integer.parseInt(sleep)/Integer.parseInt(entry);

        TextView sleepContentText = (TextView) findViewById(R.id.sleepContent);
        sleepContentText.setText(Html.fromHtml("Total No. of Hours Slept: " + sleep + "\n" +
                "Average Sleep Hours: " + avgSleep));
    }
}
