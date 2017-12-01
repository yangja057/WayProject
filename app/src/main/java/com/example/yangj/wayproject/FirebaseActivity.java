package com.example.yangj.wayproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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


    }
}
