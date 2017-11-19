package com.example.yangj.wayproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class WAddPlaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wadd_place);
    }

    public void BtnSearchClick(View view) {
        EditText editText=(EditText)findViewById(R.id.editText);
        String string=editText.getText().toString();
        Intent data=new Intent();
        data.putExtra("result",string);

        setResult(Activity.RESULT_OK, data);
        //자신을 실행할 엑티비티에게 돌려줄 결과는 setResult메서드로 설정
        this.finish();
    }
}
