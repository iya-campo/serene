package com.example.grey.serene;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class PopInterp extends Activity {

    String date, content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_interp);

        Intent myIntent = getIntent();
        if (myIntent.hasExtra("InterpDate") && myIntent.hasExtra("InterpContent")) {
            date = myIntent.getStringExtra("InterpDate");
            content = myIntent.getStringExtra("InterpContent");
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.6), (int)(height * 0.2));

        TextView interpTitleText = (TextView) findViewById(R.id.interpTitle);
        TextView interpContentText = (TextView) findViewById(R.id.interpContent);
        interpTitleText.setText(Html.fromHtml(date));
        interpContentText.setText(Html.fromHtml(content));
    }
}
