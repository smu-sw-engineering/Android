package com.example.sw_engineering.owner;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sw_engineering.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Stack;

public class ownCreateCamping extends AppCompatActivity {
    private ImageButton back_button;
    private FirebaseAuth mAuth;
    //String [] area = new String[10];
    Stack<String> area = new Stack<>();
    ListView areaList;
    EditText areainput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.own_create_camping);
        areainput = findViewById(R.id.area_input);
        areaList = findViewById(R.id.area_list);
        findViewById(R.id.area_add_btn).setOnClickListener(onClickListener);
    }
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) { //클릭된 위젯의 id를 이용
            switch (v.getId()) {
                case R.id.area_add_btn:
                    addArea();
                    break;

            }
        }
    };
    private void addArea(){
        if(areainput.length()>=1) //넣을 구역 이름을 입력했다면
        {
            area.push(areainput.getText().toString());
            ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,area.toArray());
            areaList.setAdapter(adapter);
            areainput.setText("");
        }
        else{
            startToast("구역 이름을 입력하세요.");
        }
    }
    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
