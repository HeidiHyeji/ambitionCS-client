package com.example.gogo6.myapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by 김혜지 on 2016-11-16.
 *
 * 비밀번호 변경
 */

public class PwdChangeActivity extends BaseActivity {
    EditText newPwd;
    EditText newPwdCheck;
    EditText curPwd;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwchange);//객체화:메모리로딩

        //액션 바 감추기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        newPwd = (EditText)findViewById(R.id.new_pwd);
        newPwdCheck = (EditText)findViewById(R.id.new_pwd_check);
        curPwd=(EditText)findViewById(R.id.cur_pwd);

        ImageButton home = (ImageButton) findViewById(R.id.home); //액션바기능 홈버튼(뒤로가기)
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //NavUtils.navigateUpFromSameTask(this);//메인으로이동
                finish();//상위액티비티로만 이동
            }
        });

        Button OkButton = (Button) findViewById(R.id.okButton); //액션바기능 홈버튼(뒤로가기)
        OkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("".equals(curPwd.getText().toString())){
                    checkNullPwd();
                }
                else if ("".equals(newPwd.getText().toString()) || "".equals(newPwdCheck.getText().toString())) {
                    checkNullPwd();
                }
                else if (!newPwd.getText().toString().equals(newPwdCheck.getText().toString())) {
                    checkPwd();
                }
                else {//서버한테 전송해서 현재 비밀번호 확인하고 맞으면 새비밀번호 변경 틀리면 틀림 리턴
                    finish();
                    //Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                    //startActivity(intent);
                }
            }
        });

    }
    private void checkPwd() {

            AlertDialog.Builder builder = new AlertDialog.Builder(PwdChangeActivity.this);//Alert띄울 액티비티 지정
            builder.setMessage("새로운 비밀번호가 맞지 않습니다.");
            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();//다이얼로그 끝
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            newPwd.setText(null);
            newPwdCheck.setText(null);

    }
    private void checkNullPwd() {

        AlertDialog.Builder builder = new AlertDialog.Builder(PwdChangeActivity.this);//Alert띄울 액티비티 지정
        builder.setMessage("비밀번호를 입력하시오.");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();//다이얼로그 끝
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
        newPwd.setText(null);
        newPwdCheck.setText(null);

    }
}