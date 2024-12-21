package com.example.ordercommit.response;



public class OrderResponse<Order> {
    private Integer code;
    private String message;
    private double price;

    private String address;

    public void setCode(Integer code) {
        this.code = code;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public void setPrice(double price){
        this.price=price;
    }

    public void setAddress(String address){
        this.address=address;
    }

    public Integer getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

    public double getPrice(){
        return price;
    }

    public String getAddress(){
        return address;
    }

}


