package com.example.sw_engineering.owner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sw_engineering.R;
import com.example.sw_engineering.common.ComMessageRoomList;
import com.example.sw_engineering.common.ComSetting;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.sw_engineering.customer.cusCampDetail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ownHome extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ListView listview;
    private ownHomeListViewAdapter adapter;
    ArrayList items;
    String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.own_home);
        //findViewById(R.id.plus).setOnClickListener(onClickListener);
        //findViewById(R.id.setting).setOnClickListener(onClickListener);
        adapter = new ownHomeListViewAdapter();
        listview = (ListView) findViewById(R.id.listview);
        findViewById(R.id.plus_btn).setOnClickListener(onClickListener);
        findViewById(R.id.send).setOnClickListener(onClickListener);
        findViewById(R.id.setting_btn).setOnClickListener(onClickListener);
        listview.setAdapter(adapter);


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //user의 정보를 사용할것임
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("UserInfo").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                tel = document.getString("tel");
            }
        });

        db.collection("Camp").document("privateCamp").collection(user.getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String campid = document.getId();
                            //startToast(campid);
                            DocumentReference docRef = db.collection("Camp").document("privateCamp").collection(user.getUid()).document(campid);

                            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    DocumentSnapshot document = task.getResult();
                                    String name = document.getString("name");
                                    //여기다 지역정보랑 얻어오면 됨
                                    startToast(name);
                                    adapter.addItem(name, "서울시", tel, campid);
                                    adapter.notifyDataSetChanged();
                                }
                            });

                        }
                    }
                });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, String>selection = (Map<String, String>) parent.getItemAtPosition(position);
                String campid = selection.get("camp");
                Intent oIntent = new Intent(ownHome.this, cusCampDetail.class);
                oIntent.putExtra("camp",campid);
                //추가 넘겨줄 것은 여기아래다 추가해주면 됨
                startActivity(oIntent);
            }
        });



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
                case R.id.setting_btn:
                    startComSetting();
                    break;
                case R.id.plus_btn:
                    startCreatCamping();
                    break;
                case R.id.send:
                    startMessageRoomList();
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

    public void startCreatCamping(){
        Intent intent = new Intent(ownHome.this, ownCreateCamping.class);
        startActivity(intent);
    }
    public void startMessageRoomList(){
        Intent intent = new Intent(ownHome.this, ComMessageRoomList.class);
        startActivity(intent);
    }



}
