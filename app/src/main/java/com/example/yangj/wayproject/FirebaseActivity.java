package com.example.yangj.wayproject;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Firebase에 데이터를 load하는 부분이 모두 담겨있습니다
 */
public class FirebaseActivity extends AppCompatActivity {

    /**
     * 리사이클 뷰를 이용하는데 세가지 요소가 필요함
     * 1. 리사이클 뷰
     * 2. 어댑터
     * 3. 레이아웃관리자
     */
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    String[] myDataset={"안녕","오늘"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);//사이즈를 정해줌

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        //
        mAdapter = new FirebaseAdapter(myDataset);//표시될 데이터를 연결해줌
        mRecyclerView.setAdapter(mAdapter);//어댑터를 리사이클뷰에 연결해줌


        /*
        firebase authentication
        : 로그인된 사용자의 정보를 가지고 오는 부분
        -이름
        -이메일주소->현재는 이것만 가져오게 되어있음
        -사진 정보
         */
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();//getCurrentUser(): 현재user를 가지고옴
        if (user != null) {
            // Name, email address, and profile photo Url
            //String name = user.getDisplayName();
            String email = user.getEmail();
            //Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            //String uid = user.getUid();
            email=user.getEmail();
        }

        /**
         * 데이터 베이스에 데이터를 쓰기
         */
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("tempTest");//어디에 저장을 할거야?(tree의 root)

        myRef.setValue("Hello, World!");//setValue() : 뭐라고 저장할거야?








    }
}
