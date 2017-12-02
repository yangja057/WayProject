package com.example.yangj.wayproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 2017-11-20.
 */

public class WListViewAdapter extends RecyclerView.Adapter<WListViewAdapter.ViewHolder> {

    public List<ImageData> WImageDataItemList=new ArrayList<ImageData>();
    private int itemLayout; //R.layout.listitem.xml을 담음.
    private View view;

    public WListViewAdapter(/*List<ImageData> wlistitems,*/ int itemLayout){
        //생성자

        this.itemLayout=itemLayout;
    }

    @Override
    public WListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //레이아웃을 만들어서 Holder에 저장
        view=LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WListViewAdapter.ViewHolder holder, int position) {
        //listview의 getview를 대체
        //넘겨받은 데이터를 화면에 출력하는 역할이다.

        ImageData imageItem=WImageDataItemList.get(position);

        Uri filepath= Uri.parse(imageItem.getImageUrl());

        holder.iconImageView.setImageURI(filepath);
        holder.titleTextView.setText(imageItem.getPlaceName());
        holder.descTextView.setText(imageItem.getDescription());

    }

    @Override
    public int getItemCount() {
        return WImageDataItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        //뷰 재활용을 위한 viewHolder

        ImageView iconImageView;
        TextView titleTextView;
        TextView descTextView;

        public ViewHolder(View itemView){
            super(itemView);

            iconImageView=(ImageView)itemView.findViewById(R.id.imageView1);
            titleTextView=(TextView)itemView.findViewById(R.id.textView1);
            descTextView=(TextView)itemView.findViewById(R.id.textView2);

        }
    }


}
