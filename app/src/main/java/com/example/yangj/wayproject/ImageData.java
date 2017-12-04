package com.example.yangj.wayproject;

import android.net.Uri;

/**
 * Created by 심다슬 on 2017-11-27.
 */

public class ImageData {

    //당연히 멤버변수 이름은 똑같아야됨


   public String loadUri;//이미지의 uri
    public String imageUrl;//이미지의 storage경로
    //public String title; "str1-str2" 로 필요하면 주석 해제해서 쓰세요
    public String description;//리뷰
    public String userEmail;//작성자의 이메일
    public int star;//별의 수
    public String placeID;//장소의 고유 id
    public  String placeName;//장소의 이름
    //public String one;//test용
    //public String reviewKey;//item 고유 key

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getPlaceID() {
        return placeID;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getImageUrl() {

        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
