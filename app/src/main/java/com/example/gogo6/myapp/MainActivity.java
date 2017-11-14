package com.example.gogo6.myapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by 김혜지 on 2016-11-12.\
 *
 * 메인
 */

public class MainActivity extends BaseActivity {
    private static final int LoginMsg=1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//객체화:메모리로딩

        //액션 바 감추기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //대여신청 메뉴 클릭시 액티비티 전환
        Button borrowRequestButton = (Button) findViewById(R.id.borrowRequestButton);
        //borrowRequestButton.setEnabled(false);
       // borrowRequestButton.setBackgroundResource(R.drawable.gray_button2);
        //borrowRequestButton.setTextColor(Color.parseColor("#000000"));
        borrowRequestButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //액티비티 전환코드
                Intent intent = new Intent(getApplicationContext(), BorrowRequestActivity.class);
                startActivity(intent);
            }
        });

        Button borrowCheckButton = (Button) findViewById(R.id.borrowCheckButton);
        //borrowCheckButton.setEnabled(false);
        //borrowCheckButton.setBackgroundResource(R.drawable.blue_button2);
        //borrowCheckButton.setTextColor(Color.parseColor("#000000"));
        borrowCheckButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BorrowCheckActivity.class);
                startActivity(intent);
            }
        });
        Button equipCurStateButton = (Button) findViewById(R.id.equipCurStateButton);
        equipCurStateButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EquipCurStateActivity.class);
                intent.putExtra("menu", "EquipCurState");
                startActivity(intent);
            }
        });

        //로그인 메뉴 클릭시 액티비티 전환
        ImageButton loginButton = (ImageButton) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                //액티비티 전환코드
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        ImageButton settingButton = (ImageButton) findViewById(R.id.settingButton);
        settingButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

    }
}
