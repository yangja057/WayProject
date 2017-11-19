package com.example.yangj.wayproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class WLoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlogin2);
        Intent intent=new Intent(this, WRegiReviewActivity.class);
        startActivity(intent);


    }//oncreate closed

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
