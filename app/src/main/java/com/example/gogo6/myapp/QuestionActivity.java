package com.example.gogo6.myapp;

/**
 * Created by 최진원 on 2016-11-18.
 *
 * 도움말
 */

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Display;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionActivity extends BaseActivity{
    private ExpandableListView listView;
    ArrayList<QuestionMain> dataList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Display newDisplay = getWindowManager().getDefaultDisplay();
        int width = newDisplay.getWidth();

        //액션 바
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        ImageButton home = (ImageButton) findViewById(R.id.home); //액션바기능 버튼!!뒤로가기
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "홈아이콘 이벤트", Toast.LENGTH_SHORT).show();
                //NavUtils.navigateUpFromSameTask(this);//메인으로이동
                finish();//상위액티비티로만 이동
            }
        });

        dataList = new ArrayList<QuestionMain>();
        listView = (ExpandableListView) findViewById(R.id.helpList);

        setQuestionListView();

        QuestionAdapter adapter = new QuestionAdapter(getApplicationContext(), R.layout.question_parent_listview, R.layout.question_child_listview, dataList);
        listView.setIndicatorBounds(width - 50, width); //이 코드를 지우면 화살표 위치가 바뀐다.
        listView.setAdapter(adapter);
    }
    public void setQuestionListView(){
        QuestionMain temp = new QuestionMain("대여 과정은 어떻게 되나요?");
        temp.child.add("    대여 과정은\n대여 신청 -> 대여 승인 -> 대여 완료(물리적 장비 대여) -> 장비 반납 순으로 이뤄집니다.");
        dataList.add(temp);
        temp = new QuestionMain("현장 대여 과정은 어떻게 되나요?");
        temp.child.add("    학과 사무실 방문 -> 신청서 작성 -> 대여 완료(물리적 장비 대여) -> 장비 반납 순으로 이뤄집니다.");
        dataList.add(temp);
        temp = new QuestionMain("대여 기간은 얼마나 되나요?");
        temp.child.add("    대여 완료일로 부터 3주 입니다.");
        dataList.add(temp);
        temp = new QuestionMain("대여 신청 후 승인까지의 기간은 며칠인가요?");
        temp.child.add("    3일 입니다.\n3일 이내로 승인이 완료될 수 있습니다.");
        dataList.add(temp);
        temp = new QuestionMain("반납을 못 하면 어떻게 되나요?");
        temp.child.add("    반납을 하지 못 한 경우 연체가 됩니다.\n그리고 일정 기간이상 연체가 되면 장비의 대여가 불가능해집니다.");
        dataList.add(temp);
        temp = new QuestionMain("연체일이 얼마나 되야 장비 대여가 불가능한가요?");
        temp.child.add("    반납일로부터 10일이 지나면 장비의 대여가 불가능해집니다.");
        dataList.add(temp);
        temp = new QuestionMain("대여 목록을 보고 싶은데 어디서 봐야 하나요?");
        temp.child.add("    메인 화면의 대여 확인 창에서 볼 수 있습니다.");
        dataList.add(temp);
        temp = new QuestionMain("대여 취소는 어떻게 하나요?");
        temp.child.add("    메인 화면의 대여 확인 창에서 볼 수 있는 상세정보를 통해 취소가 가능합니다.");
        dataList.add(temp);

    }
}
