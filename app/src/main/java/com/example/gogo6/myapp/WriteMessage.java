package com.example.gogo6.myapp;

import java.io.Serializable;

/**
 * Created by 김혜지 on 2016-11-27.
 *
 * 서버에게 쓰는 메세지 객체
 */

public class WriteMessage implements Serializable{
    public String str[]=null;

    public WriteMessage(String str1){
        str=new String[1];
        str[0]=str1;
    }
    public WriteMessage(String str1, String str2){
        str=new String[2];
        str[0]=str1;
        str[1]=str2;
    }
    public WriteMessage(String str1, String str2, String str3){
        str=new String[3];
        str[0]=str1;
        str[1]=str2;
        str[2]=str3;
    }
    public WriteMessage(String str1, String str2, String str3, String str4){
        str[0]=str1;
        str[1]=str2;
        str[2]=str3;
        str[3]=str4;
    }
    public String toString(){
        String result=str[0];
        for(int i=1;i<str.length;i++){
            result.concat("^"+str[i]);
        }
        return result+"^";
    }
}
