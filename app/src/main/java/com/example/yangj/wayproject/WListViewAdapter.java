package com.example.yangj.wayproject;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    //public List<UserData>WUserDataItemList=new ArrayList<UserData>();
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

        if(imageItem.imageUrl!=null){
            Uri myUri= Uri.parse(imageItem.loadUri);
            Glide.with(holder.itemView.getContext()).load(myUri).into(((ViewHolder)holder).iconImageView);
        }

        //holder.iconImageView.setImageURI(myUri);

        if(position < 5)
            holder.textViewArr[position].setText(imageItem.getPlaceName());

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
        TextView[] textViewArr = new TextView[5];
        TextView RecommendView;
        TextView UserIdView;


        public ViewHolder(View itemView){
            super(itemView);

            iconImageView=(ImageView)itemView.findViewById(R.id.imageView1);

            textViewArr[0] = (TextView)itemView.findViewById(R.id.place1);
            textViewArr[1] = (TextView)itemView.findViewById(R.id.place2);
            textViewArr[2] = (TextView)itemView.findViewById(R.id.place3);
            textViewArr[3] = (TextView)itemView.findViewById(R.id.place4);
            textViewArr[4] = (TextView)itemView.findViewById(R.id.place5);

            RecommendView=(TextView)itemView.findViewById(R.id.recommend);
            UserIdView=(TextView)itemView.findViewById(R.id.userID);
        }
    }


}
