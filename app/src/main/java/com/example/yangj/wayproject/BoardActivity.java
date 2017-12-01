package com.example.yangj.wayproject;


import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
 import org.w3c.dom.Text;
 import java.util.ArrayList;
import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/*
  데이터 베이스의 내용을 뿌리는 공간
   */

public class BoardActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
   public  List<ImageData> imageDTOs=new ArrayList<>(); //데이터 리스트 구조체
    public List<String> uidLists =new ArrayList<>(); // 사용자 리스트

    //파이어 베이스 데이터베이스 추가->문서를 읽어오기 위해 꼭 필요한 객체
    private FirebaseDatabase database;
   private BoardRecyclerViewAdapter boardRecyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        database=FirebaseDatabase.getInstance();//다른곳에서 사용하기 위해서 singletone pattern으로  등록

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//리사이클 뷰를 세팅하는 코드
        boardRecyclerViewAdapter=new BoardRecyclerViewAdapter();//adapter 생성
        recyclerView.setAdapter(boardRecyclerViewAdapter);//리사이클뷰에게 어댑터를 줌

        //데이터를 읽어오는 코드
        /*
        - "review"를 읽어옴
        - addValueEventListene : 글자가 하나씩 바뀔때 마다 데이터가 계속 넘어옴
        - client에게 알려줌
        즉 다른사람이 데이터를 수정했으면
        자동적으로 새로 고침이 됨
         */
        database.getReference().child("review").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //데이터가 날라온 것을 이미지 리스트에 담는다
                imageDTOs.clear();//수정될때마다 데이터가 날라옴/ 안해주면 데이터가 쌓여

                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    /*
                    DataSnapshot snapshot : dataSnapshot.getChildren() //처음부터 끝까지 데이터를 읽는다는 뜻임
                    getchildren() :가지 하나를 children이라고 함
                    child==현재 "review" 의 children하나를 읽어옴
                     */
                    ImageData imageData=snapshot.getValue(ImageData.class);
                    imageDTOs.add(imageData);//데이터의 개수만큼 for loop을 돌면서 list에 객체를 넣음
                }
                boardRecyclerViewAdapter.notifyDataSetChanged();//새로 고침(갱신되니까)
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }


    /*
    inner class==BoardActivivyAdapter class와 동일한데
    inner class로 구성하여 사용함
    추후에 수정할수도..
     */


    /**
     * Created by 심다슬 on 2017-11-28.
     */

    public class BoardRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board,parent,false);

            return new CustomViewHolder(view);//리사이클 뷰의 장점^.^ view holder에 view를 넘겨줌
    }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
           // ((CustomViewHolder)holder).textView.setText(imageDTOs.get(position).title);
            ((CustomViewHolder)holder).textView2.setText(imageDTOs.get(position).description);
            //Glide.with(holder.itemView.getContext()).load(imageDTOs.get(position).imageUrl).into(((CustomViewHolder)holder).imageView);
        }

        @Override
        public int getItemCount() {
            return imageDTOs.size();
        }

        //view holder innerclass
        private class CustomViewHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView textView;
            TextView textView2;

            public CustomViewHolder(View view) {
                super(view);
                imageView= (ImageView) view.findViewById(R.id.item_imageView);
                textView=(TextView) view.findViewById(R.id.item_textView);
                textView2=(TextView) view.findViewById(R.id.item_textView2);

            }
        }
    }


}
