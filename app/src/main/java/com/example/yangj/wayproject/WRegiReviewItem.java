package com.example.yangj.wayproject;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by USER on 2017-11-21.
 */

public class WRegiReviewItem {
    Button placeButton;
    ImageView UserImage;
    EditText explainText;

    private int photo;
    private String place;
    private String review;

    public WRegiReviewItem(){
        photo=0;
        review="리뷰써주세요오^_^/";
    }
    public void setPhoto(int photo){this.photo=photo;}
    public void setPlace(String place){this.place=place;}
    public void setReview(String review){this.review=review;}

    public int getPhoto(){return photo;}
    public String getPlace(){return place;}
    public String getReview(){return review;}

}
