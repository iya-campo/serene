package com.example.grey.serene;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class PopInterp extends Activity {

    String interp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_interp);

        Intent myIntent = getIntent();
        if (myIntent.hasExtra("observation")) {
            interp = myIntent.getStringExtra("observation");
        }

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.6), (int)(height * 0.2));

        TextView medsContentText = (TextView) findViewById(R.id.interpContent);
        medsContentText.setText(Html.fromHtml(interp));
    }

}
