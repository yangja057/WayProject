package com.example.yangj.wayproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
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

    public List<WListViewItem> WlistViewItemList=new ArrayList<WListViewItem>();
    private int itemLayout; //R.layout.listitem.xml을 담음.

    public WListViewAdapter(/*List<WListViewItem> wlistitems,*/ int itemLayout){
        //생성자
        //this.WlistViewItemList=wlistitems;
        this.itemLayout=itemLayout;
    }

    @Override
    public WListViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //레이아웃을 만들어서 Holder에 저장
        View view=LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WListViewAdapter.ViewHolder holder, int position) {
        //listview의 getview를 대체
        //넘겨받은 데이터를 화면에 출력하는 역할이다.

        WListViewItem listViewItem=WlistViewItemList.get(position);

        holder.iconImageView.setImageDrawable(listViewItem.getIcon());
        holder.titleTextView.setText(listViewItem.getTitle());
        holder.descTextView.setText(listViewItem.getDesc());


    }

    @Override
    public int getItemCount() {
        return WlistViewItemList.size();
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
