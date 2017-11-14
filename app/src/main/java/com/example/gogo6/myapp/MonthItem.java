package com.example.gogo6.myapp;

/**
 * Created by 김혜지 on 2016-11-12.
 *
 * 캘린더의 날짜를 저장 객체
 */
public class MonthItem {

    private int dayValue;

    public MonthItem() {

    }

    public MonthItem(int day) {
        dayValue = day;
    }

    public int getDay() {
        return dayValue;
    }

    public void setDay(int day) {
        this.dayValue = day;
    }



}

