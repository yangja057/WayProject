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

import com.bumptech.glide.Glide;

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

        //Uri myUri= Uri.parse(imageItem.loadUri);
        Uri myUri= Uri.parse(" https://firebasestorage.googleapis.com/v0/b/wayproject-5d588.appspot.com/o/images%2Fimage%3A335?alt=media&token=5f9e52f9-77e3-4c9d-ad3b-1a25a9e78806");
        Glide.with(holder.itemView.getContext()).load(myUri).into(((ViewHolder)holder).iconImageView);

        //holder.iconImageView.setImageURI(myUri);
        holder.Place1View.setText(imageItem.getPlaceName());
        holder.Place2View.setText(imageItem.getPlaceName());
        holder.Place3View.setText(imageItem.getPlaceName());
        holder.Place4View.setText(imageItem.getPlaceName());
        holder.Place5View.setText(imageItem.getPlaceName());

        holder.UserIdView.setText(imageItem.getUserEmail());
        holder.RecommendView.setText("★ x "+String.valueOf(imageItem.getStar()));

    }

    @Override
    public int getItemCount() {
        return WImageDataItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        //뷰 재활용을 위한 viewHolder

        ImageView iconImageView;
        TextView Place1View;
        TextView Place2View;
        TextView Place3View;
        TextView Place4View;
        TextView Place5View;
        TextView RecommendView;
        TextView UserIdView;


        public ViewHolder(View itemView){
            super(itemView);

            iconImageView=(ImageView)itemView.findViewById(R.id.imageView1);

            Place1View=(TextView)itemView.findViewById(R.id.place1);
            Place2View=(TextView)itemView.findViewById(R.id.place2);
            Place3View=(TextView)itemView.findViewById(R.id.place3);
            Place4View=(TextView)itemView.findViewById(R.id.place4);
            Place5View=(TextView)itemView.findViewById(R.id.place5);

            RecommendView=(TextView)itemView.findViewById(R.id.recommend);
            UserIdView=(TextView)itemView.findViewById(R.id.userID);
        }
    }


}
