package com.example.yangj.wayproject;

import android.content.ClipData;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth auth;//현재 로그인한 사용자를 얻기 위한 변수 선언
//
    private Button btnGoToJoin;
    private Button btnGoToApi;
    private Button btnGoToMainView;
    private Button btnGoRegiReview;
    private Button btnGoToLogin;
    private Button btnLogOut;
    private Button btnGoList;
    private Button btnCheck;

    private Button btnBoardData;
    private Button btnGoMainSearch;
    private Button btnGoRegiReviewRV;
    private Button btnFavoriteView;
    private Button btnWriteMyself;
    private DrawerLayout drawer;
    private  FirebaseAuth.AuthStateListener mAuthListener;
    private FrameLayout frameLayout;

    ListView listView=null;

    ActionBarDrawerToggle drawerToggle; //액션바에 있는 아이콘 클릭

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;
        switch (item.getItemId()){
            case R.id.SimpleMenu:
                Toast.makeText(this, "으데보쟈", Toast.LENGTH_SHORT).show();
                return true;
                //break;
            default:
                break;
        }

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
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final String[] items={"login", "join", "settings"};

        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);

        drawer=(DrawerLayout)findViewById(R.id.drawer);
        frameLayout=(FrameLayout)findViewById(R.id.mainFrame);

        listView=(ListView)findViewById(R.id.drawer_menulist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(getApplicationContext(), "제발 네비게이션ㅠ",Toast.LENGTH_SHORT).show();

                switch (position){
                    case 1:
                        Intent intent=new Intent(getApplicationContext(), WListActivity.class);
                        startActivity(intent);
                        break;
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
        /*
        로그인버튼 : 로그인으로 이동
        회원가입 버튼 : 회원가입으로 이동
        검색버튼 : 구글 api로 이동
        보여지는 view: 출발 도착을 눌렀을 경우 그다음에 보여지는 썸네일들이 있는 뷰
        리뷰등록 버튼 : 리뷰등록 액티비티로 이동
        testActivity :  데이터 테스트 액티비티
        boardActivity : 데이터베이스에 있는 내용이 뿌려지는 화면==LTest view? 사용자가 보기위한 화면인것임

         */

        btnCheck=(Button)findViewById(R.id.check);
        btnBoardData=(Button)findViewById(R.id.BoardData) ;
        btnGoToJoin=(Button)findViewById(R.id.goToJoin);
        btnGoToApi=(Button)findViewById(R.id.goToFirebase);
        btnGoToMainView=(Button)findViewById(R.id.goToMainView);
        btnGoRegiReview=(Button)findViewById(R.id.goRegiReview);
        btnGoToLogin=(Button)findViewById(R.id.goToLogin);
        btnLogOut=(Button)findViewById(R.id.logOut);
        btnGoList=(Button)findViewById(R.id.goListView);
        btnGoMainSearch=(Button)findViewById(R.id.goMainSearch);
        btnGoRegiReviewRV=(Button)findViewById(R.id.goRegiReviewRV);
        btnFavoriteView=(Button)findViewById(R.id.btnfavorite);
        btnWriteMyself=(Button)findViewById(R.id.btnWriteMyself);
/*
현재 로그인한 사용자
 */


        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //데이터 확인
                Intent intent=new Intent(getBaseContext(),DataTestActivity.class);
                 startActivity(intent);

            }
        });


        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //로그아웃을 수행함
                Toast.makeText(getBaseContext(),"로그아웃",Toast.LENGTH_LONG).show();
                FirebaseAuth.getInstance().signOut();
               // Intent intent=new Intent(getBaseContext(),WLoginActivity.class);
               // startActivity(intent);
            }
        });


        btnGoToJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),WJoinActivity.class);
                startActivity(intent);
            }
        });

        btnGoToApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),FirebaseActivity.class);
                startActivity(intent);
            }
        });

        //하나하나 잇기 시작합니다.
        btnGoToMainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),FirstActivity.class);
                startActivity(intent);
            }
        });

        btnBoardData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),BoardActivity.class);
                startActivity(intent);
            }
        });

        btnGoRegiReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),WRegiReviewActivity.class);
               startActivity(intent);
            }
        });

        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),WLoginActivity.class);
                startActivity(intent);
            }
        });
        btnGoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),WListActivity.class);
                startActivity(intent);
            }
        });


        btnGoMainSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(),WMainSearchActivity.class);
                startActivity(intent);
            }
        });

        btnGoRegiReviewRV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), WRegiReviewRVActivity.class);
                startActivity(intent);
            }
        });

        btnFavoriteView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), WMyLikeReviewActivity.class);
                startActivity(intent);
            }
        });

        btnWriteMyself.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getBaseContext(), WMyReviewActivity.class);
                startActivity(intent);
            }
        });

    }//oncreat closed


}
