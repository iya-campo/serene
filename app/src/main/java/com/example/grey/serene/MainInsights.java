package com.example.grey.serene;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;
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
public class MainInsights extends Fragment{

  private ListView listViewArticles;
  private ExpandableListView menu;
  private ExpandableListAdapter listAdapter;
  private List<String> listDataHeader;
  private HashMap<String, List<String>> listHash;
  DatabaseReference databaseReference;
  List<Articles> articlesList;
  Boolean menuState;
  FirebaseListAdapter adapter;
  //Button insightButton;

  public MainInsights() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_main_insights, container, false);

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

    adapter = new FirebaseListAdapter(articlesFirebaseListOptions) {
        @Override
        protected void populateView(@NonNull View v, @NonNull Object model, int position) {
            final TextView articleTitle = v.findViewById(R.id.articleText);

            String articleKey = this.getRef(position).getKey();

          FirebaseDatabase.getInstance().getReference().child("Articles").child(articleKey).child("Title").addListenerForSingleValueEvent(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 articleTitle.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
          });

        }

    };
    listViewArticles.setAdapter(adapter);
    //insightButton = (Button) view.findViewById(R.id.insightButton);
    menuState = false;
/*
    insightButton.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        if(menuState == false){
          menuState = true;
        } else {
          menuState = false;
        }
        Toast.makeText(getActivity(), "" + menuState, Toast.LENGTH_SHORT).show();
      }
    });
*/
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
