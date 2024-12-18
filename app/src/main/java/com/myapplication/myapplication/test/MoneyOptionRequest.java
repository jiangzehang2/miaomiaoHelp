package com.myapplication.myapplication.test;

public class MoneyOptionRequest {
    private String option; // 选择
    private String phoneNumber; // 手机号


    public MoneyOptionRequest(String phoneNumber,String option) {
        this.phoneNumber = phoneNumber;
        this.option = option;

    }


    // Getter 和 Setter
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }


}
