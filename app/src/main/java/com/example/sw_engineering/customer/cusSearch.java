package com.example.sw_engineering.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sw_engineering.R;
import com.example.sw_engineering.common.ComMessageDetails;
import com.example.sw_engineering.common.ComMessageRoomList;
import com.example.sw_engineering.common.ComSetting;
import com.example.sw_engineering.owner.ownHome;
import com.example.sw_engineering.owner.ownHomeListViewAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.local.QueryResult;

import java.util.Map;

public class cusSearch extends AppCompatActivity {

    ImageButton home1, menu, reserve, chatting, setting;
    ListView campList;
    private cusSearchListViewAdapter adapter;
    //String name, campid, tel, ownerid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cus_search);
        navbar();

        adapter = new cusSearchListViewAdapter();
        campList = (ListView) findViewById(R.id.totalcamp);
        campList.setAdapter(adapter);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //user의 정보를 사용할것임
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Camp").document("totalCamp").collection("totalCamp")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String campid = document.getId();
                            DocumentReference docRef = db.collection("Camp").document("totalCamp").collection("totalCamp").document(campid);

                            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                    DocumentSnapshot document = task.getResult();
                                    String name = document.getString("name");
                                    String ownerid = document.getString("owner");

                                    DocumentReference docRef1 = db.collection("UserInfo").document(ownerid);
                                    docRef1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                            DocumentSnapshot document = task.getResult();
                                            String tel = document.getString("tel");
                                            //여기다 지역정보랑 얻어오면 됨
                                            adapter.addItem(name, "서울시", tel, campid);

                                            adapter.notifyDataSetChanged();

                                        }
                                    });

                                }
                            });





                        }
                    }
                });



    /*    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //user의 정보를 사용할것임
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Camp").document("totalCamp")
                    .get().addOnCompleteListener()



            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Map<String, String> selection = (Map<String, String>) parent.getItemAtPosition(position);
                    String campid = selection.get("camp");
                    Intent oIntent = new Intent(ownHome.this, cusCampDetail.class);
                    oIntent.putExtra("camp", campid);
                    //추가 넘겨줄 것은 여기아래다 추가해주면 됨
                    startActivity(oIntent);
                }
            });
        }*/
    }

        public void navbar () {
            home1 = findViewById(R.id.home1);
            menu = findViewById(R.id.menu);
            reserve = findViewById(R.id.reserve);
            chatting = findViewById(R.id.chatting);
            setting = findViewById(R.id.setting);

            home1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), cusHome.class);
                    startActivity(intent);
                }
            });

            reserve.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), cusReserveList.class);
                    startActivity(intent);
                }
            });

            chatting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ComMessageRoomList.class);
                    startActivity(intent);
                }
            });

            setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ComSetting.class);
                    startActivity(intent);
                }
            });
        }
    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
