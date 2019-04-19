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
    FirebaseListAdapter adapter;
    public String userID;
    FirebaseDatabase database;
    DatabaseReference ref, savedRef;
    long maxid;
    Articles articleData;
    String title, author, type, content, source, id;

    public MainInsights() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_insights, container, false);
        userID = getArguments().getString("id");

        itemName = "Menu";
        Log.i("myTag", itemName);

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
                    Log.i("Adapter", itemName);
                    adapter.startListening();
                } else {
                    //adapter.stopListening();
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

        database = FirebaseDatabase.getInstance();


        adapter = new FirebaseListAdapter(articlesFirebaseListOptions) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Object model, int position) {
                final TextView articleTitle = v.findViewById(R.id.articleText);
                final String articleKey = this.getRef(position).getKey();

                FirebaseDatabase.getInstance().getReference().child("Articles").child(articleKey).child("Title").addListenerForSingleValueEvent(new ValueEventListener() {

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

                final Button heartButton = (Button) v.findViewById(R.id.heartButton);
                heartButton.setTag(position);

                heartButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int positionz = (Integer)v.getTag();
                        //Toast.makeText(getContext(), articleKey, Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getContext(), String.valueOf(heartButton.getTag()), Toast.LENGTH_SHORT).show();
                        ref = database.getReference().child("Articles");
                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String id = ref.push().getKey();
                                for (int i = 1; i <= dataSnapshot.getChildrenCount(); i++) {
                                    if (articleKey.equals(String.valueOf(i))) {
                                        //Toast.makeText(getContext(), "Boop" + dataSnapshot.getChildrenCount(), Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getContext(), articleKey, Toast.LENGTH_SHORT).show();
                                        title = (String) dataSnapshot.child(articleKey).child("Title").getValue();
                                        author = (String) dataSnapshot.child(articleKey).child("Author").getValue();
                                        //title = snapshot.child("Title").getValue(String.class);
                                    }
                                }
                                /*
                                boolean yay = true;
                                String pos = String.valueOf(positionz+1);
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    title = snapshot.child("1").child("Title").getValue(String.class);
                                    Log.i("title", String.valueOf(title));
                                    if (yay) {
                                        //Log.i("tag", "true");
                                        Toast.makeText(getContext(), pos, Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getContext(), pos + " " + id, Toast.LENGTH_SHORT).show();
                                        //title = (String) dataSnapshot.child(String.valueOf(pos)).child("Title").getValue();
                                        //author = snapshot.child(String.valueOf(pos)).child("Author").getValue(String.class);
                                        //type = snapshot.child(String.valueOf(pos)).child("Type").getValue(String.class);
                                        //content = snapshot.child(String.valueOf(pos)).child("Content").getValue(String.class);
                                        //source = snapshot.child(String.valueOf(pos)).child("Source").getValue(String.class);
                                    }
                                }
                                */

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

                        final long key = Long.parseLong(articleKey);
                        articleData = new Articles(key, title, author, type, content, source);
                        savedRef.child(String.valueOf(maxid + 1)).setValue(articleData);
                    }
                });


            }
        };
        listViewArticles.setAdapter(adapter);

        /*listViewArticles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                DatabaseReference itemRef = adapter.getRef(position);

                Toast toast = Toast.makeText(getActivity().getApplicationContext(), itemRef.getKey(), Toast.LENGTH_SHORT);
                toast.show();
            }
        });*/

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


}
