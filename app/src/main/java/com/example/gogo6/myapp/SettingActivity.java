package com.example.gogo6.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by gogo6 on 2016-11-16.
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
                Toast.makeText(getApplicationContext(), "홈아이콘 이벤트", Toast.LENGTH_SHORT).show();
                //NavUtils.navigateUpFromSameTask(this);//메인으로이동
                finish();//상위액티비티로만 이동
            }
        });

        TextView pwChangeText = (TextView) findViewById(R.id.pw_change); //액션바기능 홈버튼(뒤로가기)
        pwChangeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "비밀번호 변경", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),PwdChangeActivity.class);
                startActivity(intent);
            }
        });

        TextView question = (TextView) findViewById(R.id.question); //액션바기능 홈버튼(뒤로가기)
        pwChangeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "도움말", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),PwdChangeActivity.class);
                startActivity(intent);
            }
        });

    }
}