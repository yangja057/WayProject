package com.example.yangj.wayproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by USER on 2017-11-20.
 */

public class WListViewAdapter extends BaseAdapter {

    private ArrayList<WListViewItem> WlistViewItemList=new ArrayList<WListViewItem>();

    public WListViewAdapter(){

    }
    @Override
    public int getCount() {
        return WlistViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return WlistViewItemList.get(position);
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
            convertView=inflater.inflate(R.layout.activity_wlist_item, parent, false);

        }

        ImageView iconImageView=(ImageView)convertView.findViewById(R.id.imageView1);
        TextView titleTextView=(TextView)convertView.findViewById(R.id.textView1);
        TextView descTextView=(TextView)convertView.findViewById(R.id.textView2);

        WListViewItem listViewItem=WlistViewItemList.get(position);

        iconImageView.setImageDrawable(listViewItem.getIcon());
        titleTextView.setText(listViewItem.getTitle());
        descTextView.setText(listViewItem.getDesc());

        return convertView;
    }
    public void addItem(Drawable icon, String title, String desc){
        WListViewItem item=new WListViewItem();

        item.setIcon(icon);
        item.setTitle(title);
        item.setDesc(desc);

        WlistViewItemList.add(item);
    }

}
