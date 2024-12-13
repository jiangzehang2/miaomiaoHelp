package com.example.demo128.Response;


public class RegisterResponse {
    private boolean success; // 注册是否成功
    private String message;  // 状态信息

    // 无参构造方法
    public RegisterResponse() {}

    // 带参构造方法
    public RegisterResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    // Getter 和 Setter
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // 重写 toString 方法
    @Override
    public String toString() {
        return "RegisterResponse{" +
                "success=" + success +
                ", message='" + message + '\'' +
                '}';
    }
}
