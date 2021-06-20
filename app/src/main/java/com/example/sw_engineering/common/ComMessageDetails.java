package com.example.sw_engineering.common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.example.sw_engineering.R;

import java.util.ArrayList;

public class ComMessageDetails extends AppCompatActivity {

    EditText et;
    ListView lv;

    ArrayList<MessageItem> messageItems=new ArrayList<>();
    DetailsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_message_details);

        // 채팅방 제목
        getSupportActionBar().setTitle("김상명");

        initMsgData();

        et = findViewById(R.id.et);
        lv = findViewById(R.id.list_view);
        adapter = new DetailsAdapter(messageItems, getLayoutInflater());
        lv.setAdapter(adapter);
    }

    private void initMsgData(){
        messageItems.add(new MessageItem("김상명",
                "안녕하세요 고객입니다",
                "R.drawable.ic_launcher_background",
                "17:06"));

        messageItems.add(new MessageItem("상명캠핑장주인",
                "안녕하세요 주인입니다",
                "R.drawable.ic_launcher_background",
                "17:11"));

//        String name;
//        String message;
//        String pofileUrl;
//        String time;
    }
}