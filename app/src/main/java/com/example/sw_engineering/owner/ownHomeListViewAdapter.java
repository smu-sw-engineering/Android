package com.example.sw_engineering.owner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.sw_engineering.R;

import java.util.ArrayList;

public class ownHomeListViewAdapter extends BaseAdapter {
    private TextView campingName, campingLocation, campingPhone;
    private Button update,reserve;

    private ArrayList<ownHomeListViewItem> listViewItemList = new ArrayList<ownHomeListViewItem>();

    public ownHomeListViewAdapter() {

    }
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.own_home_list, parent, false);
        }

        campingName = (TextView) convertView.findViewById(R.id.campingName);
        campingLocation = (TextView) convertView.findViewById(R.id.campingLocation);
        campingPhone = (TextView) convertView.findViewById(R.id.campingPhone);
       // update = (Button) convertView.findViewById(R.id.update_button);
        //reserve = (Button) convertView.findViewById(R.id.reserve_button);

        ownHomeListViewItem listViewItem = listViewItemList.get(position);

        campingName.setText(listViewItem.getName());
        campingLocation.setText(listViewItem.getLocation());
        campingPhone.setText(listViewItem.getPhone());

        return convertView;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    public void addItem(String name, String location, String phone) {
        ownHomeListViewItem item = new ownHomeListViewItem();
        item.setName(name);
        item.setLocation(location);
        item.setPhone(phone);

        listViewItemList.add(item);
    }
}
