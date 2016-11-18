package com.example.gogo6.myapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//객체화:메모리로딩

        //액션 바 감추기
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        //로그인 메뉴 클릭시 액티비티 전환
        ImageButton loginMenu=(ImageButton)findViewById(R.id.loginMenu);
        loginMenu.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"로그인화면", Toast.LENGTH_LONG).show();

                //액티비티 전환코드
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        //대여신청 메뉴 클릭시 액티비티 전환
        Button requestMenu=(Button)findViewById(R.id.requestMenu);
        requestMenu.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"대여신청화면", Toast.LENGTH_LONG).show();

                //액티비티 전환코드
                Intent intent=new Intent(getApplicationContext(),RequestActivity.class);
                startActivity(intent);
            }
        });
        Button device_result=(Button)findViewById(R.id.device_result);
        device_result.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"장비 현황", Toast.LENGTH_LONG).show();


                Intent intent=new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(intent);
            }
            });

        ImageButton settingMenu=(ImageButton)findViewById(R.id.settingMenu);
        settingMenu.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"설정", Toast.LENGTH_LONG).show();

                Intent intent=new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(intent);
            }
        });
        Button checkMenu=(Button)findViewById(R.id.check_menu);
        checkMenu.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"대여 확인", Toast.LENGTH_LONG).show();

                Intent intent=new Intent(getApplicationContext(),CheckActivity.class);
                startActivity(intent);
            }
        });




        }
}
