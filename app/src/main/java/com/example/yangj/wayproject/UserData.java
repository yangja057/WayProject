package com.example.yangj.wayproject;

import android.media.Image;

import java.util.ArrayList;

/**
 * Created by 심다슬 on 2017-12-01.
 */

public class UserData {
    public  String userUID;
    public String userEmail;
    public ArrayList<ImageData> likeReviewList;//내가 즐겨찾기 한 게시글 고유 id를 담음
    public ArrayList<ImageData> myReviewList;//내가쓴 게시글 고유 id를 담음

UserData(){
    likeReviewList=new ArrayList<>();
    myReviewList=new ArrayList<>();

}

}
