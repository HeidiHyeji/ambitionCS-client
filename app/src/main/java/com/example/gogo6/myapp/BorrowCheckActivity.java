package com.example.gogo6.myapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

/**Created
 *  by 김혜지 on 2016-11-17.
 *  대여확인
 *  메인액티비티의 대여확인 메뉴 선택시 호출되는 액티비티
 */

public class BorrowCheckActivity extends BaseActivity {
    private static final int FirstMsg = 1;

    LinearLayout checkLayout;
    TextView checkText;
    ImageButton home;

    HashMap<String,TextView> checkTextAdapter=null;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_check);

        //액션 바 숨기기
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        init();

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //NavUtils.navigateUpFromSameTask(this);//메인으로이동
                finish();//상위액티비티로만 이동
            }
        });

        //서버한테 정보 받기
        addTextView("1001","2016.11.30.(목)\n삼성 갤럭시 s4\nD-2", "신청");
    }
    private void init(){
        home = (ImageButton) findViewById(R.id.home); //액션바기능 버튼!!뒤로가기
        checkTextAdapter=new HashMap<String,TextView>();
        checkLayout = (LinearLayout) findViewById(R.id.check_layout);
        checkText = new TextView(BorrowCheckActivity.this);//텍스트뷰 동적 생성
    }
    //동적으로 레이아웃에 원소를 추가하기위한 메소드
    public void addTextView(final String borrowNum, String msg, String state) {//msg=서버한테 받아야한 정보 메세지,state=대여상태

        //텍스트뷰 설정
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.bottomMargin = 5;
        checkText.setLayoutParams(params);//텍스트뷰 margin설정
        checkText.setPadding(10, 5, 10, 5);
        checkText.setLineSpacing(5, 1);

        checkText.setBackgroundColor(Color.parseColor("#464567"));
        checkText.setTextSize(15);
        checkText.setTextColor(0xffffffff);
        checkText.setText(msg);
        //대여 상태에 따라 아이콘 설정

        switch (state) {
            case "신청":
                checkText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.request, 0);
                break;
            case "승인":
                checkText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.accept, 0);
                break;
            case "완료":
                checkText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.complete, 0);
                break;
            case "반납":
                checkText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.return_img, 0);
                break;
            default:
                checkText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.error404, 0);
                break;
        }

        checkText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(getApplicationContext(), EquipDetailActivity.class);
                sendIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                sendIntent.putExtra("menu", "BorrowCheck");
                sendIntent.putExtra("borrowNum",borrowNum);
                startActivityForResult(sendIntent, FirstMsg);
            }
        });
        checkTextAdapter.put(borrowNum,checkText);
        checkLayout.addView(checkText); //레이아웃에 생성한 텍스트뷰 추가
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {//DetailActivity에서 대여 취소를 보낼 경우
            String rcvborrowNum=data.getStringExtra("borrowNum");
            TextView text=checkTextAdapter.get(rcvborrowNum);
            text.setVisibility(View.GONE);//대여 취소를 보낼 경우 텍스트 뷰가 사라짐.
        } else {
        }
    }
}