package com.example.yangj.wayproject;

        import android.media.Image;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import org.w3c.dom.Text;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by 심다슬 on 2017-11-28.
 */

public class BoardRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_board,parent,false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0 ;
    }

    private class CustomViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        TextView textView2;

        public CustomViewHolder(View view) {
            super(view);
            imageView= (ImageView) view.findViewById(R.id.item_imageView);
            textView=(TextView) view.findViewById(R.id.item_textView);
            textView2=(TextView) view.findViewById(R.id.item_textView2);

        }
    }
}
