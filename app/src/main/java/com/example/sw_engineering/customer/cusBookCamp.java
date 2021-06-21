package com.example.sw_engineering.customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sw_engineering.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class cusBookCamp extends AppCompatActivity {
    Button book_button;
    String campId;
    String userid;
    String name;
    String owner;
    Spinner in_month, in_day, out_month, out_day, member, area;
    EditText request;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cus_book_camp);
        campId = getIntent().getExtras().getString("camp");
        book_button = (Button)findViewById(R.id.book);
        in_month = (Spinner)findViewById(R.id.in_month);
        in_day = (Spinner)findViewById(R.id.in_day);
        out_month = (Spinner)findViewById(R.id.out_month);
        out_day = (Spinner)findViewById(R.id.out_day);
        member = (Spinner)findViewById(R.id.member);
        area = (Spinner)findViewById(R.id.area);
        request = (EditText)findViewById(R.id.request);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //user의 정보를 사용할것임
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("UserInfo").document(user.getUid());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                userid = document.getString("email");
            }
        });


        DocumentReference docRef1 = db.collection("Camp").document("totalCamp").collection("totalCamp").document(campId);
        docRef1.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                owner = document.getString("owner");
                name = document.getString("name");
            }
        });
        book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String i_month = in_month.getSelectedItem().toString();
                String i_day = in_day.getSelectedItem().toString();
                String o_month = out_month.getSelectedItem().toString();
                String o_day = out_month.getSelectedItem().toString();
                String mem = member.getSelectedItem().toString();
                String area = member.getSelectedItem().toString();
                String req = request.getText().toString();

                //userid: 유저의 아이디
                //name: 캠핑장 이름
                //owner: 캠핑장 주인의 getUid()
                startToast("예약 요청 메세지를 전송하였습니다.");



            }
        });


    }
    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
