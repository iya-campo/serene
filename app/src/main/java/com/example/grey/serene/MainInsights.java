package com.example.grey.serene;


import android.annotation.SuppressLint;
import android.content.Context;
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
    String title, author, type, content, source,id;

    public MainInsights() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_insights, container, false);
        userID = getArguments().getString("id");

        menu = (ExpandableListView) view.findViewById(R.id.insightsMenu);
        initData();
        listAdapter = new ExpandableListAdapter(this.getActivity(), listDataHeader, listHash);
        menu.setAdapter(listAdapter);

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

                savedRef = database.getReference().child("Saved Insights").child(userID);



                FirebaseDatabase.getInstance().getReference().child("Articles").child(articleKey).child("Title").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        title = dataSnapshot.getValue().toString();
                        articleTitle.setText(title);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





                Button heartButton = (Button) v.findViewById(R.id.heartButton);
                heartButton.setTag(position);
                heartButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int positionz=(Integer)v.getTag();
                        final long key = Long.parseLong(articleKey);
                        ref = database.getReference().child("Articles").child(String.valueOf(positionz+1));
                        ref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    String pos = String.valueOf(positionz+1);
                                    id = snapshot.child("id").getValue(String.class);
                                    if (pos.equals(id)) {
                                        title = snapshot.child("Title").getValue(String.class);
                                        author = snapshot.child("Author").getValue(String.class);
                                        type = snapshot.child("Type").getValue(String.class);
                                        content = snapshot.child("Content").getValue(String.class);
                                        source = snapshot.child("Source").getValue(String.class);
                                    }
                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


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



                        articleData = new Articles(maxid+1, title, author, type, content, source);
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

        listDataHeader.add("Menu");

        List<String> menu1 = new ArrayList<>();
        menu1.add("Serene Insights");
        menu1.add("Saved Insights");

        listHash.put(listDataHeader.get(0), menu1);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}
