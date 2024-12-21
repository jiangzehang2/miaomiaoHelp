package com.example.ordercommit.controller;


import com.example.ordercommit.Entity.Order;
import com.example.ordercommit.Entity.User;
import com.example.ordercommit.repository.OrderRepository;
import com.example.ordercommit.repository.UserRepository;
import com.example.ordercommit.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Tag(name ="商家管理接口")
@Controller
@RequestMapping(path = "/Order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;


    String path="D:\\restore";
    @Operation(description = "发布一个新的订单")
    @PostMapping(path="/add",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody Response addOrder(@RequestParam(value = "UserID") String userId,
                                           @RequestParam(value="jieDanDian") String start_location,
                                           @RequestParam(value="quCanDian") String end_location,
                                           @RequestParam(value="foodPrice") String price,
                                           @RequestParam(value="address") String address,
                                           @RequestPart("image") MultipartFile file
                                         )
    {   String fileName=file.getOriginalFilename();
        String filePath=path+"/"+fileName;
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Order order = new Order();

        double Nprice=0.0;

        if(price.equals("￥5")) Nprice=5.0;
        if(price.equals("￥10")) Nprice=10.0;
        if(price.equals("￥15")) Nprice=15.0;
        if(price.equals("￥20")) Nprice=20.0;
        if(price.equals("￥25")) Nprice=25.0;
        if(price.equals("￥30")) Nprice=30.0;
        System.out.println(price);
        System.out.println(Nprice);


        order.setStartLocation(start_location);
        order.setEndLocation(end_location);
        order.setPrice(BigDecimal.valueOf(Nprice));
        order.setAddress(address);
        order.setStatus("unaccepted");
        order.setItemImageUrl(filePath);



        User user;
        int id=Integer.parseInt(userId);
        user= userRepository.findById(id).get();

        order.setUser(user);
        orderRepository.save(order);

        Response response = new Response();


        return response.success(200,"success");
    }

    @GetMapping(path="/all")
    public @ResponseBody List<Order> getAllOrders(@RequestParam(value="status") String status){
        System.out.println(status);
        System.out.println(orderRepository.findByStatus(status));
        return orderRepository.findByStatus(status);
    }

    @GetMapping(path="/findbyid")
    public @ResponseBody Order findOrderById(@RequestParam(value="id") int id){
        Order order=orderRepository.findById(id).get();


        return order;
    }

    @PostMapping(path="/myorder")
    public @ResponseBody List<Order> getMyOrders(int userid){
        User user=userRepository.findById(userid).get();

        System.out.println(user);
        System.out.println(orderRepository.findByUser(user));
        return orderRepository.findByUser(user);
    }



    @Operation(description = "测试别的接口前先用此接口生成部分数据，此接口会在数据库中插入一个User，account，password和Username都是‘11111111’")
    @PostMapping(path="test")
    public @ResponseBody String test(){

        User user=new User();
        user.setAccount("11111111");
        user.setPassword("11111111");
        user.setUsername("11111111");
        userRepository.save(user);
        return "success test";
    }



    @PostMapping(path="upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public @ResponseBody String upload(@RequestPart("file") MultipartFile file)
    {

        return "success upload";
    }

    @Operation(description = "更改订单目的地")
    @PostMapping(path = "/change")
    public @ResponseBody String change(@RequestParam(value = "订单id") int orderid,
                                       @RequestParam(value = "目的地址") String address
    )
    {
        Order order=orderRepository.findById(orderid).get();
        order.setAddress(address);
        orderRepository.save(order);
        return "success";
    }

    @Operation(description = "删除订单")
    @PostMapping(path="delete")
    public @ResponseBody String delete(int orderid){
        Order order=orderRepository.findById(orderid).get();
        orderRepository.delete(order);
        return "success";
    }

    @Operation(description = "传入订单id，传回对应的图片")
    @GetMapping(path="/download")
    public @ResponseBody byte[] download(@RequestParam("id") int id) throws IOException{
        String address=orderRepository.findById(id).get().getItemImageUrl();
        FileInputStream fis=new FileInputStream(address);
        return fis.readAllBytes();
    }

}
