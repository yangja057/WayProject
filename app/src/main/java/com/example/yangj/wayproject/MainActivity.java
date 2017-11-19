package com.example.yangj.wayproject;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.v7.app.AppCompatActivity;
=======
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
>>>>>>> 550dc5b42514d4c91838b00091fc7341cef1ae27

public class MainActivity extends AppCompatActivity {

    private Button btnGoToJoin;
    private Button btnGoToApi;
    private Button btnGoToMainView;
    private Button btnGoRegiReview;
    private Button btnGoToLogin;
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

         */

        btnGoToJoin=(Button)findViewById(R.id.goToJoin);
        btnGoToApi=(Button)findViewById(R.id.goToApi);
        btnGoToMainView=(Button)findViewById(R.id.goToMainView);
        btnGoRegiReview=(Button)findViewById(R.id.goRegiReview);
        btnGoToLogin=(Button)findViewById(R.id.goToLogin);


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

//        btnGoRegiReview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getBaseContext(),//클래스 등록);
//                startActivity(intent);
//            }
//        });

        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),WLoginActivity.class);
                startActivity(intent);
            }
        });




    }
}
