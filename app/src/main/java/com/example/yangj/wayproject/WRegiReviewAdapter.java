package com.example.yangj.wayproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
        regiReviewItem=WRegiReviewItemList.get(position);

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.activity_wregi_review_item, parent, false);
        }
        regiReviewItem.placeButton=(Button) convertView.findViewById(R.id.placeButton);
        UserImage=(ImageButton) convertView.findViewById(R.id.UserImage);
        EditText explainText=(EditText)convertView.findViewById(R.id.explainText);

        regiReviewItem.placeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.myOnClickListener(v);
            }
        });

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
                final AlertDialog.Builder alertDialog=new AlertDialog.Builder(context);

                //제목 셋팅
                alertDialog.setTitle("게시물 삭제");

                //AlertDialog 셋팅
                alertDialog.setMessage("게시물을 삭제할 것입니까?")
                        .setCancelable(false)
                        .setPositiveButton("삭제",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        //게시물을 삭제한다.
                                        //AlertDialogActivity.this.finish();
                                    }
                                });
                return true;
            }
        });

        explainText.setText(regiReviewItem.getReview());

        return convertView;
    }


    public void addItem(){
        WRegiReviewItem item=new WRegiReviewItem();
        item.setPhoto(R.drawable.base);

        WRegiReviewItemList.add(item);

    }
}
