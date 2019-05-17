package com.example.grey.serene;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class PopSleep extends Activity {

    String sleep;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_sleep);

        Intent myIntent = getIntent();
        if (myIntent.hasExtra("SleepStats")) {
            sleep = myIntent.getStringExtra("SleepStats");
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.6), (int)(height * 0.2));

        TextView sleepContentText = (TextView) findViewById(R.id.sleepContent);
        sleepContentText.setText(Html.fromHtml("Total Number of Hours Slept: " + sleep));
    }
}
