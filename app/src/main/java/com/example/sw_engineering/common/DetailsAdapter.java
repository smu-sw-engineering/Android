package com.example.sw_engineering.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sw_engineering.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailsAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<MessageRoomData> sample;
    ArrayList<MessageItem> messageItems;

    public DetailsAdapter(ArrayList<MessageItem> messageItems, LayoutInflater layoutInflater) {
        messageItems = this.messageItems;
        mLayoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return messageItems.size();
    }

    @Override
    public Object getItem(int position) {
        return messageItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        // 현재 보여줄 position의 데이터로 뷰를 생성
        MessageItem item=messageItems.get(position);

        // 재활용할 뷰는 사용하지 않음
        View itemView=null;

        //메시지 종류 확인
        if(item.getName().equals("김상명")){
            itemView = mLayoutInflater.inflate(R.layout.com_my_msgbox, viewGroup, false);
        }else{
            itemView = mLayoutInflater.inflate(R.layout.com_other_msgbox, viewGroup, false);
        }

        //아이템 뷰에 들어갈 값 설정
        CircleImageView iv = itemView.findViewById(R.id.iv);
        TextView tvName = itemView.findViewById(R.id.tv_name);
        TextView tvMsg = itemView.findViewById(R.id.tv_msg);
        TextView tvTime = itemView.findViewById(R.id.tv_time);

        tvName.setText(item.getName());
        tvMsg.setText(item.getMessage());
        tvTime.setText(item.getTime());

        // 프로필 사진 설정
//        Glide.with(itemView).load(item.getPofileUrl()).into(iv);
//        iv.setImageResource(sample.get(position).PofileUrl());

        return itemView;
    }
}
