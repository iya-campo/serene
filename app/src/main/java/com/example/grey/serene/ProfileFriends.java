package com.example.grey.serene;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;


public class ProfileFriends extends Fragment {

    private static final String TAG = "ProfileFriends";

    ListView friendsList;
    int[] friendIcon = {R.drawable.account_icon, R.drawable.account_icon};
    String[] friendMessage = {"Your logs has been sent to [Username].", "You can do it!"};

    private Button btnTest2;
    private Button addFriendsButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_friends, container, false);

        friendsList = (ListView) view.findViewById(R.id.friendsListView);
        ListViewFriendsAdapter listViewFriendsAdapter = new ListViewFriendsAdapter(this.getActivity(), friendIcon, friendMessage);
        friendsList.setAdapter(listViewFriendsAdapter);

        addFriendsButton = (Button) view.findViewById(R.id.addFriendsButton);
        addFriendsButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent showChat = new Intent(getActivity().getApplicationContext(), Chat.class);
                startActivity(showChat);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }

        });

        return view;
    }
}
