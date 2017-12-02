package com.example.yangj.wayproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class WRegiReviewRVActivity extends AppCompatActivity {
    static private final int STARTING_POINT = 0;
    static private final int ENDING_POINT = 1;
    static private final int CUR_SELECT_PLACE = 2;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ImageData> listItems;

    private EditText StartingPointEdt;
    private EditText EndingPointEdt;
    private Button addItemButton;

    private String StartingPointName;
    private String StartingPointId;
    private String EndingPointName;
    private String EndingPointId;

    private String CurSelectPlaceName;
    private String CurSelectPlaceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wregi_review_rv);

        recyclerView = (RecyclerView) findViewById(R.id.regi_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StartingPointEdt = (EditText) findViewById(R.id.regi_btnStartingPoint);
        EndingPointEdt = (EditText) findViewById(R.id.regi_btnEndingPoint);
        addItemButton = (Button) findViewById(R.id.regi_addItemButton);

        listItems = new ArrayList<>();

        adapter = new WRegiReviewRVAdapter(listItems, this);
        recyclerView.setAdapter(adapter);

        StartingPointEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), WAddPlaceActivity.class);
                startActivityForResult(intent, STARTING_POINT);
            }
        });

        EndingPointEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), WAddPlaceActivity.class);
                startActivityForResult(intent, ENDING_POINT);
            }
        });

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageData listItem = new ImageData();
                listItems.add(listItem);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void selectPlaceOnClickListener(){
        Intent intent = new Intent(getBaseContext(), WAddPlaceActivity.class);
        startActivityForResult(intent, CUR_SELECT_PLACE);
    }

    public String getCurSelectPlaceName(){
        return CurSelectPlaceName;
    }

    public String getCurSelectPlaceId(){
        return CurSelectPlaceId;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case STARTING_POINT:
                    StartingPointName = data.getStringExtra("placeName");
                    StartingPointId = data.getStringExtra("placeId");
                    StartingPointEdt.setText(StartingPointName);
                    break;
                case ENDING_POINT:
                    EndingPointName = data.getStringExtra("placeName");
                    EndingPointId = data.getStringExtra("placeId");
                    EndingPointEdt.setText(EndingPointName);
                    break;
                case CUR_SELECT_PLACE:
                    CurSelectPlaceName = data.getStringExtra("placeName");
                    CurSelectPlaceId = data.getStringExtra("placeId");
                    break;
            }
        }
        else if(resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(getBaseContext(),"데이터 가져오기 실패",Toast.LENGTH_LONG).show();
        }
    }
}
