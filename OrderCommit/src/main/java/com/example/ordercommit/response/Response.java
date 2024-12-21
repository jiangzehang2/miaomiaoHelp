package com.example.ordercommit.response;

public class Response {
    private int code;

    private String message;

    public void setCode(int code) {
        this.code = code;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public int getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }
    public Response success(int code, String message) {
            Response response = new Response();
            response.setCode(code);
            response.setMessage(message);
            return response;
    }
}
