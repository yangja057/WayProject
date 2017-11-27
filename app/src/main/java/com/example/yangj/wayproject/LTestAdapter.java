package com.example.yangj.wayproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by USER on 2017-11-26.
 */

public class LTestAdapter extends BaseAdapter{
    private ArrayList<WRegiReviewItem> LTestList=new ArrayList<WRegiReviewItem>();

    public LTestAdapter(){

    }
    @Override
    public int getCount() {
        return LTestList.size();
    }

    @Override
    public Object getItem(int position) {
        return LTestList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos=position;
        final Context context=parent.getContext();

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_wregi_review_item, parent, false);

        }

        ImageView iconImageView=(ImageView)convertView.findViewById(R.id.imageView1);
        TextView titleTextView=(TextView)convertView.findViewById(R.id.textView1);
        TextView descTextView=(TextView)convertView.findViewById(R.id.textView2);

        WRegiReviewItem LRegiViewItem=LTestList.get(position);

        Button placeButton=(Button) convertView.findViewById(R.id.placeButton);
        ImageView UserImage=(ImageView)convertView.findViewById(R.id.UserImage);
        EditText explainText=(EditText)convertView.findViewById(R.id.explainText);

        UserImage.setImageResource(LRegiViewItem.getPhoto());
        placeButton.setText(LRegiViewItem.getPlaceName());
        explainText.setText(LRegiViewItem.getReview());

        return convertView;
    }

    public void addItem(int photo, String place, String review){

        WRegiReviewItem item=new WRegiReviewItem();

        item.setPlaceName(place);
        item.setReview(review);
        item.setPhoto(photo);

        LTestList.add(item);
    }

}

