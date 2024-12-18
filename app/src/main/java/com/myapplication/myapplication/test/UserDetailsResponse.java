package com.myapplication.myapplication.test;

public class UserDetailsResponse {


    public boolean success;  // 注册是否成功
    public String name;     // 如果成功，返回的令牌
    public String password;   // 注册状态信息

    // Getter 和 Setter
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



