package com.example.yangj.wayproject;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;

import static java.sql.Types.NULL;

public class WRegiReviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wregi_review);
    }

    public void BtnSubClick(View view) {
        Intent intent=new Intent(this,WAddPlaceActivity.class);
        intent.putExtra("value", NULL);    //1번째 인자는 나중에 데이터를 꺼내기 위한 키
        //2번째 인자는 전달할 데이터

        startActivityForResult(intent, 0);  //1번째 인자는 인텐트, 2번째 인자는 요청코드를 집어넣는다.
        //요청코드는 실행한 엑티비티를 구분하기 위해 지정하는 숫자.
        //결과를 돌려 받을 때 요청코드를 보고 어떤 엑티비티가 결과를 반환했는지 알 수 있음.
        //값은 음수를 제외한 아무숫자나 주면 된다.

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //onActivityResult는 startActivityForResult로 실행할 액티비티가 종료되면 호출,
        //종료된 엑티비티가 setResult메서드로 설정한 결과코드와 데이터(인텐트를 첨부했을 때만)전달된다.
        //첫번째 매개변수는 요청코드, 두번째 매개변수는 종료된 엑티비티가 setResult로 지정한 결과코드
        //마지막 매개변수는 종료된 엑티비티가 인텐트를 첨부했으면 그 인텐트가 들어 있고, 없으면 null
        super.onActivityResult(requestCode, resultCode, data);
        String result=data.getStringExtra("result");    //result : "기"
        int num=0, num2=0;
        EditText editText=(EditText) findViewById(R.id.addText);    //editText연결
        String BeforeString=editText.getText().toString();    //원래 써져 있던 것을 가져온다.
        int num3=editText.getSelectionStart();
        editText.append(result);   //edittext에 "기"를 쓴다.


        String currentString=editText.getText().toString();

        num=BeforeString.length();
        num2=num + result.length();

        SpannableStringBuilder ssb=new SpannableStringBuilder(currentString);   //원래의 글자가 있으면 이를 바꾸는 것. 즉, 글자가 먼저 있어야함.
        ssb.setSpan(new StyleSpan(Typeface.BOLD),
                num, num2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        editText.append(ssb, num, num2);

        String str=" ";
        char c[] = str.toCharArray();
        currentString=editText.getText().toString();

        String sr=currentString.substring(num3, num3+num2);
        editText.append(" ");
//        editText.setText(c,0,1);

        editText.setSelection(editText.length());
    }
}
