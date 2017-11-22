package com.example.yangj.wayproject;

/**
 * Created by USER on 2017-11-21.
 */

public class WRegiReviewItem {
    private int photo;
    private String place;
    private String review;

    public WRegiReviewItem(){
        photo=0;
        place="장소가 어디에요?ㅇㅅㅇ";
        review="리뷰써주세요오^_^/";
    }
    public void setPhoto(int photo){this.photo=photo;}
    public void setPlace(String place){this.place=place;}
    public void setReview(String review){this.review=review;}

    public int getPhoto(){return photo;}
    public String getPlace(){return place;}
    public String getReview(){return review;}

}
