package com.example.sw_engineering.common;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sw_engineering.R;
import com.example.sw_engineering.owner.ownHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ComMessageRoomList extends AppCompatActivity {

    String username;

    ArrayList<MessageRoomData> messageRoomData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_message_room_list);

        // 현재 username 가져오기
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //user의 정보를 사용할것임
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("UserInfo").document(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                username = document.getString("username");
            }
        });



        this.InitializeMovieData();

        ListView listView = (ListView)findViewById(R.id.list_view);
        final MessageRoomAdapater messageRoomAdapater = new MessageRoomAdapater(this,messageRoomData);

        listView.setAdapter(messageRoomAdapater);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
//                Toast.makeText(getApplicationContext(),
//                        messageRoomAdapater.getItem(position).getsender(),
//                        Toast.LENGTH_LONG).show();

                // 채팅방으로 이동
                Intent intent = new Intent(ComMessageRoomList.this, ComMessageDetails.class);
                startActivity(intent);
            }
        });
    }

    public void InitializeMovieData()
    {
        messageRoomData = new ArrayList<MessageRoomData>();

        messageRoomData.add(new MessageRoomData(
                    R.drawable.sangmyung_camp,
                    "상명캠핑장",
                    "6월3일 16:14",
                    "예약 승인 해드렸습니다!"
            ));
            messageRoomData.add(new MessageRoomData(
                    R.drawable.smu_camp,
                    "스뮤캠핑장",
                    "6월3일 13:25",
                    "언제로 예약 변경해드릴까요?"
            ));

//        if(username.equals("최수뭉")){
//            messageRoomData.add(new MessageRoomData(
//                    R.drawable.sangmyung_camp,
//                    "상명캠핑장",
//                    "6월3일 16:14",
//                    "예약 승인 해드렸습니다!"
//            ));
//            messageRoomData.add(new MessageRoomData(
//                    R.drawable.smu_camp,
//                    "스뮤캠핑장",
//                    "6월3일 13:25",
//                    "언제로 예약 변경해드릴까요?"
//            ));
//        }else if(username.equals("상명캠핑장")){
//            messageRoomData.add(new MessageRoomData(
//                    R.drawable.profile_smchoi,
//                    "최수뭉",
//                    "6월3일 16:14",
//                    "6월 13일에 A구역 예약 신청했습니다."
//            ));
//        }
    }
}