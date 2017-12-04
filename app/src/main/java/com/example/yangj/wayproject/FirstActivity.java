package com.example.yangj.wayproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class FirstActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private WRegiReviewRVAdapter adapter;
    private List<ImageData> listItems;
    private FirebaseDatabase database;//데이터 베이스 추가
    private FirebaseAuth auth;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        recyclerView=(RecyclerView)findViewById(R.id.FirstView);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        database=FirebaseDatabase.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference();
        auth=FirebaseAuth.getInstance();

        database.getReference().child("users").child(auth.getCurrentUser().getUid()).child("myReviewList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.i("다스리의 로그", "onChildAdded:" + dataSnapshot.getKey());

                //데이터가 날라온 것을 이미지 리스트에 담는다
                listItems.clear();//수정될때마다 데이터가 날라옴/ 안해주면 데이터가 쌓여

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    /*
                    DataSnapshot snapshot : dataSnapshot.getChildren() //처음부터 끝까지 데이터를 읽는다는 뜻임
                    getchildren() :가지 하나를 children이라고 함
                    child==현재 "review" 의 children하나를 읽어옴
                     */
                    ImageData imageData=snapshot.getValue(ImageData.class);
                    adapter.listItems.add(imageData);//데이터의 개수만큼 for loop을 돌면서 list에 객체를 넣음
                    //listItems.add(imageData);
                    Log.i("다스리의 로그", "imageData:" + dataSnapshot.getChildren().toString());
                }
                adapter.notifyDataSetChanged();//새로 고침(갱신되니까)
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("다스리의 로그","여기들어왔다는건...잘못됐다는뜻임");

            }
        });


    }
}
