package com.example.grey.serene;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

    private Activity context;
    private String[] article;

    public ListViewAdapter(Activity context, String[] article) {
        this.context = context;
        this.article = article;
    }

    @Override
    public int getCount() {
        return article.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        view = context.getLayoutInflater().inflate(R.layout.listview_layout, null);

        TextView articleTextVIew = (TextView) view.findViewById(R.id.articleText);

        articleTextVIew.setText(article[position]);
        return view;
    }
}
