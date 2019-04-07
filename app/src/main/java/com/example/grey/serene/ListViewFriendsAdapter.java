package com.example.grey.serene;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewFriendsAdapter extends BaseAdapter {

    private Activity context;
    private int[] icon;
    private String[] message;

    public ListViewFriendsAdapter(Activity context, int[] icon, String[] message) {
        this.context = context;
        this.icon = icon;
        this.message = message;
    }

    @Override
    public int getCount() {
        return icon.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        view = context.getLayoutInflater().inflate(R.layout.listview_friends_layout, null);

        ImageView iconImageView = (ImageView) view.findViewById(R.id.friendIcon);
        TextView messageTextVIew = (TextView) view.findViewById(R.id.friendMessage);

        iconImageView.setImageResource(icon[position]);
        messageTextVIew.setText(message[position]);
        return view;
    }
}
