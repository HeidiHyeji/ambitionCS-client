package com.example.gogo6.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 김혜지 on 2016-11-10.
 *
 * 인트로
 * 서버 ip 설정
 */

public class IntroActivity extends AppCompatActivity{
    EditText ipEditText;
    Handler handler;
    ClientThread thread;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        //액션 바 감추기
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();

        ipEditText = (EditText)findViewById(R.id.ipEditText);
        Button connectButton=(Button)findViewById(R.id.connectButton);

        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addr = ipEditText.getText().toString().trim();
                if("".equals(ipEditText.getText().toString())){
                    Toast.makeText(getApplicationContext(), "서버 ip를 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else if(thread == null){
                    try {
                        Intent serviceIntent = new Intent("com.example.gogo6.myapp.ClientService");
                        serviceIntent.putExtra("ip",addr);

                        startService( serviceIntent );
                        Intent intent =new Intent(IntroActivity.this,MainActivity.class);
                        startActivity(intent);
                        //finish(); //인트로 액티비티 종료
                    }catch (RuntimeException e){
                        Toast.makeText(getApplicationContext(), "서버에 연결할 수 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
