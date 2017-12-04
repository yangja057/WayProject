package com.example.yangj.wayproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;

/**
 * 즐겨찾기 액티비티인것임
 */
public class WMyLikeReviewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UserData UserItems; //현재 어떤 사용자가 사용하고 있는지의 정보를 갖고옴.
    private FirebaseAuth auth;
    public  List<ImageData> listItems=new ArrayList<ImageData>(); //데이터 리스트 구조체
    private WListViewAdapter listViewAdapter;

    //파이어 베이스 데이터베이스 추가->문서를 읽어오기 위해 꼭 필요한 객체
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wmy_like_review);
        setTitle("My FavoriteList");

        UserItems =new UserData();

        database=FirebaseDatabase.getInstance();//다른곳에서 사용하기 위해서 singletone pattern으로  등록
        auth=FirebaseAuth.getInstance();

        listViewAdapter=new WListViewAdapter(R.layout.activity_wlist_item);

        recyclerView=(RecyclerView)findViewById(R.id.LikerecyclerView) ;

        recyclerView.setAdapter(listViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //썸네일을 클릭하면 boardActivity로 넘어갈 게시물의 id를 넘겨줘야한다.
                        Toast.makeText(view.getContext(), "position = " + position, Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent(getApplicationContext(), FirstActivity.class);
//                        startActivity(intent);
                        //ImageData imageData=listViewAdapter.WImageDataItemList.get(position);
                        //String uid=auth.getCurrentUser().getUid().toString();
                        //Intent.putExtra("ReviewID", uid);
                    }
                })
        );



        //

        // Get a key for a new Post.
       // .child("myReviewList").

        database.getReference().child("users").child(auth.getCurrentUser().getUid()).orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //데이터가 날라온 것을 이미지 리스트에 담는다
                listItems.clear();//수정될때마다 데이터가 날라옴/ 안해주면 데이터가 쌓여

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    /*
                    DataSnapshot snapshot : dataSnapshot.getChildren() //처음부터 끝까지 데이터를 읽는다는 뜻임
                    getchildren() :가지 하나를 children이라고 함
                    child==현재 "review" 의 children하나를 읽어옴
                     */
                    ImageData imageData=snapshot.getValue(ImageData.class);
                    //listItems.add(imageData);//데이터의 개수만큼 for loop을 돌면서 list에 객체를 넣음
                    listViewAdapter.WImageDataItemList.add(imageData);
                    Log.i("다스리의 로그", "imageData:" + imageData.getUserEmail());
                }

                listViewAdapter.notifyDataSetChanged();//새로 고침(갱신되니까)
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("다스리의 로그","여기들어왔다는건...잘못됐다는뜻임");

            }
        });

    }


    //데이터를 읽어오는 코드
        /*
        - "review"를 읽어옴
        - addValueEventListene : 글자가 하나씩 바뀔때 마다 데이터가 계속 넘어옴
        - client에게 알려줌
        즉 다른사람이 데이터를 수정했으면
        자동적으로 새로 고침이 됨
         */



}
