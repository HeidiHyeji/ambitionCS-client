package com.example.gogo6.myapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 김혜지 on 2016-11-16.
 * 선택된 장비의 세부사항
 */

public class EquipDetailActivity extends Activity {
    ImageView image;
    TextView numText;
    TextView typeText;
    TextView nameText;
    TextView detailText;

    Button cancelButton;
    Button checkButton;

    Bitmap bmImg=null;
    Intent rcvIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail);

        TextView detailLabel=(TextView)findViewById(R.id.detail_label);
        cancelButton=(Button) findViewById(R.id.cancel_button);
        checkButton=(Button) findViewById(R.id.check_button);
        image=(ImageView)findViewById(R.id.image);
        numText=(TextView)findViewById(R.id.numText);
        typeText=(TextView)findViewById(R.id.typeText);
        nameText=(TextView)findViewById(R.id.nameText);
        detailText=(TextView)findViewById(R.id.detailText);

        //폰트 설정
        Typeface typeface=Typeface.createFromAsset(this.getAssets(), "fonts/조선일보명조.ttf");
        detailLabel.setTypeface(typeface);
        cancelButton.setTypeface(typeface);
        checkButton.setTypeface(typeface);

        rcvIntent = getIntent();
        if(rcvIntent.getStringExtra("menu").equals("EquipCurState")){
            cancelButton.setVisibility(View.GONE);//해당 뷰를 안 보여줌(공간마저 감춤)
        }
        else if(rcvIntent.getStringExtra("menu").equals("BorrowCheck")){
            cancelButton.setVisibility(View.VISIBLE);// 해당 뷰를 보여줌
        }

        setDetatil(new Object());//params:서버한테 받은 세부사항 정보

        checkButton.setOnClickListener(new View.OnClickListener() {//확인버튼
            @Override
            public void onClick(View view) {
                finish();//상위액티비티로만 이동
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {//대여취소버튼
            @Override
            public void onClick(View view) {//서버한테 대여취소에관한 정보 전달
                Intent sendIntent=new Intent();
                String borrowNum=rcvIntent.getStringExtra("borrowNum");
                sendIntent.putExtra("borrowNum",borrowNum);
                setResult(RESULT_OK,sendIntent);
                finish();//상위액티비티로만 이동
            }
        });

    }
    public void setDetatil(Object obj){//서버한테 받은 값으로 상세내용 설정
       // image.setImageBitmap(getDrawable().getSo);
        numText.setText(breakText(numText.getPaint(),"1001",320));//numText에 자동줄바꿈으로 텍스트 설정
        typeText.setText(breakText(typeText.getPaint(),"스마트폰",320));
        nameText.setText(breakText(nameText.getPaint(),"삼성 갤럭시 s4",320));
        detailText.setText(breakText(detailText.getPaint(),"Android/32G",320));
    }
    /**
     * 줄바꿈
     * @param textPaint        TextView의 Paint 객체
     * @param strText        문자열
     * @param breakWidth    줄바꿈 하고 싶은 width값 지정
     * @return
     */
    public String breakText(Paint textPaint, String strText, int breakWidth) {
        StringBuilder sb = new StringBuilder();
        int endValue = 0;
        do{
            endValue = textPaint.breakText(strText, true, breakWidth, null);
            if(endValue > 0) {
                sb.append(strText.substring(0, endValue)).append("\n");
                strText = strText.substring(endValue);
            }
        }while(endValue > 0);
        return sb.toString().substring(0, sb.length()-1);  // 마지막 "\n"를 제거
    }



}
