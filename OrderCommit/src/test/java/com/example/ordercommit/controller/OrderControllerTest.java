package com.example.ordercommit.controller;

import com.example.ordercommit.Entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderControllerTest {


    OrderController orderController = new OrderController();
    @Test
    void addOrder() {
        MultipartFile file = null;
        orderController.addOrder("2","startlocation","endlocation","￥5","address",file);

    }

    @Test
    void getAllOrders() {
        List<Order> list;

        list=orderController.getAllOrders("pending");
        System.out.println(list);

    }

    @Test
    void getMyOrders() {
    List<Order> order=orderController.getMyOrders(2);
    System.out.println(order);
    }

    @Test
    void download() {
    }

    public static void main(String args[]){
        OrderControllerTest orderControllerTest = new OrderControllerTest();
        orderControllerTest.getMyOrders();
    }

}