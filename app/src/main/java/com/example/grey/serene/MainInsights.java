package com.example.grey.serene;


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


/**
 * A simple {@link Fragment} subclass.
 */
public class MainInsights extends Fragment {

  //Article ListView
  ListView articleList;
  String[] articleName = {"good posture may ease symptoms of depression", "good morning, heartache", "meet the real narcissists (they're not what you think)", "Article 1", "Article 2", "Article 3", "Article 4", "Article 5"};

  //Menu ListView
  private ExpandableListView listView;
  private ExpandableListAdapter expandableListAdapter;
  private List<String> listDataHeader;
  private HashMap<String, List<String>> listHash;

  public MainInsights() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main_insights, container, false);

    articleList = (ListView) view.findViewById(R.id.articleListView);
    ListViewAdapter listViewAdapter = new ListViewAdapter(this.getActivity(), articleName);
    articleList.setAdapter(listViewAdapter);

    listView = (ExpandableListView) view.findViewById(R.id.insightsMenu);
    initData();
    expandableListAdapter = new ExpandableListAdapter(this.getActivity(), listDataHeader, listHash);
    listView.setAdapter(expandableListAdapter);

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

  public void addArticles(){
    
  }
}
