package com.example.yangj.wayproject;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
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

    //네비게이션바 달기.
    private DrawerLayout drawer;
    private FrameLayout frameLayout;
    ListView listView=null; //네비게이션바에 들어가는 list를 담습니다.
    ActionBarDrawerToggle drawerToggle; //액션바에 있는 아이콘 클릭
    //

    static private final int STARTING_POINT = 0;
    static private final int ENDING_POINT = 1;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);  //내비게이션바

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
                        //recyclerview에서 클릭을 한 position의 값을 알아낸 뒤 imageData에 position에 해당하는 값을 담아온다.
                        //그리고 intent에 ID로 게시물의 고유키인 reviewKey를 전달한다.
                        intent.putExtra("ID", img.reviewKey);
                        Log.i("다스리의 로그12", "id" + img.reviewKey);
                        startActivity(intent);
                    }
                })
        );
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //여리의 내비게이션바 만들기
        final String[] items={"로그아웃", "리뷰추가", "내가 쓴 리뷰보기","즐겨찾기","설정"}; //내비게이션바에 있을 메뉴들
        ArrayAdapter naviadapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);

        drawer=(DrawerLayout)findViewById(R.id.list_drawer);
        frameLayout=(FrameLayout)findViewById(R.id.listFrame);
        listView=(ListView)findViewById(R.id.drawer_wlist);
        listView.setAdapter(naviadapter);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(getApplicationContext(), "제발 네비게이션ㅠ",Toast.LENGTH_SHORT).show();

                switch (position){
                    case 0:
                        //로그아웃
                        Toast.makeText(getBaseContext(),"로그아웃",Toast.LENGTH_LONG).show();
                        FirebaseAuth.getInstance().signOut();
                        finish();
                        break;
                    case 1:
                        //나의 리뷰 추가하기.
                        Intent intent3=new Intent(getApplicationContext(), WRegiReviewRVActivity.class);
                        startActivity(intent3);
                        break;
                    case 2:
                        //즐겨찾기
                        Toast.makeText(getApplicationContext(), "내가 쓴 리뷰보기",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), WMyReviewActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        //내가 쓴 리뷰보기
                        Toast.makeText(getApplicationContext(), "즐겨찾기",Toast.LENGTH_SHORT).show();
                        Intent intent2=new Intent(getApplicationContext(), WMyLikeReviewActivity.class);
                        startActivity(intent2);

                        break;
                    case 4:
                        //settings
                        Toast.makeText(getApplicationContext(), "settings",Toast.LENGTH_SHORT).show();
                    default:
                        break;
                }
                drawer.closeDrawer(Gravity.LEFT);
            }
        });
        drawerToggle=new ActionBarDrawerToggle(this, drawer, R.string.open_drawer, R.string.close_drawer){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };
        drawer.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //여기까지가 여리의내비게이션바

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

        SearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //검색버튼을 누르면 recycler에 게시물들이 썸네일처럼 뿌려져야함.

                database.getReference().child("review").child(startingPointId+"-"+endingPointId).child("-L0J0cYBZnkv69wNw8pB").child(0+"").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        //데이터가 날라온 것을 이미지 리스트에 담는다
                        listItems.clear();//수정될때마다 데이터가 날라옴/ 안해주면 데이터가 쌓여

//                        for(DataSnapshot snapshot : dataSnapshot.getChildren()){
//                    /*
//                    DataSnapshot snapshot : dataSnapshot.getChildren() //처음부터 끝까지 데이터를 읽는다는 뜻임
//                    getchildren() :가지 하나를 children이라고 함
//                    child==현재 "review" 의 children하나를 읽어옴
//                     */
//
//                            ImageData imageData=snapshot.getValue(ImageData.class);
//                            //listItems.add(imageData);//데이터의 개수만큼 for loop을 돌면서 list에 객체를 넣음
//                            //adapter.WImageDataItemList.add(imageData);
//                            Log.i("다스리의 로그", "imageData:" + imageData.getUserEmail());
//                        }

                        ImageData imageData=dataSnapshot.getValue(ImageData.class);
                        adapter.WImageDataItemList.add(imageData);
                        adapter.notifyDataSetChanged();//새로 고침(갱신되니까)
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.i("다스리의 로그","여기들어왔다는건...잘못됐다는뜻임");

                    }
                });
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