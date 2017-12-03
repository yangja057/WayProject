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
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Toast;

public class WListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText Startedit;
    private EditText Endedit;
    private String startingPointName;
    private String startingPointId;
    private String endingPointName;
    private String endingPointId;


    static private final int STARTING_POINT = 0;
    static private final int ENDING_POINT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlist);

        recyclerView=(RecyclerView)findViewById(R.id.ListrecyclerView);
        Startedit=(EditText)findViewById(R.id.list_btnStartingPoint);
        Endedit=(EditText)findViewById(R.id.list_btnEndingPoint);

        WListViewAdapter adapter;
        adapter=new WListViewAdapter(R.layout.activity_wlist_item);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        //썸네일을 클릭하면 boardActivity로 넘어갈 게시물의 id를 넘겨줘야한다.
                        Toast.makeText(view.getContext(), "position = " + position, Toast.LENGTH_SHORT).show();
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