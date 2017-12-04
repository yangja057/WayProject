package com.example.yangj.wayproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class FirstActivity extends AppCompatActivity {
    boolean touchbookmark=false;    //bookmark버튼을 클릭했는가 하지 않았는가.

    RecyclerView recyclerView;
    private BoardRecyclerViewAdapter adapter;
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

        /*
        자영이가 넘긴 string을 받아서 str1-str2를 append해줌
         */
        database.getReference().child("review").child("null-null").child("-L-WJ-0XpmWXS3cf76t9").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.i("다스리의 로그", "onChildAdded:" + dataSnapshot.getKey());

                //데이터가 날라온 것을 이미지 리스트에 담는다
               // listItems.clear();//수정될때마다 데이터가 날라옴/ 안해주면 데이터가 쌓여

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    /*
                    DataSnapshot snapshot : dataSnapshot.getChildren() //처음부터 끝까지 데이터를 읽는다는 뜻임
                    getchildren() :가지 하나를 children이라고 함
                    child==현재 "review" 의 children하나를 읽어옴
                     */
                    GenericTypeIndicator<List<ImageData>> genericTypeIndicator =new GenericTypeIndicator<List<ImageData>>(){};
                   listItems=dataSnapshot.getValue(genericTypeIndicator);

                }
               adapter.notifyDataSetChanged();//새로 고침(갱신되니까)
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("다스리의 로그","여기들어왔다는건...잘못됐다는뜻임");

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.favorite_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.bookmark: //북마크(즐겨찾기)
                Toast.makeText(this, "즐겨찾기", Toast.LENGTH_SHORT).show();
                if(!touchbookmark){
                    //bookmark버튼을 클릭했을때, icon을 까만 별로 바꾼다.★
                    item.setIcon(R.drawable.ic_action_bookmark2);
                    touchbookmark=true;
                }
                else
                {
                    //즐겨찾기 해제시 icon을 텅빈 별로 바꾼다.☆
                    item.setIcon(R.drawable.ic_action_bookmark);
                    touchbookmark=false;
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
