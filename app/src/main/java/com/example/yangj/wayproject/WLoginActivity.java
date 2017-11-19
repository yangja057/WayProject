package com.example.yangj.wayproject;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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
