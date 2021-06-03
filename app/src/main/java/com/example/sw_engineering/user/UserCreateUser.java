package com.example.sw_engineering.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sw_engineering.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

//개인정보 이용 약관 체크 후에 회원가입 가능한 기능 추가해야함

public class UserCreateUser extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_create_user);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.createUserB).setOnClickListener(onClickListener);
        //findViewById(R.id.login_button1).setOnClickListener(onClickListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) { //클릭된 위젯의 id를 이용
            switch (v.getId()) {
                case R.id.createUserB:
                    Intent intent_login = new Intent(UserCreateUser.this, UserLogin.class);
                    signUp();
                    //boolean loginSuccess = signUp(); // 회원가입 함수 실행
                    //가입조건이 충족됐을 때 프로필 수정 화면으로 넘어가는 조건이 되어야함
                    //if(loginSuccess == true)
                    //startLoginActivity();
                    //break;
                    //case R.id.login_button1:
                    //startLoginActivity(); // 로그인 창으로 간다
                    //break;

            }
        }
    };

    // 회원가입 함수
    public boolean signUp() {
        // 이메일, 패스워드, 패스워드 확인 값을 받음
        String email = ((EditText) findViewById(R.id.createIdField)).getText().toString();
        String password = ((EditText) findViewById(R.id.createPasswordField)).getText().toString();
        String name = ((EditText) findViewById(R.id.CreateNameField)).getText().toString();
        String username = ((EditText) findViewById(R.id.createNIckNameField)).getText().toString();
        String tel = ((EditText) findViewById(R.id.createTelephoneField)).getText().toString();
        String confirm = ((EditText) findViewById(R.id.createUserCheckerField)).getText().toString();
        CheckBox owner = (CheckBox) findViewById(R.id.createOwnerDetail);
        //startToast(name);
        if (email.contains("@") && email.contains(".")) { // 아이디가 이메일 형식인지 확인
            if (password.length() >= 6 && name.length()>=1 && username.length()>=1 && tel.length() >= 1 && confirm.length() >= 1) { // 비밀번호가 여섯자리 이상인지 확인
                mAuth.createUserWithEmailAndPassword(email, password) // 변수 email과 password의 저장된 값을 전송 (mAuth는 Firebase 객체)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    if(owner.isChecked())
                                        infoInit(true, email, name, password, tel,username, confirm); //캠핑장 주인 : 1
                                    else infoInit(false, email, name, password, tel, username, confirm); //캠핑장 고객: 0
                                    FirebaseUser user = mAuth.getCurrentUser(); // 로그인 성공
                                    startLoginActivity();


                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                }
                            }
                        });
                startToast("정상적으로 회원가입 되었습니다.");
                return true;
            }
            else { // 비밀번호가 여섯자리 이상이 아닐 때
                startToast("비밀번호는 여섯자리 이상으로 설정해주십시오.");

            }
        }
        else{ // 아이디가 이메일 형식이 아닐 때
            startToast("올바른 이메일 형식을 입력해주십시오.");

        }
        return false;
    }
    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    // 로그인 창으로 가는 함수
    private void startLoginActivity() {
        Intent intent = new Intent(this, UserLogin.class);
        startActivity(intent);
    }
    private void infoInit(Boolean owner, String email, String name, String pw, String tel, String username, String confirm){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user_info = new HashMap<>();
        user_info.put("owner", owner); //주인인지 아닌지 구분
        user_info.put("password", pw);
        user_info.put("name", name);
        user_info.put("username", username);
        user_info.put("tel",tel);
        user_info.put("confirm", confirm);
        user_info.put("email", email);
        FirebaseUser userId = mAuth.getCurrentUser();
        db.collection("UserInfo").document(user.getUid())
                .set(user_info, SetOptions.merge());
        //예약하고 좋아요, 캠핑장 만들기, 전체 캠핑장 조회는 다음과 같은 구조로 넣는다.
          /*if(owner =="0") { //캠핑장 고객 유저일때 좋아요 목록과 예약 목록 카테고리에 정보를 넣는다.
              db.collection("Camp").document("Like").collection(user.getUid()).document("캠핑장 id");
              db.collection("Camp").document("Book").collection(user.getUid()).document("캠핑장 id");
          }
          else{ //캠핑장 주인 유저일때 만든 자신의 캠프 목록 카테고리를 만든다.
              db.collection("Camp").document("My").collection(user.getUid()).document("캠핑장 id");
          }*/

    }

}