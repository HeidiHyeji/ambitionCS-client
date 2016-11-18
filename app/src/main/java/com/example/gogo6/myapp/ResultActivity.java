package com.example.gogo6.myapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by 진원 on 2016-11-15.
 */

public class ResultActivity extends BaseActivity {
    private static final int FirstMsg=1;
    TableLayout tableLayout;
    CheckBox checkBox;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    int index=0;
    Boolean nothingChecked=true;
    ArrayList<Boolean> isChecked;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //액션 바
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ImageButton home = (ImageButton) findViewById(R.id.home); //액션바기능 홈버튼(뒤로가기)
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "홈아이콘 이벤트", Toast.LENGTH_SHORT).show();
                //NavUtils.navigateUpFromSameTask(this);//메인으로이동
                finish();//상위액티비티로만 이동
            }
        });

        isChecked=new ArrayList<Boolean>();


            for (int i = 0; i < 30; i++)
                setResultTable("뉴읭", "뉴읭뉴읭", "뉴읭");




        Button requestButton = (Button) findViewById(R.id.request_button);
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=0;i<index;i++){
                if(isChecked.get(i)) {
                    nothingChecked=false;
                    break;
                }
            }
                AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);//Alert띄울 액티비티 지정
                if(!nothingChecked) {
                    builder.setMessage("신청이 완료되었습니다.\n빠른 시일내에 과사에 방문하여 실습장비를 찾아가시길 바랍니다.");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);
                            dialog.dismiss();//다이얼로그 끝
                        }
                    });
                }
                else{
                    builder.setMessage("신청할 장비를 선택하십시오.");
                    builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();//다이얼로그 끝
                        }
                    });
                }
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
    public void setResultTable(final String text1, String text2, String text3){
        tableLayout = (TableLayout)findViewById(R.id.result_table);//테이블 레이아웃 찾기

        //더해질 테이블 행 생성
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(android.app.ActionBar.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        //생성한 행에 넣을 객체 생성
        checkBox=new CheckBox(this);
        textView1=new TextView(this);
        textView2=new TextView(this);
        textView3=new TextView(this);

        //생성한 행에 넣을 객체의 너비와 높이
        checkBox.setLayoutParams(new TableRow.LayoutParams(8, TableRow.LayoutParams.WRAP_CONTENT));
        textView1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        //그래비티 지정
        textView1.setGravity(Gravity.CENTER);
        textView2.setGravity(Gravity.CENTER);
        textView3.setGravity(Gravity.CENTER);

        //생성한 행에 넣을 객체의 내용 지정
        textView1.setText(text1);
        textView2.setText(text2);
        textView3.setText(text3);

        textView1.setBackground(getResources().getDrawable(R.drawable.button_selector2));
        textView2.setBackground(getResources().getDrawable(R.drawable.button_selector2));
        textView3.setBackground(getResources().getDrawable(R.drawable.button_selector2));

        //체크되었는지 확인하고 저장하기
        checkBox.setId(index);
        isChecked.add(index,false);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                @Override
                                                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                                                    if (b) {
                                                        isChecked.set(checkBox.getId(), true);
                                                    } else {
                                                        isChecked.set(checkBox.getId(), false);
                                                    }
                                                }
                                            });
        //생성한 행에 넣을 객체 중 텍스트뷰에만 리스너 달기
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivityForResult(intent,FirstMsg);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        //생성한 행에 객체 삽입
        tr.addView(checkBox,0);
        tr.addView(textView1,1);
        tr.addView(textView2,2);
        tr.addView(textView3,3);

        //테이블레이아웃에 행 삽입

        tableLayout.addView(tr);
        index++;
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode == RESULT_OK) {

        }
        else{}
    }
}
