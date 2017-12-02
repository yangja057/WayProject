package com.example.yangj.wayproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class WRegiReviewRVActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<ImageData> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wregi_review_rv);

        recyclerView = (RecyclerView) findViewById(R.id.regi_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItems = new ArrayList<>();

        for(int i=0;i<=10;i++){
            ImageData listItem = new ImageData();
            listItems.add(listItem);
        }

        adapter = new WRegiReviewRVAdapter(listItems, this);

        recyclerView.setAdapter(adapter);
    }
}
