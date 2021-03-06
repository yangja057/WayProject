package com.example.yangj.wayproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.List;

/**
 * Created by yangj on 2017-12-02.
 */

public class WRegiReviewRVAdapter extends RecyclerView.Adapter<WRegiReviewRVAdapter.ViewHolder>{
    static private final int PLACE_BUTTON = 0;
    static private final int USER_IMAGE = 1;

    public List<ImageData> listItems;
    private Context context;

    StorageReference storageReference;

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

       // Log.d("다슬","여기는 들어오는데 저기는 안들어간다고?");
        final ImageData listItem = listItems.get(position);

        holder.edtPlaceButton.setText(listItem.getPlaceName());

        if(listItem.imageUrl!=null){
            Uri myuri=Uri.parse(listItem.imageUrl);
            Bitmap bitmap= null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(),myuri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            holder.imbUserImage.setImageBitmap(bitmap);
            //holder.imbUserImage.setImageURI(myUri);
        }

        listItem.setDescription(holder.edtExplainText.getText().toString());
        holder.edtExplainText.setText(listItem.getDescription());
        //Log.d("짜요이의 로그","!" + holder.edtExplainText.getText().toString());

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

        //파이어베이스 저장소에 저장
        storageReference= FirebaseStorage.getInstance().getReference();
        if(listItem.imageUrl!=null){
            Uri temp=Uri.parse(listItem.imageUrl);
            StorageReference riversRef=storageReference.child("images/"+temp.getLastPathSegment());
            riversRef.putFile(temp).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    listItem.loadUri=taskSnapshot.getDownloadUrl().toString();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    //Toast.makeText(WRegiReviewRVAdapter.this,e.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public EditText edtPlaceButton;
        public ImageView imbUserImage;
        public EditText edtExplainText;

        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);

            edtPlaceButton = (EditText) itemView.findViewById(R.id.regi_placeButton);
            imbUserImage = (ImageView) itemView.findViewById(R.id.regi_UserImage);
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
