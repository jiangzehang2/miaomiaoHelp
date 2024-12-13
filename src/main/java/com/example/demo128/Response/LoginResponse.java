package com.example.demo128.Response;

public class LoginResponse {
    private boolean success; // 登录是否成功
    private String token;    // 如果登录成功，返回的令牌
    private String message;  // 状态信息

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