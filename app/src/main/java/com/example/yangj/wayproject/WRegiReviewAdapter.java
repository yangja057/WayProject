package com.example.yangj.wayproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by USER on 2017-11-21.
 */

public class WRegiReviewAdapter extends BaseAdapter{

    private ArrayList<WRegiReviewItem> WRegiReviewItemList=new ArrayList<WRegiReviewItem>();
    private View parentconvertView;
    WRegiReviewItem regiReviewItem;

    Button placeButton;
    ImageButton UserImage;
    WRegiReviewActivity mActivity;
    private Context context;

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
        context=parent.getContext();
        parentconvertView=convertView;

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_wregi_review_item, parent, false);
        }
        placeButton=(Button) convertView.findViewById(R.id.placeButton);
        UserImage=(ImageButton) convertView.findViewById(R.id.UserImage);
        EditText explainText=(EditText)convertView.findViewById(R.id.explainText);

        placeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.myOnClickListener(v);
            }
        });

        regiReviewItem=WRegiReviewItemList.get(position);

       UserImage.setImageResource(regiReviewItem.getPhoto());

       UserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //디바이스에서 가져오기
            }
        });

       UserImage.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "길게눌려여",Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        explainText.setText(regiReviewItem.getReview());

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
