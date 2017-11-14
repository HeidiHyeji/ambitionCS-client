package com.example.gogo6.myapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedWriter;

/**
 * Created by 김혜지 on 2016-11-12.\
 *
 * 로그인
 */

public class LoginActivity extends BaseActivity {
    private String studentNum;
    private String pwd;
    private BufferedWriter networkWriter=null;
    static Thread thread =null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //액션 바
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        final EditText studentNumEditText = (EditText)findViewById(R.id.studentNumEditText);
        final EditText pwdEditText = (EditText)findViewById(R.id.pwdEditText);
        Button loginButton = (Button)findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentNum=studentNumEditText.getText().toString();
                pwd=pwdEditText.getText().toString();
                //로그인 버튼 누르면 서버에 로그인 정보 전송
                finish();
            }
        });
        ImageButton home = (ImageButton) findViewById(R.id.home); //액션바기능 버튼!!뒤로가기
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();//상위액티비티로만 이동
            }
        });

        //기억나지 않으세요"텍스트 클릭시 다이얼로그 생성
        TextView forgotText = (TextView) findViewById(R.id.fogotText);
        forgotText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);//Alert띄울 액티비티 지정
                builder.setMessage("\n회원가입, ID/PW찾기는 과사에 방문하여 문의해주시기 바랍니다.");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//다이얼로그 끝
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });




    }


}
