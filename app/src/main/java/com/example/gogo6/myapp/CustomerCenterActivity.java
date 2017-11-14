package com.example.gogo6.myapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by 김혜지 on 2016-11-21.
 * 고객센터
 * 메인화면-설정-고객센터 선택시
 * 개발에 관한 정보와 관련 연락처, 로고 등을 알수 있음.
 */

public class CustomerCenterActivity extends BaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customercenter);//객체화:메모리로딩

        //액션 바 감추기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ImageButton home = (ImageButton) findViewById(R.id.home); //액션바기능 홈버튼(뒤로가기)
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //NavUtils.navigateUpFromSameTask(this);//메인으로이동
                finish();//상위액티비티로만 이동
            }
        });
    }
}
