package com.example.gogo6.myapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //액션 바 감추기
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        //로그인 메뉴 클릭시 액티비티 전환
        Button loginMenu=(Button)findViewById(R.id.loginMenu);
        loginMenu.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"로그인화면", Toast.LENGTH_LONG).show();

                //액티비티 전환코드
                 Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                 startActivity(intent);
            }
        });



    }
}
