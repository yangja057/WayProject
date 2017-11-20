package com.example.yangj.wayproject;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;//현재 로그인한 사용자를 얻기 위한 변수 선언

    private Button btnGoToJoin;
    private Button btnGoToApi;
    private Button btnGoToMainView;
    private Button btnGoRegiReview;
    private Button btnGoToLogin;
    private Button btnLogOut;
    private Button btnGoList;
    private Button btnCheck;

    private  FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        로그인버튼 : 로그인으로 이동
        회원가입 버튼 : 회원가입으로 이동
        검색버튼 : 구글 api로 이동
        보여지는 view: 출발 도착을 눌렀을 경우 그다음에 보여지는 썸네일들이 있는 뷰
        리뷰등록 버튼 : 리뷰등록 액티비티로 이동
        testActivity :  데이터 테스트 액티비티

         */


        btnCheck=(Button)findViewById(R.id.check);

        btnGoToJoin=(Button)findViewById(R.id.goToJoin);
        btnGoToApi=(Button)findViewById(R.id.goToApi);
        btnGoToMainView=(Button)findViewById(R.id.goToMainView);
        btnGoRegiReview=(Button)findViewById(R.id.goRegiReview);
        btnGoToLogin=(Button)findViewById(R.id.goToLogin);
        btnLogOut=(Button)findViewById(R.id.logOut);
        btnGoList=(Button)findViewById(R.id.goListView);


/*
현재 로그인한 사용자
 */


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //데이터 확인
                Intent intent=new Intent(getBaseContext(),DataTestActivity.class);
                 startActivity(intent);

            }
        });


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그아웃을 수행함
                Toast.makeText(getBaseContext(),"로그아웃",Toast.LENGTH_LONG).show();
                FirebaseAuth.getInstance().signOut();
               // Intent intent=new Intent(getBaseContext(),WLoginActivity.class);
               // startActivity(intent);
            }
        });


        btnGoToJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),WJoinActivity.class);
                startActivity(intent);
            }
        });

//        btnGoToApi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getBaseContext(),//클래스 등록);
//                startActivity(intent);
//            }
//        });

//        btnGoToMainView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getBaseContext(),//클래스 등록);
//                startActivity(intent);
//            }
//        });

        btnGoRegiReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),WRegiReviewActivity.class);
               startActivity(intent);
            }
        });

        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),WLoginActivity.class);
                startActivity(intent);
            }
        });
        btnGoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),WListActivity.class);
                startActivity(intent);
            }
        });




    }//oncreat closed



}
