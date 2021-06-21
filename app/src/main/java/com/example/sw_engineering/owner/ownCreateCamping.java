package com.example.sw_engineering.owner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sw_engineering.R;
import com.example.sw_engineering.common.ComSetting;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ownCreateCamping extends AppCompatActivity {
    private ImageButton back_button;
    private FirebaseAuth mAuth;
    //String [] area = new String[10];
    Stack<String> area = new Stack<>();
    ListView areaList;
    EditText areainput;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //user의 정보를 사용할것임
    //FirebaseStorage storage = FirebaseStorage.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.own_create_camping);
        areainput = findViewById(R.id.area_input);
        areaList = findViewById(R.id.area_list);
        findViewById(R.id.area_add_btn).setOnClickListener(onClickListener);
        findViewById(R.id.plus_image).setOnClickListener(onClickListener);
        findViewById(R.id.add_camp).setOnClickListener(onClickListener);
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
                case R.id.plus_image:
                    plus_image();
                    break;
                case R.id.add_camp:
                    upload_camp();

            }
        }
    };
    private void upload_camp()
    {
        String userUid = user.getUid(); //유저 UID 가져오기
        String campname = ((EditText) findViewById(R.id.camp_name)).getText().toString();
        String campinfo = ((EditText) findViewById(R.id.camp_info)).getText().toString();

        final Map<String, String> post = new HashMap<>();
        post.put("name", campname);
        post.put("info", campinfo);

        DocumentReference campDoc = db.collection("Camp").document("privateCamp").collection(user.getUid()).document();
        final String myId = campDoc.getId();
        DocumentReference postDoc1 = db.collection("Camp").document("totalCamp").collection(myId).document(user.getUid());
        postDoc1.set(post, SetOptions.merge());


        while(!area.empty())
        {
            Map<Integer, String> Area = new HashMap<>();
            //Area.put(i, area.peek());
            DocumentReference postArea = db.collection("Camp").document(user.getUid()).collection("privateCamp").document(myId).collection("Area").document(area.peek());
            postArea.set(post, SetOptions.merge());
            area.pop();
            campDoc.set(Area);
        }
        DocumentReference postDoc = db.collection("Camp").document(user.getUid()).collection("privateCamp").document(myId);
        postDoc.set(post, SetOptions.merge());
        startToast("캠핑 생성 완료");
        startOwnHome();

    }
    public void startOwnHome(){
        Intent intent = new Intent(ownCreateCamping.this, ownHome.class);
        startActivity(intent);
    }
    private void plus_image() //사진 넣기
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "이미지를 선택하세요."), 0);
    }
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
