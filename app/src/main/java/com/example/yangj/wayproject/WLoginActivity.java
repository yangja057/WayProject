package com.example.yangj.wayproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class WLoginActivity extends AppCompatActivity {

    //변수선언
    private FirebaseAuth mAuth;
    private EditText editLoginID;
    private EditText editLoginPW;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlogin);

        mAuth = FirebaseAuth.getInstance();
        editLoginID=(EditText)findViewById(R.id.loginID);
        editLoginPW=(EditText)findViewById(R.id.loginPW);
        btnLogin=(Button)findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WLoginActivity.this,"버튼은 눌림",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
