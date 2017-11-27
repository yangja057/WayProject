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
    private String placeName;
    private String placeId;
    private String review;

    public WRegiReviewItem(){
        photo=0;
        review="리뷰써주세요오^_^/";
    }
    public void setPhoto(int photo){this.photo=photo;}
    public void setPlaceName(String placeName){this.placeName=placeName;}
    public void setPlaceId(String placeId){this.placeId=placeId;}
    public void setReview(String review){this.review=review;}

    public int getPhoto(){return photo;}
    public String getPlaceName(){return placeName;}
    public String getPlaceId(){return placeId;}
    public String getReview(){return review;}

}
