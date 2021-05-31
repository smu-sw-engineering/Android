package com.example.sw_engineering.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sw_engineering.R;
import com.example.sw_engineering.customer.cusHome;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserLogin extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG ="LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);
        mAuth = FirebaseAuth.getInstance();
        findViewById(R.id.login_button2).setOnClickListener(onClickListener);
        findViewById(R.id.resister_button).setOnClickListener(onClickListener);
        findViewById(R.id.logout_button).setOnClickListener(onClickListener);

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
                case R.id.login_button2:
                    Login();
                    break;
                case R.id.resister_button:
                    startSignUpActivity();
                    break;
                case R.id.logout_button:
                    SignOut();
            }
        }
    };

    public void Login(){
        String email = ((EditText)findViewById(R.id.idlogin)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordlogin)).getText().toString();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            startToast("로그인 성공");//로그인 성공 시 홈으로 이동
                            Intent intent = new Intent(UserLogin.this, cusHome.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            startToast("로그인 실패");
                        }

                        // ...
                    }
                });
    }
    private void startToast(String msg){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
    private void startSignUpActivity(){
        Intent intent = new Intent(this, UserCreateUser.class);
        startActivity(intent);
    }
    private void SignOut() {
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            FirebaseAuth.getInstance().signOut();
            startToast("로그아웃 했습니다.");

        }
    }

}