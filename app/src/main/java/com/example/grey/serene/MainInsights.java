package com.example.grey.serene;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainInsights extends Fragment {

    private static String itemName;
    private boolean menuState; //false close, true open

    private ListView listViewArticles;
    private ExpandableListView menu;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    FirebaseListAdapter adapter, adapterSaved;
    public String userID;
    FirebaseDatabase database;
    DatabaseReference ref, savedRef;
    long maxid;
    Articles articleData;
    String title, author, type, content, source;
    Long id;

    public MainInsights() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        userID = getArguments().getString("id");
        View view = inflater.inflate(R.layout.fragment_main_insights, container, false);

        itemName = "Menu";

        menu = (ExpandableListView) view.findViewById(R.id.insightsMenu);
        initData();
        listAdapter = new ExpandableListAdapter(this.getActivity(), listDataHeader, listHash);
        menu.setAdapter(listAdapter);

        menu.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View view, int groupPosition, int childPosition, long id) {
                TextView listItem = (TextView) view.findViewById(R.id.listItem);
                itemName = listItem.getText().toString();
                if (itemName.equals("Serene Insights")) {
                    listViewArticles.setAdapter(adapter);
                } else {
                    listViewArticles.setAdapter(adapterSaved);
                    adapterSaved.startListening();
                    Toast.makeText(getContext(), "working", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        menuState = false;
        final LinearLayout spinnerContainer = (LinearLayout) view.findViewById(R.id.spinnerContainer);
        menu.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (!menuState) {
                    menuState = true;
                    spinnerContainer.setBackgroundResource(R.drawable.bg_menu_container_open);
                } else {
                    menuState = false;
                    spinnerContainer.setBackgroundResource(R.drawable.bg_menu_container);
                }
                return false;
            }
        });

        listViewArticles = (ListView) view.findViewById(R.id.articleListView);

        Query query = FirebaseDatabase.getInstance().getReference().child("Articles");
        FirebaseListOptions<Articles> articlesFirebaseListOptions = new FirebaseListOptions.Builder<Articles>()
                .setLayout(R.layout.listview_layout)
                .setQuery(query, Articles.class)
                .build();

        Query querySaved = FirebaseDatabase.getInstance().getReference().child("Saved Insights");
        FirebaseListOptions<Articles> savedArticlesFirebaseListOptions = new FirebaseListOptions.Builder<Articles>()
                .setLayout(R.layout.listview_layout)
                .setQuery(querySaved, Articles.class)
                .build();

        database = FirebaseDatabase.getInstance();

        adapter = new FirebaseListAdapter(articlesFirebaseListOptions) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                final TextView articleTitle = v.findViewById(R.id.articleText);
                final String articleKey = this.getRef(position).getKey();

                FirebaseDatabase.getInstance().getReference().child("Articles").child(articleKey).child("title").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String title = dataSnapshot.getValue().toString();
                        articleTitle.setText(title);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                savedRef = database.getReference().child("Saved Insights").child(userID);
                savedRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            maxid = (dataSnapshot.getChildrenCount());
                        }
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

                        ref = database.getReference().child("Articles");
                        ref.child(String.valueOf(position)).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Intent showArticle = new Intent(getActivity().getApplicationContext(), Article.class);
                                showArticle.putExtra("userID", userID);

                                showArticle.putExtra("id", String.valueOf(position));
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

                        ref = database.getReference().child("Articles");
                        ref.child(String.valueOf(position)).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                id = dataSnapshot.child("id").getValue(Long.class); //Doesn't work. Returns 1???
                                title = dataSnapshot.child("title").getValue(String.class);
                                author = dataSnapshot.child("author").getValue(String.class);
                                type = dataSnapshot.child("type").getValue(String.class);
                                content = dataSnapshot.child("content").getValue(String.class);
                                source = dataSnapshot.child("source").getValue(String.class);

                                articleData = new Articles(position, title, author, type, content, source);
                                savedRef.child(String.valueOf(maxid + 1)).setValue(articleData);

                                Toast.makeText(getContext(), "Article " + String.valueOf(position) + " added to Saved Insights", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                });
            }
        };

        adapterSaved = new FirebaseListAdapter(savedArticlesFirebaseListOptions) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                final TextView articleTitle = v.findViewById(R.id.articleText);
                final String articleKey = this.getRef(position).getKey();
                Toast.makeText(getContext(), articleKey, Toast.LENGTH_SHORT).show();

                FirebaseDatabase.getInstance().getReference().child("Saved Insights").child(userID).child(articleKey).child("title").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String title = dataSnapshot.getValue().toString();
                        articleTitle.setText(title);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Button articleButton = (Button) v.findViewById(R.id.articleButton);
                articleButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Intent showArticle = new Intent(getActivity().getApplicationContext(), Article.class);
                        startActivity(showArticle);
                        getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }

                });
            }
        };

        return view;
    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHash = new HashMap<>();

        listDataHeader.add(itemName);

        List<String> menu = new ArrayList<>();
        menu.add("Serene Insights");
        menu.add("Saved Insights");

        listHash.put(listDataHeader.get(0), menu);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }
}
