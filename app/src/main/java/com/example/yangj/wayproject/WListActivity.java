package com.example.yangj.wayproject;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class WListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlist);

        ListView listView;
        WListViewAdapter adapter;

        adapter=new WListViewAdapter();

        listView=(ListView)findViewById(R.id.listview1);
        listView.setAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bear),
                "Bear", "Cute><");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.brothers),
                "brother", "bears");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ice),
                "ice", "ice");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.bear),
                "Bear", "Cute><");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.brothers),
                "brother", "bears");
        adapter.addItem(ContextCompat.getDrawable(this, R.drawable.ice),
                "ice", "ice");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WListViewItem item=(WListViewItem) parent.getItemAtPosition(position);
                String titleStr=item.getTitle();
                String descStr=item.getDesc();
                int photo=item.getPhoto();
                Drawable iconDrawable=item.getIcon();

                Toast.makeText(WListActivity.this, titleStr, Toast.LENGTH_SHORT).show();

            }
        });
    }
}