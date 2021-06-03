package com.example.sw_engineering.common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sw_engineering.R;

import java.util.ArrayList;

public class ComMessageRoomList extends AppCompatActivity {

    ArrayList<MessageRoomData> messageRoomData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_message_room_list);

        this.InitializeMovieData();

        ListView listView = (ListView)findViewById(R.id.list_view);
        final MessageRoomAdapater messageRoomAdapater = new MessageRoomAdapater(this,messageRoomData);

        listView.setAdapter(messageRoomAdapater);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        messageRoomAdapater.getItem(position).getsender(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void InitializeMovieData()
    {
        messageRoomData = new ArrayList<MessageRoomData>();

        messageRoomData.add(new MessageRoomData(
                R.drawable.ic_launcher_background,
                "상명캠핑장 주인",
                "6월3일 16:14",
                "예약 승인 해드렸습니다!"
        ));
        messageRoomData.add(new MessageRoomData(
                R.drawable.ic_launcher_background,
                "스뮤캠핑장 주인",
                "6월3일 13:25",
                "언제로 예약 변경해드릴까요?"
        ));
    }
}