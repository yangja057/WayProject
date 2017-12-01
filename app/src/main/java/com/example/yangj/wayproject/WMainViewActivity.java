package com.example.yangj.wayproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * 로그인화면 이후에 쓰이는 메인화면 액티비티 입니다
 * 1. 사용자의 정보
 * 2. 즐겨찾기(버튼누르면 해당 액티비티로 이동->mylikereview acti)
 * 3. 검색하기(..)
 * 4. 나의 글 보기(..)
 */
public class WMainViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wmain_view);
    }
}
