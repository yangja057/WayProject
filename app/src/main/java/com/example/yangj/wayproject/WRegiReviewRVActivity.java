package com.example.yangj.wayproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import java.util.ArrayList;
import java.util.List;

public class WRegiReviewRVActivity extends AppCompatActivity implements WRegiReviewRVAdapter.OnItemClickListener {
    static private final int STARTING_POINT = 0;
    static private final int ENDING_POINT = 1;
    static private final int CUR_SELECT_PLACE = 2;
    static private final int PICK_IMAGE_REQUEST=3;
    static private final int PLACE_BUTTON = 0;
    static private final int USER_IMAGE = 1;

    private int storeIndex=0;
    private RecyclerView recyclerView;
    private WRegiReviewRVAdapter adapter;

    private List<ImageData> listItems;

    private EditText StartingPointEdt;
    private EditText EndingPointEdt;
    private Button addItemButton;

    private String StartingPointName;
    private String StartingPointId;
    private String EndingPointName;
    private String EndingPointId;

    private String CurSelectPlaceName;
    private String CurSelectPlaceId;

    private int ItemPostion;

    /*
    firebase 데이터 로드
     */
    private Uri filePath;
    private StorageReference storageReference;

    //realtime database
    private FirebaseDatabase database;//데이터 베이스 추가
    private FirebaseAuth auth;
    //private EditText editImageTitle;//사진의 이름을 저장할 et



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.newPost:  //글 등록

                Toast.makeText(this,"글등록버튼",Toast.LENGTH_SHORT).show();

             /*
                realtime db
                 */
                database=FirebaseDatabase.getInstance();
                storageReference= FirebaseStorage.getInstance().getReference();
                auth=FirebaseAuth.getInstance();

                //파일 업로드가 성공했을 경우
                //사진 storage에 저장


//                for (storeIndex=0;storeIndex<listItems.size()-1;storeIndex++){
//
//                    StorageReference riversRef = storageReference.child("images/"+ Uri.parse(listItems.get(storeIndex).imageUrl).getLastPathSegment());
//                    riversRef.putFile(Uri.parse(listItems.get(storeIndex).imageUrl)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                        @Override
//                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                            //사진등록이 성공했을 경우, 사진을 storage에 저장하기전에 데이터를 보존해둠
//
//                                Uri downloadUrl=taskSnapshot.getDownloadUrl();
//                                //listItems.get(storeIndex).loadUri=downloadUrl.toString();//imageUri를 taskSnapshot.getDownloadUrl()의 string값으로 저장
//                            listItems.get(storeIndex).loadUri = downloadUrl.toString();
//
//                            //adapter.notifyItemChanged(storeIndex);
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                        @Override
//                        public void onFailure(@NonNull Exception exception) {
//                            Toast.makeText(WRegiReviewRVActivity.this, exception.getMessage(),Toast.LENGTH_LONG).show();
//                        }
//                    });
//                    //Log.d("다스리의 로그",listItems.get(storeIndex).loadUri);
//                }

                //Toast.makeText(getBaseContext(),"downhell"+listItems.get(0).loadUri,Toast.LENGTH_LONG).show();

                //Log.d("다스리의 로그",listItems.get(0).loadUri);
                //database.getReference().child()
                database.getReference().child("review").child(StartingPointId+"-"+EndingPointId).push().setValue(listItems);

                // m_imageData.imageUrl=filePath.toString(); //m_imageData.imageUrl=downloadUrl.toString();
                // m_imageData.myUrl=Uri.parse(m_imageData.imageUrl);

               //user정보 등록
                UserData m_userData=new UserData();
                m_userData.userEmail=auth.getCurrentUser().getEmail();
                m_userData.userUID=auth.getCurrentUser().getUid();

                for(int i=0;i<listItems.size();i++){
                    m_userData.myReviewList.add(listItems.get(i));
                    //m_userData.myReviewList.add(listItems.get(i));
                }
                database.getReference().child("users").child(m_userData.userUID).push().setValue(m_userData);

                /**
                 * 중요) child안에 emial string 넣으면 안됨<-보안상의 문제인듯
                 */

                //String start_end;->이부분을 출발-도착 이렇게 append시켜서 child("str1-st2")이안에 넣어주십셔
                //"review"는 첫번째 루트
                //"users"는 두번째 루트트


                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wregi_review_rv);

        recyclerView = (RecyclerView) findViewById(R.id.regi_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StartingPointEdt = (EditText) findViewById(R.id.regi_btnStartingPoint);
        EndingPointEdt = (EditText) findViewById(R.id.regi_btnEndingPoint);
        addItemButton = (Button) findViewById(R.id.regi_addItemButton);

        listItems = new ArrayList<>();

        adapter = new WRegiReviewRVAdapter(listItems, this);

        adapter.setOnItemClickListener(this);

        recyclerView.setAdapter(adapter);

        StartingPointEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), WAddPlaceActivity.class);
                startActivityForResult(intent, STARTING_POINT);
            }
        });

        EndingPointEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), WAddPlaceActivity.class);
                startActivityForResult(intent, ENDING_POINT);
            }
        });

        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageData listItem = new ImageData();
                listItems.add(listItem);
                //Log.d("다스리의 로그",listItem.description);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void selectPlaceOnClickListener(){
        Intent intent = new Intent(getBaseContext(), WAddPlaceActivity.class);
        startActivityForResult(intent, CUR_SELECT_PLACE);
    }

    public String getCurSelectPlaceName(){
        return CurSelectPlaceName;
    }

    public String getCurSelectPlaceId(){
        return CurSelectPlaceId;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
            switch (requestCode){
                case STARTING_POINT:
                    StartingPointName = data.getStringExtra("placeName");
                    StartingPointId = data.getStringExtra("placeId");
                    StartingPointEdt.setText(StartingPointName);
                    break;
                case ENDING_POINT:
                    EndingPointName = data.getStringExtra("placeName");
                    EndingPointId = data.getStringExtra("placeId");
                    EndingPointEdt.setText(EndingPointName);
                    break;
                case CUR_SELECT_PLACE:
                    ImageData listItem = listItems.get(ItemPostion);
                    listItem.setPlaceName(data.getStringExtra("placeName"));
                    listItem.setPlaceID(data.getStringExtra("placeId"));
                    adapter.notifyItemChanged(ItemPostion);
                    break;
            }
        }
        else if(resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(getBaseContext(),"데이터 가져오기 실패",Toast.LENGTH_LONG).show();
        }

        if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            //사진 가지고 오기

             filePath=data.getData();//uri type
            //imagePath=data.getData().toString();//파일 경로 저장했는데//string->database로
            ImageData listItem=listItems.get(ItemPostion);
            listItem.imageUrl=filePath.toString();
            adapter.notifyItemChanged(ItemPostion);



//            try {
//                //Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),filePath);
//
//                adapter.notifyItemChanged(ItemPostion);
//                //Log.d("다스리의 로그",""+ItemPostion);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

        }
    }

    @Override
    public void onItemClick(int position, int id) {
        switch (id){
            case PLACE_BUTTON:
                ItemPostion = position;
                Intent intent = new Intent(getBaseContext(), WAddPlaceActivity.class);
                startActivityForResult(intent, CUR_SELECT_PLACE);
                break;
            case USER_IMAGE:
                ItemPostion = position;
                Intent intent2=new Intent();
                intent2.setType("image/*");
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent2,"Select an Image"),PICK_IMAGE_REQUEST);

                break;
            default:
                break;
        }

        //Log.d("다스리의 로그",""+position);

    }




}
