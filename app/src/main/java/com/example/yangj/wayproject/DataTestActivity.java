package com.example.yangj.wayproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class DataTestActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST =234 ;// 아무거나 값을 줘도 상관없음

    private Button buttonChoose,buttonUpload;
    private ImageView imageView;
    private Uri filePath;
    private StorageReference storageReference;
    private String imagePath;//로드된 이미지 파일의 경로

    //realtime database
    private FirebaseDatabase database;//데이터 베이스 추가
    private EditText editUpLoadReview;
    private ImageButton ibtnUploadImage;
    private FirebaseAuth auth;
    //private EditText editImageTitle;//사진의 이름을 저장할 et
    //
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_test);


        storageReference= FirebaseStorage.getInstance().getReference();

        imageView=(ImageView)findViewById(R.id.imageView);
        buttonChoose=(Button)findViewById(R.id.buttonChoose);
        buttonUpload=(Button)findViewById(R.id.buttonUpload);


          /*
        realtime database
         */
        database=FirebaseDatabase.getInstance();
        editUpLoadReview=(EditText)findViewById(R.id.uploadReview);//리뷰등록하는곳
        ibtnUploadImage=(ImageButton)findViewById(R.id.uploadImage);//이미지 등록하는 버튼
        auth=FirebaseAuth.getInstance();
        //

        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open file chooser
                showFileChooser();
            }
        });


        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //upload file to firebase storage
                uploadFile();

            }
        });

        //되는지 잘 모르겠음


        //

    }//closed oncreate

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);//data가 사진 파일의 객체인것임
        if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            filePath=data.getData();
            imagePath=data.getData().toString();//파일 경로 저장했는데
            try {
                Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void showFileChooser(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select an Image"),PICK_IMAGE_REQUEST);

    }

    private void uploadFile(){

        if(filePath!=null) {
            final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

          //  StorageReference riversRef = storageReference.child("images/"+filePath+".jpg");
            StorageReference riversRef = storageReference.child("images/"+ filePath.getLastPathSegment());

            riversRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //파일 업로드가 성공했을 경우
                            progressDialog.dismiss();
                            Toast.makeText(DataTestActivity.this, "File Uploaded",Toast.LENGTH_LONG).show();

                            Uri downloadUrl=taskSnapshot.getDownloadUrl();

                            /*
                            real time data base
                             */
                            ImageData m_imageData=new ImageData();
                            UserData m_userData=new UserData();

                            m_imageData.imageUrl=filePath.toString(); //m_imageData.imageUrl=downloadUrl.toString();
                            //m_imageData.title=editImageTitle.getText().toString();
                            m_imageData.description=editUpLoadReview.getText().toString();
                            m_imageData.userEmail=auth.getCurrentUser().getEmail();//등록한 사용자의 이메일을 반환
                            m_imageData.star++;

                            //user정보 등록
                            m_userData.userID=auth.getCurrentUser().getEmail();
                            m_userData.myReviewList.add(m_imageData);
                           m_userData.likeReviewList.add(m_imageData);
                           m_userData.userUID=auth.getCurrentUser().getUid();

                           //Toast.makeText(DataTestActivity.this, curUser,Toast.LENGTH_LONG).show();

                            /**
                             * 중요) child안에 emial string 넣으면 안됨<-보안상의 문제인듯
                             */


                            //String start_end;->이부분을 출발-도착 이렇게 append시켜서 child("str1-st2")이안에 넣어주십셔
                            //"review"는 첫번째 루트
                            //"users"는 두번째 루트트
                          database.getReference().child("review: ").child("str1-str2").push().setValue(m_imageData);//데이터저장(쌓이는 형태)
                            database.getReference().child("users").child(m_userData.userUID).setValue(m_userData);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(DataTestActivity.this, exception.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress=(100.0*taskSnapshot.getBytesTransferred())/taskSnapshot.getTotalByteCount();
                    progressDialog.setMessage(((int)progress)+"% Uploading...");
                }
            })
            ;
        }else{
            //display error toast
        }
    }
}
