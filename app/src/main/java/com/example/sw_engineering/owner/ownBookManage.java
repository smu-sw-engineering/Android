package com.example.sw_engineering.owner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sw_engineering.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class ownBookManage extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button book_btn;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//    Spinner spin1 = (Spinner)findViewById(R.id.enter_month);
//    Spinner spin2 = (Spinner)findViewById(R.id.enter_day);
//    Spinner spin3 = (Spinner)findViewById(R.id.exit_month);
//    Spinner spin4 = (Spinner)findViewById(R.id.exit_day);
//    Spinner spin5 = (Spinner)findViewById(R.id.people);
//    Spinner spin6 = (Spinner)findViewById(R.id.zone);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.own_book_manage);
        findViewById(R.id.book).setOnClickListener(onClickListener);
//        book_btn = findViewById(R.id.book);
//        book_btn.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                uploadBook();
//                Intent intent = new Intent(getApplicationContext(), ownHome.class);
//                startActivity(intent);
//            }
//        });

    }
    public void onStart() {
        super.onStart();

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) { //클릭된 위젯의 id를 이용
            switch (v.getId()) {
                case R.id.book:
                    uploadBook();
                    Intent intent = new Intent(ownBookManage.this, ownHome.class);
                    startActivity(intent);
                    break;


            }
        }
    };
    public void uploadBook() {
        String name = ((EditText)findViewById(R.id.name)).getText().toString();
        Map<String, String> post = new HashMap<>();
        Spinner spin1 = (Spinner)findViewById(R.id.enter_month);
        Spinner spin2 = (Spinner)findViewById(R.id.enter_day);
        Spinner spin3 = (Spinner)findViewById(R.id.exit_month);
        Spinner spin4 = (Spinner)findViewById(R.id.exit_day);
        Spinner spin5 = (Spinner)findViewById(R.id.people);
        Spinner spin6 = (Spinner)findViewById(R.id.zone);

        String enter_month = spin1.getSelectedItem().toString();
        String enter_day = spin2.getSelectedItem().toString();
        String exit_month = spin3.getSelectedItem().toString();
        String exit_day = spin4.getSelectedItem().toString();
        String people = spin5.getSelectedItem().toString();
        String zone = spin6.getSelectedItem().toString();

        post.put("enter", enter_month+"/"+enter_day);
        post.put("exit", exit_month+"/"+exit_day);
        post.put("people", people);
        post.put("zone", zone);
        post.put("name", name);

        DocumentReference book = db.collection("Book").document(user.getUid()).collection("customer_book").document();
        book.set(post, SetOptions.merge());


    }
}
