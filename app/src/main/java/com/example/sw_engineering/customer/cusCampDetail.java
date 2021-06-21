package com.example.sw_engineering.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sw_engineering.R;
import com.example.sw_engineering.common.ComMessageRoomList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class cusCampDetail extends AppCompatActivity {

    String campId;
    TextView namefield;
    TextView infofield;
    Button book;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_camp_detail);
        campId = getIntent().getExtras().getString("camp");

        namefield = (TextView)findViewById(R.id.name);
        infofield = (TextView)findViewById(R.id.info);
        book = (Button)findViewById(R.id.book);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //user의 정보를 사용할것임
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef1 = db.collection("Camp").document("totalCamp").collection("totalCamp").document(campId);
        docRef1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                String name = document.getString("name");
                String info = document.getString("info");
                String owner = document.getString("owner");
                //여기다 지역정보랑 얻어오면 됨
                namefield.setText(name);
                infofield.setText(info);


            }
        });
        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startToast(campid);
                Intent oIntent = new Intent(cusCampDetail.this, cusBookCamp.class);
                oIntent.putExtra("camp", campId);
                //추가 넘겨줄 것은 여기아래다 추가해주면 됨
                startActivity(oIntent);
            }
        });

    }


}

