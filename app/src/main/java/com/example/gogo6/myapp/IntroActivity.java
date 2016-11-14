package com.example.gogo6.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by gogo6 on 2016-11-10.
 */

public class IntroActivity extends AppCompatActivity{
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //액션 바 감추기
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        //2초후에 인트로 액티비티 제거
        Handler handler=new Handler();
        handler.postDelayed(new Runnable(){
            public void run(){
                Intent intent =new Intent(IntroActivity.this,MainActivity.class);
                startActivity(intent);

                finish();
            }
        },2000);
    }
}
