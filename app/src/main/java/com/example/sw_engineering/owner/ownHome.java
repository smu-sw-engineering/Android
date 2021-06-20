package com.example.sw_engineering.owner;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sw_engineering.R;
import com.example.sw_engineering.common.ComSetting;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ownHome extends AppCompatActivity {
    private Button update_button, plus_button;
    private FirebaseAuth mAuth;
    private ListView listview;
    private ownHomeListViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.own_home);
        //findViewById(R.id.plus).setOnClickListener(onClickListener);
        //findViewById(R.id.setting).setOnClickListener(onClickListener);
        adapter = new ownHomeListViewAdapter();
        listview = (ListView) findViewById(R.id.listview);
        findViewById(R.id.plus_btn).setOnClickListener(onClickListener);
        listview.setAdapter(adapter);

        adapter.addItem("상명캠핑장", "서울시", "010-****");
        adapter.addItem("상명캠핑장1", "서울시", "010-****-****");
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //startToast(user.getUid());


    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) { //클릭된 위젯의 id를 이용
            switch (v.getId()) {
                //case R.id.plus:
                  //  startownCreateCamping();
                    //break;
                case R.id.setting:
                    startComSetting();
                    break;
                case R.id.plus_btn:
                    startCreatCamping();
            }
        }
    };

    public void startownCreateCamping(){
        Intent intent = new Intent(ownHome.this, ownCreateCamping.class);
        startActivity(intent);
    }
    public void startComSetting(){
        Intent intent = new Intent(ownHome.this, ComSetting.class);
        startActivity(intent);
    }
    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    public void startCreatCamping(){
        Intent intent = new Intent(ownHome.this, ownCreateCamping.class);
        startActivity(intent);
    }
}
