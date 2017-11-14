package com.example.gogo6.myapp;

import java.util.ArrayList;

/**
 * Created by 최진원 on 2016-11-18.
 *
 * 도움말 객체
 */

public class QuestionMain {
    public ArrayList<String> child;
    public String groupName;
    QuestionMain(String name){
        groupName = name;
        child = new ArrayList<String>();
    }
}
