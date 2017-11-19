package com.example.yangj.wayproject;

<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class WLoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
=======
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
>>>>>>> 550dc5b42514d4c91838b00091fc7341cef1ae27

public class WLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlogin);
    }
}
