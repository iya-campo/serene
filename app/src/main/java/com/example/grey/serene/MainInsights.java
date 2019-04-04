package com.example.grey.serene;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainInsights extends Fragment {

  private ExpandableListView listView;
  private ExpandableListAdapter listAdapter;
  private List<String> listDataHeader;
  private HashMap<String, List<String>> listHash;

  Boolean menuState;
  //Button insightButton;

  public MainInsights() {
    // Required empty public constructor
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_main_insights, container, false);

    listView = (ExpandableListView) view.findViewById(R.id.insightsMenu);
    initData();
    listAdapter = new ExpandableListAdapter(this.getActivity(), listDataHeader, listHash);
    listView.setAdapter(listAdapter);

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

  public void addArticles(){
    
  }
}
