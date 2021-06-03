package com.example.sw_engineering.owner;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sw_engineering.R;
import com.example.sw_engineering.common.ComSetting;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ownHome extends AppCompatActivity {
    private Button update_button, plus_button;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.own_home);
        findViewById(R.id.plus).setOnClickListener(onClickListener);
        findViewById(R.id.setting).setOnClickListener(onClickListener);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        startToast(user.getUid());


    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) { //클릭된 위젯의 id를 이용
            switch (v.getId()) {
                case R.id.plus:
                    startownCreateCamping();
                    break;
                case R.id.setting:
                    startComSetting();
                    break;
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

}
