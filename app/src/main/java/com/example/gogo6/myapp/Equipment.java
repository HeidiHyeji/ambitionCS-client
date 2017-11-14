package com.example.gogo6.myapp;

/**
 * Created by 김혜지 on 2016-11-27.
 *
 * 서버로 부터 받은 메세지를 토크나이징하여
 * 장비에 관한 데이터를 정리
 */

public class Equipment {
    private String adminNum;
    private String type;
    private String eName;
    private String eStatus;
    private String image;
    private String details;

    public String getAdminNum() {
        return adminNum;
    }

    public void setAdminNum(String adminNum) {
        this.adminNum = adminNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public String geteStatus() {
        return eStatus;
    }

    public void seteStatus(String eStatus) {
        this.eStatus = eStatus;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
