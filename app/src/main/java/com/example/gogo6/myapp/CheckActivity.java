package com.example.gogo6.myapp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by gogo6 on 2016-11-17.
 */

public class CheckActivity extends BaseActivity {

    LinearLayout checkLayout;
    TextView checkText;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_check);

        ImageButton home = (ImageButton) findViewById(R.id.home); //액션바기능 버튼!!뒤로가기
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "홈아이콘 이벤트", Toast.LENGTH_SHORT).show();
                //NavUtils.navigateUpFromSameTask(this);//메인으로이동
                finish();//상위액티비티로만 이동
            }
        });

        checkLayout = (LinearLayout) findViewById(R.id.check_layout);
        addTextView("2016.11.17.(목)\\nMacBook\\nD-0","신청");
    }

    //동적으로 레이아웃에 원소를 추가하기위한 메소드
    public void addTextView(String msg,String state) {//msg=서버한테 받아야한 정보 메세지,state=대여상태

        //텍스트뷰 동적 생성
        checkText = new TextView(CheckActivity.this);

        //텍스트뷰 설정
        checkText.setBackgroundColor(Color.parseColor("#464567"));
        checkText.setTextSize(15);
        checkText.setTextColor(0xffffff);
        checkText.setText(msg);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 5;
        checkText.setLayoutParams(params);//텍스트뷰 margin설정
        checkText.setPadding(10, 5, 10, 5);
        checkText.setLineSpacing(5, 0);

        //대여 상태에 따라 아이콘 설정
        switch (state){
            case "신청":
                checkText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.request, 0);
                break;
            case "승인":
                checkText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.accept, 0);
                break;
            case "완료":
                checkText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.complete, 0);
                break;
            case "반납":
                checkText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.return_img, 0);
                break;
            default:
                checkText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.error404, 0);
                break;
        }

        checkLayout.addView(checkText); //레이아웃에 생성한 텍스트뷰 추가
    }
}