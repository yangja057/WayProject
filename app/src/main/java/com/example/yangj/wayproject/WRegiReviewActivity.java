package com.example.yangj.wayproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.util.List;

public class WRegiReviewActivity extends AppCompatActivity {


    private static final int PLACE_PICKER_REQUEST = 1;
    private static int WHICH_POINT = 0; //StartingPoint 이면 0, EndingPoint 이면 1

    TextView tvStartingPoint;
    TextView tvEndingPoint;

    Button btnStartingPoint;
    Button btnEndingPoint;
    Button AddButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wregi_review);


        ListView listView;
        final WRegiReviewAdapter adapter=new WRegiReviewAdapter();

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
                searchStartingPoint(v);
            }
        });
        btnEndingPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchEndingPoint(v);
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

    public void searchStartingPoint(View view){
        //This is the place to call the place picker function

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        Intent intent;
        try {
            intent = builder.build(WRegiReviewActivity.this);
            startActivityForResult(intent, PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

//        try{
//            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
//            WHICH_POINT = 0;
//
//        }catch (GooglePlayServicesRepairableException e){
//            e.printStackTrace();
//        }catch(GooglePlayServicesNotAvailableException e){
//            e.printStackTrace();
//        }
    }

    public void searchEndingPoint(View view){
        //This is the place to call the place picker function

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        try{
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
            WHICH_POINT = 1;

        }catch (GooglePlayServicesRepairableException e){
            e.printStackTrace();
        }catch(GooglePlayServicesNotAvailableException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PLACE_PICKER_REQUEST){
            if(resultCode == RESULT_OK){
                Place place = PlacePicker.getPlace(WRegiReviewActivity.this, data);
                if(WHICH_POINT == 0){
                    tvStartingPoint.setText(place.getName());
                }
                else{
                    tvEndingPoint.setText(place.getName());
                }
            }
        }
    }
}
