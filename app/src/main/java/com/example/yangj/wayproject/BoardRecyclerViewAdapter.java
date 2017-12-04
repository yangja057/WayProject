package com.example.yangj.wayproject;

        import android.media.Image;
        import android.net.Uri;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.bumptech.glide.Glide;

        import org.w3c.dom.Text;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by 심다슬 on 2017-11-28.
 */

public class BoardRecyclerViewAdapter extends RecyclerView.Adapter<BoardRecyclerViewAdapter.ViewHolder> {

    public List<ImageData>WBoardList=new ArrayList<ImageData>();
    private int itemLayout; //R.layout.imageData를 담음.
    private View view;

    //생성할 때 물려줄 xml을 물려줘야한다.

    public BoardRecyclerViewAdapter(int itemLayout){
        this.itemLayout=itemLayout;
    }
    @Override
    public BoardRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        view= LayoutInflater.from(parent.getContext()).inflate(itemLayout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //listview의 getview를 대체
        //넘겨받은 데이터를 화면에 출력하는 역할이다.

        ImageData imageItem=WBoardList.get(position);

       // Uri myUri= Uri.parse(" https://firebasestorage.googleapis.com/v0/b/wayproject-5d588.appspot.com/o/images%2Fimage%3A335?alt=media&token=5f9e52f9-77e3-4c9d-ad3b-1a25a9e78806");
        Uri myUri= Uri.parse(WBoardList.get(position).loadUri);
        Glide.with(holder.itemView.getContext()).load(myUri).into(holder.imageView);

        holder.imageView.setImageURI(myUri);
        holder.Place.setText(imageItem.getPlaceName());
        holder.Review.setText(imageItem.getDescription());

    }
    @Override
    public int getItemCount() {
        return 0 ;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        Button Place;
        TextView Review;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.regi_UserImage);
            Place=(Button) itemView.findViewById(R.id.regi_placeButton);
            Review=(TextView) itemView.findViewById(R.id.regi_explainText);

        }
    }
}
