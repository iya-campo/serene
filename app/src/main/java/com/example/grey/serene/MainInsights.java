package com.example.grey.serene;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainInsights extends Fragment {

    private ExpandableListView listViewArticles;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHash;
    DatabaseReference databaseReference;
    List<Articles> articlesList;
    Boolean menuState;
    //Button insightButton;
    Context myContext;

    public MainInsights() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public MainInsights(Context context) {
        this.myContext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Articles");
        listViewArticles = (ExpandableListView) getView().findViewById(R.id.insightsMenu);
        articlesList = new ArrayList<>();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_insights, container, false);

        initData();
        listAdapter = new ExpandableListAdapter(this.getActivity(), listDataHeader, listHash);
        listViewArticles.setAdapter(listAdapter);

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
        //attaching value event listener
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous artist list
                articlesList.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting articles
                    Articles articles = postSnapshot.child("Title").getValue(Articles.class);
                    //adding artist to the list
                    articlesList.add(articles);
                }

                System.out.print(articlesList);
                //creating adapter
                ArticlesList articlesAdapter = new ArticlesList(getActivity(), articlesList);
                //attaching adapter to the listview
                listViewArticles.setAdapter(articlesAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
