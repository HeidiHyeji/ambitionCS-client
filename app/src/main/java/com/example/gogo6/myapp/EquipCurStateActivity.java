package com.example.gogo6.myapp;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.StringTokenizer;



/**
 * Created by 최진원 on 2016-11-15.
 *
 * 장비현황
 * 현재 이용가능 한 장비를 조회
 * 혹은
 * 선택된 날짜와 종류 한에 이용가능한 장비를 조회
 */

public class EquipCurStateActivity extends BaseActivity {

    private static final int FirstMsg=1;
    TableLayout tableLayout;
    TextView typeColum;
    CheckBox checkBox;
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    int index=0;
    Boolean nothingChecked=true;
    ArrayList<Boolean> isChecked;
    ImageButton home;
    String intentStrmenu;
    Button requestButton;
    private IClientService mBinder = null;
    Thread curThread;
    IClientServiceCallback mClientServiceCallback = new IClientServiceCallback.Stub() {
        public void readMsgFromServer(final String read_msg){;
            Log.i("clientservice", "MsgFromServer : " + read_msg);

            runOnUiThread( new Runnable(){
                @Override
                public void run(){
                    StringTokenizer stLine=new StringTokenizer(read_msg,",");
                    while(stLine.hasMoreTokens()) {
                        String line=stLine.nextToken();
                        Equipment equip=new Equipment();
                        StringTokenizer stWord = new StringTokenizer(line,"^");
                        try{
                            equip.setAdminNum(stWord.nextToken());
                            equip.setType(stWord.nextToken());
                            equip.seteName(stWord.nextToken());
                            equip.seteStatus(stWord.nextToken());
                        }catch (NullPointerException e){}
                        //setResultTable(intentStrmenu, equip.getAdminNum(),equip.getType(), equip.geteName());
                    }
                }
            } );
        }
    };

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected( ComponentName name,
                                        IBinder service )
        {
            Log.d( "clientservice", "onServiceConnected()" );

            mBinder = IClientService.Stub.asInterface( service );
            synchronized(this){
                this.notifyAll();
                Log.d( "clientservice", "onServiceConnected()????" );
            }
            Log.d( "clientservice", "onServiceConnected()aaa" );
        }

        @Override
        public void onServiceDisconnected( ComponentName name )
        {
            Log.d( "clientservice", "onServiceDisconnected()" );
        }
    };
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //액션 바
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        init();

        setResultTable(intentStrmenu, "1001", "삼성 갤럭시 s4", "스마트폰", "able");
        setResultTable(intentStrmenu, "1002", "삼성 갤럭시 s4", "스마트폰", "able");
        setResultTable(intentStrmenu, "1003", "삼성 갤럭시 노트3", "스마트폰", "able");
        setResultTable(intentStrmenu, "1004", "애플 iPhone 5s", "스마트폰", "able");
        setResultTable(intentStrmenu, "1005", "애플 iPhone 6s", "스마트폰", "able");
        setResultTable(intentStrmenu, "1006", "애플 iPhone 7", "스마트폰", "able");
        setResultTable(intentStrmenu, "1007", "애플 iPhone 7", "스마트폰", "able");
        setResultTable(intentStrmenu, "1008", "애플 iPhone 7 Plus", "스마트폰", "able");
        setResultTable(intentStrmenu, "2001", "삼성 갤럭시탭 S2", "태블릿PC", "able");
        setResultTable(intentStrmenu, "2002", "애플 iPad Air2", "태블릿PC", "able");
        setResultTable(intentStrmenu, "2003", "애플 iPad PRO", "태블릿PC", "able");
        setResultTable(intentStrmenu, "2004", "애플 iPad PRO", "태블릿PC", "able");
        setResultTable(intentStrmenu, "3001", "애플 맥북에어", "노트북", "able");
        setResultTable(intentStrmenu, "3002", "애플 맥북에어", "노트북", "able");
        setResultTable(intentStrmenu, "3003", "애플 맥북프로", "노트북", "able");
        setResultTable(intentStrmenu, "3004", "삼성 아티브북9 메탈", "노트북", "able");
        setResultTable(intentStrmenu, "3005", "삼성 아티브북9 메탈", "노트북", "able");
        setResultTable(intentStrmenu, "3006", "LG 그램", "노트북", "able");
        setResultTable(intentStrmenu, "3007", "LG 울트라", "노트북", "able");
        setResultTable(intentStrmenu, "4001", "삼성 DM500T6Z-A10S", "데스크탑", "able");
        setResultTable(intentStrmenu, "4002", "삼성 DM500S6A-A57", "데스크탑", "able");
        setResultTable(intentStrmenu, "4003", "삼성 DM500S6A-A57", "데스크탑", "able");
        setResultTable(intentStrmenu, "4004", "삼성 DM500S6A-A57", "데스크탑", "able");
        setResultTable(intentStrmenu, "5001", "LG 32MP58HQW", "모니터", "able");
        setResultTable(intentStrmenu, "5002", "삼성 S32E511C", "모니터", "able");
        setResultTable(intentStrmenu, "6001", "기가바이트 GM-AIRE M73", "마우스", "able");
        setResultTable(intentStrmenu, "6002", "로지텍 M185", "마우스", "able");
        setResultTable(intentStrmenu, "7001", "후지제록스 CP116W", "프린터", "able");
        setResultTable(intentStrmenu, "7002", "캐논 LBP251DWZ", "프린터", "able");
        setResultTable(intentStrmenu, "8001", "아이락스 KR-6170 X-Slim", "키보드", "able");
        setResultTable(intentStrmenu, "9001", "Arduino", "Arduino", "able");
        setResultTable(intentStrmenu, "10001", "RaspberryPi3", "RaspberryPi", "able");
        setResultTable(intentStrmenu, "11001", "드론", "드론", "able");


        curThread=Thread.currentThread();
        // 클라이언트 서비스 연결
        Intent serviceIntent = new Intent("com.example.gogo6.myapp.ClientService");
        bindService( serviceIntent, mConnection, BIND_AUTO_CREATE );
        AsyncTask asyncTaskA = new AsyncTask() {
            synchronized protected Object doInBackground(Object[] objects) {
                if("".equals(mBinder)){
                    try {
                        this.wait();
                        Log.v("clientservice",""+"*******");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                Log.v("clientservice",""+"???");

                return true;
            }
            protected void onPostExecute(Boolean result) {
                super.onPreExecute();
                Log.v("clientservice","aaa!!!!");
                try {
                    if(intentStrmenu.equals("EquipCurState")) {
                        mBinder.writeMsgToServer(new WriteMessage("equip").toString(), mClientServiceCallback);
                    }
                    else{
                        mBinder.writeMsgToServer(new WriteMessage("search").toString(), mClientServiceCallback);//대여신청 타입 인텐트 만들기!!!!
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        };
        asyncTaskA.execute();
    }

    @Override
    protected void onDestroy()
    {
        // 카운트 서비스 해제
        unbindService( mConnection );

        super.onDestroy();
    }

    private void init(){
        tableLayout = (TableLayout)findViewById(R.id.result_table);//테이블 레이아웃 찾기
        typeColum = (TextView)findViewById(R.id.typeColum);
        home = (ImageButton) findViewById(R.id.home); //액션바기능 홈버튼(뒤로가기)
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //NavUtils.navigateUpFromSameTask(this);//메인으로이동
                finish();//상위액티비티로만 이동
            }
        });

        Intent intent = getIntent();
        intentStrmenu=intent.getStringExtra("menu");

        isChecked=new ArrayList<Boolean>();

        requestButton = (Button) findViewById(R.id.request_button);
        if(intentStrmenu.equals("EquipCurState")){
            requestButton.setVisibility(View.GONE);
        }
        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i=0;i<index;i++){
                    if(isChecked.get(i)) {
                        nothingChecked=false;
                        break;
                    }
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(EquipCurStateActivity.this);//Alert띄울 액티비티 지정
                if(!nothingChecked) {
                    builder.setMessage("신청이 완료되었습니다.\n승인을 기다리십시오.");
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
    public void setResultTable(String intentStr, final String text1, String text2, String text3, String text4){

        //더해질 테이블 행 생성
        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(android.app.ActionBar.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        //생성한 행에 넣을 객체 생성
        checkBox=new CheckBox(this);
        textView1=new TextView(this);
        textView2=new TextView(this);
        textView3=new TextView(this);
        textView4 = new TextView(this);

        //생성한 행에 넣을 객체의 너비와 높이
        checkBox.setLayoutParams(new TableRow.LayoutParams(8, TableRow.LayoutParams.WRAP_CONTENT));
        textView1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        textView2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        textView3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
        textView4.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));

        //그래비티 지정
        textView1.setGravity(Gravity.CENTER);
        textView2.setGravity(Gravity.CENTER);
        textView3.setGravity(Gravity.CENTER);
        textView4.setGravity(Gravity.CENTER);

        //생성한 행에 넣을 객체의 내용 지정
        textView1.setText(text1);
        textView2.setText(text2);
        textView3.setText(text3);
        textView4.setText(text4);

        textView1.setBackground(getResources().getDrawable(R.drawable.button_selector2));
        textView2.setBackground(getResources().getDrawable(R.drawable.button_selector2));
        textView3.setBackground(getResources().getDrawable(R.drawable.button_selector2));
        textView4.setBackground(getResources().getDrawable(R.drawable.button_selector2));

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
        textView1.setOnClickListener(new TextListener());
        textView2.setOnClickListener(new TextListener());
        textView3.setOnClickListener(new TextListener());
        textView4.setOnClickListener(new TextListener());

        if(intentStrmenu.equals("BorrowRequest")){
            textView2.setVisibility(View.GONE);//해당 뷰를 안 보여줌(공간마저 감춤)
            typeColum.setVisibility(View.GONE);
            checkBox.setVisibility(View.VISIBLE);
        }
        else if(intentStrmenu.equals("EquipCurState")){
            textView2.setVisibility(View.VISIBLE);// 해당 뷰를 보여줌
            checkBox.setVisibility(View.GONE);
        }

        //생성한 행에 객체 삽입
        tr.addView(checkBox,0);
        tr.addView(textView1,1);
        tr.addView(textView2,2);
        tr.addView(textView3,3);
        tr.addView(textView4, 4);


        //테이블레이아웃에 행 삽입

        tableLayout.addView(tr);
        index++;
    }
    private class TextListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), EquipDetailActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("menu","EquipCurState");
            startActivity(intent);
        }
    }
}
