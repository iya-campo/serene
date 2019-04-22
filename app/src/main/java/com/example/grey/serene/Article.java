package com.example.grey.serene;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Article extends AppCompatActivity {

    String id, title, author, type, content, source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Intent myIntent = getIntent();

        if (myIntent.hasExtra("id")) {
            id = myIntent.getStringExtra("id");
            title = myIntent.getStringExtra("title");
            author = myIntent.getStringExtra("author");
            type = myIntent.getStringExtra("type");
            content = myIntent.getStringExtra("content");
            source = myIntent.getStringExtra("source");
        }

        TextView articleTitleText = (TextView) findViewById(R.id.articleTitleText);
        TextView articleAuthorText = (TextView) findViewById(R.id.articleAuthorText);
        TextView articleContentText = (TextView) findViewById(R.id.articleContentText);

        String setTitle = title;
        String setAuthor = "<u>By " + author + "</u>";
        String setContent = content;

        articleTitleText.setText(setTitle);
        articleAuthorText.setText(Html.fromHtml(setAuthor));
        articleContentText.setText(setContent);

    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
