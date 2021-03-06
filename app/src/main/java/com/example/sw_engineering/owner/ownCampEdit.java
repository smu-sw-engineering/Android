package com.example.sw_engineering.owner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sw_engineering.R;
import com.example.sw_engineering.common.ComSetting;
import com.example.sw_engineering.customer.cusCampDetail;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ownCampEdit extends AppCompatActivity {
    EditText name;
    EditText info;
    TextView title;
    String uid;
    Stack<String> area = new Stack<>();
    ListView areaList;
    EditText area_input;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference usersCollectionRef = db.collection("Camp");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.own_camp_edit);
        area_input = findViewById(R.id.area_input);
        areaList = findViewById(R.id.area_list);
        //Intent intent = getIntent();
        //uid = intent.getStringExtra("camp");
        uid = "aIMPsfneHva2Iakjhg3S";
        startToast(uid);
        findViewById(R.id.area_add_btn).setOnClickListener(onClickListener);
        findViewById(R.id.update_btn).setOnClickListener(onClickListener);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        getName();

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.area_add_btn:
                    addArea();
                    break;
                case R.id.update_btn:
                    setName();
                    Intent intent = new Intent(ownCampEdit.this, ownHome.class);
                    startActivity(intent);
                    break;

            }
        }
    };

    public void getName() {
        name = findViewById(R.id.name);
        info = findViewById(R.id.info);
        title = findViewById(R.id.title);
        area_input = findViewById(R.id.area_input);

        startToast(uid);
        usersCollectionRef.document("privateCamp").collection(user.getUid()).document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                title.setText(document.getString("name"));
                name.setText(document.getString("name"));
                info.setText(document.getString("info"));

            }
        });
        while(!area.empty())
        {
            Map<Integer, String> Area = new HashMap<>();
            //Area.put(i, area.peek());
            //DocumentReference postArea = db.collection("Camp").document("privateCamp").collection(user.getUid()).document(uid).collection("Area").document(area.peek());
            usersCollectionRef.document("privateCamp").collection(user.getUid()).document(uid).collection("Area").document().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    DocumentSnapshot document = task.getResult();
                    title.setText(document.getString("name"));
                    area_input.setText(document.getString(""));

                }
            });
            area.pop();
        }

    }
    private void addArea(){
        if(area_input.length()>=1)
        {
            area.push(area_input.getText().toString());
            ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,area.toArray());
            areaList.setAdapter(adapter);
            area_input.setText("");
        }
        else{
            startToast("?????? ????????? ???????????????.");
        }
    }

    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    public void setName() {
        String name = ((EditText) findViewById(R.id.name)).getText().toString();
        String info = ((EditText) findViewById(R.id.info)).getText().toString();

        Map<String, String> information = new HashMap<>();
        information.put("name", name);
        information.put("info", info);

        DocumentReference setCamp = db.collection("Camp").document("privateCamp").collection(user.getUid()).document(uid);
        setCamp.set(information, SetOptions.merge());

    }
}
