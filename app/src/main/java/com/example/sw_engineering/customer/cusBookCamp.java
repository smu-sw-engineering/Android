package com.example.sw_engineering.customer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sw_engineering.R;
import com.example.sw_engineering.common.ComMessageDetails;
import com.example.sw_engineering.common.MessageItem;
import com.example.sw_engineering.owner.ownCreateCamping;
import com.example.sw_engineering.owner.ownHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class cusBookCamp extends AppCompatActivity {
    Button book_button;
    String campId;
    String userid;
    String name;
    String owner;
    Spinner in_month, in_day, out_month, out_day, member, area;
    EditText request;

    //Firebase Database 관리 객체참조변수
    FirebaseDatabase firebaseDatabase;

    //'chat'노드의 참조객체 참조변수
    DatabaseReference chatRef;

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

                String message = "입실: "+i_month+"/"+i_day+"\n퇴실: "+o_month+"/"+o_day+"\n인원: "+mem+"\n구역: "+area+"\n요청사항: "+req+"\n고객 email: "+userid+"\n캠핑장 이름: "+name;
                clickSend(message);

                // 메시지로 이동
                Intent intent = new Intent(cusBookCamp.this, ComMessageDetails.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        //Firebase DB관리 객체와 'caht'노드 참조객체 얻어오기
        firebaseDatabase= FirebaseDatabase.getInstance();
        chatRef= firebaseDatabase.getReference("Chatting");

    }

    public void clickSend(String message) {

        //firebase DB에 저장할 값들( 닉네임, 메세지, 프로필 이미지URL, 시간)
        String nickName= "시스템";
        int pofileImage= R.drawable.profile_smchoi;

        //메세지 작성 시간 문자열로..
        Calendar calendar= Calendar.getInstance(); //현재 시간을 가지고 있는 객체
        String time=calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE); //14:16

        //firebase DB에 저장할 값(MessageItem객체) 설정
        MessageItem messageItem= new MessageItem(nickName,message,time,pofileImage);
        //'char'노드에 MessageItem객체를 통해
        chatRef.push().setValue(messageItem);

        startToast("예약 요청 메세지를 전송하였습니다.");
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


}
