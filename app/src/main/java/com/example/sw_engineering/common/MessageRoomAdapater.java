package com.example.sw_engineering.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sw_engineering.R;

import java.util.ArrayList;

public class MessageRoomAdapater extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<MessageRoomData> sample;

    public MessageRoomAdapater(Context context, ArrayList<MessageRoomData> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public MessageRoomData getItem(int position) {
        return sample.get(position);
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.com_message_room_item, null);

        ImageView profileImage = (ImageView)view.findViewById(R.id.profile_image);
        TextView sender = (TextView)view.findViewById(R.id.sender);
        TextView date = (TextView)view.findViewById(R.id.date);
        TextView preContents = (TextView)view.findViewById(R.id.pre_contents);

        profileImage.setImageResource(sample.get(position).getprofileImage());
        sender.setText(sample.get(position).getsender());
        date.setText(sample.get(position).getDate());
        preContents.setText(sample.get(position).getpreContents());

        return view;
    }
}