package com.example.gogo6.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by 김혜지 on 2016-11-16.
 *
 * 설정 메뉴
 */

public class SettingActivity extends BaseActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);//객체화:메모리로딩

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

        TextView pwChangeText = (TextView) findViewById(R.id.pw_change); //액션바기능 홈버튼(뒤로가기)
        pwChangeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),PwdChangeActivity.class);
                startActivity(intent);
            }
        });

        TextView question = (TextView) findViewById(R.id.question); //액션바기능 홈버튼(뒤로가기)
        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),QuestionActivity.class);
                startActivity(intent);
            }
        });

        TextView customerCenterText = (TextView) findViewById(R.id.customerCenterText); //액션바기능 홈버튼(뒤로가기)
        customerCenterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),CustomerCenterActivity.class);
                startActivity(intent);
            }
        });

    }
}