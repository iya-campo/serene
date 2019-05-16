package com.example.grey.serene;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainHome extends Fragment {

    ListView recentArticleList;
    FirebaseListAdapter adapterRecent;
    FirebaseDatabase database;
    DatabaseReference ref, savedRef;
    Articles articleData;
    String title, author, type, content, source;

    String userID = Main.userID;
    String date = Main.date;

    long id;

    public MainHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_home, container, false);

        TextView dateToday = (TextView) view.findViewById(R.id.dateTodayText);
        dateToday.setText(date);

        recentArticleList = (ListView) view.findViewById(R.id.recentArticleListView);

        dateToday = (TextView) view.findViewById(R.id.dateTodayText);
        dateToday.setText(date);

        Query query = FirebaseDatabase.getInstance().getReference().child("suggestedArticles").child(userID);
        FirebaseListOptions<Articles> articlesFirebaseListOptions = new FirebaseListOptions.Builder<Articles>()
                .setLayout(R.layout.listview_layout)
                .setQuery(query, Articles.class)
                .build();

        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("suggestedArticles").child(userID);


        adapterRecent = new FirebaseListAdapter(articlesFirebaseListOptions) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                final TextView articleTitle = v.findViewById(R.id.articleText);
                final String articleKey = this.getRef(position).getKey();

                FirebaseDatabase.getInstance().getReference().child("suggestedArticles").child(userID).child(articleKey).child("title").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String title = dataSnapshot.getValue(String.class);
                        articleTitle.setText(title);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Button articleButton = (Button) v.findViewById(R.id.articleButton);
                articleButton.setTag(position);
                articleButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final int position = (Integer) v.getTag() + 1;

                        ref.child(String.valueOf(position)).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Intent showArticle = new Intent(getActivity().getApplicationContext(), Article.class);

                                showArticle.putExtra("id", String.valueOf(dataSnapshot.child("id").getValue(Long.class)));
                                showArticle.putExtra("title", dataSnapshot.child("title").getValue(String.class));
                                showArticle.putExtra("author", dataSnapshot.child("author").getValue(String.class));
                                showArticle.putExtra("type", dataSnapshot.child("type").getValue(String.class));
                                showArticle.putExtra("content", dataSnapshot.child("content").getValue(String.class));
                                showArticle.putExtra("source", dataSnapshot.child("source").getValue(String.class));

                                startActivity(showArticle);
                                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });

                Button heartButton = (Button) v.findViewById(R.id.heartButton);
                heartButton.setTag(position);
                heartButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final int position = (Integer) v.getTag() + 1;

                        ref.child(String.valueOf(position)).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                id = dataSnapshot.child("id").getValue(Long.class); //Doesn't work. Returns 1???
                                title = dataSnapshot.child("title").getValue(String.class);
                                author = dataSnapshot.child("author").getValue(String.class);
                                type = dataSnapshot.child("type").getValue(String.class);
                                content = dataSnapshot.child("content").getValue(String.class);
                                source = dataSnapshot.child("source").getValue(String.class);

                                articleData = new Articles(id, title, author, type, content, source);
                                savedRef.push().setValue(articleData);

                                Toast.makeText(getContext(), "Added to Saved Insights", Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });
            }
        };
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    recentArticleList.setAdapter(adapterRecent);
                } else {
                    ///
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        Button homeJournalButton = (Button) view.findViewById(R.id.homeJournalButton);

        homeJournalButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showJournalEntry = new Intent(getActivity().getApplicationContext(), JournalEntry.class);
                showJournalEntry.putExtra("journalDate", date);
                startActivity(showJournalEntry);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        adapterRecent.startListening();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        adapterRecent.stopListening();
    }

}
