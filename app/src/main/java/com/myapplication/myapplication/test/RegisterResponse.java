package com.myapplication.myapplication.test;

public class RegisterResponse {
    public boolean success;  // 注册是否成功
    public String token;     // 如果成功，返回的令牌
    public String message;   // 注册状态信息

    // Getter 和 Setter
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


