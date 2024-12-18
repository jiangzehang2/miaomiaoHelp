package com.myapplication.myapplication.test;

public class UserDetailsRequest {
    private String phoneNumber; // 手机号



    public UserDetailsRequest(String phoneNumber) {
        this.phoneNumber = phoneNumber;

    }

    // Getter 和 Setter
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
