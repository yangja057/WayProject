package com.example.yangj.wayproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class WLoginActivity extends AppCompatActivity {

    private TextView textNowUser;
    private TextView textNowEmail;
    //변수선언
    private FirebaseAuth mAuth;
    private EditText editLoginID;
    private EditText editLoginPW;
    private Button btnLogin;

    private  FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlogin);

        //현재 사용자 등록및 확인
        textNowUser=(TextView)findViewById(R.id.nowUser);
        textNowEmail=(TextView)findViewById(R.id.nowEmail) ;

        mAuth = FirebaseAuth.getInstance();
        editLoginID=(EditText)findViewById(R.id.loginID);
        editLoginPW=(EditText)findViewById(R.id.loginPW);
        btnLogin=(Button)findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WLoginActivity.this,"로그인 버튼 눌림",Toast.LENGTH_SHORT).show();
                loginUser(editLoginID.getText().toString(),editLoginPW.getText().toString());
            }
        });

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user!=null){
                    //user is signed in
                    Toast.makeText(WLoginActivity.this,"로그인이 되어있는 상태입니다",Toast.LENGTH_LONG).show();
                   // textNowUser.setText(mAuth.getCurrentUser().getDisplayName());
                    textNowEmail.setText(mAuth.getCurrentUser().getEmail());
                  //  Intent intent=new Intent(WLoginActivity.this,MainActivity.class);
                  //  startActivity(intent);
                  //  finish();
                }else{
                    //user is signed out
                    Toast.makeText(WLoginActivity.this,"사용자가 없습니다. 로그인을 해주세요",Toast.LENGTH_LONG).show();

                }

            }
        };

    }//oncreate closed

    private  void loginUser(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "signInWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
//                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                            Toast.makeText(WLoginActivity.this,"이메일 로그인 완료",Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });


    }
    public void onStart(){
       super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
        public void onStop() {
            super.onStop();
            if (mAuthListener != null) {
                mAuth.removeAuthStateListener(mAuthListener);
            }
        }

}
