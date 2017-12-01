package com.example.yangj.wayproject;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class WListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText Startedit;
    private EditText Endedit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlist);

        recyclerView=(RecyclerView)findViewById(R.id.ListrecyclerView);
        Startedit=(EditText)findViewById(R.id.list_btnStartingPoint);
        Endedit=(EditText)findViewById(R.id.list_btnEndingPoint);

        WListViewAdapter adapter;
        adapter=new WListViewAdapter(R.layout.activity_wlist_item);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        WListViewItem item1=new WListViewItem();
        item1.setTitle("Bear");
        item1.setIcon(ContextCompat.getDrawable(this,R.drawable.bear));
        item1.setDesc("So Cute><");

        WListViewItem item2=new WListViewItem();
        item2.setTitle("brother");
        item2.setIcon(ContextCompat.getDrawable(this, R.drawable.brothers));
        item2.setDesc("bears");

        adapter.WlistViewItemList.add(item1);
        adapter.WlistViewItemList.add(item2);

        Startedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Toast.makeText(getApplicationContext(), "이건 출발지를 선택하는 애지롱",Toast.LENGTH_SHORT).show();
            }
        });

        Endedit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "이건 도착지를 선택하는 애지롱",Toast.LENGTH_SHORT).show();
            }
        });
//       adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ice),
//               "ice", "ice");
//       adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bear),
//               "Bear", "Cute><");
//       adapter.addItem(ContextCompat.getDrawable(this, R.drawable.brothers),
//                "brother", "bears");
//        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ice),
//                "ice", "ice");


    }
}