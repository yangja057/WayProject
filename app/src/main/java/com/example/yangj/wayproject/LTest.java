package com.example.yangj.wayproject;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class LTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ltest);

        ListView listView;
        final LTestAdapter adapter=new LTestAdapter();

        listView=(ListView)findViewById(R.id.testlist1);
        listView.setAdapter(adapter);

        adapter.addItem(R.drawable.konkuk, "건국대학교", "일감호가 이쁘다.");

        }


}
