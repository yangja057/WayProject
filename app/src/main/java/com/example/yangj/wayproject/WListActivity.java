package com.example.yangj.wayproject;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText Startedit;
    private EditText Endedit;
    private String startingPointName;
    private String startingPointId;
    private String endingPointName;
    private String endingPointId;
    private Button SearchButton;
    private WListViewAdapter adapter;
    private FirebaseAuth auth;
    public List<ImageData> listItems=new ArrayList<ImageData>(); //데이터 리스트 구조체

    //파이어 베이스 데이터베이스 추가->문서를 읽어오기 위해 꼭 필요한 객체
    private FirebaseDatabase database;

    static private final int STARTING_POINT = 0;
    static private final int ENDING_POINT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlist);

        recyclerView=(RecyclerView)findViewById(R.id.ListrecyclerView);
        Startedit=(EditText)findViewById(R.id.list_btnStartingPoint);
        Endedit=(EditText)findViewById(R.id.list_btnEndingPoint);
        SearchButton=(Button)findViewById(R.id.list_btnsearch);

        adapter=new WListViewAdapter(R.layout.activity_wlist_item);
        database=FirebaseDatabase.getInstance();//다른곳에서 사용하기 위해서 singletone pattern으로  등록
        auth= FirebaseAuth.getInstance();

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //썸네일을 클릭하면 boardActivity로 넘어갈 게시물의 id를 넘겨줘야한다.
                        Toast.makeText(view.getContext(), "position = " + position, Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), FirstActivity.class);
                        ImageData img=adapter.WImageDataItemList.get(position);
                        intent.putExtra("ID", img.reviewKey);
                    }
                })
        );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        Startedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //Toast.makeText(getApplicationContext(), "이건 출발지를 선택하는 애지롱",Toast.LENGTH_SHORT).show();
              Intent intent = new Intent(getBaseContext(), WAddPlaceActivity.class);
              startActivityForResult(intent, STARTING_POINT);
            }
        });

        Endedit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "이건 도착지를 선택하는 애지롱",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getBaseContext(), WAddPlaceActivity.class);
                startActivityForResult(intent, ENDING_POINT);
            }
        });

        database.getReference().child("review").child(startingPointId+"-"+endingPointId).addValueEventListener(new ValueEventListener() {
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
                    adapter.WImageDataItemList.add(imageData);
                    Log.i("다스리의 로그", "imageData:" + imageData.getUserEmail());
                }

                adapter.notifyDataSetChanged();//새로 고침(갱신되니까)
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("다스리의 로그","여기들어왔다는건...잘못됐다는뜻임");

            }
        });

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case STARTING_POINT:
                    startingPointName = data.getStringExtra("placeName");
                    startingPointId = data.getStringExtra("placeId");
                    Startedit.setText(startingPointName);
                    break;
                case ENDING_POINT:
                    endingPointName = data.getStringExtra("placeName");
                    endingPointId = data.getStringExtra("placeId");
                    Endedit.setText(endingPointName);
                    break;
            }
        }
        else if(resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(getBaseContext(),"데이터 가져오기 실패",Toast.LENGTH_LONG).show();
        }
    }
}