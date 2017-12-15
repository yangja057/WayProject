package com.example.yangj.wayproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyReviewActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseAuth auth;
    //파이어 베이스 데이터베이스 추가->문서를 읽어오기 위해 꼭 필요한 객체
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private UserData UserItems; //현재 어떤 사용자가 사용하고 있는지의 정보를 갖고옴.
    public List<ImageData> listItems=new ArrayList<ImageData>(); //데이터 리스트 구조체
    private WListViewAdapter listViewAdapter;

    private ArrayList<String> m_keyData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wmy_review);

        UserItems =new UserData();
        m_keyData=new ArrayList<>();
        database= FirebaseDatabase.getInstance();//다른곳에서 사용하기 위해서 singletone pattern으로  등록

        auth= FirebaseAuth.getInstance();

        listViewAdapter=new WListViewAdapter(R.layout.activity_wlist_item);

        recyclerView=(RecyclerView)findViewById(R.id.MyReviewView);

        recyclerView.setAdapter(listViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //썸네일을 클릭하면 boardActivity로 넘어갈 게시물의 id를 넘겨줘야한다.
                        Toast.makeText(view.getContext(), "position = " + position, Toast.LENGTH_SHORT).show();

                    }
                })
        );


        //여기 추가
        ref=FirebaseDatabase.getInstance().getReference().child("users").child(auth.getCurrentUser().getUid()).child("MyReviewList");

//        database.getReference().child("users").child(auth.getCurrentUser().getUid()).child("MyReviewList").child("-L0KNIP6vpZbESkXYDTa")
        // ref.orderByChild("list").
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //데이터가 날라온 것을 이미지 리스트에 담는다
                listViewAdapter.WImageDataItemList.clear();//수정될때마다 데이터가 날라옴/ 안해주면 데이터가 쌓여

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    m_keyData.clear();
                    String uidKey=snapshot.getKey();//child에 있는 키값을 모두 받음
                    m_keyData.add(uidKey);

                    //키값 받아서 다시 서치
                    ref.child(uidKey).child("list").child(0+"").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            //원래는 이 밑이 데이터를 추가하는 코드임
                            // adapter.WImageDataItemList.clear();//수정될때마다 데이터가 날라옴/ 안해주면 데이터가 쌓여


                            ImageData imageData=dataSnapshot.getValue(ImageData.class);
                            listViewAdapter.WImageDataItemList.add(imageData);
                           // Log.d("데이터 확인",listViewAdapter.WImageDataItemList.get(0).description);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    Log.i("다스리의 로그", "uidKey:" + uidKey);
                }
                listViewAdapter.notifyDataSetChanged();
//
//                ImageData imageData=dataSnapshot.getValue(ImageData.class);
//                listViewAdapter.WImageDataItemList.add(imageData);
//                Toast.makeText(MyReviewActivity.this,"여기 들어옴",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("다스리의 로그","여기들어왔다는건...잘못됐다는뜻임");

            }
        });

    }


}
