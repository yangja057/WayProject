package com.example.yangj.wayproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class WMainSearchActivity extends AppCompatActivity {

    static private final int STARTING_POINT = 0;
    static private final int ENDING_POINT = 1;

    Button btnStartingPoint;
    String startingPointName;
    String startingPointId;

    Button btnEndingPoint;
    String endingPointName;
    String endingPointId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wmain_search);

        btnStartingPoint = (Button)findViewById(R.id.btnStartingPoint);
        btnEndingPoint = (Button)findViewById(R.id.btnEndingPoint);

        btnStartingPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), WAddPlaceActivity.class);
                startActivityForResult(intent, STARTING_POINT);
            }
        });

        btnEndingPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), WAddPlaceActivity.class);
                startActivityForResult(intent, ENDING_POINT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            switch(requestCode){
                case STARTING_POINT:
                    startingPointName = data.getStringExtra("placeName");
                    startingPointId = data.getStringExtra("placeId");
                    btnStartingPoint.setText(startingPointName);
                    break;
                case ENDING_POINT:
                    endingPointName = data.getStringExtra("placeName");
                    endingPointId = data.getStringExtra("placeId");
                    btnEndingPoint.setText(endingPointName);
                    break;
            }
        }
        else if(resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(getBaseContext(),"데이터 가져오기 실패",Toast.LENGTH_LONG).show();
        }
    }
}
