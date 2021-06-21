package com.example.sw_engineering.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sw_engineering.R;
import com.example.sw_engineering.common.ComSetting;
import com.example.sw_engineering.owner.ownCreateCamping;
import com.example.sw_engineering.owner.ownHome;
import com.example.sw_engineering.common.ComMessageRoomList;
public class cusHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cus_home);
        findViewById(R.id.menu).setOnClickListener(onClickListener);
        findViewById(R.id.reserve).setOnClickListener(onClickListener);
        findViewById(R.id.chatting).setOnClickListener(onClickListener);
        findViewById(R.id.setting).setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) { //클릭된 위젯의 id를 이용
            switch (v.getId()) {
                //case R.id.plus:
                //  startownCreateCamping();
                //break;
                case R.id.menu:
                    startcusSearch();
                    break;
                case R.id.reserve:
                    startcusReserveList();
                    break;
                case R.id.chatting:
                    startComMessageRoomList();
                    break;
                case R.id.setting:
                    startComSetting();
                    break;
            }
        }
    };

    private void startcusSearch()
    {
        Intent intent = new Intent(cusHome.this, cusSearch.class);
        startActivity(intent);
    }
    private void startcusReserveList()
    {
        Intent intent = new Intent(cusHome.this, cusReserveList.class);
        startActivity(intent);
    }
    private void startComMessageRoomList()
    {
        Intent intent = new Intent(cusHome.this, ComMessageRoomList.class);
        startActivity(intent);
    }
    private void startComSetting()
    {
        Intent intent = new Intent(cusHome.this, ComSetting.class);
        startActivity(intent);
    }
}