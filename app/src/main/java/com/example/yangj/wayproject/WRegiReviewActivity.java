package com.example.yangj.wayproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class WRegiReviewActivity extends AppCompatActivity {
    static private final int STARTING_POINT = 0;
    static private final int ENDING_POINT = 1;
    static private final int CUR_SELECT_PLACE = 2;

    String startingPointName;
    String startingPointId;

    String endingPointName;
    String endingPointId;

    static String curSelectPlace;

    Button btnStartingPoint;
    Button btnEndingPoint;
    Button AddButton;

    boolean touchbookmark=false;


    final WRegiReviewAdapter adapter=new WRegiReviewAdapter(WRegiReviewActivity.this);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu item for use in the action bas
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Handle press on the action bar items
        switch(item.getItemId()){
            case R.id.newPost:  //글 등록
                Toast.makeText(this,"글등록버튼",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bookmark: //북마크(즐겨찾기)
                Toast.makeText(this, "즐겨찾기", Toast.LENGTH_SHORT).show();
                if(!touchbookmark){
                    item.setIcon(R.drawable.ic_action_bookmark2);
                    touchbookmark=true;
                }
                else
                {
                    item.setIcon(R.drawable.ic_action_bookmark);
                    touchbookmark=false;
                }
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wregi_review);
        ListView listView;

        listView=(ListView)findViewById(R.id.listview1);
        listView.setAdapter(adapter);

        btnStartingPoint = (Button)findViewById(R.id.btnStartingPoint);
        btnEndingPoint = (Button)findViewById(R.id.btnEndingPoint);
        AddButton=(Button)findViewById(R.id.button3);

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

    public void selectPlaceOnClickListener(View v){
        Intent intent = new Intent(getBaseContext(), WAddPlaceActivity.class);
        startActivityForResult(intent, CUR_SELECT_PLACE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            switch (requestCode){
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
                case CUR_SELECT_PLACE:
                    adapter.regiReviewItem.setPlaceName(data.getStringExtra("placeName"));
                    adapter.regiReviewItem.setPlaceId(data.getStringExtra("placeId"));
                    adapter.regiReviewItem.placeButton.setText(adapter.regiReviewItem.getPlaceName());
                    break;
            }
        }
        else if(resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(getBaseContext(),"데이터 가져오기 실패",Toast.LENGTH_LONG).show();
        }
    }
}
