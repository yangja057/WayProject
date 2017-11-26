package com.example.yangj.wayproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class WRegiReviewActivity extends AppCompatActivity {

    private static int WHICH_POINT = 0; //StartingPoint 이면 0, EndingPoint 이면 1

    TextView tvStartingPoint;
    TextView tvEndingPoint;

    static String curSelectPlace;

    Button btnStartingPoint;
    Button btnEndingPoint;
    Button AddButton;

    final WRegiReviewAdapter adapter=new WRegiReviewAdapter(WRegiReviewActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wregi_review);


        ListView listView;

        listView=(ListView)findViewById(R.id.listview1);
        listView.setAdapter(adapter);

        tvStartingPoint = (TextView)findViewById(R.id.tvStartingPoint);
        tvEndingPoint = (TextView)findViewById(R.id.tvEndingPoint);
        btnStartingPoint = (Button)findViewById(R.id.btnStartingPoint);
        btnEndingPoint = (Button)findViewById(R.id.btnEndingPoint);
        AddButton=(Button)findViewById(R.id.button3);

        btnStartingPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), WAddPlaceActivity.class);
                startActivityForResult(intent, 1);
                WHICH_POINT = 0;
            }
        });

        btnEndingPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), WAddPlaceActivity.class);
                startActivityForResult(intent, 1);
                WHICH_POINT = 1;
            }
        });

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(WRegiReviewActivity.this, "눌려?", Toast.LENGTH_SHORT ).show();
                adapter.addItem();
                adapter.notifyDataSetChanged();
                //String titleStr=item.getTitle();
                //String descStr=item.getDesc();
                //int photo=item.getPhoto();
                //Drawable iconDrawable=item.getIcon();
            }
        });
    }

    public void myOnClickListener(View v){
        Intent intent = new Intent(getBaseContext(), WAddPlaceActivity.class);
        startActivityForResult(intent, 1);
        WHICH_POINT = 2;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                if(WHICH_POINT == 0){
                    tvStartingPoint.setText(data.getStringExtra("placeName"));
                }
                else if(WHICH_POINT == 1){
                    tvEndingPoint.setText(data.getStringExtra("placeName"));
                }
                else if(WHICH_POINT == 2){
                    adapter.regiReviewItem.setPlace(data.getStringExtra("placeName"));
                    adapter.regiReviewItem.placeButton.setText(adapter.regiReviewItem.getPlace());
                }
            }
            if(resultCode == Activity.RESULT_CANCELED){
                Toast.makeText(getBaseContext(),"데이터 가져오기 실패",Toast.LENGTH_LONG).show();
            }
        }
    }

    public String getCurSelectPlace(){
        return curSelectPlace;
    }
}
