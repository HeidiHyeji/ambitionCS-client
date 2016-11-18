package com.example.gogo6.myapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;


public class CalendarActivity extends Activity {

    GridView monthView;
    CalendarMonthAdapter monthViewAdapter;

    TextView monthText;
    TextView selectedCalendarText;

    int curYear;
    int curMonth;

    int curPosition;

    Button checkButton;
    int day;
    //EditText scheduleInput;
    //Button saveButton;

    //ListView scheduleList;
   // ScheduleListAdapter scheduleAdapter;
    //ArrayList<ScheduleListItem> outScheduleList;

    public static final int REQUEST_CODE_SCHEDULE_INPUT = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_calendar);
       // getWindow().setFeatureDrawableResource(Window.FEATURE_LEFT_ICON,android.R.drawable.ic_dialog_alert);

        //액션 바 숨기기
        //ActionBar actionBar = getSupportActionBar();
       // actionBar.hide();

        selectedCalendarText=(TextView)findViewById(R.id.selectedCalendarText);
        checkButton = (Button) findViewById(R.id.check_button);
        checkButton.setEnabled(false);

        monthView = (GridView) findViewById(R.id.monthView);
        monthViewAdapter = new CalendarMonthAdapter(this);
        monthView.setAdapter(monthViewAdapter);

        // 달력의 날짜 클릭->curItem으로 받음
        monthView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MonthItem curItem = (MonthItem) monthViewAdapter.getItem(position);
                day = curItem.getDay();

                Toast.makeText(getApplicationContext(), day + "일이 선택되었습니다.", Toast.LENGTH_LONG).show();

                monthViewAdapter.setSelectedPosition(position);
                monthViewAdapter.notifyDataSetChanged();

                selectedCalendarText.setText(monthText.getText()+" "+day+"일");
                // set schedule to the TextView 누른 날짜 위치 표시
                curPosition = position;

                if(day != 0){
                    checkButton.setEnabled(true);
                }
/*
                outScheduleList = monthViewAdapter.getSchedule(position);
                if (outScheduleList == null) {
                    outScheduleList = new ArrayList<ScheduleListItem>();
                }
          //      scheduleAdapter.scheduleList = outScheduleList;

             //   scheduleAdapter.notifyDataSetChanged();*/
            }
        });

                //월 텍스트 셋팅
        monthText = (TextView) findViewById(R.id.monthText);
        setMonthText();

                //이전 달 버튼 클릭
        Button monthPrevious = (Button) findViewById(R.id.monthPrevious);
        monthPrevious.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setPreviousMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });

                //다음 달 버튼 클릭
        Button monthNext = (Button) findViewById(R.id.monthNext);
        monthNext.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                monthViewAdapter.setNextMonth();
                monthViewAdapter.notifyDataSetChanged();

                setMonthText();
            }
        });


        curPosition = -1;

     //   scheduleList = (ListView)findViewById(R.id.scheduleList);
   //     scheduleAdapter = new ScheduleListAdapter(this);
     //   scheduleList.setAdapter(scheduleAdapter);


        checkButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent tmp1 = new Intent();
                    tmp1.putExtra("selectedCalendar", selectedCalendarText.getText());

                    Toast.makeText(getApplicationContext(), "확인되었습니다.", Toast.LENGTH_LONG).show();

                    setResult(RESULT_OK, tmp1);
                    finish();

            }
        });

        //텍스트뷰.setTypeface(Typeface.설정할폰트);
        Typeface typeface=Typeface.createFromAsset(this.getAssets(), "fonts/조선일보명조.ttf");

        TextView requestScheduleLabel=(TextView)findViewById(R.id.request_schedule_label); //스케줄라벨:"대여신청일"
        requestScheduleLabel.setTypeface(typeface);
        checkButton.setTypeface(typeface);

    }


    private void setMonthText() {
        curYear = monthViewAdapter.getCurYear();
        curMonth = monthViewAdapter.getCurMonth();

        monthText.setText(curYear + "년 " + (curMonth+1) + "월");
    }
/*
    private void addOptionMenuItems(Menu menu) {
        int id = Menu.FIRST;
        menu.clear();

        menu.add(id, id, Menu.NONE, "일정 추가");
    }
*/
    /*
    private void showScheduleInput() {
    //    Intent intent = new Intent(this, ScheduleInputActivity.class);
    //    startActivityForResult(intent, REQUEST_CODE_SCHEDULE_INPUT);
    }
*/
/*
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        if (intent != null) {
            if (requestCode == REQUEST_CODE_SCHEDULE_INPUT) {
                String time = intent.getStringExtra("time");
                String message = intent.getStringExtra("message");

                if (message != null) {
                    Toast toast = Toast.makeText(getBaseContext(), "result code : " + resultCode + ", time : " + time + ", message : " + message, Toast.LENGTH_LONG);
                    toast.show();

                    ScheduleListItem aItem = new ScheduleListItem(time, message);


                    if (outScheduleList == null) {
                        outScheduleList = new ArrayList<ScheduleListItem>();
                    }
                    outScheduleList.add(aItem);

                    monthViewAdapter.putSchedule(curPosition, outScheduleList);

             //       scheduleAdapter.scheduleList = outScheduleList;
            //        scheduleAdapter.notifyDataSetChanged();
                }
            }
        }

    }
*/
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        addOptionMenuItems(menu);

        return true;
    }
*/
    /*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Menu.FIRST:
                showScheduleInput();

                return true;
            default:
                break;
        }

        return false;
    }
    */
}

