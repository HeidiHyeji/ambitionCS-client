package com.example.gogo6.myapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by gogo6 on 2016-11-16.
 */

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_detail);

        //텍스트뷰.setTypeface(Typeface.설정할폰트);
        Typeface typeface=Typeface.createFromAsset(this.getAssets(), "fonts/조선일보명조.ttf");

        TextView detailLabel=(TextView)findViewById(R.id.detail_label);
        detailLabel.setTypeface(typeface);
       Button cancelButton=(Button) findViewById(R.id.cancel_button);
        cancelButton.setTypeface(typeface);
        Button checkButton=(Button) findViewById(R.id.check_button);
        checkButton.setTypeface(typeface);

        checkButton.setOnClickListener(new View.OnClickListener() {//확인버튼
            @Override
            public void onClick(View view) {
                Intent tmp1 = new Intent();
                //tmp1.putExtra("selectedCalendar", selectedCalendarText.getText());
                //Toast.makeText(getApplicationContext(), "확인되었습니다.", Toast.LENGTH_LONG).show();

                setResult(RESULT_OK, tmp1);
                finish();//상위액티비티로만 이동
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {//대여취소버튼
            @Override
            public void onClick(View view) {//서버한테 대여취소에관한 정보 전달
                finish();//상위액티비티로만 이동
            }
        });

    }
}
