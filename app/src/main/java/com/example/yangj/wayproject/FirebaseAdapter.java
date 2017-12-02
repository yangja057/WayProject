package com.example.yangj.wayproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 심다슬 on 2017-12-02.
 */

public class FirebaseAdapter extends RecyclerView.Adapter<FirebaseAdapter.ViewHolder> {

    private String[] mDataset;
    List<ImageData> mdata;
   // List<Comment> mComments;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        public TextView mTextView;

        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.mTextView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    //생성자1
    public FirebaseAdapter(String[] myDataset) {
        mDataset = myDataset;
    }

    //생성자 2 ImageData type의 생성자
     //Provide a suitable constructor (depends on the kind of dataset)
    public FirebaseAdapter(List<ImageData> _comments) {
        this.mdata = _comments;
    }

    //생성자 3 Comment type의 생성자
//    public FirebaseAdapter(List<Comment> mComments) {
//        this.mComments=mComments;
//    }

    // Create new views (invoked by the layout manager)
    @Override
    public FirebaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.firebase_board, parent, false);//inflate가 참조하는 뷰가 item_board임
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        /*
        onBindViewHolder 라는 곳에서는 mTextView의 position(순서)에 맞는 데이터를 넣음
         */
        //holder.mTextView.setText(mDataset[position]);
        holder.mTextView.setText(mdata.get(position).description);//List의 해당위치의 객체 멤버함수인 리뷰를 가지고옴
   }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //return mDataset.length; //String 일경우에는 length이지만
        return mdata.size();//List일 경우에는 size, 즉 배열의사이즈
    }

}
