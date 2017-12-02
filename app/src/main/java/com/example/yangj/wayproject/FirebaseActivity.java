package com.example.yangj.wayproject;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

/**
 * Firebase에 데이터를 load하는 부분이 모두 담겨있습니다
 */
public class FirebaseActivity extends AppCompatActivity {

    private Button btnGOGO;
    private Button btnDOWN;
    /**
     * 리사이클 뷰를 이용하는데 세가지 요소가 필요함
     * 1. 리사이클 뷰
     * 2. 어댑터
     * 3. 레이아웃관리자
     */
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    /**
     * 데이터를 읽고 쓰기 위한 객체 선언
     */
    private  FirebaseDatabase database;//데이터 쓰기
    private DatabaseReference myRef;//데이터 읽기

    /*
    여러가지 형태의 데이터 클래스들
     */
    String[] myDataset={"안녕","오늘"};
   // private List<Comment>mComments = new ArrayList<>();
    private List<ImageData> mData = new ArrayList<>();
    private List<String> mCommentIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        btnDOWN=(Button)findViewById(R.id.downFireBase);//이 버튼을 누르면 firebase에 있는 내용을 불러와 뿌림
        btnGOGO=(Button)findViewById(R.id.regiFireBase) ;//이 버튼을 누르면 firebase에 등록되는것을 볼 수 있음

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);//사이즈를 정해줌

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        //

        //기본 구조인 String으로 adapter를 쥐어주는 방식 <- String[] myDataset
        //mAdapter = new FirebaseAdapter(myDataset);//표시될 데이터를 연결해줌
        //mRecyclerView.setAdapter(mAdapter);//어댑터를 리사이클뷰에 연결해줌

        mAdapter = new FirebaseAdapter(mData);//표시될 데이터를 연결해줌
        mRecyclerView.setAdapter(mAdapter);//어댑터를 리사이클뷰에 연결해줌

        /**
         * 데이터 베이스에 데이터를 쓰기
         */
        // Write a message to the database
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef=database.getReference();

        btnGOGO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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


          /*
        혹시 몰라서 추가하는 시간 순서대로 정리하기
        : 최신순으로 firebase의 데이터를 불러오고 싶을 경우 용이하게 쓰일것 같아서
        일단 내맘속에 저^.^장^.^
        ->우리는 이부분을 user의 uid로 구성했음
         */
                Calendar c = Calendar.getInstance();
                //System.out.println("Current time => " + c.getTime());//print문은 필요없는 것임

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = df.format(c.getTime());



                //DatabaseReference myRef = database.getReference("tempTest");//어디에 저장을 할거야?(tree의 root)
                //DatabaseReference myRef = database.getReference("tempTest").child(formattedDate);//이렇게 할 경우 "tempTest"의 child가 생김
               // myRef = database.getReference("tempTest").child(formattedDate);---1

                //hash table 삽입하여 firebase 의 구조를 만듦
                Hashtable<String, String> test = new Hashtable<String, String>();
                test.put("one","이것은 test인것임");
                test.put("two", "이것은 test2인것임");
                test.put("three", "이것또한 test3일지니");

                //데이터 저장 방법 1
                //myRef.setValue("Hello, World!");//setValue() : 뭐라고 저장할거야?
                myRef.child("tempTest").child(formattedDate).setValue(test);//setValue의 인자로는 객체가 들어감---2 //1,2는 같은 것임
            }
        });//btnGOGO finish


        btnDOWN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef.child("review: ").child("str1-str2").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        //이것만 필요함


                        // A new comment has been added, add it to the displayed list
                        //private List<Comment> mComments = new ArrayList<>(); //리스트를 만듦(객체들의 리스트)

                        //Comment comment = dataSnapshot.getValue(Comment.class); //ImageData class 로 바꿔줘야됨됨
                        ImageData data = dataSnapshot.getValue(ImageData.class);

                        // [START_EXCLUDE]
                        // Update RecyclerView
                        mCommentIds.add(dataSnapshot.getKey());//getKey() : 키값을 가져옴
                        mData.add(data);//arrayList에 객체 하나를 추가(add)함
                        mAdapter.notifyItemInserted(mData.size() - 1);
                        // [END_EXCLUDE]

                       Log.d("다스리의 로그", "onChildAdded:" + data.description.toString());
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                        Log.e("다스리의 로그","데이터가 왜 잘못되는걸까");
                    }
                });
            }
        });//btnDOWN finish




    }//onCreate closed
}
