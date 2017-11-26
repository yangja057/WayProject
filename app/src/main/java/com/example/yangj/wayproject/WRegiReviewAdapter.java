package com.example.yangj.wayproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by USER on 2017-11-21.
 */

public class WRegiReviewAdapter extends BaseAdapter{

    private ArrayList<WRegiReviewItem> WRegiReviewItemList=new ArrayList<WRegiReviewItem>();
    private View parentconvertView;
    WRegiReviewItem regiReviewItem;
    WRegiReviewActivity mActivity;

    public WRegiReviewAdapter(WRegiReviewActivity activity){
        mActivity = activity;
    }
    @Override
    public int getCount() {
        return WRegiReviewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return WRegiReviewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos=position;
        final Context context=parent.getContext();
        parentconvertView=convertView;
        regiReviewItem=WRegiReviewItemList.get(position);

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_wregi_review_item, parent, false);
        }

        regiReviewItem.placeButton = (Button) convertView.findViewById(R.id.placeButton);
        regiReviewItem.UserImage = (ImageView)convertView.findViewById(R.id.UserImage);
        regiReviewItem.explainText = (EditText)convertView.findViewById(R.id.explainText);

        regiReviewItem.placeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.myOnClickListener(v);
            }
        });

       regiReviewItem.placeButton.setText(regiReviewItem.getPlace());

//        if(regiReviewItem.getPhoto()==0){
//            //ImageView UserImage=(ImageView)parentconvertView.findViewById(R.id.UserImage);
//            UserImage.setImageResource(R.drawable.base);
//        }
       regiReviewItem.UserImage.setImageResource(regiReviewItem.getPhoto());

       regiReviewItem.explainText.setText(regiReviewItem.getReview());

        return convertView;
    }
//    public void addItem(int photo, String place, String review){
//        WRegiReviewItem item=new WRegiReviewItem();
//
//        item.setPhoto(photo);
//        item.setPlace(place);
//        item.setReview(review);
//    }

    public void addItem(){
        WRegiReviewItem item=new WRegiReviewItem();
        item.setPhoto(R.drawable.base);
        WRegiReviewItemList.add(item);
    }
}
