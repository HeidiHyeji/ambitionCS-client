package com.example.gogo6.myapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by gogo6 on 2016-11-12.
 */

public class LoginActivity extends BaseActivity {
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //액션 바
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //기억나지 않으세요"텍스트 클릭시 다이얼로그 생성
        TextView forgotText = (TextView) findViewById(R.id.fogotText);
        forgotText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);//Alert띄울 액티비티 지정
                //builder.setTitle("최초접속시 아이디는 학번, 비밀번호는 주민번호뒷자리입니다.회원가입, ID/PW찾기는 웹상의 경기대학교 홈페이지를 방문하여 확인하시기 바랍니다.");
                builder.setMessage("최초접속시 아이디는 학번, 비밀번호는 주민번호뒷자리입니다.\n회원가입, ID/PW찾기는 웹상의 경기대학교 홈페이지를 방문하여 확인하시기 바랍니다.");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();//다이얼로그 끝
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        ImageButton home = (ImageButton) findViewById(R.id.home); //액션바기능 버튼!!뒤로가기
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "홈아이콘 이벤트", Toast.LENGTH_SHORT).show();
                //NavUtils.navigateUpFromSameTask(this);//메인으로이동
                finish();//상위액티비티로만 이동
            }
        });

    }


}
