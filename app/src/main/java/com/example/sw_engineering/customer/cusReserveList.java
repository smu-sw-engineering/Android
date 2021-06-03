package com.example.sw_engineering.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sw_engineering.R;
import com.example.sw_engineering.common.ComMessageDetails;
import com.example.sw_engineering.common.ComMessageRoomList;
import com.example.sw_engineering.common.ComSetting;

public class cusReserveList extends AppCompatActivity {

    ImageButton home, menu, reserve, chatting, setting;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cus_reservation_list);
        navbar();
    }

    public void navbar() {
        home = findViewById(R.id.home);
        menu = findViewById(R.id.menu);
        reserve = findViewById(R.id.reserve);
        chatting = findViewById(R.id.chatting);
        setting = findViewById(R.id.setting);

        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), cusHome.class);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), cusSearch.class);
                startActivity(intent);
            }
        });

        chatting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ComMessageRoomList.class);
                startActivity(intent);
            }
        });

        setting.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ComSetting.class);
                startActivity(intent);
            }
        });
    }
}
