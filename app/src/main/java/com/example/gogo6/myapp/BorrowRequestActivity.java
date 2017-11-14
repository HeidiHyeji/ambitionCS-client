package com.example.gogo6.myapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by 김혜지 on 2016-11-14.
 * 대여신청
 * 메인화면의 대여신청메뉴 선택시 호출되는 액티비티
 * 대여신청할 날짜와 장비종류를 선택할 수 있다.
 */

public class BorrowRequestActivity extends BaseActivity {
    TextView scheduleLabel;
    private static final int FirstMsg=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        //액션 바 숨기기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Spinner spinner = (Spinner) findViewById(R.id.txt_device_type);
        final ArrayAdapter sAdapter = ArrayAdapter.createFromResource(this, R.array.device, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(sAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        ImageButton home = (ImageButton) findViewById(R.id.home); //액션바기능 홈버튼(뒤로가기)
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //NavUtils.navigateUpFromSameTask(this);//메인으로이동
                finish();//상위액티비티로만 이동
            }
        });

        LinearLayout scheduleFrame = (LinearLayout) findViewById(R.id.schedule_frame);//스케쥴프레임
        scheduleFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tmp1=new Intent();
                //액티비티 전환코드
                tmp1.setClass(BorrowRequestActivity.this,CalendarActivity.class);
                startActivityForResult(tmp1,FirstMsg);
            }
        });
        //텍스트뷰.setTypeface(Typeface.설정할폰트);
        scheduleLabel=(TextView)findViewById(R.id.scheduleLabel); //스케줄라벨:_____년 __월 __일
        scheduleLabel.setTypeface(Typeface.SANS_SERIF);

        Button checkButton = (Button) findViewById(R.id.check_button); //조회하기 버튼
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //조회하기 버튼을 누르면 서버한테 날짜와 장비종류를 전송하여 빌릴수있는 장비DB를 받는다.
                if(scheduleLabel.getText().equals("____년  __월  __일")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(BorrowRequestActivity.this);//Alert띄울 액티비티 지정
                    builder.setMessage("대여 신청일을\n선택하여 주십시오.");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();//다이얼로그 끝
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    //액티비티 전환코드==>일단을 이렇게 해둠!!인텐트까지 전송하거나 나중에 수정
                    Intent intent = new Intent(getApplicationContext(), EquipCurStateActivity.class);
                    intent.putExtra("menu","BorrowRequest");
                    startActivity(intent);
                }
            }
        });
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode == RESULT_OK) {
            scheduleLabel.setText(data.getStringExtra("selectedCalendar"));
        }
        else{}
    }


}


