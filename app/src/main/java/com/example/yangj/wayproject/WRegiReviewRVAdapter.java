package com.example.yangj.wayproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.List;

/**
 * Created by yangj on 2017-12-02.
 */

public class WRegiReviewRVAdapter extends RecyclerView.Adapter<WRegiReviewRVAdapter.ViewHolder>{
<<<<<<< HEAD
    static private final int PLACE_BUTTON = 0;
    static private final int USER_IMAGE = 1;

    private List<ImageData> listItems;
=======

    public List<ImageData> listItems;
>>>>>>> 5cc61efe91afa0252c38ea83e36239b562231bd0
    private Context context;

    public WRegiReviewRVAdapter(List<ImageData> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.regi_review_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ImageData listItem = listItems.get(position);

        holder.edtPlaceButton.setText(listItem.getPlaceName());
        //holder.imbUserImage.setImageURI(listItem.getImageUrl());
        holder.edtExplainText.setText(listItem.getDescription());

        holder.edtPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position, PLACE_BUTTON);
            }
        });
        holder.imbUserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(position, USER_IMAGE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public EditText edtPlaceButton;
        public ImageButton imbUserImage;
        public EditText edtExplainText;

        public ViewHolder(View itemView) {
            super(itemView);

            edtPlaceButton = (EditText) itemView.findViewById(R.id.regi_placeButton);
            imbUserImage = (ImageButton) itemView.findViewById(R.id.regi_UserImage);
            edtExplainText = (EditText) itemView.findViewById(R.id.regi_explainText);
        }
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(int position, int id);
    }
}
