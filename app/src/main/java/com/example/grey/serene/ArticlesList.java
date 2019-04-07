package com.example.grey.serene;

import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ArticlesList extends ArrayAdapter<Articles> {

    private Activity context;
    private List<Articles> articlesList;

    public ArticlesList(Activity context,List<Articles> articlesList){
        super(context, R.layout.fragment_main_insights, articlesList);
        this.context = context;
        this.articlesList = articlesList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.fragment_main_insights, null, true);

        TextView textViewTitle = (TextView) listViewItem.findViewById(R.id.articleText);
        //TextView textViewAuthor = (TextView) listViewItem.findViewById(R.id.aasda);
        //TextView textViewType = (TextView) listViewItem.findViewById(R.id.aasda);
        //TextView textViewSource = (TextView) listViewItem.findViewById(R.id.aasda);
        //TextView textViewContent = (TextView) listViewItem.findViewById(R.id.aasda);

        Articles articles = articlesList.get(position);

        textViewTitle.setText(articles.getTitle());
        //textViewAuthor.setText(articles.getAuthor());
        //textViewType.setText(articles.getType());
        //textViewSource.setText(articles.getSource());
        //textViewContent.setText(articles.getContent());

        return listViewItem;
    }

}
