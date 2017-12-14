package com.example.yangj.wayproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {
    boolean touchbookmark=false;    //bookmark버튼을 클릭했는가 하지 않았는가.

    RecyclerView recyclerView;
    private BoardRecyclerViewAdapter adapter;
    private List<ImageData> listItems;
    private FirebaseDatabase database;//데이터 베이스 추가
    private FirebaseAuth auth;
    private StorageReference storageReference;

    private String ReviewId;
    private Intent intent;

    UserData m_userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        intent = getIntent();

        recyclerView=(RecyclerView)findViewById(R.id.FirstView);
        adapter=new BoardRecyclerViewAdapter(R.layout.item_board);//adapter 생성
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());

        database=FirebaseDatabase.getInstance();
        storageReference= FirebaseStorage.getInstance().getReference();
        auth=FirebaseAuth.getInstance();
        listItems=new ArrayList<ImageData>();
        /*
        자영이가 넘긴 string을 받아서 str1-str2를 append해줌
        child("-L-WkVk7aH9IJSYDVS0e") 이건 여리가 넘긴 게시물 고유 키값 imagedata.reviewkey
        여리는 이 두개의 값을 모두 넘겨줘야 됨

         */

        database.getReference().child("review").child("ChIJhTv7M9ykfDURcOPgVAYJGYE-ChIJOdw9FOCYfDUR4-e79v57J_Q").child("-L0M2TUGIt3HEaWxWw_R").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.i("다스리의 로그", "onChildAdded:" + dataSnapshot.getKey());
                //데이터가 날라온 것을 이미지 리스트에 담는다
                adapter.WBoardList.clear();//수정될때마다 데이터가 날라옴/ 안해주면 데이터가 쌓여

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                    ImageData imageData=snapshot.getValue(ImageData.class);
                    //listItems.add(imageData);//데이터의 개수만큼 for loop을 돌면서 list에 객체를 넣음
                  adapter.WBoardList.add(imageData);//데이터의 개수만큼 for loop을 돌면서 list에 객체를 넣음

                    Log.d("다슬로그","리뷰 조금 출력?"+imageData.description);
                   // GenericTypeIndicator<List<ImageData>> genericTypeIndicator =new GenericTypeIndicator<List<ImageData>>(){};
                   //listItems=dataSnapshot.getValue(genericTypeIndicator);adapter.

//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            adapter.notifyDataSetChanged();
//                        }
//                    });
                    adapter.notifyDataSetChanged();
                    //adapter.notifyItemInserted(adapter.WBoardList.size()-1);//새로 고침(갱신되니까)
                }
                adapter.notifyDataSetChanged();//새로 고침(갱신되니까)
               // adapter.notifyItemInserted(adapter.WBoardList.size()-1);//새로 고침(갱신되니까)
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("다스리의 로그","여기들어왔다는건...잘못됐다는뜻임");

            }
        });

        // 현재 유저 데이터 저장
        m_userData = new UserData();
        m_userData.userEmail = auth.getCurrentUser().getEmail();
        m_userData.userUID = auth.getCurrentUser().getUid();
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

                    ReviewId = intent.getStringExtra("ID");//클릭한 게시물 키값 받아오기

                    //추가

//                    database.getReference().child("review").child("ChIJhTv7M9ykfDURcOPgVAYJGYE-ChIJOdw9FOCYfDUR4-e79v57J_Q").child(ReviewId).runTransaction(new Transaction.Handler() {
//                        @Override
//                        public Transaction.Result doTransaction(MutableData mutableData) {
//                            ImageData p = mutableData.getValue(ImageData.class);
//                            if (p == null) {
//                                return Transaction.success(mutableData);
//                            }
//
//                            if (p.stars.containsKey(m_userData.userUID)) {
//                                // Unstar the post and remove self from stars
//                                p.star = p.star - 1;
//                                p.stars.remove(m_userData.userUID);
//                            } else {
//                                // Star the post and add self to stars
//                                p.star = p.star + 1;
//                                p.stars.put(m_userData.userUID, true);
//                            }
//
//                            // Set value and report transaction success
//                            mutableData.setValue(p);
//                            return Transaction.success(mutableData);
//                        }
//
//                        @Override
//                        public void onComplete(DatabaseError databaseError, boolean b,
//                                               DataSnapshot dataSnapshot) {
//                            // Transaction completed
//                            Log.d("다슬로그", "postTransaction:onComplete:" + databaseError);
//                        }
//                    });



                    // 즐겨찾기 한 게시물의 아이템을 list에 저장
                    database.getReference().child("review").child("ChIJhTv7M9ykfDURcOPgVAYJGYE-ChIJOdw9FOCYfDUR4-e79v57J_Q").child(ReviewId).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            adapter.WBoardList.clear();
                            m_userData.list.clear();

                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                                ImageData imageData = snapshot.getValue(ImageData.class);
                                imageData.star++;
                                m_userData.list.add(imageData);
                                adapter.WBoardList.add(imageData);
                                adapter.notifyDataSetChanged();
                            }

                             database.getReference().child("users").child(m_userData.userUID).child("MyLikeReviewList").child(ReviewId).setValue(m_userData);//다시등록
                      // database.getReference().child("review").child("ChIJhTv7M9ykfDURcOPgVAYJGYE-ChIJOdw9FOCYfDUR4-e79v57J_Q").child(ReviewId).setValue(m_userData.list);//다시 등록
                         adapter.notifyDataSetChanged();
                          finish();

                        }
                        //database.getReference().child("review").child("ChIJhTv7M9ykfDURcOPgVAYJGYE-ChIJOdw9FOCYfDUR4-e79v57J_Q").child(ReviewId).setValue(m_userData.list);//다시 등록

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            Log.i("다스리의 로그","여기들어왔다는건...잘못됐다는뜻임");
                        }
                    });

                    //Log.i("로그", m_userData.list.get(0).getPlaceName());
                  database.getReference().child("review").child("ChIJhTv7M9ykfDURcOPgVAYJGYE-ChIJOdw9FOCYfDUR4-e79v57J_Q").child(ReviewId).setValue(adapter.WBoardList);

                }//if closed
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
