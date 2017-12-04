package com.example.yangj.wayproject;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class WJoinActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    //변수선언
    private FirebaseAuth mAuth;
    private EditText editJoinID;
    private EditText editJoinPW;
    private Button btnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wjoin);


        mAuth = FirebaseAuth.getInstance();
        editJoinID=(EditText)findViewById(R.id.joinID);
        editJoinPW=(EditText)findViewById(R.id.joinPW);
        btnJoin=(Button)findViewById(R.id.join);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WJoinActivity.this,"버튼은 눌림",Toast.LENGTH_SHORT).show();
                createUser(editJoinID.getText().toString(),editJoinPW.getText().toString());
            }
        });

    }
    /**
     * 회원을 비밀번호와 이메일로 등록해주는 함수
     */
    private void createUser(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            Log.d(TAG, "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);


                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
//                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                            Toast.makeText(WJoinActivity.this,"회원가입성공",Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        // ...
                    }
                });
    }

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
